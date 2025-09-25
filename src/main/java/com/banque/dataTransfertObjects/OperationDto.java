package com.banque.dataTransfertObjects;

import java.sql.Date;

public class OperationDto {

    private String libelle;
    private float montant;
    private Date dateOperation;
    private String type;

    public OperationDto() {}
    public OperationDto(String libelle, float montant, Date dateOperation, String type) {
        this.libelle = libelle;
        this.montant = montant;
        this.dateOperation = dateOperation;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
