package com.banque.mapper;

import com.banque.dataTransfertObjects.PersonneDto;
import com.banque.model.PersonneMorale;
import com.banque.model.PersonnePhysique;
import com.banque.model.TypePersonneMorale;
import org.springframework.stereotype.Component;

@Component
public class PersonneMapper {

    public PersonneDto toDto (PersonnePhysique personnePhysique) {
        String nom = personnePhysique.getNom();
        String adresse = personnePhysique.getAdresse();
        String typePersonne = "Personne physique";
        return new PersonneDto(nom, adresse, typePersonne);
    }

    public PersonnePhysique PersonnePhysiqueToEntity (PersonneDto personneDto) {
        return new PersonnePhysique(personneDto.getNom(), personneDto.getAdresse(), personneDto.getTypePersonne());
    }

    public PersonneDto toDto (PersonneMorale personneMorale) {
        String nom = personneMorale.getRaisonSociale();
        String adresse = personneMorale.getAdresse();
        String typePersonne = "Personne morale";
        return new PersonneDto(nom, adresse, typePersonne);
    }

    public PersonneMorale PersonneMoraleToEntity (PersonneDto personneDto, String SIRET, TypePersonneMorale typePersonneMorale) {
        return new PersonneMorale(personneDto.getAdresse(), SIRET, personneDto.getNom(), typePersonneMorale);
    }
}
