package com.banque.service;

import com.banque.model.PersonnePhysique;

import java.util.List;

/**
 * Service pour gérer les personnes physiques.
 * Définit les méthodes CRUD pour créer, récupérer, mettre à jour et supprimer des personnes physiques.
 */
public interface PersonnePhysiqueService {

    /**
     * Récupère toutes les personnes physiques.
     *
     * @return liste de toutes les personnes physiques
     */
    List<PersonnePhysique> getAllPersonnesPhysiques();

    /**
     * Crée une nouvelle personne physique.
     *
     * @param personnePhysique personne physique à créer
     * @return personne physique créée
     */
    PersonnePhysique createPersonnePhysique(PersonnePhysique personnePhysique);

    /**
     * Récupère une personne physique par son ID.
     *
     * @param id identifiant de la personne physique
     * @return personne physique correspondante
     */
    PersonnePhysique getPersonnePhysiqueById(Long id);

    /**
     * Met à jour une personne physique existante.
     *
     * @param personnePhysique personne physique à mettre à jour
     * @return personne physique mise à jour
     */
    PersonnePhysique updatePersonnePhysique(PersonnePhysique personnePhysique);

    /**
     * Supprime une personne physique par son ID.
     *
     * @param id identifiant de la personne physique à supprimer
     */
    void deletePersonnePhysiqueById(Long id);
}
