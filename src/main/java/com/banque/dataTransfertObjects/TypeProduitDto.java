package com.banque.dataTransfertObjects;

import java.util.Objects;

/**
 * Data Transfer Object représentant un type de produit bancaire.
 *
 * Contient les informations spécifiques d'un type de produit, telles que :
 * - cotisation de la carte
 * - taux d'intérêt ou agios
 * - intitulé du produit
 */
public class TypeProduitDto {

    /** Cotisation annuelle ou mensuelle de la carte bancaire associée */
    private float cotisationCarte;

    /** Taux d'intérêt ou d'agios appliqué au produit bancaire */
    private float tauxInteretAgios;

    /** Intitulé du produit (ex : "Compte courant", "Livret A") */
    private String intitule;

    /**
     * Constructeur par défaut.
     */
    public TypeProduitDto() {}

    /**
     * Constructeur avec initialisation complète.
     *
     * @param cotisationCarte cotisation de la carte bancaire
     * @param tauxInteretAgios taux d'intérêt ou agios
     * @param intitule intitulé du produit
     */
    public TypeProduitDto(float cotisationCarte, float tauxInteretAgios, String intitule) {
        this.cotisationCarte = cotisationCarte;
        this.tauxInteretAgios = tauxInteretAgios;
        this.intitule = intitule;
    }

    /**
     * Retourne la cotisation de la carte bancaire.
     *
     * @return cotisation de la carte
     */
    public float getCotisationCarte() {
        return cotisationCarte;
    }

    /**
     * Définit la cotisation de la carte bancaire.
     *
     * @param cotisationCarte cotisation de la carte
     */
    public void setCotisationCarte(float cotisationCarte) {
        this.cotisationCarte = cotisationCarte;
    }

    /**
     * Retourne le taux d'intérêt ou agios.
     *
     * @return taux d'intérêt ou agios
     */
    public float getTauxInteretAgios() {
        return tauxInteretAgios;
    }

    /**
     * Définit le taux d'intérêt ou agios.
     *
     * @param tauxInteretAgios taux d'intérêt ou agios
     */
    public void setTauxInteretAgios(float tauxInteretAgios) {
        this.tauxInteretAgios = tauxInteretAgios;
    }

    /**
     * Retourne l'intitulé du produit.
     *
     * @return intitulé
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Définit l'intitulé du produit.
     *
     * @param intitule intitulé du produit
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    // ------------------ Méthodes Object ------------------

    /**
     * Retourne une représentation textuelle de l'objet.
     *
     * @return chaîne descriptive du produit
     */
    @Override
    public String toString() {
        return "TypeProduitDto{" +
                "cotisationCarte=" + cotisationCarte +
                ", tauxInteretAgios=" + tauxInteretAgios +
                ", intitule='" + intitule + '\'' +
                '}';
    }

    /**
     * Vérifie l'égalité entre deux objets {@link TypeProduitDto}.
     *
     * @param o objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TypeProduitDto that = (TypeProduitDto) o;
        return Float.compare(cotisationCarte, that.cotisationCarte) == 0
                && Float.compare(tauxInteretAgios, that.tauxInteretAgios) == 0
                && Objects.equals(intitule, that.intitule);
    }

    /**
     * Retourne le code de hachage de l'objet.
     *
     * @return hashCode basé sur les champs
     */
    @Override
    public int hashCode() {
        return Objects.hash(cotisationCarte, tauxInteretAgios, intitule);
    }
}
