package com.banque.service;

import com.banque.model.PersonneMorale;

import java.util.List;

/**
 * Service pour gérer les personnes morales.
 * Définit les méthodes CRUD pour créer, récupérer, mettre à jour et supprimer des personnes morales.
 */
public interface PersonneMoraleService {

    /**
     * Récupère toutes les personnes morales.
     *
     * @return liste de toutes les personnes morales
     */
    List<PersonneMorale> getAllPersonnesMorales();

    /**
     * Crée une nouvelle personne morale.
     *
     * @param personneMorale personne morale à créer
     * @return personne morale créée
     */
    PersonneMorale createPersonneMorale(PersonneMorale personneMorale);

    /**
     * Récupère une personne morale par son ID.
     *
     * @param id identifiant de la personne morale
     * @return personne morale correspondante
     */
    PersonneMorale getPersonneMoraleById(Long id);

    /**
     * Met à jour une personne morale existante.
     *
     * @param personneMorale personne morale à mettre à jour
     * @return personne morale mise à jour
     */
    PersonneMorale updatePersonneMorale(PersonneMorale personneMorale);

    /**
     * Supprime une personne morale par son ID.
     *
     * @param id identifiant de la personne morale à supprimer
     */
    void deletePersonneMoraleById(Long id);
}
