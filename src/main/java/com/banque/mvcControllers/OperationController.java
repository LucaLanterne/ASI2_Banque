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
import java.util.HashMap;
import java.util.List;

@Controller
public class OperationController {
    private OperationService operationService;
    private ProduitBancaireService produitBancaireService;
    private ProduitBancaire produitBancaire;
    private List<ProduitBancaire> listeProduitsBancaires;
    private List<Operation> listeOperations;
    private Operation operation;

    @Autowired
    public OperationController(OperationService operationService, ProduitBancaireService produitBancaireService) {
        this.operationService=operationService;
        this.produitBancaireService=produitBancaireService;
        this.listeProduitsBancaires=produitBancaireService.getAllProduitsBancaires();
        this.produitBancaire=null;
        this.listeOperations=null;
        this.operation=null;
    }

    @GetMapping("/operation")
    public String loadPageOperation(Model model)
    {
        this.listeProduitsBancaires=produitBancaireService.getAllProduitsBancaires();
        return "lesOperations/operations";
    }

    @PostMapping("/operation/loadOperation")
    public String loadOperation(@ModelAttribute("produitBancaireId") Long produitBancaireId, Model model)
    {
        this.produitBancaire=produitBancaireService.getProduitBancaireById(produitBancaireId);
        this.listeOperations=operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(produitBancaireId);
        return "redirect:/operation";
    }

    @PostMapping("/operation/loadOperationAutocomplete")
    public String loadOperationAutocomplete(@ModelAttribute("numeroCompteAutocomplete") String numeroCompteAutocomplete, Model model)
    {
        if (numeroCompteAutocomplete.contains("(")) {
            String numeroCompte = numeroCompteAutocomplete.substring(0, numeroCompteAutocomplete.indexOf("("));
            this.produitBancaire = produitBancaireService.findFirstByNumeroCompteIgnoreCase(numeroCompte);
            if (this.produitBancaire!=null)
            {
                this.listeOperations = operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(produitBancaire.getId());
            }
            else
            {
                System.out.println("Numéro de compte inexistant "+numeroCompte);
            }
        }
        return "redirect:/operation";
    }

    @GetMapping(value={"/operation/edit", "/operation/edit/{id}"})
    public String editOperation(@PathVariable(required = false) Long id, Model model)
    {
        if (id==null)
        {
            // Création d'un nouveau produit
            this.operation=new Operation();
            this.operation.setProduitBancaire(this.produitBancaire);
        }
        else {
            // Modification d'un produit existant
            this.operation=operationService.getOperationById(id);
        }
        // Envoi des données au modèle pour la construction de la page
        // edit_operation.html
        model.addAttribute("operation", this.operation);
        // Demande de génération de la page suivante
        return "lesOperations/edit_operation";
    }

    @PostMapping("/operation/save/")
    public String saveOperation(@ModelAttribute("operation") Operation operation, Model model)
    {
        operation.setProduitBancaire(this.operation.getProduitBancaire());
        this.operation=operation;
        operationService.updateOperation(this.operation);
        // Mise à jour de la liste des opérations
        this.listeOperations=operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(this.produitBancaire.getId());
        return "redirect:/operation";
    }

    @GetMapping("/operation/delete/{id}")
    public String deleteOperation(@PathVariable Long id)
    {
        operationService.deleteOperationById(id);
        this.listeOperations=operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(id);
        return "redirect:/operation";
    }

    @GetMapping( "/operation/details/{id}")
    public String detailsOperationForm(@PathVariable Long id, Model model)
    {
        this.operation=operationService.getOperationById(id);
        model.addAttribute("operation", this.operation);
        return "lesOperations/details_operation";
    }

    @RequestMapping(value="/operation/listeNumeroCompteAutocomplete")
    @ResponseBody
    public List<String> listeNumeroCompteAutocomplete(@RequestParam(value="term", required=false, defaultValue = "") String numeroComptePartiel)
    {
        listeProduitsBancaires=produitBancaireService.getProduitBancaireByNumeroCompteStartsWithIgnoreCase(numeroComptePartiel);
        List<String> listeNumerosComptes=new ArrayList<>();
        HashMap<Long,String> liste=new HashMap<>();
        for (ProduitBancaire pb : listeProduitsBancaires)
        {
            if (pb.getNumeroCompte().startsWith(numeroComptePartiel)) {
                String entree="";
                entree+=pb.getNumeroCompte()+"(";
                for (Personne p: pb.getClientBancaire().getPersonnes())
                {
                    entree+=p.nomComplet() + " - ";
                }
                entree+=")";
                listeNumerosComptes.add(entree);
            }
        }
        return listeNumerosComptes;
    }

    @ModelAttribute("listeProduitsBancaires")
    public List<ProduitBancaire> getListeProduitsBancaires()
    {
        return listeProduitsBancaires;
    }

    @ModelAttribute("listeOperations")
    public List<Operation> getlisteOperations()
    {
        return listeOperations;
    }

    @ModelAttribute("produitBancaire")
    public ProduitBancaire getProduitBancaire()
    {
        return this.produitBancaire;
    }

    @ModelAttribute("operation")
    public Operation getOperation()
    {
        return this.operation;
    }
}