package com.banque.mapper;

import com.banque.dataTransfertObjects.ClientBancaireDto;
import com.banque.dataTransfertObjects.OperationDto;
import com.banque.dataTransfertObjects.ProduitBancaireDto;
import com.banque.dataTransfertObjects.TypeProduitDto;
import com.banque.model.ProduitBancaire;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProduitBancaireMapper {
    public ProduitBancaireDto toDto(ProduitBancaire produitBancaire) {
        float soldeCourant = produitBancaire.getSolde_courant();
        TypeProduitDto typeProduitDto = new TypeProduitDto();
        ClientBancaireDto clientBancaireDto =  new ClientBancaireDto();
        String numeroCompte =  produitBancaire.getNumeroCompte();
        Set<OperationDto> operationDtos = new HashSet<OperationDto>();
        return new ProduitBancaireDto(numeroCompte, soldeCourant, typeProduitDto, clientBancaireDto, operationDtos);
    }

//    public ProduitBancaire toEntity(ProduitBancaireDto produitBancaireDto) {
//        return new ProduitBancaire(produitBancaireDto.getSoldeCourant(), produitBancaireDto.getNumeroCompte());
//    }
}
