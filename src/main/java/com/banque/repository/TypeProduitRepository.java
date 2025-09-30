package com.banque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banque.model.TypeProduit;

import java.lang.reflect.Type;
import java.util.ArrayList;

public interface TypeProduitRepository extends JpaRepository<TypeProduit, Long> {
    TypeProduit findByIntitule(String intitule);
    ArrayList<TypeProduit> findByIntituleLike(String typeProduit);
    ArrayList<TypeProduit> findByIntituleContains(String typeProduit);
    ArrayList<TypeProduit> findFirst3ByOrderByIdDesc();
    ArrayList<TypeProduit> findByTauxInteretAgiosGreaterThanEqualOrderByTauxInteretAgiosAsc(float taux);
}