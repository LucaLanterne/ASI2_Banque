package com.banque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banque.model.TypePersonneMorale;

import java.util.ArrayList;

/**
 * Repository JPA pour gérer les entités {@link TypePersonneMorale}.
 * Fournit des méthodes pour rechercher des types de personnes morales via des filtres.
 */
public interface TypePersonneMoraleRepository extends JpaRepository<TypePersonneMorale, Long> {

    /**
     * Recherche les types de personnes morales dont l'intitulé correspond exactement à la chaîne donnée.
     *
     * @param intitule intitulé exact du type de personne morale
     * @return liste des types de personnes morales correspondants
     */
    ArrayList<TypePersonneMorale> findByIntitule(String intitule);

    /**
     * Recherche les types de personnes morales dont l'intitulé contient la chaîne donnée (LIKE SQL).
     *
     * @param typePersonneMorale chaîne partielle à rechercher dans l'intitulé
     * @return liste des types de personnes morales correspondants
     */
    ArrayList<TypePersonneMorale> findByIntituleLike(String typePersonneMorale);
}
