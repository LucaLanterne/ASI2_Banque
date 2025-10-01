package com.banque.service.impl;

import com.banque.model.PersonneMorale;
import com.banque.repository.PersonneMoraleRepository;
import com.banque.service.PersonneMoraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service pour gérer les personnes morales.
 * Fournit les opérations CRUD sur les entités PersonneMorale.
 */
@Service
public class PersonneMoraleServiceImpl implements PersonneMoraleService {

    private final PersonneMoraleRepository personneMoraleRepository;

    /**
     * Constructeur pour l'injection du repository PersonneMoraleRepository.
     *
     * @param personneMoraleRepository repository pour les personnes morales
     */
    @Autowired
    public PersonneMoraleServiceImpl(PersonneMoraleRepository personneMoraleRepository) {
        this.personneMoraleRepository = personneMoraleRepository;
    }

    /**
     * Récupère toutes les personnes morales.
     *
     * @return liste de toutes les personnes morales
     */
    @Override
    public List<PersonneMorale> getAllPersonnesMorales() {
        return personneMoraleRepository.findAll();
    }

    /**
     * Crée une nouvelle personne morale.
     *
     * @param personneMorale personne morale à créer
     * @return personne morale créée
     */
    @Override
    public PersonneMorale createPersonneMorale(PersonneMorale personneMorale) {
        return personneMoraleRepository.save(personneMorale);
    }

    /**
     * Récupère une personne morale par son identifiant.
     *
     * @param id identifiant de la personne morale
     * @return personne morale correspondante
     */
    @Override
    public PersonneMorale getPersonneMoraleById(Long id) {
        return personneMoraleRepository.findById(id).orElseThrow();
    }

    /**
     * Met à jour une personne morale existante.
     *
     * @param personneMorale personne morale à mettre à jour
     * @return personne morale mise à jour
     */
    @Override
    public PersonneMorale updatePersonneMorale(PersonneMorale personneMorale) {
        return personneMoraleRepository.save(personneMorale);
    }

    /**
     * Supprime une personne morale par son identifiant.
     *
     * @param id identifiant de la personne morale à supprimer
     */
    @Override
    public void deletePersonneMoraleById(Long id) {
        personneMoraleRepository.findById(id).orElseThrow();
        personneMoraleRepository.deleteById(id);
    }
}
