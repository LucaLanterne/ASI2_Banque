package com.banque.service;

import com.banque.model.Operation;
import com.banque.model.ProduitBancaire;

import java.util.List;

/**
 * Service pour gérer les produits bancaires.
 * Définit les méthodes CRUD et les recherches spécifiques sur les produits bancaires.
 */
public interface ProduitBancaireService {

    /**
     * Récupère tous les produits bancaires.
     *
     * @return liste de tous les produits bancaires
     */
    List<ProduitBancaire> getAllProduitsBancaires();

    /**
     * Crée un nouveau produit bancaire.
     *
     * @param produitBancaire produit bancaire à créer
     * @return produit bancaire créé
     */
    ProduitBancaire createProduitBancaire(ProduitBancaire produitBancaire);

    /**
     * Récupère un produit bancaire par son ID.
     *
     * @param id identifiant du produit bancaire
     * @return produit bancaire correspondant
     */
    ProduitBancaire getProduitBancaireById(Long id);

    /**
     * Met à jour un produit bancaire existant.
     *
     * @param produitBancaire produit bancaire à mettre à jour
     * @return produit bancaire mis à jour
     */
    ProduitBancaire updateProduitBancaire(ProduitBancaire produitBancaire);

    /**
     * Supprime un produit bancaire par son ID.
     *
     * @param id identifiant du produit bancaire à supprimer
     */
    void deleteProduitBancaireById(Long id);

    /**
     * Récupère un compte chèque spécifique par son ID.
     *
     * @param id identifiant du compte chèque
     * @return produit bancaire correspondant au compte chèque
     */
    ProduitBancaire getCompteChequeById(Long id);

    /**
     * Récupère tous les produits bancaires dont le numéro de compte commence par une chaîne donnée (insensible à la casse).
     *
     * @param numeroComptePartiel début du numéro de compte
     * @return liste des produits bancaires correspondants
     */
    List<ProduitBancaire> getProduitBancaireByNumeroCompteStartsWithIgnoreCase(String numeroComptePartiel);

    /**
     * Récupère le premier produit bancaire correspondant exactement au numéro de compte (insensible à la casse).
     *
     * @param numeroCompte numéro de compte exact
     * @return produit bancaire correspondant
     */
    ProduitBancaire findFirstByNumeroCompteIgnoreCase(String numeroCompte);
}
