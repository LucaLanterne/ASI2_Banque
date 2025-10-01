package com.banque.mvcControllers;

import com.banque.model.PersonneMorale;
import com.banque.service.PersonneMoraleService;
import com.banque.service.TypePersonneMoraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonneMoraleController {

    private final PersonneMoraleService personneMoraleService;
    private final TypePersonneMoraleService typePersonneMoraleService;

    @Autowired
    public PersonneMoraleController(PersonneMoraleService personneMoraleService,  TypePersonneMoraleService typePersonneMoraleService) {
        this.personneMoraleService = personneMoraleService;
        this.typePersonneMoraleService = typePersonneMoraleService;
    }

    @GetMapping("/personneMorale")
    public String listePersonnesMorales(Model model) {
        model.addAttribute("listePersonnesMorales", personneMoraleService.getAllPersonnesMorales());
        return "lesPersonnesMorales/personneMorale";
    }

    @GetMapping(value={"/personneMorale/edit", "/personneMorale/edit/{id}"})
    public String editPersonneMoraleForm(@PathVariable(required = false) Long id, Model model) {
        PersonneMorale pp;
        if (id == null) {
            pp = new PersonneMorale();
        } else {
            pp = personneMoraleService.getPersonneMoraleById(id);
        }
        model.addAttribute("personneMorale", pp);
        model.addAttribute("listeTypesPersonnesMorales", typePersonneMoraleService.getAllTypesPersonnesMorales());
        return "lesPersonnesMorales/edit_personneMorale";
    }

    @PostMapping("/personneMorale/save/")
    public String editPersonneMorale(@ModelAttribute("personneMorale") PersonneMorale personneMorale,
                                     @ModelAttribute("typePersonneMoraleId") Long TypePersonneMoraleId, Model model) {
        personneMorale.setTypePersonneMorale(typePersonneMoraleService.getTypePersonneMoraleById(TypePersonneMoraleId));
        personneMoraleService.updatePersonneMorale(personneMorale);
        return "redirect:/personneMorale";
    }

    @GetMapping("/personneMorale/delete/{id}")
    public String deletePersonneMorale(@PathVariable Long id, Model model) {
        personneMoraleService.deletePersonneMoraleById(id);
        return "redirect:/personneMorale";
    }
}
