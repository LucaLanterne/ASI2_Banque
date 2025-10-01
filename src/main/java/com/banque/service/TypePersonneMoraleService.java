package com.banque.service;

import com.banque.model.TypePersonneMorale;

import java.util.List;

/**
 * Service pour gérer les types de personnes morales.
 * Définit les méthodes CRUD pour les types de personnes morales.
 */
public interface TypePersonneMoraleService {

    /**
     * Récupère tous les types de personnes morales.
     *
     * @return liste des types de personnes morales
     */
    List<TypePersonneMorale> getAllTypesPersonnesMorales();

    /**
     * Crée un nouveau type de personne morale.
     *
     * @param typePersonneMorale type de personne morale à créer
     * @return type de personne morale créé
     */
    TypePersonneMorale createTypePersonneMorale(TypePersonneMorale typePersonneMorale);

    /**
     * Récupère un type de personne morale par son ID.
     *
     * @param id identifiant du type de personne morale
     * @return type de personne morale correspondant
     */
    TypePersonneMorale getTypePersonneMoraleById(Long id);

    /**
     * Met à jour un type de personne morale existant.
     *
     * @param typePersonneMorale type de personne morale à mettre à jour
     * @return type de personne morale mis à jour
     */
    TypePersonneMorale updateTypePersonneMorale(TypePersonneMorale typePersonneMorale);

    /**
     * Supprime un type de personne morale par son ID.
     *
     * @param id identifiant du type de personne morale à supprimer
     */
    void deleteTypePersonneMoraleById(Long id);
}
