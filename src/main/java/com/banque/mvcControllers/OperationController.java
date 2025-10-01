package com.banque.mvcControllers;

import com.banque.model.Operation;
import com.banque.model.Personne;
import com.banque.model.ProduitBancaire;
import com.banque.service.OperationService;
import com.banque.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur MVC pour gérer les opérations bancaires.
 * Fournit les fonctionnalités d'affichage, création, modification, suppression et autocomplétion des opérations.
 */
@Controller
public class OperationController {

    private final OperationService operationService;
    private final ProduitBancaireService produitBancaireService;
    private ProduitBancaire produitBancaire;
    private List<ProduitBancaire> listeProduitsBancaires;
    private List<Operation> listeOperations;
    private Operation operation;

    /**
     * Constructeur du contrôleur OperationController.
     * Permet l'injection des services nécessaires pour gérer les opérations et les produits bancaires.
     *
     * @param operationService service pour gérer les opérations bancaires
     * @param produitBancaireService service pour gérer les produits bancaires
     */
    @Autowired
    public OperationController(OperationService operationService, ProduitBancaireService produitBancaireService) {
        this.operationService = operationService;
        this.produitBancaireService = produitBancaireService;
        this.listeProduitsBancaires = produitBancaireService.getAllProduitsBancaires();
        this.produitBancaire = null;
        this.listeOperations = null;
        this.operation = null;
    }

    /**
     * Charge la page principale des opérations.
     * Mapping GET sur /operation.
     * Affiche tous les produits bancaires disponibles dans la vue.
     *
     * @param model modèle pour passer les données à la vue
     * @return template "lesOperations/operations"
     */
    @GetMapping("/operation")
    public String loadPageOperation(Model model) {
        this.listeProduitsBancaires = produitBancaireService.getAllProduitsBancaires();
        return "lesOperations/operations";
    }

    /**
     * Charge les opérations associées à un produit bancaire sélectionné.
     * Mapping POST sur /operation/loadOperation.
     * Si un produit est sélectionné, récupère les opérations correspondantes.
     * Redirige vers la page principale des opérations.
     *
     * @param produitBancaireId ID du produit bancaire
     * @param model modèle pour passer les données à la vue
     * @return redirection vers /operation
     */
    @PostMapping("/operation/loadOperation")
    public String loadOperation(@ModelAttribute("produitBancaireId") Long produitBancaireId, Model model) {
        this.produitBancaire = produitBancaireService.getProduitBancaireById(produitBancaireId);
        this.listeOperations = operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(produitBancaireId);
        return "redirect:/operation";
    }

    /**
     * Charge les opérations via autocomplétion sur le numéro de compte.
     * Mapping POST sur /operation/loadOperationAutocomplete.
     * Si le numéro correspond à un produit bancaire existant, récupère les opérations.
     * Redirige vers la page principale des opérations.
     *
     * @param numeroCompteAutocomplete numéro de compte partiel ou complet
     * @param model modèle pour passer les données à la vue
     * @return redirection vers /operation
     */
    @PostMapping("/operation/loadOperationAutocomplete")
    public String loadOperationAutocomplete(@ModelAttribute("numeroCompteAutocomplete") String numeroCompteAutocomplete, Model model) {
        if (numeroCompteAutocomplete.contains("(")) {
            String numeroCompte = numeroCompteAutocomplete.substring(0, numeroCompteAutocomplete.indexOf("("));
            this.produitBancaire = produitBancaireService.findFirstByNumeroCompteIgnoreCase(numeroCompte);
            if (this.produitBancaire != null) {
                this.listeOperations = operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(produitBancaire.getId());
            } else {
                System.out.println("Numéro de compte inexistant " + numeroCompte);
            }
        }
        return "redirect:/operation";
    }

    /**
     * Formulaire pour créer ou éditer une opération.
     * Mapping GET sur /operation/edit ou /operation/edit/{id}.
     * Si l'id est présent, récupère l'opération existante, sinon crée une nouvelle opération vide.
     *
     * @param id ID de l'opération (optionnel)
     * @param model modèle pour passer les données à la vue
     * @return template "lesOperations/edit_operation"
     */
    @GetMapping(value = {"/operation/edit", "/operation/edit/{id}"})
    public String editOperation(@PathVariable(required = false) Long id, Model model) {
        if (id == null) {
            this.operation = new Operation();
            this.operation.setProduitBancaire(this.produitBancaire);
        } else {
            this.operation = operationService.getOperationById(id);
        }
        model.addAttribute("operation", this.operation);
        return "lesOperations/edit_operation";
    }

    /**
     * Sauvegarde ou met à jour une opération.
     * Mapping POST sur /operation/save/.
     * Redirige vers la liste des opérations après sauvegarde.
     *
     * @param operation opération envoyée par le formulaire
     * @param model modèle pour la vue
     * @return redirection vers /operation
     */
    @PostMapping("/operation/save/")
    public String saveOperation(@ModelAttribute("operation") Operation operation, Model model) {
        operation.setProduitBancaire(this.operation.getProduitBancaire());
        this.operation = operation;
        operationService.updateOperation(this.operation);
        this.listeOperations = operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(this.produitBancaire.getId());
        return "redirect:/operation";
    }

    /**
     * Supprime une opération par son ID.
     * Mapping GET sur /operation/delete/{id}.
     * Redirige vers la liste des opérations après suppression.
     *
     * @param id ID de l'opération à supprimer
     * @return redirection vers /operation
     */
    @GetMapping("/operation/delete/{id}")
    public String deleteOperation(@PathVariable Long id) {
        operationService.deleteOperationById(id);
        this.listeOperations = operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(id);
        return "redirect:/operation";
    }

    /**
     * Affiche les détails d'une opération spécifique.
     * Mapping GET sur /operation/details/{id}.
     * Template affiché : "lesOperations/details_operation.html".
     *
     * @param id ID de l'opération
     * @param model modèle pour passer les données à la vue
     * @return template "lesOperations/details_operation"
     */
    @GetMapping("/operation/details/{id}")
    public String detailsOperationForm(@PathVariable Long id, Model model) {
        this.operation = operationService.getOperationById(id);
        model.addAttribute("operation", this.operation);
        return "lesOperations/details_operation";
    }

    /**
     * Fournit la liste des numéros de compte pour l'autocomplétion.
     * Mapping GET sur /operation/listeNumeroCompteAutocomplete.
     * Retourne une liste JSON des comptes et noms des clients associés.
     *
     * @param numeroComptePartiel partie du numéro de compte recherchée
     * @return liste des entrées pour autocomplétion
     */
    @RequestMapping(value = "/operation/listeNumeroCompteAutocomplete")
    @ResponseBody
    public List<String> listeNumeroCompteAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "") String numeroComptePartiel) {
        listeProduitsBancaires = produitBancaireService.getProduitBancaireByNumeroCompteStartsWithIgnoreCase(numeroComptePartiel);
        List<String> listeNumerosComptes = new ArrayList<>();
        for (ProduitBancaire pb : listeProduitsBancaires) {
            if (pb.getNumeroCompte().startsWith(numeroComptePartiel)) {
                String entree = pb.getNumeroCompte() + "(";
                for (Personne p : pb.getClientBancaire().getPersonnes()) {
                    entree += p.nomComplet() + " - ";
                }
                entree += ")";
                listeNumerosComptes.add(entree);
            }
        }
        return listeNumerosComptes;
    }

    // ------------------- Getters & Setters pour la vue -------------------

    /**
     * Récupère la liste des produits bancaires pour la vue.
     *
     * @return liste des produits bancaires
     */
    @ModelAttribute("listeProduitsBancaires")
    public List<ProduitBancaire> getListeProduitsBancaires() {
        return listeProduitsBancaires;
    }

    /**
     * Récupère la liste des opérations pour la vue.
     *
     * @return liste des opérations
     */
    @ModelAttribute("listeOperations")
    public List<Operation> getListeOperations() {
        return listeOperations;
    }

    /**
     * Récupère le produit bancaire courant pour la vue.
     *
     * @return produit bancaire
     */
    @ModelAttribute("produitBancaire")
    public ProduitBancaire getProduitBancaire() {
        return produitBancaire;
    }

    /**
     * Récupère l'opération courante pour la vue.
     *
     * @return opération
     */
    @ModelAttribute("operation")
    public Operation getOperation() {
        return operation;
    }
}
