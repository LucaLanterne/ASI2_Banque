package com.banque.mvcControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Contrôleur MVC principal pour gérer les pages générales du site.
 * Fournit notamment la page d'accueil.
 */
@Controller
public class MainController {

    /**
     * Affiche la page d'accueil du site.
     * Mapping GET sur l'URL racine "".
     * La vue "index" correspond au template Thymeleaf / ressources/templates/index.html
     *
     * @return nom du template "index"
     */
    @GetMapping("")
    public String afficherHomePage() {
        return "index";
    }
}
