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

@Controller
public class ClientBancaireController {

    private final ClientBancaireService clientBancaireService;
    private final PersonneMoraleService personneMoraleService;
    private final PersonnePhysiqueService personnePhysiqueService;

    @Autowired
    public ClientBancaireController (ClientBancaireService clientBancaireService, PersonnePhysiqueService personnePhysiqueService,
                                     PersonneMoraleService personneMoraleService) {
        this.clientBancaireService = clientBancaireService;
        this.personnePhysiqueService = personnePhysiqueService;
        this.personneMoraleService = personneMoraleService;
    }

    @GetMapping("/clientBancaire")
    public String listeClientsBancaires(Model model) {
        model.addAttribute("listeClientsBancaires", clientBancaireService.getAllClientsBancaires());
        return "lesClientsBancaires/clientBancaire";
    }

    @GetMapping(value={"/clientBancaire/edit", "/clientBancaire/edit/{id}"})
    public String editClientBancaireForm(@PathVariable(required = false) Long id, Model model) {
        ClientBancaire cb;
        if (id == null) {
            cb = new ClientBancaire();
        } else  {
            cb = clientBancaireService.getClientBancaireById(id);
        }
        model.addAttribute("clientBancaire", cb);
        List<Personne> personnes = new ArrayList<>();
        personnes.addAll(personneMoraleService.getAllPersonnesMorales());
        personnes.addAll(personnePhysiqueService.getAllPersonnesPhysiques());
        model.addAttribute("listePersonnes", personnes);
        return "lesClientsBancaires/edit_clientBancaire";
    }

    @PostMapping("/clientBancaire/save/")
    public String editClientBancaire(@ModelAttribute("clientBancaire") ClientBancaire clientBancaire,
                                     @RequestParam("personnesIds") List<Long> personnesIds) {
        List<Personne> personnes = new ArrayList<>();
        for (Long id : personnesIds) {
            Personne personne = null;
            try {
                // Essai en personne physique
                PersonnePhysique pp = personnePhysiqueService.getPersonnePhysiqueById(id);
                if (pp != null) personne = pp;
            } catch (Exception ignored) {}
            if (personne == null) {
                try {
                    // Essai en personne morale
                    PersonneMorale pm = personneMoraleService.getPersonneMoraleById(id);
                    if (pm != null) personne = pm;
                } catch (Exception ignored) {}
            }
            if (personne != null) {
                personnes.add(personne);
            }
        }
        clientBancaire.setPersonnes(personnes);
        clientBancaireService.updateClientBancaire(clientBancaire);
        return "redirect:/clientBancaire";
    }

    @GetMapping("/clientBancaire/delete/{id}")
    public String deleteClientBancaire(@PathVariable Long id, Model model) {
        clientBancaireService.deleteClientBancaireByID(id);
        return "redirect:/clientBancaire";
    }
}
