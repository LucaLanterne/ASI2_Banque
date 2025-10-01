package com.banque.dataTransfertObjects;

/**
 * Data Transfer Object représentant une personne.
 *
 * Contient les informations de base d'une personne : nom, adresse et type.
 */
public class PersonneDto {

    /** Nom de la personne */
    protected String nom;

    /** Adresse de la personne */
    protected String adresse;

    /** Type de la personne (ex : "Client", "Employé") */
    protected String typePersonne;

    /**
     * Constructeur par défaut.
     */
    public PersonneDto() {}

    /**
     * Constructeur avec initialisation du nom seulement.
     *
     * @param nom nom de la personne
     */
    public PersonneDto(String nom) {
        this.nom = nom;
    }

    /**
     * Constructeur avec initialisation complète.
     *
     * @param nom nom de la personne
     * @param adresse adresse de la personne
     * @param typePersonne type de la personne
     */
    public PersonneDto(String nom, String adresse, String typePersonne) {
        this.nom = nom;
        this.adresse = adresse;
        this.typePersonne = typePersonne;
    }

    /**
     * Méthode d'initialisation protégée (implémentation vide par défaut).
     *
     * @param nom nom à initialiser
     */
    protected void init(String nom) {}

    // ------------------- Getters & Setters -------------------

    /**
     * Retourne le nom de la personne.
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la personne.
     *
     * @param nom nom de la personne
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'adresse de la personne.
     *
     * @return adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse de la personne.
     *
     * @param adresse adresse de la personne
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Retourne le type de la personne.
     *
     * @return typePersonne
     */
    public String getTypePersonne() {
        return typePersonne;
    }

    /**
     * Définit le type de la personne.
     *
     * @param typePersonne type de la personne
     */
    public void setTypePersonne(String typePersonne) {
        this.typePersonne = typePersonne;
    }

    /**
     * Retourne une représentation en chaîne de caractères de l'objet PersonneDto.
     *
     * @return représentation textuelle de la personne
     */
    @Override
    public String toString() {
        return "PersonneDto{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", typePersonne='" + typePersonne + '\'' +
                '}';
    }
}
