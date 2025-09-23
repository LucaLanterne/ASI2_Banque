package com.banque.mapper;

import com.banque.dataTransfertObjects.TypeProduitDto;
import com.banque.model.TypeProduit;
import org.springframework.stereotype.Component;

@Component
public class TypeProduitMapper {
    public TypeProduitDto toDto(TypeProduit typeProduit) {
        float cotisationCarte = typeProduit.getCotisationCarte();
        float tauxInteretAgios = typeProduit.getTauxInteretAgios();
        String intitule = typeProduit.getIntitule();
        return new TypeProduitDto(cotisationCarte, tauxInteretAgios, intitule);
    }

    public TypeProduit toEntity(TypeProduitDto typeProduitDto) {
        return new TypeProduit(typeProduitDto.getTauxInteretAgios(), typeProduitDto.getIntitule(),
                typeProduitDto.getCotisationCarte());
    }
}
