package com.banque.dataTransfertObjects;

import java.sql.Date;

/**
 * Data Transfer Object représentant une opération bancaire.
 *
 * Contient les informations d'une opération : libellé, montant, date et type.
 */
public class OperationDto {

    /** Libellé de l'opération */
    private String libelle;

    /** Montant de l'opération */
    private float montant;

    /** Date de l'opération */
    private Date dateOperation;

    /** Type de l'opération */
    private String type;

    /**
     * Constructeur par défaut.
     */
    public OperationDto() {}

    /**
     * Constructeur avec initialisation complète.
     *
     * @param libelle libellé de l'opération
     * @param montant montant de l'opération
     * @param dateOperation date de l'opération
     * @param type type de l'opération
     */
    public OperationDto(String libelle, float montant, Date dateOperation, String type) {
        this.libelle = libelle;
        this.montant = montant;
        this.dateOperation = dateOperation;
        this.type = type;
    }

    // ------------------ Getters & Setters ------------------

    /**
     * Retourne le type de l'opération.
     *
     * @return type de l'opération
     */
    public String getType() {
        return type;
    }

    /**
     * Définit le type de l'opération.
     *
     * @param type type de l'opération
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retourne la date de l'opération.
     *
     * @return date de l'opération
     */
    public Date getDateOperation() {
        return dateOperation;
    }

    /**
     * Définit la date de l'opération.
     *
     * @param dateOperation date de l'opération
     */
    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    /**
     * Retourne le montant de l'opération.
     *
     * @return montant
     */
    public float getMontant() {
        return montant;
    }

    /**
     * Définit le montant de l'opération.
     *
     * @param montant montant de l'opération
     */
    public void setMontant(float montant) {
        this.montant = montant;
    }

    /**
     * Retourne le libellé de l'opération.
     *
     * @return libellé
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit le libellé de l'opération.
     *
     * @param libelle libellé de l'opération
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
