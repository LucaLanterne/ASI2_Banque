package com.banque.mapper;

import com.banque.dataTransfertObjects.ClientBancaireDto;
import com.banque.dataTransfertObjects.OperationDto;
import com.banque.dataTransfertObjects.ProduitBancaireDto;
import com.banque.dataTransfertObjects.TypeProduitDto;
import com.banque.model.ProduitBancaire;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper permettant de convertir entre {@link ProduitBancaire} (entité)
 * et {@link ProduitBancaireDto} (DTO).
 */
@Component
public class ProduitBancaireMapper {

    private final TypeProduitMapper typeProduitMapper;
    private final ClientBancaireMapper clientBancaireMapper;
    private final OperationMapper operationMapper;

    /**
     * Constructeur avec injection des dépendances mappers.
     *
     * @param typeProduitMapper mapper des types de produits bancaires
     * @param clientBancaireMapper mapper des clients bancaires
     * @param operationMapper mapper des opérations
     */
    public ProduitBancaireMapper(TypeProduitMapper typeProduitMapper,
                                 ClientBancaireMapper clientBancaireMapper,
                                 OperationMapper operationMapper) {
        this.typeProduitMapper = typeProduitMapper;
        this.clientBancaireMapper = clientBancaireMapper;
        this.operationMapper = operationMapper;
    }

    /**
     * Convertit une entité {@link ProduitBancaire} en {@link ProduitBancaireDto}.
     *
     * @param produitBancaire entité produit bancaire
     * @return DTO correspondant
     */
    public ProduitBancaireDto toDto(ProduitBancaire produitBancaire) {
        float soldeCourant = produitBancaire.getSolde_courant();
        TypeProduitDto typeProduitDto = typeProduitMapper.toDto(produitBancaire.getTypeProduit());
        ClientBancaireDto clientBancaireDto = clientBancaireMapper.toDto(produitBancaire.getClientBancaire());
        String numeroCompte = produitBancaire.getNumeroCompte();
        List<OperationDto> operationDtos = operationMapper.toDtoList(produitBancaire.getOperations());

        return new ProduitBancaireDto(
                numeroCompte,
                soldeCourant,
                typeProduitDto,
                clientBancaireDto,
                operationDtos
        );
    }

    /**
     * Convertit un {@link ProduitBancaireDto} en entité {@link ProduitBancaire}.
     *
     * @param produitBancaireDto DTO produit bancaire
     * @return entité correspondante
     */
    public ProduitBancaire toEntity(ProduitBancaireDto produitBancaireDto) {
        return new ProduitBancaire(
                produitBancaireDto.getSoldeCourant(),
                produitBancaireDto.getNumeroCompte(),
                typeProduitMapper.toEntity(produitBancaireDto.getTypeProduitDto()),
                clientBancaireMapper.toEntity(produitBancaireDto.getClientBancaireDto())
        );
    }
}
