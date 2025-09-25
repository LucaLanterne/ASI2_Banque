package com.banque.dataTransfertObjects;

import java.util.List;

public class ProduitBancaireDto {

    private float soldeCourant;
    private TypeProduitDto typeProduitDto;
    private ClientBancaireDto clientBancaireDto;
    private String numeroCompte;
    private List<OperationDto> operationDtos;

    public ProduitBancaireDto() {}
    public ProduitBancaireDto(String numeroCompte, float soldeCourant, TypeProduitDto typeProduitDto,
                              ClientBancaireDto clientBancaireDto, List<OperationDto> operationDtos) {
        this.numeroCompte = numeroCompte;
        this.soldeCourant = soldeCourant;
        this.typeProduitDto = typeProduitDto;
        this.clientBancaireDto = clientBancaireDto;
        this.operationDtos = operationDtos;
    }

    public float getSoldeCourant() {
        return soldeCourant;
    }

    public void setSoldeCourant(float soldeCourant) {
        this.soldeCourant = soldeCourant;
    }

    public TypeProduitDto getTypeProduitDto() {
        return typeProduitDto;
    }

    public void setTypeProduitDto(TypeProduitDto typeProduitDto) {
        this.typeProduitDto = typeProduitDto;
    }

    public ClientBancaireDto getClientBancaireDto() {
        return clientBancaireDto;
    }

    public void setClientBancaireDto(ClientBancaireDto clientBancaireDto) {
        this.clientBancaireDto = clientBancaireDto;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public List<OperationDto> getOperationDtos() {
        return operationDtos;
    }

    public void setOperationDtos(List<OperationDto> operationDtos) {
        this.operationDtos = operationDtos;
    }
}
