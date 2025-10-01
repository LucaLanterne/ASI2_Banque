package com.banque.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Entité représentant une opération bancaire (ex : retrait, dépôt, virement).
 *
 * <p>
 * Une opération est liée à un {@link ProduitBancaire} via une relation ManyToOne.
 * </p>
 */
@Entity(name = "Operation")
@Table(name = "operation")
public class Operation {

    /** Identifiant unique de l’opération */
    @Id
    @SequenceGenerator(
            name = "operation_sequence",
            sequenceName = "operation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "operation_sequence")
    @Column(name = "id")
    private Long id;

    /** Date de l’opération */
    @Column(name = "date_operation", nullable = false, columnDefinition = "DATE")
    private Date dateOperation;

    /** Montant de l’opération */
    @Column(name = "montant", nullable = false, columnDefinition = "FLOAT")
    private float montant;

    /** Type de l’opération (exemple : retrait, dépôt) */
    @Column(name = "type", columnDefinition = "TEXT")
    private String type;

    /** Libellé ou description de l’opération */
    @Column(name = "libelle", columnDefinition = "TEXT")
    private String libelle;

    /** Produit bancaire auquel appartient l’opération */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_bancaire_id")
    private ProduitBancaire produitBancaire;

    /** Constructeur par défaut */
    public Operation() {}

    /**
     * Constructeur avec paramètres principaux.
     *
     * @param dateOperation date de l’opération
     * @param montant montant de l’opération
     * @param type type de l’opération
     * @param libelle libellé de l’opération
     */
    public Operation(Date dateOperation, float montant, String type, String libelle) {
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.type = type;
        this.libelle = libelle;
    }

    // ------------------- Getters & Setters -------------------

    /**
     * Récupère l'identifiant unique de l'opération.
     *
     * @return identifiant unique de l’opération
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique de l'opération.
     *
     * @param id identifiant unique de l’opération
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère la date de l'opération.
     *
     * @return date de l’opération
     */
    public Date getDateOperation() {
        return dateOperation;
    }

    /**
     * Définit la date de l'opération.
     *
     * @param dateOperation date de l’opération
     */
    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    /**
     * Récupère le montant de l'opération.
     *
     * @return montant de l’opération
     */
    public float getMontant() {
        return montant;
    }

    /**
     * Définit le montant de l'opération.
     *
     * @param montant montant de l’opération
     */
    public void setMontant(float montant) {
        this.montant = montant;
    }

    /**
     * Récupère le type de l'opération.
     *
     * @return type de l’opération
     */
    public String getType() {
        return type;
    }

    /**
     * Définit le type de l'opération.
     *
     * @param type type de l’opération
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Récupère le libellé de l'opération.
     *
     * @return libellé de l’opération
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit le libellé de l'opération.
     *
     * @param libelle libellé de l’opération
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Récupère le produit bancaire associée à l'opération.
     *
     * @return produit bancaire lié à l’opération
     */
    public ProduitBancaire getProduitBancaire() {
        return produitBancaire;
    }

    /**
     * Définit le produit bancaire associée à l'opération.
     *
     * @param produitBancaire produit bancaire lié à l’opération
     */
    public void setProduitBancaire(ProduitBancaire produitBancaire) {
        this.produitBancaire = produitBancaire;
    }

    // ------------------- Gestion des liens -------------------

    /**
     * Méthode appelée automatiquement avant la suppression d’une opération.
     * Supprime proprement la référence à son produit bancaire
     * pour éviter les incohérences dans la relation.
     */
    @PreRemove
    private void gererLiens() {
        if (produitBancaire != null) {
            produitBancaire.getOperations().remove(this);
        }
        produitBancaire = null;
    }

    // ------------------- Méthodes utilitaires -------------------

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
        return Float.compare(montant, operation.montant) == 0
                && Objects.equals(id, operation.id)
                && Objects.equals(dateOperation, operation.dateOperation)
                && Objects.equals(type, operation.type)
                && Objects.equals(libelle, operation.libelle)
                && Objects.equals(produitBancaire, operation.produitBancaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
