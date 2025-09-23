package com.banque.dataTransfertObjects;

import java.sql.Date;

public class OperationDto {

    private String intituleOperation;
    private float montant;
    private Date dateOperation;
    private String TypeOperation;

    public OperationDto() {}
    public OperationDto(String intituleOperation, float montant, Date dateOperation, String TypeOperation) {
        this.intituleOperation = intituleOperation;
        this.montant = montant;
        this.dateOperation = dateOperation;
        this.TypeOperation = TypeOperation;
    }

    public String getTypeOperation() {
        return TypeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        TypeOperation = typeOperation;
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

    public String getIntituleOperation() {
        return intituleOperation;
    }

    public void setIntituleOperation(String intituleOperation) {
        this.intituleOperation = intituleOperation;
    }
}
