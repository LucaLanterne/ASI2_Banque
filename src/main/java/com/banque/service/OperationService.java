package com.banque.service;

import com.banque.model.Operation;

import java.util.List;

public interface OperationService {
    public List<Operation> getAllOperations();
    public Operation createOperation(Operation operation);
    public Operation getOperationById(Long id);
    public Operation updateOperation(Operation operation);
    public void deleteOperationById(Long id);
    public List<Operation> get5DerniereOperationsByProduitBancaireId(Long idProduitBancaire);
}
