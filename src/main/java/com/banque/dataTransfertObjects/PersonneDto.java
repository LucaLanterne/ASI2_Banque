package com.banque.dataTransfertObjects;

public class PersonneDto {

    protected String nom;
    protected String adresse;
    protected String typePersonne;

    public PersonneDto() {}
    public PersonneDto(String nom) {
        this.nom = nom;
    }
    public PersonneDto(String nom, String adresse, String typePersonne) {
        this.nom = nom;
        this.adresse = adresse;
        this.typePersonne = typePersonne;
    }

    protected void init(String nom){}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypePersonne() {
        return typePersonne;
    }

    public void setTypePersonne(String typePersonne) {
        this.typePersonne = typePersonne;
    }

    @Override
    public String toString() {
        return "PersonneDto{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", typePersonne='" + typePersonne + '\'' +
                '}';
    }
}
