package com.banque.model;

import com.banque.repository.TypePersonneMoraleRepository;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "PersonneMorale")
@Table(name = "personne_morale")
public class PersonneMorale extends Personne {

    @Column(name = "siret", nullable = true, columnDefinition = "TEXT")
    private String SIRET;
    @Column(name = "raison_sociale", nullable = false, columnDefinition = "TEXT")
    private String raisonSociale;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_personne_morale_id")
    private TypePersonneMorale typePersonneMorale;

    public PersonneMorale() {}

    public PersonneMorale(String adresse, String SIRET, String raisonSociale, TypePersonneMorale TypePersonneMorale) {
        super(adresse);
        this.SIRET = SIRET;
        this.raisonSociale = raisonSociale;
        this.typePersonneMorale = TypePersonneMorale;
        typePersonneMorale.getPersonnesMorales().add(this);
    }

    @Override
    public String nomComplet() {
        return raisonSociale;
    }

    public TypePersonneMorale getTypePersonneMorale() {
        return typePersonneMorale;
    }
    public void setTypePersonneMorale(TypePersonneMorale TypePersonneMorale) {
        this.typePersonneMorale = TypePersonneMorale;
    }

    public String getSIRET() {
        return SIRET;
    }

    public void setSIRET(String SIRET) {
        this.SIRET = SIRET;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    @PreRemove
    private void gererLiensPM()
    {
        // On enlève la personne morale de la liste des personnes qui est dans le type de personne lié
        // Cela casse le lien de TypePersonneMorale vers cette personne.
        if (typePersonneMorale!=null)
        {
            typePersonneMorale.getPersonnesMorales().remove(this);
        }
        // On casse le lien de la personne morale vers son type de personne morale
        typePersonneMorale=null;
    }

    @Override
    public String toString() {
        return "\nPersonneMorale{" +
                "\nid=" + id +
                ", \nSIRET='" + SIRET + '\'' +
                ", \nraisonSociale='" + raisonSociale + '\'' +
                ", \nadresse='" + adresse + '\'' +
                ", \ntypePersonneMorale=" + typePersonneMorale +'\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonneMorale that = (PersonneMorale) o;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
