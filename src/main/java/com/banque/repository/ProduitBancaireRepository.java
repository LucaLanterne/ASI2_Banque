package com.banque.repository;

import com.banque.model.ProduitBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository JPA pour gérer les entités {@link ProduitBancaire}.
 * Fournit des méthodes pour rechercher des produits bancaires par différents critères.
 */
public interface ProduitBancaireRepository extends JpaRepository<ProduitBancaire, Long> {

    /**
     * Recherche un produit bancaire par son ID et l'intitulé de son type de produit.
     *
     * @param id identifiant du produit bancaire
     * @param typeProduitIntitule intitulé du type de produit
     * @return produit bancaire correspondant, ou null si non trouvé
     */
    ProduitBancaire findByIdAndTypeProduitIntitule(Long id, String typeProduitIntitule);

    /**
     * Recherche tous les produits bancaires dont le numéro de compte commence par la chaîne donnée (insensible à la casse).
     *
     * @param numeroComptePartiel début du numéro de compte
     * @return liste des produits bancaires correspondants
     */
    List<ProduitBancaire> findByNumeroCompteStartsWithIgnoreCase(String numeroComptePartiel);

    /**
     * Recherche le premier produit bancaire dont le numéro de compte correspond exactement à la chaîne donnée (insensible à la casse).
     *
     * @param numeroCompte numéro de compte complet
     * @return produit bancaire correspondant, ou null si non trouvé
     */
    ProduitBancaire findFirstByNumeroCompteIgnoreCase(String numeroCompte);
}
