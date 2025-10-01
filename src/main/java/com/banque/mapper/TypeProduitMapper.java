package com.banque.mapper;

import com.banque.dataTransfertObjects.TypeProduitDto;
import com.banque.model.TypeProduit;
import org.springframework.stereotype.Component;

/**
 * Mapper permettant de convertir entre {@link TypeProduit} (entité)
 * et {@link TypeProduitDto} (DTO).
 */
@Component
public class TypeProduitMapper {

    /**
     * Convertit une entité {@link TypeProduit} en {@link TypeProduitDto}.
     *
     * @param typeProduit entité type de produit
     * @return DTO correspondant
     */
    public TypeProduitDto toDto(TypeProduit typeProduit) {
        float cotisationCarte = typeProduit.getCotisationCarte();
        float tauxInteretAgios = typeProduit.getTauxInteretAgios();
        String intitule = typeProduit.getIntitule();

        return new TypeProduitDto(
                cotisationCarte,
                tauxInteretAgios,
                intitule
        );
    }

    /**
     * Convertit un {@link TypeProduitDto} en entité {@link TypeProduit}.
     *
     * @param typeProduitDto DTO type de produit
     * @return entité correspondante
     */
    public TypeProduit toEntity(TypeProduitDto typeProduitDto) {
        return new TypeProduit(
                typeProduitDto.getTauxInteretAgios(),
                typeProduitDto.getIntitule(),
                typeProduitDto.getCotisationCarte()
        );
    }
}
