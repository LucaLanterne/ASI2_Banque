package com.banque.mvcControllers;

import com.banque.model.PersonnePhysique;
import com.banque.service.PersonnePhysiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonnePhysiqueController {

    private final PersonnePhysiqueService personnePhysiqueService;

    @Autowired
    public PersonnePhysiqueController(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    @GetMapping("/personnePhysique")
    public String listePersonnesPhysiques(Model model) {
        model.addAttribute("listePersonnesPhysiques", personnePhysiqueService.getAllPersonnesPhysiques());
        return "lesPersonnesPhysiques/personnePhysique";
    }

    @GetMapping(value={"/personnePhysique/edit", "/personnePhysique/edit/{id}"})
    public String editPersonnePhysiqueForm(@PathVariable(required = false) Long id, Model model) {
        PersonnePhysique pp;
        if (id == null) {
            pp = new PersonnePhysique();
        } else {
            pp = personnePhysiqueService.getPersonnePhysiqueById(id);
        }
        model.addAttribute("personnePhysique", pp);
        return "lesPersonnesPhysiques/edit_personnePhysique";
    }

    @PostMapping("/personnePhysique/save/")
    public String editPersonnePhysique(@ModelAttribute("personnePhysique") PersonnePhysique personnePhysique, Model model) {
        personnePhysiqueService.updatePersonnePhysique(personnePhysique);
        return "redirect:/personnePhysique";
    }

    @GetMapping("/personnePhysique/delete/{id}")
    public String deletePersonnePhysique(@PathVariable Long id, Model model) {
        personnePhysiqueService.deletePersonnePhysiqueById(id);
        return "redirect:/personnePhysique";
    }
}
