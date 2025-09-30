package com.banque.repository;

import com.banque.model.ProduitBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitBancaireRepository extends JpaRepository<ProduitBancaire, Long> {
    public ProduitBancaire findByIdAndTypeProduitIntitule(Long id, String typeProduitIntitule);
    public List<ProduitBancaire> findByNumeroCompteStartsWithIgnoreCase(String numeroComptePartiel);
    public ProduitBancaire findFirstByNumeroCompteIgnoreCase(String numeroCompte);
}
