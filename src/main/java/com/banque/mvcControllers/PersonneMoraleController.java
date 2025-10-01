package com.banque.mvcControllers;

import com.banque.model.PersonneMorale;
import com.banque.service.PersonneMoraleService;
import com.banque.service.TypePersonneMoraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur MVC pour gérer les personnes morales.
 * Fournit les fonctionnalités d'affichage, création, modification et suppression.
 */
@Controller
public class PersonneMoraleController {

    private final PersonneMoraleService personneMoraleService;
    private final TypePersonneMoraleService typePersonneMoraleService;

    /**
     * Constructeur du contrôleur PersonneMoraleController.
     * Permet l'injection des services nécessaires.
     *
     * @param personneMoraleService service pour gérer les personnes morales
     * @param typePersonneMoraleService service pour gérer les types de personnes morales
     */
    @Autowired
    public PersonneMoraleController(PersonneMoraleService personneMoraleService, TypePersonneMoraleService typePersonneMoraleService) {
        this.personneMoraleService = personneMoraleService;
        this.typePersonneMoraleService = typePersonneMoraleService;
    }

    /**
     * Affiche la liste de toutes les personnes morales.
     * Mapping GET sur /personneMorale. La vue "personneMorale" reçoit la liste des personnes morales.
     *
     * @param model modèle pour passer les données à la vue
     * @return template "lesPersonnesMorales/personneMorale"
     */
    @GetMapping("/personneMorale")
    public String listePersonnesMorales(Model model) {
        model.addAttribute("listePersonnesMorales", personneMoraleService.getAllPersonnesMorales());
        return "lesPersonnesMorales/personneMorale";
    }

    /**
     * Formulaire pour créer ou éditer une personne morale.
     * Mapping GET sur /personneMorale/edit ou /personneMorale/edit/{id}.
     * Si l'id est présent, récupère la personne morale existante, sinon crée une nouvelle personne morale vide.
     *
     * @param id ID de la personne morale (optionnel)
     * @param model modèle pour passer les données à la vue
     * @return template "lesPersonnesMorales/edit_personneMorale"
     */
    @GetMapping(value = {"/personneMorale/edit", "/personneMorale/edit/{id}"})
    public String editPersonneMoraleForm(@PathVariable(required = false) Long id, Model model) {
        PersonneMorale pp = (id == null) ? new PersonneMorale() : personneMoraleService.getPersonneMoraleById(id);
        model.addAttribute("personneMorale", pp);
        model.addAttribute("listeTypesPersonnesMorales", typePersonneMoraleService.getAllTypesPersonnesMorales());
        return "lesPersonnesMorales/edit_personneMorale";
    }

    /**
     * Sauvegarde ou met à jour une personne morale.
     * Mapping POST sur /personneMorale/save/.
     * Redirige vers la liste des personnes morales après sauvegarde.
     *
     * @param personneMorale personne morale envoyée par le formulaire
     * @param TypePersonneMoraleId ID du type de personne morale associé
     * @param model modèle pour la vue
     * @return redirection vers /personneMorale
     */
    @PostMapping("/personneMorale/save/")
    public String editPersonneMorale(@ModelAttribute("personneMorale") PersonneMorale personneMorale,
                                     @ModelAttribute("typePersonneMoraleId") Long TypePersonneMoraleId, Model model) {
        personneMorale.setTypePersonneMorale(typePersonneMoraleService.getTypePersonneMoraleById(TypePersonneMoraleId));
        personneMoraleService.updatePersonneMorale(personneMorale);
        return "redirect:/personneMorale";
    }

    /**
     * Supprime une personne morale par son ID.
     * Mapping GET sur /personneMorale/delete/{id}.
     * Redirige vers la liste des personnes morales après suppression.
     *
     * @param id ID de la personne morale à supprimer
     * @param model modèle (non utilisé mais nécessaire pour signature MVC)
     * @return redirection vers /personneMorale
     */
    @GetMapping("/personneMorale/delete/{id}")
    public String deletePersonneMorale(@PathVariable Long id, Model model) {
        personneMoraleService.deletePersonneMoraleById(id);
        return "redirect:/personneMorale";
    }
}
