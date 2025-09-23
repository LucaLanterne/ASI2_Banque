package com.banque.dataTransfertObjects;

import java.util.Set;

public class ClientBancaireDto {

    private Set<PersonneDto> personneDtos;

    public ClientBancaireDto() {}
    public ClientBancaireDto(Set<PersonneDto> personneDtos) {
        this.personneDtos = personneDtos;
    }

    public Set<PersonneDto> getPersonneDtos() {
        return personneDtos;
    }

    public void addPersonneDto(PersonneDto personneDto) {
        this.personneDtos.add(personneDto);
    }

    public void removePersonneDto(PersonneDto personneDto) {
        this.personneDtos.remove(personneDto);
    }
}
