package com.banque.service.impl;

import com.banque.model.Operation;
import com.banque.repository.OperationRepository;
import com.banque.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service pour gérer les opérations bancaires.
 * Fournit les opérations CRUD et des méthodes spécifiques pour récupérer les dernières opérations d'un produit bancaire.
 */
@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    /**
     * Récupère toutes les opérations bancaires.
     *
     * @return liste de toutes les opérations
     */
    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    /**
     * Crée une nouvelle opération.
     *
     * @param operation opération à créer
     * @return opération créée
     */
    @Override
    public Operation createOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    /**
     * Récupère une opération par son identifiant.
     *
     * @param id identifiant de l'opération
     * @return opération correspondante
     */
    @Override
    public Operation getOperationById(Long id) {
        return operationRepository.findById(id).orElseThrow();
    }

    /**
     * Met à jour une opération existante.
     *
     * @param operation opération à mettre à jour
     * @return opération mise à jour
     */
    @Override
    public Operation updateOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    /**
     * Supprime une opération par son identifiant.
     *
     * @param id identifiant de l'opération à supprimer
     */
    @Override
    public void deleteOperationById(Long id) {
        operationRepository.findById(id).orElseThrow();
        operationRepository.deleteById(id);
    }

    /**
     * Récupère les 5 dernières opérations d'un produit bancaire, triées par date décroissante.
     *
     * @param idProduitBancaire identifiant du produit bancaire
     * @return liste des 5 dernières opérations
     */
    @Override
    public List<Operation> get5DerniereOperationsByProduitBancaireId(Long idProduitBancaire) {
        return operationRepository.findTop5ByProduitBancaireIdOrderByDateOperationDesc(idProduitBancaire);
    }

    /**
     * Récupère toutes les opérations d'un produit bancaire, triées par date décroissante.
     *
     * @param idProduitBancaire identifiant du produit bancaire
     * @return liste de toutes les opérations
     */
    @Override
    public List<Operation> findAllByProduitBancaireIdOrderByDateOperationDesc(Long idProduitBancaire) {
        return operationRepository.findAllByProduitBancaireIdOrderByDateOperationDesc(idProduitBancaire);
    }
}
