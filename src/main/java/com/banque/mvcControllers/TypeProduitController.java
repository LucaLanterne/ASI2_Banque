package com.banque.mvcControllers;

import com.banque.model.TypeProduit;
import com.banque.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur MVC pour gérer les types de produits bancaires.
 * Fournit les fonctionnalités d'affichage, création, modification et suppression des types de produits.
 */
@Controller
public class TypeProduitController {

    private final TypeProduitService typeProduitService;

    /**
     * Constructeur du contrôleur TypeProduitController.
     * Permet l'injection du service nécessaire pour gérer les types de produits.
     *
     * @param typeProduitService service pour les opérations sur les types de produits
     */
    @Autowired
    public TypeProduitController(TypeProduitService typeProduitService) {
        this.typeProduitService = typeProduitService;
    }

    /**
     * Affiche la liste de tous les types de produits.
     * Mapping GET sur /typeProduit. La vue "typesProduits" reçoit la liste des types.
     *
     * @param model modèle pour passer les données à la vue
     * @return template "lesTypesProduits/typesProduits"
     */
    @GetMapping("/typeProduit")
    public String listTypesProduits(Model model) {
        model.addAttribute("listeTypesProduits", typeProduitService.getAllTypesProduits());
        return "lesTypesProduits/typesProduits";
    }

    /**
     * Affiche le formulaire pour créer ou éditer un type de produit.
     * Mapping GET sur /typeProduit/edit ou /typeProduit/edit/{id}.
     * Si l'id est présent, récupère le type existant, sinon crée un nouveau type vide.
     *
     * @param id    identifiant du type de produit (optionnel)
     * @param model modèle pour passer le type de produit à la vue
     * @return template "lesTypesProduits/edit_typeProduit"
     */
    @GetMapping(value = {"/typeProduit/edit", "/typeProduit/edit/{id}"})
    public String editTypeProduitForm(@PathVariable(required = false) Long id, Model model) {
        TypeProduit tp = (id == null) ? new TypeProduit() : typeProduitService.getTypeProduitById(id);
        model.addAttribute("typeProduit", tp);
        return "lesTypesProduits/edit_typeProduit";
    }

    /**
     * Sauvegarde ou met à jour un type de produit.
     * Mapping POST sur /typeProduit/save/.
     * Redirige vers la liste des types après sauvegarde.
     *
     * @param typeProduit type de produit envoyé par le formulaire
     * @param model modèle pour la vue
     * @return redirection vers /typeProduit
     */
    @PostMapping("/typeProduit/save/")
    public String editTypeProduit(@ModelAttribute("typeProduit") TypeProduit typeProduit, Model model) {
        typeProduitService.updateTypeProduit(typeProduit);
        return "redirect:/typeProduit";
    }

    /**
     * Supprime un type de produit selon son identifiant.
     * Mapping GET sur /typeProduit/delete/{id}.
     * Redirection vers la liste des types après suppression.
     *
     * @param id identifiant du type de produit à supprimer
     * @return redirection vers /typeProduit
     */
    @GetMapping("/typeProduit/delete/{id}")
    public String deleteTypeProduit(@PathVariable Long id) {
        typeProduitService.deleteTypeProduitById(id);
        return "redirect:/typeProduit";
    }
}
