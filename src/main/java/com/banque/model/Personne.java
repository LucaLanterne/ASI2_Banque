package com.banque.model;

import jakarta.persistence.*;
import java.util.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Classe abstraite représentant une personne (physique ou morale).
 *
 * <p>
 * Une {@link Personne} peut être associée à plusieurs {@link ClientBancaire}
 * via une relation ManyToMany. Les sous-classes concrètes (ex: {@link PersonnePhysique},
 * {@link PersonneMorale}) définissent leur propre implémentation de {@link #nomComplet()}.
 * </p>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne {

    /** Identifiant unique de la personne */
    @Id
    @SequenceGenerator(
            name = "personne_sequence",
            sequenceName = "personne_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "personne_sequence")
    @Column(name = "id")
    protected Long id;

    /** Adresse de la personne */
    @Column(name = "adresse", columnDefinition = "TEXT")
    protected String adresse;

    /**
     * Liste des clients bancaires liés à cette personne.
     * Relation bidirectionnelle ManyToMany (côté non propriétaire).
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "personnes")
    protected List<ClientBancaire> clientsBancaires = new ArrayList<>();

    /** Constructeur par défaut */
    public Personne() {}

    /**
     * Constructeur avec adresse.
     *
     * @param adresse adresse de la personne
     */
    public Personne(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Retourne le nom complet de la personne.
     * Chaque sous-classe doit définir sa propre implémentation.
     *
     * @return nom complet
     */
    public abstract String nomComplet();

    // ------------------- Getters & Setters -------------------

    /**
     * Récupère l'identifiant unique d'une personne.
     * @return identifiant unique de la personne
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique d'une personne.
     *
     * @param id identifiant unique de la personne
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère l'adresse d'une personne.
     *
     * @return adresse de la personne
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse d'une personne.
     *
     * @param adresse adresse de la personne
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Récupère les clients bancaires associés à une personne.
     *
     * @return liste des clients bancaires associés
     */
    public List<ClientBancaire> getClientsBancaires() {
        return clientsBancaires;
    }

    /**
     * Définit les clients bancaires associés à une personne.
     *
     * @param clientsBancaires liste des clients bancaires associés
     */
    public void setClientsBancaires(List<ClientBancaire> clientsBancaires) {
        this.clientsBancaires = clientsBancaires;
    }

    /**
     * Ajoute un client bancaire à la personne et met à jour
     * la relation du côté du client bancaire si nécessaire.
     *
     * @param clientBancaire client bancaire à associer
     */
    public void addClientBancaire(ClientBancaire clientBancaire) {
        clientsBancaires.add(clientBancaire);

        // Côté non propriétaire → on met à jour si nécessaire
        if (!clientBancaire.getPersonnes().contains(this)) {
            clientBancaire.addPersonne(this);
        }
    }

    /**
     * Supprime le lien entre la personne et un client bancaire.
     * Si la suppression est initiée par la personne,
     * on enlève également la référence côté client bancaire.
     *
     * @param clientBancaire client bancaire à dissocier
     */
    public void removeClientBancaireFromClientBancaire(ClientBancaire clientBancaire) {
        if (clientsBancaires.contains(clientBancaire)) {
            clientsBancaires.remove(clientBancaire);
        }
        if (clientBancaire.getPersonnes().contains(this)) {
            clientBancaire.removePersonne(this);
        }
    }

    /**
     * Supprime le lien avec un client bancaire sans appeler
     * la méthode de suppression sur le {@link ClientBancaire}.
     *
     * @param clientBancaire client bancaire à dissocier
     */
    public void removeClientBancaire(ClientBancaire clientBancaire) {
        clientsBancaires.remove(clientBancaire);
        if (clientBancaire.getPersonnes().contains(this)) {
            clientBancaire.getPersonnes().remove(this);
        }
    }

    // ------------------- Gestion des liens -------------------

    /**
     * Méthode appelée automatiquement avant la suppression d’une personne.
     * Nettoie les liens avec les {@link ClientBancaire} pour éviter
     * les incohérences dans la relation.
     */
    @PreRemove
    protected void gererLiens() {
        for (ClientBancaire cb : clientsBancaires) {
            if (cb != null) {
                cb.removePersonne(this);
            }
        }
        clientsBancaires.clear();
    }

    // ------------------- Méthodes utilitaires -------------------

    @Override
    public String toString() {
        return "Personne{" +
                "Adresse='" + adresse + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(adresse, personne.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(adresse);
    }
}
