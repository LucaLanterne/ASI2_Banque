package com.banque.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Entité représentant un client bancaire.
 *
 * <p>
 * Un client bancaire est lié à :
 * - une ou plusieurs {@link Personne} (relation ManyToMany),
 * - un ou plusieurs {@link ProduitBancaire} (relation OneToMany).
 * </p>
 */
@Entity(name = "ClientBancaire")
@Table(name = "client_bancaire")
public class ClientBancaire {

    /** Identifiant unique du client bancaire */
    @Id
    @SequenceGenerator(
            name = "client_bancaire_sequence",
            sequenceName = "client_bancaire_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "client_bancaire_sequence")
    @Column(name = "id")
    protected Long id;

    /**
     * Liste des personnes associées au client bancaire.
     * Relation ManyToMany avec {@link Personne}.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "client_personne",
            joinColumns = { @JoinColumn(name = "client_bancaire_id") },
            inverseJoinColumns = { @JoinColumn(name = "personne_id") }
    )
    private List<Personne> personnes = new ArrayList<>();

    /**
     * Liste des produits bancaires du client.
     * Relation OneToMany avec {@link ProduitBancaire}.
     */
    @OneToMany(
            mappedBy = "clientBancaire",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<ProduitBancaire> produitBancaires = new ArrayList<>();

    /** Constructeur par défaut */
    public ClientBancaire() {}

    // ------------------- Getters & Setters -------------------

    /**
     * Récupère l'identifiant unique du client bancaire.
     *
     * @return identifiant unique du client
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du client bancaire.
     *
     * @param id identifiant unique du client bancaire
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère les produits bancaires associés au client bancaire.
     *
     * @return liste des produits bancaires du client bancaire
     */
    public List<ProduitBancaire> getProduitBancaires() {
        return produitBancaires;
    }

    /**
     * Définit les produits bancaires associés au client bancaire.
     *
     * @param produitBancaires liste des produits bancaires à associer
     */
    public void setProduitBancaires(List<ProduitBancaire> produitBancaires) {
        this.produitBancaires = produitBancaires;
    }

    /**
     * Ajoute un produit bancaire au client.
     *
     * @param produitBancaire produit bancaire à ajouter
     */
    public void addProduitBancaire(ProduitBancaire produitBancaire) {
        this.produitBancaires.add(produitBancaire);
    }

    /**
     * Supprime un produit bancaire du client.
     *
     * @param produitBancaire produit bancaire à supprimer
     */
    public void removeProduitBancaire(ProduitBancaire produitBancaire) {
        this.produitBancaires.remove(produitBancaire);
    }

    /**
     * Récupère la liste des personnes associées au client bancaire.
     *
     * @return liste des personnes associées
     */
    public List<Personne> getPersonnes() {
        return personnes;
    }

    /**
     * Définit la liste des personnes associées au client bancaire.
     *
     * @param personnes liste des personnes à associer
     */
    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    /**
     * Associe une personne au client bancaire
     * et met à jour la relation bidirectionnelle.
     *
     * @param personne personne à ajouter
     */
    public void addPersonne(Personne personne) {
        personnes.add(personne);
        personne.addClientBancaire(this);
    }

    /**
     * Supprime une personne du client bancaire
     * et met à jour la relation bidirectionnelle.
     *
     * @param personne personne à supprimer
     */
    public void removePersonne(Personne personne) {
        personnes.remove(personne);
        personne.getClientsBancaires().remove(this);
    }

    // ------------------- Gestion des liens -------------------

    /**
     * Méthode appelée automatiquement avant la suppression d’un client.
     * Sert à nettoyer la relation avec les personnes liées
     * pour éviter les incohérences.
     */
    @PreRemove
    private void gererLiens() {
        for (Personne personne : this.personnes) {
            personne.getClientsBancaires().remove(this);
        }
        this.personnes.clear();
    }

    // ------------------- Méthodes utilitaires -------------------

    @Override
    public String toString() {
        return "\nClientBancaire{" +
                "\n\tid=" + id +
                '}';
    }
}
