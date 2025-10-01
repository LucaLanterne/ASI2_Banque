package com.banque.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entité représentant une personne morale (ex : entreprise).
 *
 * <p>
 * Hérite de {@link Personne}. Une {@link PersonneMorale} a :
 * - un SIRET,
 * - une raison sociale,
 * - un {@link TypePersonneMorale} qui la catégorise.
 * </p>
 *
 * <p>
 * Elle est associée à plusieurs {@link ClientBancaire} via la relation
 * héritée de {@link Personne}.
 * </p>
 */
@Entity(name = "PersonneMorale")
@Table(name = "personne_morale")
public class PersonneMorale extends Personne {

    /** Numéro SIRET de la personne morale */
    @Column(name = "siret", columnDefinition = "TEXT")
    private String SIRET;

    /** Raison sociale (nom officiel) de la personne morale */
    @Column(name = "raison_sociale", nullable = false, columnDefinition = "TEXT")
    private String raisonSociale;

    /** Type de la personne morale */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_personne_morale_id")
    private TypePersonneMorale typePersonneMorale;

    /** Constructeur par défaut */
    public PersonneMorale() {}

    /**
     * Constructeur complet.
     *
     * @param adresse adresse de la personne morale
     * @param SIRET numéro SIRET
     * @param raisonSociale raison sociale
     * @param typePersonneMorale type de la personne morale
     */
    public PersonneMorale(String adresse, String SIRET, String raisonSociale, TypePersonneMorale typePersonneMorale) {
        super(adresse);
        this.SIRET = SIRET;
        this.raisonSociale = raisonSociale;
        this.typePersonneMorale = typePersonneMorale;
        typePersonneMorale.getPersonnesMorales().add(this);
    }

    /**
     * Retourne le nom complet de la personne morale (raison sociale).
     *
     * @return raison sociale
     */
    @Override
    public String nomComplet() {
        return raisonSociale;
    }

    // ------------------- Getters & Setters -------------------

    /**
     * Récupère le type de la personne morale.
     *
     * @return type de la personne morale
     */
    public TypePersonneMorale getTypePersonneMorale() {
        return typePersonneMorale;
    }

    /**
     * Définit le type de la personne morale.
     *
     * @param typePersonneMorale type de la personne morale
     */
    public void setTypePersonneMorale(TypePersonneMorale typePersonneMorale) {
        this.typePersonneMorale = typePersonneMorale;
    }

    /**
     * Récupère le numéro SIRET de la personne morale.
     *
     * @return SIRET de la personne morale
     */
    public String getSIRET() {
        return SIRET;
    }

    /**
     * Définit le numéro SIRET de la personne morale.
     *
     * @param SIRET numéro SIRET de la personne morale
     */
    public void setSIRET(String SIRET) {
        this.SIRET = SIRET;
    }

    /**
     * Récupère la raison sociale de la personne morale.
     *
     * @return raison sociale
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Définit la raison sociale de la personne morale.
     *
     * @param raisonSociale raison sociale de la personne morale
     */
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    // ------------------- Gestion des liens -------------------

    /**
     * Méthode appelée automatiquement avant la suppression d’une personne morale.
     * Supprime la personne morale de la liste de son {@link TypePersonneMorale}
     * et casse le lien pour éviter les incohérences.
     */
    @PreRemove
    private void gererLiensPM() {
        if (typePersonneMorale != null) {
            typePersonneMorale.getPersonnesMorales().remove(this);
        }
        typePersonneMorale = null;
    }

    // ------------------- Méthodes utilitaires -------------------

    @Override
    public String toString() {
        return "\nPersonneMorale{" +
                "\nid=" + id +
                ", \nSIRET='" + SIRET + '\'' +
                ", \nraisonSociale='" + raisonSociale + '\'' +
                ", \nadresse='" + adresse + '\'' +
                ", \ntypePersonneMorale=" + typePersonneMorale + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonneMorale that = (PersonneMorale) o;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
