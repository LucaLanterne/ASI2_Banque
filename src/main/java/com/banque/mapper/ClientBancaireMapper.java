package com.banque.mapper;

import com.banque.dataTransfertObjects.ClientBancaireDto;
import com.banque.dataTransfertObjects.PersonneDto;
import com.banque.model.ClientBancaire;
import com.banque.model.Personne;
import com.banque.model.PersonneMorale;
import com.banque.repository.PersonneMoraleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientBancaireMapper
{
    private final PersonneMapper personneMapper;
    private final PersonneMoraleRepository personneMoraleRepository;

    public ClientBancaireMapper(PersonneMapper personneMapper,  PersonneMoraleRepository personneMoraleRepository) {
        this.personneMapper = personneMapper;
        this.personneMoraleRepository = personneMoraleRepository;
    }

    public ClientBancaireDto toDto (ClientBancaire clientBancaire) {
        List<Personne> personnes = clientBancaire.getPersonnes();
        List<PersonneDto> personneDtos = personneMapper.toDtoList(personnes);
        return new ClientBancaireDto(personneDtos);
    }

    public ClientBancaire toEntity (ClientBancaireDto clientBancaireDto) {
        List<Personne> personnes = new ArrayList<>();
        List<PersonneDto> personneDtos = clientBancaireDto.getPersonneDtos();
        for (PersonneDto personneDto : personneDtos) {
            if (personneDto.getTypePersonne().equals("Personne physique")) {
                personnes.add(personneMapper.toEntity(personneDto));
            }
            else if (personneDto.getTypePersonne().equals("Personne morale")) {
                PersonneMorale personneMorale = personneMoraleRepository.findByRaisonSociale(personneDto.getNom());
                personnes.add(personneMapper.toEntity(personneDto, personneMorale.getSIRET(), personneMorale.getTypePersonneMorale()));
            }
        }
        ClientBancaire clientBancaire = new ClientBancaire();
        clientBancaire.setPersonnes(personnes);
        return  clientBancaire;
    }

}
