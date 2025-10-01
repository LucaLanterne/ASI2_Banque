package com.banque.mvcControllers;

import com.banque.model.PersonnePhysique;
import com.banque.service.PersonnePhysiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur MVC pour gérer les personnes physiques.
 * Fournit les fonctionnalités d'affichage, création, modification et suppression des personnes physiques.
 */
@Controller
public class PersonnePhysiqueController {

    private final PersonnePhysiqueService personnePhysiqueService;

    /**
     * Constructeur du contrôleur PersonnePhysiqueController.
     * Permet l'injection du service nécessaire pour gérer les personnes physiques.
     *
     * @param personnePhysiqueService service pour gérer les personnes physiques
     */
    @Autowired
    public PersonnePhysiqueController(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Affiche la liste de toutes les personnes physiques.
     * Mapping GET sur /personnePhysique. La vue "personnePhysique" reçoit la liste des personnes.
     *
     * @param model modèle pour passer les données à la vue
     * @return template "lesPersonnesPhysiques/personnePhysique"
     */
    @GetMapping("/personnePhysique")
    public String listePersonnesPhysiques(Model model) {
        model.addAttribute("listePersonnesPhysiques", personnePhysiqueService.getAllPersonnesPhysiques());
        return "lesPersonnesPhysiques/personnePhysique";
    }

    /**
     * Affiche le formulaire pour créer ou éditer une personne physique.
     * Mapping GET sur /personnePhysique/edit ou /personnePhysique/edit/{id}.
     * Si l'id est présent, récupère la personne existante, sinon crée une nouvelle personne vide.
     *
     * @param id    identifiant de la personne (optionnel)
     * @param model modèle pour passer la personne à la vue
     * @return template "lesPersonnesPhysiques/edit_personnePhysique"
     */
    @GetMapping(value = {"/personnePhysique/edit", "/personnePhysique/edit/{id}"})
    public String editPersonnePhysiqueForm(@PathVariable(required = false) Long id, Model model) {
        PersonnePhysique pp = (id == null) ? new PersonnePhysique() : personnePhysiqueService.getPersonnePhysiqueById(id);
        model.addAttribute("personnePhysique", pp);
        return "lesPersonnesPhysiques/edit_personnePhysique";
    }

    /**
     * Sauvegarde ou met à jour une personne physique.
     * Mapping POST sur /personnePhysique/save/.
     * Redirige vers la liste des personnes physiques après sauvegarde.
     *
     * @param personnePhysique personne envoyée par le formulaire
     * @param model modèle pour la vue
     * @return redirection vers /personnePhysique
     */
    @PostMapping("/personnePhysique/save/")
    public String editPersonnePhysique(@ModelAttribute("personnePhysique") PersonnePhysique personnePhysique, Model model) {
        personnePhysiqueService.updatePersonnePhysique(personnePhysique);
        return "redirect:/personnePhysique";
    }

    /**
     * Supprime une personne physique selon son identifiant.
     * Mapping GET sur /personnePhysique/delete/{id}.
     * Redirection vers la liste des personnes physiques après suppression.
     *
     * @param id    identifiant de la personne à supprimer
     * @param model modèle (non utilisé mais nécessaire pour signature MVC)
     * @return redirection vers /personnePhysique
     */
    @GetMapping("/personnePhysique/delete/{id}")
    public String deletePersonnePhysique(@PathVariable Long id, Model model) {
        personnePhysiqueService.deletePersonnePhysiqueById(id);
        return "redirect:/personnePhysique";
    }
}
