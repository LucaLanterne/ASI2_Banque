package com.banque.dataTransfertObjects;

import java.util.List;
import java.util.Set;

public class ClientBancaireDto {

    private List<PersonneDto> personneDtos;

    public ClientBancaireDto() {}
    public ClientBancaireDto(List<PersonneDto> personneDtos) {
        this.personneDtos = personneDtos;
    }

    public List<PersonneDto> getPersonneDtos() {
        return personneDtos;
    }

    public void addPersonneDto(PersonneDto personneDto) {
        this.personneDtos.add(personneDto);
    }

    public void removePersonneDto(PersonneDto personneDto) {
        this.personneDtos.remove(personneDto);
    }
}
