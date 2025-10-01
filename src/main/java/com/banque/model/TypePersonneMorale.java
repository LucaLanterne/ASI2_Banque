package com.banque.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Entité représentant le type d'une personne morale.
 *
 * <p>
 * Un {@link TypePersonneMorale} peut regrouper plusieurs {@link PersonneMorale}.
 * </p>
 */
@Entity(name = "TypePersonneMorale")
@Table(name = "type_personne_morale")
public class TypePersonneMorale {

    /** Identifiant unique du type */
    @Id
    @SequenceGenerator(
            name = "typepersonnemorale_sequence",
            sequenceName = "typepersonnemorale_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "typepersonnemorale_sequence")
    @Column(name = "id")
    private Long id;

    /** Intitulé du type */
    @Column(name = "intitule", nullable = false, columnDefinition = "TEXT")
    private String intitule;

    /** Liste des personnes morales liées à ce type */
    @OneToMany(mappedBy = "typePersonneMorale", fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PersonneMorale> personnesMorales = new ArrayList<>();

    /** Constructeur par défaut */
    public TypePersonneMorale() {}

    /**
     * Constructeur avec intitulé.
     *
     * @param intitule nom du type
     */
    public TypePersonneMorale(String intitule) {
        this.intitule = intitule;
    }

    // ------------------- Getters & Setters -------------------

    /**
     * Récupère l'identifiant du type.
     *
     * @return identifiant unique
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du type.
     *
     * @param id identifiant unique
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère l'intitulé du type.
     *
     * @return intitulé du type
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Définit l'intitulé du type.
     *
     * @param intitule intitulé du type
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * Récupère la liste des personnes morales liées à ce type.
     *
     * @return liste des personnes morales
     */
    public List<PersonneMorale> getPersonnesMorales() {
        return personnesMorales;
    }

    /**
     * Définit la liste des personnes morales liées à ce type.
     *
     * @param personnesMorales liste de personnes morales
     */
    public void setPersonnesMorales(List<PersonneMorale> personnesMorales) {
        this.personnesMorales = personnesMorales;
    }

    /**
     * Ajoute une personne morale à la liste.
     *
     * @param personne personne morale à ajouter
     */
    public void addPersonneMorale(PersonneMorale personne) {
        this.personnesMorales.add(personne);
    }

    /**
     * Supprime une personne morale de la liste et casse le lien avec ce type.
     *
     * @param personne personne morale à supprimer
     */
    public void removePersonneMorale(PersonneMorale personne) {
        this.personnesMorales.remove(personne);
        personne.setTypePersonneMorale(null);
    }

    // ------------------- Méthodes utilitaires -------------------

    @Override
    public String toString() {
        return "TypePersonneMorale{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TypePersonneMorale that = (TypePersonneMorale) o;
        return Objects.equals(id, that.id) && Objects.equals(intitule, that.intitule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, intitule);
    }
}
