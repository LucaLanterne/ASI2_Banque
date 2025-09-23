package com.banque.repository;

import com.banque.model.ClientBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientBancaireRepository extends JpaRepository<ClientBancaire, Long> {
}
