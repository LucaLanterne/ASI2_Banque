package com.banque.service.impl;

import com.banque.model.Operation;
import com.banque.repository.OperationRepository;
import com.banque.service.OperationService;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation createOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public Operation getOperationById(Long id) {
        return operationRepository.findById(id).orElseThrow();
    }

    @Override
    public Operation updateOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public void deleteOperationById(Long id) {
        operationRepository.findById(id).orElseThrow();
        operationRepository.deleteById(id);
    }
}
