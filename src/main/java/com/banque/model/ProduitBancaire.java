package com.banque.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Entité représentant un produit bancaire (compte, livret, etc.).
 *
 * <p>
 * Un {@link ProduitBancaire} est lié à :
 * - un {@link TypeProduit} qui définit les caractéristiques du produit,
 * - un {@link ClientBancaire} propriétaire du produit,
 * - une liste d'{@link Operation} représentant les mouvements sur le produit.
 * </p>
 */
@Entity
public class ProduitBancaire {

    /** Identifiant unique du produit bancaire */
    @Id
    @SequenceGenerator(
            name = "produit_bancaire_sequence",
            sequenceName = "produit_bancaire_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "produit_bancaire_sequence")
    @Column(name = "id")
    protected Long id;

    /** Solde courant du produit bancaire */
    @Column(name = "solde_courant", nullable = false, columnDefinition = "FLOAT")
    private float solde_courant;

    /** Numéro du compte bancaire */
    @Column(name = "numero_compte", nullable = false, columnDefinition = "TEXT")
    private String numeroCompte;

    /** Type du produit bancaire */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_produit_id")
    private TypeProduit typeProduit;

    /** Liste des opérations liées au produit bancaire */
    @OneToMany(mappedBy = "produitBancaire", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Operation> operations = new ArrayList<>();

    /** Client propriétaire du produit bancaire */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_bancaire_id")
    private ClientBancaire clientBancaire;

    /** Constructeur par défaut */
    public ProduitBancaire() {}

    /**
     * Constructeur complet.
     *
     * @param solde_courant solde initial du produit
     * @param numeroCompte numéro du compte
     * @param typeProduit type du produit bancaire
     * @param clientBancaire client propriétaire
     */
    public ProduitBancaire(float solde_courant, String numeroCompte, TypeProduit typeProduit, ClientBancaire clientBancaire) {
        this.solde_courant = solde_courant;
        this.numeroCompte = numeroCompte;
        this.typeProduit = typeProduit;
        typeProduit.getProduitsBancaires().add(this);
        this.clientBancaire = clientBancaire;
    }

    // ------------------- Getters & Setters -------------------

    /**
     * Récupère l'identifiant du produit bancaire.
     *
     * @return identifiant unique
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du produit bancaire.
     *
     * @param id identifiant unique
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le solde courant du produit bancaire.
     *
     * @return solde courant
     */
    public float getSolde_courant() {
        return solde_courant;
    }

    /**
     * Définit le solde courant du produit bancaire.
     *
     * @param solde_courant solde à définir
     */
    public void setSolde_courant(float solde_courant) {
        this.solde_courant = solde_courant;
    }

    /**
     * Récupère le numéro du compte.
     *
     * @return numéro du compte bancaire
     */
    public String getNumeroCompte() {
        return numeroCompte;
    }

    /**
     * Définit le numéro du compte.
     *
     * @param numeroCompte numéro du compte bancaire
     */
    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    /**
     * Récupère le type de produit bancaire.
     *
     * @return type de produit
     */
    public TypeProduit getTypeProduit() {
        return typeProduit;
    }

    /**
     * Définit le type de produit bancaire.
     *
     * @param typeProduit type de produit
     */
    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    /**
     * Récupère le client propriétaire du produit.
     *
     * @return client bancaire
     */
    public ClientBancaire getClientBancaire() {
        return clientBancaire;
    }

    /**
     * Définit le client propriétaire du produit.
     *
     * @param clientBancaire client bancaire
     */
    public void setClientBancaire(ClientBancaire clientBancaire) {
        this.clientBancaire = clientBancaire;
    }

    /**
     * Récupère la liste des opérations du produit bancaire.
     *
     * @return liste des opérations
     */
    public List<Operation> getOperations() {
        return operations;
    }

    /**
     * Définit la liste des opérations du produit bancaire.
     *
     * @param operations liste des opérations
     */
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    /**
     * Ajoute une opération à la liste des opérations.
     *
     * @param operation opération à ajouter
     */
    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    /**
     * Supprime une opération de la liste des opérations et casse le lien vers ce produit.
     *
     * @param operation opération à supprimer
     */
    public void removeOperation(Operation operation) {
        this.operations.remove(operation);
        operation.setProduitBancaire(null);
    }

    // ------------------- Gestion des liens -------------------

    /**
     * Méthode appelée avant suppression pour gérer les liens avec le {@link TypeProduit}.
     * Supprime le produit de la liste des produits du type et casse le lien.
     */
    @PreRemove
    private void gererLiens() {
        if (typeProduit != null) {
            typeProduit.getProduitsBancaires().remove(this);
        }
        typeProduit = null;
    }

    // ------------------- Méthodes utilitaires -------------------

    @Override
    public String toString() {
        return "ProduitBancaire{" +
                "id=" + id +
                ", solde_courant=" + solde_courant +
                ", numeroCompte='" + numeroCompte + '\'' +
                ", typeProduit=" + typeProduit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProduitBancaire that = (ProduitBancaire) o;
        return Float.compare(solde_courant, that.solde_courant) == 0
                && Objects.equals(id, that.id)
                && Objects.equals(numeroCompte, that.numeroCompte)
                && Objects.equals(typeProduit, that.typeProduit)
                && Objects.equals(operations, that.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
