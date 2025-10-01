package com.banque.mapper;

import com.banque.dataTransfertObjects.ClientBancaireDto;
import com.banque.dataTransfertObjects.PersonneDto;
import com.banque.model.ClientBancaire;
import com.banque.model.Personne;
import com.banque.model.PersonneMorale;
import com.banque.repository.PersonneMoraleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper permettant de convertir entre {@link ClientBancaire} (entité)
 * et {@link ClientBancaireDto} (DTO).
 *
 * Utilise {@link PersonneMapper} pour gérer la conversion des personnes.
 */
@Component
public class ClientBancaireMapper {

    private final PersonneMapper personneMapper;
    private final PersonneMoraleRepository personneMoraleRepository;

    /**
     * Constructeur avec injection des dépendances nécessaires.
     *
     * @param personneMapper mapper pour les personnes
     * @param personneMoraleRepository repository pour la gestion des personnes morales
     */
    public ClientBancaireMapper(PersonneMapper personneMapper, PersonneMoraleRepository personneMoraleRepository) {
        this.personneMapper = personneMapper;
        this.personneMoraleRepository = personneMoraleRepository;
    }

    /**
     * Convertit une entité {@link ClientBancaire} en {@link ClientBancaireDto}.
     *
     * @param clientBancaire entité client bancaire
     * @return DTO client bancaire
     */
    public ClientBancaireDto toDto(ClientBancaire clientBancaire) {
        List<Personne> personnes = clientBancaire.getPersonnes();
        List<PersonneDto> personneDtos = personneMapper.toDtoList(personnes);
        return new ClientBancaireDto(personneDtos);
    }

    /**
     * Convertit un {@link ClientBancaireDto} en entité {@link ClientBancaire}.
     *
     * Les personnes physiques sont directement mappées via {@link PersonneMapper}.
     * Les personnes morales sont récupérées via le repository {@link PersonneMoraleRepository}
     * en fonction de leur raison sociale, afin de récupérer des informations
     * supplémentaires comme le SIRET ou le type de personne morale.
     *
     * @param clientBancaireDto DTO client bancaire
     * @return entité client bancaire
     */
    public ClientBancaire toEntity(ClientBancaireDto clientBancaireDto) {
        List<Personne> personnes = new ArrayList<>();
        List<PersonneDto> personneDtos = clientBancaireDto.getPersonneDtos();

        for (PersonneDto personneDto : personneDtos) {
            if (personneDto.getTypePersonne().equals("Personne physique")) {
                personnes.add(personneMapper.toEntity(personneDto));
            } else if (personneDto.getTypePersonne().equals("Personne morale")) {
                PersonneMorale personneMorale =
                        personneMoraleRepository.findByRaisonSociale(personneDto.getNom());
                personnes.add(personneMapper.toEntity(
                        personneDto,
                        personneMorale.getSIRET(),
                        personneMorale.getTypePersonneMorale()
                ));
            }
        }

        ClientBancaire clientBancaire = new ClientBancaire();
        clientBancaire.setPersonnes(personnes);
        return clientBancaire;
    }
}
