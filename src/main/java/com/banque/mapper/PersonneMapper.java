package com.banque.mapper;

import com.banque.dataTransfertObjects.PersonneDto;
import com.banque.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper permettant de convertir entre {@link Personne} (entités physiques ou morales)
 * et {@link PersonneDto} (DTO).
 */
@Component
public class PersonneMapper {

    /**
     * Convertit une entité {@link Personne} en {@link PersonneDto}.
     *
     * - Si {@link PersonnePhysique}, utilise le nom et l'adresse directement.
     * - Si {@link PersonneMorale}, utilise la raison sociale comme nom et l'adresse.
     *
     * @param personne entité à convertir
     * @return DTO correspondant
     */
    public PersonneDto toDto(Personne personne) {
        String nom = "";
        String adresse = "";
        String typePersonne = "";

        if (personne instanceof PersonnePhysique) {
            nom = ((PersonnePhysique) personne).getNom();
            adresse = ((PersonnePhysique) personne).getAdresse();
            typePersonne = "Personne physique";
        } else if (personne instanceof PersonneMorale) {
            nom = ((PersonneMorale) personne).getRaisonSociale();
            adresse = ((PersonneMorale) personne).getAdresse();
            typePersonne = "Personne morale";
        }

        return new PersonneDto(nom, adresse, typePersonne);
    }

    /**
     * Convertit une liste d'entités {@link Personne} en liste de {@link PersonneDto}.
     *
     * @param personnes liste d'entités
     * @return liste de DTO correspondants
     */
    public List<PersonneDto> toDtoList(List<Personne> personnes) {
        List<PersonneDto> dtos = new ArrayList<>();
        for (Personne personne : personnes) {
            dtos.add(toDto(personne));
        }
        return dtos;
    }

    /**
     * Convertit un {@link PersonneDto} en entité {@link PersonnePhysique}.
     *
     * @param personneDto DTO représentant une personne physique
     * @return entité PersonnePhysique
     */
    public Personne toEntity(PersonneDto personneDto) {
        return new PersonnePhysique(
                personneDto.getNom(),
                personneDto.getAdresse(),
                personneDto.getTypePersonne()
        );
    }

    /**
     * Convertit un {@link PersonneDto} en entité {@link PersonneMorale}.
     *
     * @param personneDto DTO représentant une personne morale
     * @param SIRET identifiant SIRET
     * @param typePersonneMorale type de la personne morale
     * @return entité PersonneMorale
     */
    public Personne toEntity(PersonneDto personneDto, String SIRET, TypePersonneMorale typePersonneMorale) {
        return new PersonneMorale(
                personneDto.getAdresse(),
                SIRET,
                personneDto.getNom(),
                typePersonneMorale
        );
    }
}
