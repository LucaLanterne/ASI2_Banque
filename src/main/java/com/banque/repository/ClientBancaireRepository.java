package com.banque.repository;

import com.banque.model.ClientBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository JPA pour l'entité {@link ClientBancaire}.
 * Fournit un accès direct à la base de données pour les clients bancaires.
 */
public interface ClientBancaireRepository extends JpaRepository<ClientBancaire, Long> {

}
