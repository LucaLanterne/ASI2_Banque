package com.banque.mapper;

import com.banque.dataTransfertObjects.ClientBancaireDto;
import com.banque.dataTransfertObjects.OperationDto;
import com.banque.dataTransfertObjects.ProduitBancaireDto;
import com.banque.dataTransfertObjects.TypeProduitDto;
import com.banque.model.ProduitBancaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ProduitBancaireMapper
{
    private final TypeProduitMapper typeProduitMapper;
    private final ClientBancaireMapper clientBancaireMapper;
    private final OperationMapper operationMapper;

    @Autowired
    public ProduitBancaireMapper(TypeProduitMapper typeProduitMapper,
                                 ClientBancaireMapper clientBancaireMapper,
                                 OperationMapper operationMapper) {
        this.typeProduitMapper = typeProduitMapper;
        this.clientBancaireMapper = clientBancaireMapper;
        this.operationMapper = operationMapper;
    }

    public ProduitBancaireDto toDto(ProduitBancaire produitBancaire) {
        float soldeCourant = produitBancaire.getSolde_courant();
        TypeProduitDto typeProduitDto = typeProduitMapper.toDto(produitBancaire.getTypeProduit());
        ClientBancaireDto clientBancaireDto = clientBancaireMapper.toDto(produitBancaire.getClientBancaire());
        String numeroCompte =  produitBancaire.getNumeroCompte();
        List<OperationDto> operationDtos = operationMapper.toDtoList(produitBancaire.getOperations());
        return new ProduitBancaireDto(numeroCompte, soldeCourant, typeProduitDto, clientBancaireDto, operationDtos);
    }

    public ProduitBancaire toEntity(ProduitBancaireDto produitBancaireDto) {
        return new ProduitBancaire(produitBancaireDto.getSoldeCourant(), produitBancaireDto.getNumeroCompte(), typeProduitMapper.toEntity(produitBancaireDto.getTypeProduitDto()), clientBancaireMapper.toEntity(produitBancaireDto.getClientBancaireDto()));
    }
}
