package com.banque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banque.model.TypePersonneMorale;

import java.util.ArrayList;

public interface TypePersonneMoraleRepository extends JpaRepository<TypePersonneMorale, Long> {
    ArrayList<TypePersonneMorale> findByIntitule(String intitule);
    ArrayList<TypePersonneMorale> findByIntituleLike(String typePersonneMorale);
}