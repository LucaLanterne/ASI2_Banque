package com.banque.repository;

import com.banque.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository JPA pour l'entité {@link Operation}.
 * Fournit un accès direct à la base de données pour les opérations bancaires.
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {

    /**
     * Récupère les 5 dernières opérations d'un produit bancaire donné.
     *
     * @param idProduitBancaire identifiant du produit bancaire
     * @return liste des 5 dernières opérations triées par date décroissante
     */
    List<Operation> findTop5ByProduitBancaireIdOrderByDateOperationDesc(Long idProduitBancaire);

    /**
     * Récupère toutes les opérations d'un produit bancaire donné, triées par date décroissante.
     *
     * @param produitBancaireId identifiant du produit bancaire
     * @return liste des opérations triées par date décroissante
     */
    List<Operation> findAllByProduitBancaireIdOrderByDateOperationDesc(Long produitBancaireId);
}
