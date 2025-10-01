package com.banque.service.impl;

import com.banque.model.TypePersonneMorale;
import com.banque.repository.TypePersonneMoraleRepository;
import com.banque.service.TypePersonneMoraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service pour gérer les types de personnes morales.
 * Fournit les opérations CRUD standard.
 */
@Service
public class TypePersonneMoraleServiceImpl implements TypePersonneMoraleService {

    private final TypePersonneMoraleRepository typePersonneMoraleRepository;

    /**
     * Constructeur pour l'injection du repository TypePersonneMoraleRepository.
     *
     * @param typePersonneMoraleRepository repository pour les types de personnes morales
     */
    @Autowired
    public TypePersonneMoraleServiceImpl(TypePersonneMoraleRepository typePersonneMoraleRepository) {
        this.typePersonneMoraleRepository = typePersonneMoraleRepository;
    }

    /**
     * Récupère tous les types de personnes morales.
     *
     * @return liste de tous les types de personnes morales
     */
    @Override
    public List<TypePersonneMorale> getAllTypesPersonnesMorales() {
        return typePersonneMoraleRepository.findAll();
    }

    /**
     * Crée un nouveau type de personne morale.
     *
     * @param typePersonneMorale type de personne morale à créer
     * @return type de personne morale créé
     */
    @Override
    public TypePersonneMorale createTypePersonneMorale(TypePersonneMorale typePersonneMorale) {
        return typePersonneMoraleRepository.save(typePersonneMorale);
    }

    /**
     * Récupère un type de personne morale par son identifiant.
     *
     * @param id identifiant du type de personne morale
     * @return type de personne morale correspondant
     */
    @Override
    public TypePersonneMorale getTypePersonneMoraleById(Long id) {
        return typePersonneMoraleRepository.findById(id).orElseThrow();
    }

    /**
     * Met à jour un type de personne morale existant.
     *
     * @param typePersonneMorale type de personne morale à mettre à jour
     * @return type de personne morale mis à jour
     */
    @Override
    public TypePersonneMorale updateTypePersonneMorale(TypePersonneMorale typePersonneMorale) {
        return typePersonneMoraleRepository.save(typePersonneMorale);
    }

    /**
     * Supprime un type de personne morale par son identifiant.
     *
     * @param id identifiant du type de personne morale à supprimer
     */
    @Override
    public void deleteTypePersonneMoraleById(Long id) {
        typePersonneMoraleRepository.findById(id).orElseThrow();
        typePersonneMoraleRepository.deleteById(id);
    }
}
