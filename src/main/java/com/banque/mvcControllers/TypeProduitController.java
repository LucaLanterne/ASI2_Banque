package com.banque.mvcControllers;

import com.banque.model.TypeProduit;
import com.banque.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TypeProduitController {
    private final TypeProduitService typeProduitService;

    @Autowired
    public TypeProduitController(TypeProduitService typeProduitService) {
        this.typeProduitService = typeProduitService;
    }

    @GetMapping("/typeProduit")
    public String listTypesProduits(Model model)
    {
        model.addAttribute("listeTypesProduits", typeProduitService.getAllTypesProduits());
        return "lesTypesProduits/typesProduits";
    }

    // Ajout et modification de types de produits
    // On définit donc deux routes. Une route sans paramètre pour la création de types de produits.
    // Et une route qui passe l'id du type des produits à modifier.
    // On en profite pour indiquer que le paramètre Long id peut être null (dans le cas de la création).
    @GetMapping(value={"/typeProduit/edit", "/typeProduit/edit/{id}"})
    public String editTypeProduitForm(@PathVariable(required = false) Long id, Model model)
    {
        TypeProduit tp;
        if (id==null)
        {
            // Si on n'a pas récupéré d'attribut id de la page HTML c'est que le client souhaite
            // créer un nouveau type de produit.
            tp=new TypeProduit();
        }
        else {
            // On récupère l'id du type de produit qui est passée dans l'URL
            // On poste l'objet typeProduit pour cet id à la page edit_typeProduit.
            tp=typeProduitService.getTypeProduitById(id);
        }
        // On ajoute tp au modèle de la page.
        // On pourra accéder à une variable de nom typeProduit depuis le template HTML
        model.addAttribute("typeProduit", tp);
        // On appelle de moteur de template pour la construction de la page suivante
        // qui va afficher les champs à créer/modifier pour les types de produits.
        // Nous indiquons ici le chemin vers le template à charger.
        return "lesTypesProduits/edit_typeProduit";
    }

    // Route appelée depuis la page edit_typeProduit.html.
    // C'est une route sans paramètre.
    @PostMapping("/typeProduit/save/")
    public String editTypeProduit(@ModelAttribute("typeProduit") TypeProduit typeProduit, Model model)
    {
        typeProduitService.updateTypeProduit(typeProduit);
        return "redirect:/typeProduit";
    }

    @GetMapping("/typeProduit/delete/{id}")
    public String deleteTypeProduit(@PathVariable Long id)
    {
        typeProduitService.deleteTypeProduitById(id);
        return "redirect:/typeProduit";
    }
}