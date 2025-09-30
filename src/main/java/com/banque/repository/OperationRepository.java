package com.banque.repository;

import com.banque.model.Operation;
import com.banque.model.ProduitBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findTop5ByProduitBancaireIdOrderByDateOperationDesc(Long idProduitBancaire);

    List<Operation> findAllByProduitBancaireIdOrderByDateOperationDesc(Long produitBancaireId);
}
