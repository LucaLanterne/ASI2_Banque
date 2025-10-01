package com.banque.service;

import com.banque.model.Operation;

import java.util.List;

/**
 * Service pour gérer les opérations bancaires.
 * Définit les méthodes CRUD et les recherches spécifiques sur les opérations.
 */
public interface OperationService {

    /**
     * Récupère toutes les opérations.
     *
     * @return liste de toutes les opérations
     */
    List<Operation> getAllOperations();

    /**
     * Crée une nouvelle opération.
     *
     * @param operation opération à créer
     * @return opération créée
     */
    Operation createOperation(Operation operation);

    /**
     * Récupère une opération par son ID.
     *
     * @param id identifiant de l'opération
     * @return opération correspondante
     */
    Operation getOperationById(Long id);

    /**
     * Met à jour une opération existante.
     *
     * @param operation opération à mettre à jour
     * @return opération mise à jour
     */
    Operation updateOperation(Operation operation);

    /**
     * Supprime une opération par son ID.
     *
     * @param id identifiant de l'opération à supprimer
     */
    void deleteOperationById(Long id);

    /**
     * Récupère les 5 dernières opérations pour un produit bancaire donné.
     *
     * @param idProduitBancaire ID du produit bancaire
     * @return liste des 5 dernières opérations
     */
    List<Operation> get5DerniereOperationsByProduitBancaireId(Long idProduitBancaire);

    /**
     * Récupère toutes les opérations d'un produit bancaire triées par date décroissante.
     *
     * @param idProduitBancaire ID du produit bancaire
     * @return liste des opérations triées par date descendante
     */
    List<Operation> findAllByProduitBancaireIdOrderByDateOperationDesc(Long idProduitBancaire);
}
