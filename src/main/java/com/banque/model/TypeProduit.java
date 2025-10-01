package com.banque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Entité représentant le type d'un produit bancaire.
 *
 * <p>
 * Un {@link TypeProduit} peut être associé à plusieurs {@link ProduitBancaire}.
 * </p>
 */
@Entity(name = "TypeProduit")
@Table(name = "type_produit")
public class TypeProduit {

    /** Identifiant unique du type de produit */
    @Id
    @SequenceGenerator(
            name = "typeproduit_sequence",
            sequenceName = "typeproduit_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "typeproduit_sequence")
    @Column(name = "id")
    private Long id;

    /** Taux d'intérêt appliqué aux agios */
    @Column(name = "tx_interet_agios", nullable = true, columnDefinition = "FLOAT")
    private float tauxInteretAgios;

    /** Intitulé du type de produit */
    @Column(name = "intitule", nullable = false, columnDefinition = "TEXT")
    private String intitule;

    /** Cotisation de la carte associée au produit */
    @Column(name = "cotisation_carte", nullable = true, columnDefinition = "FLOAT")
    private float cotisationCarte;

    /** Liste des produits bancaires associés à ce type */
    @OneToMany(mappedBy = "typeProduit", fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnore
    private List<ProduitBancaire> produitsBancaires = new ArrayList<>();

    /** Constructeur par défaut */
    public TypeProduit() {}

    /**
     * Constructeur complet.
     *
     * @param tauxInteretAgios taux d'intérêt des agios
     * @param intitule nom du type de produit
     * @param cotisationCarte cotisation de la carte associée
     */
    public TypeProduit(float tauxInteretAgios, String intitule, float cotisationCarte) {
        this.tauxInteretAgios = tauxInteretAgios;
        this.intitule = intitule;
        this.cotisationCarte = cotisationCarte;
    }

    // ------------------- Getters & Setters -------------------

    /**
     * Récupère l'identifiant unique du type de produit.
     *
     * @return identifiant du type de produit
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du type de produit.
     *
     * @param id identifiant du type de produit
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le taux d'intérêt appliqué aux agios.
     *
     * @return taux d'intérêt des agios
     */
    public float getTauxInteretAgios() {
        return tauxInteretAgios;
    }

    /**
     * Définit le taux d'intérêt appliqué aux agios.
     *
     * @param tauxInteretAgios taux d'intérêt des agios
     */
    public void setTauxInteretAgios(float tauxInteretAgios) {
        this.tauxInteretAgios = tauxInteretAgios;
    }

    /**
     * Récupère l'intitulé du type de produit.
     *
     * @return intitulé du type de produit
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Définit l'intitulé du type de produit.
     *
     * @param intitule intitulé du type de produit
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * Récupère la cotisation de la carte associée à ce type de produit.
     *
     * @return cotisation de la carte
     */
    public float getCotisationCarte() {
        return cotisationCarte;
    }

    /**
     * Définit la cotisation de la carte associée à ce type de produit.
     *
     * @param cotisationCarte cotisation de la carte
     */
    public void setCotisationCarte(float cotisationCarte) {
        this.cotisationCarte = cotisationCarte;
    }

    /**
     * Récupère la liste des produits bancaires liés à ce type de produit.
     *
     * @return liste des produits bancaires
     */
    public List<ProduitBancaire> getProduitsBancaires() {
        return produitsBancaires;
    }

    /**
     * Définit la liste des produits bancaires liés à ce type de produit.
     *
     * @param produitsBancaires liste des produits bancaires
     */
    public void setProduitsBancaires(List<ProduitBancaire> produitsBancaires) {
        this.produitsBancaires = produitsBancaires;
    }

    /**
     * Ajoute un produit bancaire à la liste des produits liés à ce type de produit.
     *
     * @param produitBancaire produit bancaire à ajouter
     */
    public void addProduitBancaire(ProduitBancaire produitBancaire) {
        this.produitsBancaires.add(produitBancaire);
    }

    /**
     * Supprime un produit bancaire de la liste des produits liés à ce type de produit
     * et casse le lien du produit vers ce type.
     *
     * @param produitBancaire produit bancaire à supprimer
     */
    public void removeProduitBancaire(ProduitBancaire produitBancaire) {
        this.produitsBancaires.remove(produitBancaire);
        produitBancaire.setTypeProduit(null);
    }

    // ------------------- Gestion des liens -------------------

    /**
     * Méthode appelée avant suppression pour casser les liens avec les produits bancaires.
     * Utile lorsque la cardinalité minimale est 0.
     */
    @PreRemove
    private void gererLiens() {
        for (ProduitBancaire pb : produitsBancaires) {
            pb.setTypeProduit(null);
        }
        produitsBancaires.clear();
    }

    // ------------------- Méthodes utilitaires -------------------

    @Override
    public String toString() {
        return "TypeProduit{" +
                "id=" + id +
                ", tauxInteretAgios=" + tauxInteretAgios +
                ", intitule='" + intitule + '\'' +
                ", cotisationCarte=" + cotisationCarte +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TypeProduit that = (TypeProduit) o;
        return Float.compare(tauxInteretAgios, that.tauxInteretAgios) == 0
                && Float.compare(cotisationCarte, that.cotisationCarte) == 0
                && Objects.equals(intitule, that.intitule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tauxInteretAgios, intitule, cotisationCarte);
    }
}
