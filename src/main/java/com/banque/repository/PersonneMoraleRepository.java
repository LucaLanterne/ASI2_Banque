package com.banque.repository;

import com.banque.model.PersonneMorale;

/**
 * Repository JPA pour l'entité {@link PersonneMorale}.
 * Permet l'accès aux données des personnes morales.
 */
public interface PersonneMoraleRepository extends PersonneRepository<PersonneMorale> {

    /**
     * Recherche une personne morale à partir de sa raison sociale.
     *
     * @param raisonSociale raison sociale de la personne morale
     * @return la personne morale correspondante, ou null si elle n'existe pas
     */
    PersonneMorale findByRaisonSociale(String raisonSociale);
}
