package com.banque.mvcControllers;

import com.banque.model.ProduitBancaire;
import com.banque.service.ClientBancaireService;
import com.banque.service.ProduitBancaireService;
import com.banque.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur MVC pour gérer les produits bancaires.
 * Fournit les fonctionnalités d'affichage, création, modification et suppression des produits bancaires.
 */
@Controller
public class ProduitBancaireController {

    private final ProduitBancaireService produitBancaireService;
    private final TypeProduitService typeProduitService;
    private final ClientBancaireService clientBancaireService;

    /**
     * Constructeur du contrôleur ProduitBancaireController.
     * Permet l'injection des services nécessaires pour gérer les produits bancaires, leurs types et clients.
     *
     * @param produitBancaireService service pour les opérations sur les produits bancaires
     * @param clientBancaireService  service pour gérer les clients bancaires associés
     * @param typeProduitService     service pour gérer les types de produits
     */
    @Autowired
    public ProduitBancaireController(ProduitBancaireService produitBancaireService,
                                     ClientBancaireService clientBancaireService,
                                     TypeProduitService typeProduitService) {
        this.produitBancaireService = produitBancaireService;
        this.clientBancaireService = clientBancaireService;
        this.typeProduitService = typeProduitService;
    }

    /**
     * Affiche la liste de tous les produits bancaires.
     * Mapping GET sur /produitBancaire. La vue "produitBancaire" reçoit la liste des produits.
     *
     * @param model modèle pour passer les données à la vue
     * @return template "lesProduitsBancaires/produitBancaire"
     */
    @GetMapping("/produitBancaire")
    public String listeProduitsBancaires(Model model) {
        model.addAttribute("listeProduitsBancaires", produitBancaireService.getAllProduitsBancaires());
        return "lesProduitsBancaires/produitBancaire";
    }

    /**
     * Affiche le formulaire pour créer ou éditer un produit bancaire.
     * Mapping GET sur /produitBancaire/edit ou /produitBancaire/edit/{id}.
     * Si l'id est présent, récupère le produit existant, sinon crée un nouveau produit avec un numéro de compte généré.
     *
     * @param id    identifiant du produit (optionnel)
     * @param model modèle pour passer le produit, la liste des clients et types à la vue
     * @return template "lesProduitsBancaires/edit_produitBancaire"
     */
    @GetMapping(value = {"produitBancaire/edit", "produitBancaire/edit/{id}"})
    public String editProduitBancaireForm(@PathVariable(required = false) Long id, Model model) {
        ProduitBancaire pb = (id == null) ? new ProduitBancaire() : produitBancaireService.getProduitBancaireById(id);
        if (id == null) {
            int n = produitBancaireService.getAllProduitsBancaires().size() + 1;
            pb.setNumeroCompte("FR76-000" + n);
        }
        model.addAttribute("produitBancaire", pb);
        model.addAttribute("listeClientsBancaires", clientBancaireService.getAllClientsBancaires());
        model.addAttribute("listeTypesProduits", typeProduitService.getAllTypesProduits());
        return "lesProduitsBancaires/edit_produitBancaire";
    }

    /**
     * Sauvegarde ou met à jour un produit bancaire avec le client et le type sélectionnés.
     * Mapping POST sur /produitBancaire/save/.
     * Redirige vers la liste des produits après sauvegarde.
     *
     * @param produitBancaire produit envoyé par le formulaire
     * @param clientBancaireId ID du client associé
     * @param typeProduitId    ID du type de produit associé
     * @param model modèle pour la vue
     * @return redirection vers /produitBancaire
     */
    @PostMapping("/produitBancaire/save/")
    public String editProduitBancaire(@ModelAttribute("produitBancaire") ProduitBancaire produitBancaire,
                                      @RequestParam("clientBancaireId") Long clientBancaireId,
                                      @RequestParam("typeProduitId") Long typeProduitId, Model model) {
        produitBancaire.setClientBancaire(clientBancaireService.getClientBancaireById(clientBancaireId));
        produitBancaire.setTypeProduit(typeProduitService.getTypeProduitById(typeProduitId));
        produitBancaireService.updateProduitBancaire(produitBancaire);
        return "redirect:/produitBancaire";
    }

    /**
     * Supprime un produit bancaire selon son identifiant.
     * Mapping GET sur /produitBancaire/delete/{id}.
     * Redirection vers la liste des produits après suppression.
     *
     * @param id identifiant du produit à supprimer
     * @return redirection vers /produitBancaire
     */
    @GetMapping("/produitBancaire/delete/{id}")
    public String deleteProduitBancaire(@PathVariable Long id) {
        produitBancaireService.deleteProduitBancaireById(id);
        return "redirect:/produitBancaire";
    }
}
