package com.banque.mapper;

import com.banque.dataTransfertObjects.OperationDto;
import com.banque.dataTransfertObjects.PersonneDto;
import com.banque.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonneMapper
{
    public PersonneDto toDto (Personne personne)
    {
        String nom = "";
        String adresse = "";
        String typePersonne = "";
        if (personne instanceof PersonnePhysique) {
            nom = ((PersonnePhysique) personne).getNom();
            adresse = ((PersonnePhysique) personne).getAdresse();
            typePersonne = "Personne physique";
        }
        else if (personne instanceof PersonneMorale) {
            nom = ((PersonneMorale) personne).getRaisonSociale();
            adresse = ((PersonneMorale) personne).getAdresse();
            typePersonne = "Personne morale";
        }
        return new PersonneDto(nom, adresse, typePersonne);
    }

    public List<PersonneDto> toDtoList (List<Personne> personnes) {
        List<PersonneDto> dtos = new ArrayList<>();
        for (Personne personne : personnes) {
            dtos.add(toDto(personne));
        }
        return dtos;
    }

    public Personne toEntity (PersonneDto personneDto) {
        return new PersonnePhysique(personneDto.getNom(), personneDto.getAdresse(), personneDto.getTypePersonne());
    }

    public Personne toEntity (PersonneDto personneDto, String SIRET, TypePersonneMorale typePersonneMorale) {
        return new PersonneMorale(personneDto.getAdresse(), SIRET, personneDto.getNom(), typePersonneMorale);
    }

}
