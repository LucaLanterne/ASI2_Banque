package com.banque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entité représentant une personne physique.
 *
 * <p>
 * Hérite de {@link Personne}. Une {@link PersonnePhysique} possède :
 * - un nom,
 * - un prénom,
 * et une adresse héritée de {@link Personne}.
 * </p>
 *
 * <p>
 * Elle peut être liée à plusieurs {@link ClientBancaire} via la relation
 * héritée de {@link Personne}.
 * </p>
 */
@Entity(name = "PersonnePhysique")
@Table(name = "personne_physique")
public class PersonnePhysique extends Personne {

    /** Nom de la personne physique */
    @Column(name = "nom", nullable = false, columnDefinition = "TEXT")
    private String nom;

    /** Prénom de la personne physique */
    @Column(name = "prenom", columnDefinition = "TEXT")
    private String prenom;

    /** Constructeur par défaut */
    public PersonnePhysique() {
        super();
    }

    /**
     * Constructeur avec paramètres principaux.
     *
     * @param adresse adresse de la personne
     * @param nom nom de famille
     * @param prenom prénom de la personne
     */
    public PersonnePhysique(String adresse, String nom, String prenom) {
        super(adresse);
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Retourne le nom complet de la personne physique (prénom + nom).
     *
     * @return nom complet
     */
    @Override
    public String nomComplet() {
        return prenom + " " + nom;
    }

    // ------------------- Getters & Setters -------------------

    /**
     * Récupère le nom de la personne physique.
     *
     * @return nom de la personne
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la personne physique.
     *
     * @param nom nom de la personne
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prénom de la personne physique.
     *
     * @return prénom de la personne
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de la personne physique.
     *
     * @param prenom prénom de la personne
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // ------------------- Méthodes utilitaires -------------------

    @Override
    public String toString() {
        return "PersonnePhysique{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
