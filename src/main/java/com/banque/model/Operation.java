package com.banque.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Operation")
@Table(name = "operation")
public class Operation {
    @Id
    @SequenceGenerator(name = "operation_sequence", sequenceName = "operation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "operation_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "date_operation", nullable = false, columnDefinition = "DATE")
    private Date dateOperation;

    @Column(name = "montant", nullable = false, columnDefinition = "FLOAT")
    private float montant;

    @Column(name = "type", nullable = true, columnDefinition = "TEXT")
    private String type;

    @Column(name = "libelle", nullable = true, columnDefinition = "TEXT")
    private String libelle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_bancaire_id")
    private ProduitBancaire produitBancaire;

    public Operation() {}

    public Operation(Date date_operation, float montant, String type, String libelle) {
        this.dateOperation = date_operation;
        this.montant = montant;
        this.type = type;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ProduitBancaire getProduitBancaire() {
        return produitBancaire;
    }

    public void setProduitBancaire(ProduitBancaire produitBancaire) {
        this.produitBancaire = produitBancaire;
    }

    @PreRemove
    private void gererLiens()
    {
        if (produitBancaire!=null)
        {
            produitBancaire.getOperations().remove(this);
        }
        produitBancaire=null;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", dateOperation=" + dateOperation +
                ", montant=" + montant +
                ", type='" + type + '\'' +
                ", libelle='" + libelle + '\'' +
                ", produitBancaire=" + produitBancaire +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Float.compare(montant, operation.montant) == 0 && Objects.equals(id, operation.id) && Objects.equals(dateOperation, operation.dateOperation) && Objects.equals(type, operation.type) && Objects.equals(libelle, operation.libelle) && Objects.equals(produitBancaire, operation.produitBancaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
