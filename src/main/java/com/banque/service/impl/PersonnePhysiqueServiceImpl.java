package com.banque.service.impl;

import com.banque.model.PersonnePhysique;
import com.banque.repository.PersonnePhysiqueRepository;
import com.banque.service.PersonnePhysiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnePhysiqueServiceImpl implements PersonnePhysiqueService {

    public final PersonnePhysiqueRepository personnePhysiqueRepository;

    @Autowired
    public PersonnePhysiqueServiceImpl(PersonnePhysiqueRepository personnePhysiqueRepository) {
        this.personnePhysiqueRepository = personnePhysiqueRepository;
    }

    @Override
    public List<PersonnePhysique> getAllPersonnesPhysiques(){
        return personnePhysiqueRepository.findAll();
    }

    @Override
    public PersonnePhysique createPersonnePhysique(PersonnePhysique personnePhysique)
    {
        return personnePhysiqueRepository.save(personnePhysique);
    }

    @Override
    public PersonnePhysique getPersonnePhysiqueById(Long id)
    {
        return personnePhysiqueRepository.findById(id).orElseThrow();
    }

    @Override
    public PersonnePhysique updatePersonnePhysique(PersonnePhysique personnePhysique)
    {
        return personnePhysiqueRepository.save(personnePhysique);
    }

    @Override
    public void deletePersonnePhysiqueById(Long id)
    {
        personnePhysiqueRepository.findById(id).orElseThrow();
        personnePhysiqueRepository.deleteById(id);
    }
}
