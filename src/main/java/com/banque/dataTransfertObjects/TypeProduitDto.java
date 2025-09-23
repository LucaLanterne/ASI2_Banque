package com.banque.dataTransfertObjects;

import com.banque.model.TypeProduit;

import java.util.Objects;

public class TypeProduitDto {

    private float cotisationCarte;
    private float tauxInteretAgios;
    private String intitule;

    public TypeProduitDto() {}
    public TypeProduitDto(float cotisationCarte, float tauxInteretAgios, String intitule) {
        this.cotisationCarte = cotisationCarte;
        this.tauxInteretAgios = tauxInteretAgios;
        this.intitule = intitule;
    }

    public float getCotisationCarte() {
        return cotisationCarte;
    }

    public void setCotisationCarte(float cotisationCarte) {
        this.cotisationCarte = cotisationCarte;
    }

    public float getTauxInteretAgios() {
        return tauxInteretAgios;
    }

    public void setTauxInteretAgios(float tauxInteretAgios) {
        this.tauxInteretAgios = tauxInteretAgios;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @Override
    public String toString() {
        return "TypeProduitDto{" +
                "cotisationCarte=" + cotisationCarte +
                ", tauxInteretAgios=" + tauxInteretAgios +
                ", intitule='" + intitule + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TypeProduitDto that = (TypeProduitDto) o;
        return Float.compare(cotisationCarte, that.cotisationCarte) == 0 && Float.compare(tauxInteretAgios, that.tauxInteretAgios) == 0 && Objects.equals(intitule, that.intitule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cotisationCarte, tauxInteretAgios, intitule);
    }
}
