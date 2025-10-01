package com.banque.dataTransfertObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object représentant un client bancaire.
 *
 * Ce DTO contient une liste de {@link PersonneDto} associées à ce client.
 */
public class ClientBancaireDto {

    /**
     * Liste des personnes liées à ce client bancaire.
     */
    private List<PersonneDto> personneDtos;

    /**
     * Constructeur par défaut.
     * Initialise la liste des personnes à une liste vide pour éviter les NullPointerException.
     */
    public ClientBancaireDto() {
        this.personneDtos = new ArrayList<>();
    }

    /**
     * Constructeur avec initialisation de la liste de personnes.
     * Si la liste passée est null, elle sera initialisée à une liste vide.
     *
     * @param personneDtos liste initiale de {@link PersonneDto}
     */
    public ClientBancaireDto(List<PersonneDto> personneDtos) {
        this.personneDtos = (personneDtos != null) ? personneDtos : new ArrayList<>();
    }

    // ------------------- Getters & Setters -------------------

    /**
     * Retourne la liste des personnes associées à ce client bancaire.
     *
     * @return liste de {@link PersonneDto}
     */
    public List<PersonneDto> getPersonneDtos() {
        return personneDtos;
    }

    /**
     * Ajoute une personne à la liste du client.
     *
     * @param personneDto personne à ajouter
     */
    public void addPersonneDto(PersonneDto personneDto) {
        this.personneDtos.add(personneDto);
    }

    /**
     * Supprime une personne de la liste du client.
     *
     * @param personneDto personne à supprimer
     */
    public void removePersonneDto(PersonneDto personneDto) {
        this.personneDtos.remove(personneDto);
    }
}
