package com.banque.service.impl;

import com.banque.model.PersonnePhysique;
import com.banque.repository.PersonnePhysiqueRepository;
import com.banque.service.PersonnePhysiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service pour gérer les personnes physiques.
 * Fournit les opérations CRUD sur les entités PersonnePhysique.
 */
@Service
public class PersonnePhysiqueServiceImpl implements PersonnePhysiqueService {

    private final PersonnePhysiqueRepository personnePhysiqueRepository;

    /**
     * Constructeur pour l'injection du repository PersonnePhysiqueRepository.
     *
     * @param personnePhysiqueRepository repository pour les personnes physiques
     */
    @Autowired
    public PersonnePhysiqueServiceImpl(PersonnePhysiqueRepository personnePhysiqueRepository) {
        this.personnePhysiqueRepository = personnePhysiqueRepository;
    }

    /**
     * Récupère toutes les personnes physiques.
     *
     * @return liste de toutes les personnes physiques
     */
    @Override
    public List<PersonnePhysique> getAllPersonnesPhysiques() {
        return personnePhysiqueRepository.findAll();
    }

    /**
     * Crée une nouvelle personne physique.
     *
     * @param personnePhysique personne physique à créer
     * @return personne physique créée
     */
    @Override
    public PersonnePhysique createPersonnePhysique(PersonnePhysique personnePhysique) {
        return personnePhysiqueRepository.save(personnePhysique);
    }

    /**
     * Récupère une personne physique par son identifiant.
     *
     * @param id identifiant de la personne physique
     * @return personne physique correspondante
     */
    @Override
    public PersonnePhysique getPersonnePhysiqueById(Long id) {
        return personnePhysiqueRepository.findById(id).orElseThrow();
    }

    /**
     * Met à jour une personne physique existante.
     *
     * @param personnePhysique personne physique à mettre à jour
     * @return personne physique mise à jour
     */
    @Override
    public PersonnePhysique updatePersonnePhysique(PersonnePhysique personnePhysique) {
        return personnePhysiqueRepository.save(personnePhysique);
    }

    /**
     * Supprime une personne physique par son identifiant.
     *
     * @param id identifiant de la personne physique à supprimer
     */
    @Override
    public void deletePersonnePhysiqueById(Long id) {
        personnePhysiqueRepository.findById(id).orElseThrow();
        personnePhysiqueRepository.deleteById(id);
    }
}
