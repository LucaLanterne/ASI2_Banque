package com.banque.service;

import com.banque.model.PersonnePhysique;

import java.util.List;

public interface PersonnePhysiqueService {
    public List<PersonnePhysique> getAllPersonnesPhysiques();
    public PersonnePhysique createPersonnePhysique(PersonnePhysique personnePhysique);
    public PersonnePhysique getPersonnePhysiqueById(Long id);
    public PersonnePhysique updatePersonnePhysique(PersonnePhysique personnePhysique);
    public void deletePersonnePhysiqueById(Long id);
}
