package com.banque.model;

import jakarta.persistence.*;

import java.util.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "TypePersonneMorale")
@Table(name = "type_personne_morale")
public class TypePersonneMorale {
    @Id
    @SequenceGenerator(name = "typepersonnemorale_sequence", sequenceName = "typepersonnemorale_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "typepersonnemorale_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "intitule", nullable = false, columnDefinition = "TEXT")
    private String intitule;

    //Création du lien OneToMany côté NON propriétaire
    // mappedBy contient le nom de l'attribut ManyToOne dans PersonneMorale
    @OneToMany(mappedBy = "typePersonneMorale", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PersonneMorale> personnesMorales = new ArrayList<>();

    public TypePersonneMorale() {}

    public TypePersonneMorale(String intitule) {
        this.intitule = intitule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<PersonneMorale> getPersonnesMorales() {
        return personnesMorales;
    }

    public void setPersonnesMorales(List<PersonneMorale> personnesMorales) {
        this.personnesMorales = personnesMorales;
    }

    public void addPersonneMorale(PersonneMorale personne) {
        this.personnesMorales.add(personne);
    }

    public void removePersonneMorale(PersonneMorale personne) {
        this.personnesMorales.remove(personne);
        personne.setTypePersonneMorale(null);
    }

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
