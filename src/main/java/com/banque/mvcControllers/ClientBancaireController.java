package com.banque.mvcControllers;

import com.banque.model.ClientBancaire;
import com.banque.model.Personne;
import com.banque.model.PersonneMorale;
import com.banque.model.PersonnePhysique;
import com.banque.service.ClientBancaireService;
import com.banque.service.PersonneMoraleService;
import com.banque.service.PersonnePhysiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur MVC pour gérer les clients bancaires.
 * Fournit les fonctionnalités d'affichage, création, modification, suppression et autocomplétion des clients bancaires.
 */
@Controller
public class ClientBancaireController {

    private final ClientBancaireService clientBancaireService;
    private final PersonneMoraleService personneMoraleService;
    private final PersonnePhysiqueService personnePhysiqueService;

    /**
     * Constructeur du contrôleur ClientBancaireController.
     * Permet l'injection des services nécessaires pour gérer les clients bancaires et leurs personnes associées.
     *
     * @param clientBancaireService service pour les opérations sur les clients bancaires
     * @param personnePhysiqueService service pour gérer les personnes physiques
     * @param personneMoraleService service pour gérer les personnes morales
     */
    @Autowired
    public ClientBancaireController(ClientBancaireService clientBancaireService,
                                    PersonnePhysiqueService personnePhysiqueService,
                                    PersonneMoraleService personneMoraleService) {
        this.clientBancaireService = clientBancaireService;
        this.personnePhysiqueService = personnePhysiqueService;
        this.personneMoraleService = personneMoraleService;
    }

    /**
     * Affiche la liste de tous les clients bancaires.
     * Mapping GET sur /clientBancaire. La vue "clientBancaire" reçoit la liste des clients.
     *
     * @param model modèle pour passer les données à la vue
     * @return template "lesClientsBancaires/clientBancaire"
     */
    @GetMapping("/clientBancaire")
    public String listeClientsBancaires(Model model) {
        model.addAttribute("listeClientsBancaires", clientBancaireService.getAllClientsBancaires());
        return "lesClientsBancaires/clientBancaire";
    }

    /**
     * Affiche le formulaire pour créer ou éditer un client bancaire.
     * Mapping GET sur /clientBancaire/edit ou /clientBancaire/edit/{id}.
     * Si l'id est présent, récupère le client existant, sinon crée un nouveau client vide.
     *
     * @param id    identifiant du client (optionnel)
     * @param model modèle pour passer le client et la liste des personnes à la vue
     * @return template "lesClientsBancaires/edit_clientBancaire"
     */
    @GetMapping(value = {"/clientBancaire/edit", "/clientBancaire/edit/{id}"})
    public String editClientBancaireForm(@PathVariable(required = false) Long id, Model model) {
        ClientBancaire cb = (id == null) ? new ClientBancaire() : clientBancaireService.getClientBancaireById(id);
        model.addAttribute("clientBancaire", cb);

        // On fournit toutes les personnes pour sélectionner celles à associer au client
        List<Personne> personnes = new ArrayList<>();
        personnes.addAll(personneMoraleService.getAllPersonnesMorales());
        personnes.addAll(personnePhysiqueService.getAllPersonnesPhysiques());
        model.addAttribute("listePersonnes", personnes);

        return "lesClientsBancaires/edit_clientBancaire";
    }

    /**
     * Sauvegarde ou met à jour un client bancaire avec les personnes sélectionnées.
     * Mapping POST sur /clientBancaire/save/.
     * Redirige vers la liste des clients après sauvegarde.
     *
     * @param clientBancaire client envoyé par le formulaire
     * @param personnesIds   IDs des personnes liées au client
     * @return redirection vers /clientBancaire
     */
    @PostMapping("/clientBancaire/save/")
    public String editClientBancaire(@ModelAttribute("clientBancaire") ClientBancaire clientBancaire,
                                     @RequestParam("personnesIds") List<Long> personnesIds) {
        List<Personne> personnes = new ArrayList<>();

        // Récupération des entités PersonnePhysique ou PersonneMorale pour chaque ID
        for (Long id : personnesIds) {
            Personne personne = null;
            try {
                PersonnePhysique pp = personnePhysiqueService.getPersonnePhysiqueById(id);
                if (pp != null) personne = pp;
            } catch (Exception ignored) {}
            if (personne == null) {
                try {
                    PersonneMorale pm = personneMoraleService.getPersonneMoraleById(id);
                    if (pm != null) personne = pm;
                } catch (Exception ignored) {}
            }
            if (personne != null) personnes.add(personne);
        }

        clientBancaire.setPersonnes(personnes);
        clientBancaireService.updateClientBancaire(clientBancaire);
        return "redirect:/clientBancaire";
    }

    /**
     * Supprime un client bancaire selon son identifiant.
     * Mapping GET sur /clientBancaire/delete/{id}.
     * Redirection vers la liste des clients après suppression.
     *
     * @param id    identifiant du client à supprimer
     * @param model modèle (non utilisé mais nécessaire pour signature MVC)
     * @return redirection vers /clientBancaire
     */
    @GetMapping("/clientBancaire/delete/{id}")
    public String deleteClientBancaire(@PathVariable Long id, Model model) {
        clientBancaireService.deleteClientBancaireByID(id);
        return "redirect:/clientBancaire";
    }
}
