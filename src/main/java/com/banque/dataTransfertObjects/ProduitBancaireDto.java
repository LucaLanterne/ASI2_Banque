package com.banque.dataTransfertObjects;

import java.util.List;

/**
 * Data Transfer Object représentant un produit bancaire.
 *
 * Contient les informations relatives à un compte ou produit bancaire :
 * - numéro de compte
 * - solde courant
 * - type de produit
 * - client associé
 * - liste des opérations effectuées
 */
public class ProduitBancaireDto {

    /** Solde courant du compte ou produit bancaire */
    private float soldeCourant;

    /** Type du produit bancaire (ex : Compte courant, Livret A, etc.) */
    private TypeProduitDto typeProduitDto;

    /** Client bancaire associé à ce produit */
    private ClientBancaireDto clientBancaireDto;

    /** Numéro unique du compte bancaire */
    private String numeroCompte;

    /** Liste des opérations rattachées à ce produit bancaire */
    private List<OperationDto> operationDtos;

    /**
     * Constructeur par défaut.
     */
    public ProduitBancaireDto() {}

    /**
     * Constructeur avec initialisation complète.
     *
     * @param numeroCompte numéro de compte
     * @param soldeCourant solde courant
     * @param typeProduitDto type de produit bancaire
     * @param clientBancaireDto client bancaire associé
     * @param operationDtos liste des opérations liées
     */
    public ProduitBancaireDto(String numeroCompte, float soldeCourant, TypeProduitDto typeProduitDto,
                              ClientBancaireDto clientBancaireDto, List<OperationDto> operationDtos) {
        this.numeroCompte = numeroCompte;
        this.soldeCourant = soldeCourant;
        this.typeProduitDto = typeProduitDto;
        this.clientBancaireDto = clientBancaireDto;
        this.operationDtos = operationDtos;
    }

    /**
     * Retourne le solde courant.
     *
     * @return solde courant
     */
    public float getSoldeCourant() {
        return soldeCourant;
    }

    /**
     * Définit le solde courant.
     *
     * @param soldeCourant solde courant
     */
    public void setSoldeCourant(float soldeCourant) {
        this.soldeCourant = soldeCourant;
    }

    /**
     * Retourne le type du produit bancaire.
     *
     * @return type du produit
     */
    public TypeProduitDto getTypeProduitDto() {
        return typeProduitDto;
    }

    /**
     * Définit le type du produit bancaire.
     *
     * @param typeProduitDto type du produit
     */
    public void setTypeProduitDto(TypeProduitDto typeProduitDto) {
        this.typeProduitDto = typeProduitDto;
    }

    /**
     * Retourne le client associé au produit bancaire.
     *
     * @return client bancaire
     */
    public ClientBancaireDto getClientBancaireDto() {
        return clientBancaireDto;
    }

    /**
     * Définit le client associé au produit bancaire.
     *
     * @param clientBancaireDto client bancaire
     */
    public void setClientBancaireDto(ClientBancaireDto clientBancaireDto) {
        this.clientBancaireDto = clientBancaireDto;
    }

    /**
     * Retourne le numéro de compte.
     *
     * @return numéro de compte
     */
    public String getNumeroCompte() {
        return numeroCompte;
    }

    /**
     * Définit le numéro de compte.
     *
     * @param numeroCompte numéro de compte
     */
    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    /**
     * Retourne la liste des opérations liées au produit bancaire.
     *
     * @return liste d'opérations
     */
    public List<OperationDto> getOperationDtos() {
        return operationDtos;
    }

    /**
     * Définit la liste des opérations liées au produit bancaire.
     *
     * @param operationDtos liste d'opérations
     */
    public void setOperationDtos(List<OperationDto> operationDtos) {
        this.operationDtos = operationDtos;
    }
}
