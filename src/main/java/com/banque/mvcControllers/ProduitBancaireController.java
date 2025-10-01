package com.banque.mvcControllers;

import com.banque.model.ProduitBancaire;
import com.banque.service.ClientBancaireService;
import com.banque.service.ProduitBancaireService;
import com.banque.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProduitBancaireController {

    private final ProduitBancaireService produitBancaireService;
    private final TypeProduitService typeProduitService;
    private final ClientBancaireService clientBancaireService;


    @Autowired
    public ProduitBancaireController(ProduitBancaireService produitBancaireService,
                                     ClientBancaireService clientBancaireService,
                                     TypeProduitService typeProduitService) {
        this.produitBancaireService = produitBancaireService;
        this.clientBancaireService = clientBancaireService;
        this.typeProduitService = typeProduitService;
    }

    @GetMapping("/produitBancaire")
    public String listeProduitsBancaires(Model model) {
        model.addAttribute("listeProduitsBancaires", produitBancaireService.getAllProduitsBancaires());
        return "lesProduitsBancaires/produitBancaire";
    }

    @GetMapping(value={"produitBancaire/edit", "produitBancaire/edit/{id}"})
    public String editProduitBancaireForm(@PathVariable(required = false) Long id, Model model) {
        ProduitBancaire pb;
        if (id == null) {
            pb = new ProduitBancaire();
            int n = produitBancaireService.getAllProduitsBancaires().size()+1;
            pb.setNumeroCompte("FR76-000" + n);
            System.out.println("********************************************************\n" +
                    pb.getNumeroCompte() + "\n" +
                    "********************************************************\n");
        } else {
            pb = produitBancaireService.getProduitBancaireById(id);
        }
        model.addAttribute("produitBancaire", pb);
        model.addAttribute("listeClientsBancaires", clientBancaireService.getAllClientsBancaires());
        model.addAttribute("listeTypesProduits", typeProduitService.getAllTypesProduits());
        return "lesProduitsBancaires/edit_produitBancaire";
    }

    @PostMapping("/produitBancaire/save/")
    public String editProduitBancaire(@ModelAttribute("produitBancaire") ProduitBancaire produitBancaire,
                                      @RequestParam Long clientBancaireId,
                                      @RequestParam Long typeProduitId, Model model) {
        produitBancaire.setClientBancaire(clientBancaireService.getClientBancaireById(clientBancaireId));
        produitBancaire.setTypeProduit(typeProduitService.getTypeProduitById(typeProduitId));
        produitBancaireService.updateProduitBancaire(produitBancaire);
        return "redirect:/produitBancaire";
    }

    @GetMapping("/produitBancaire/delete/{id}")
    public String deleteProduitBancaire(@PathVariable Long id) {
        produitBancaireService.deleteProduitBancaireById(id);
        return "redirect:/produitBancaire";
    }
}
