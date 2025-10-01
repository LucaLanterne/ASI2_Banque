package com.banque.service;

import com.banque.model.TypeProduit;
import java.util.List;

/**
 * Service pour gérer les types de produits bancaires.
 * Définit les méthodes CRUD pour les types de produits.
 */
public interface TypeProduitService {

    /**
     * Récupère tous les types de produits bancaires.
     *
     * @return liste des types de produits
     */
    List<TypeProduit> getAllTypesProduits();

    /**
     * Crée un nouveau type de produit bancaire.
     *
     * @param typeProduit type de produit à créer
     * @return type de produit créé
     */
    TypeProduit createTypeProduit(TypeProduit typeProduit);

    /**
     * Récupère un type de produit bancaire par son ID.
     *
     * @param id identifiant du type de produit
     * @return type de produit correspondant
     */
    TypeProduit getTypeProduitById(long id);

    /**
     * Met à jour un type de produit bancaire existant.
     *
     * @param typeProduit type de produit à mettre à jour
     * @return type de produit mis à jour
     */
    TypeProduit updateTypeProduit(TypeProduit typeProduit);

    /**
     * Supprime un type de produit bancaire par son ID.
     *
     * @param id identifiant du type de produit à supprimer
     */
    void deleteTypeProduitById(long id);
}
