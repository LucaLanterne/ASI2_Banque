package com.banque.service.impl;

import com.banque.model.TypeProduit;
import com.banque.repository.TypeProduitRepository;
import com.banque.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service pour gérer les types de produits bancaires.
 * Fournit les opérations CRUD standard.
 */
@Service
public class TypeProduitServiceImpl implements TypeProduitService {

    private final TypeProduitRepository typeProduitRepository;

    /**
     * Constructeur pour l'injection du repository TypeProduitRepository.
     *
     * @param typeProduitRepository repository pour les types de produits
     */
    @Autowired
    public TypeProduitServiceImpl(TypeProduitRepository typeProduitRepository) {
        this.typeProduitRepository = typeProduitRepository;
    }

    /**
     * Récupère tous les types de produits.
     *
     * @return liste de tous les types de produits
     */
    @Override
    public List<TypeProduit> getAllTypesProduits() {
        return typeProduitRepository.findAll();
    }

    /**
     * Crée un nouveau type de produit.
     *
     * @param typeProduit type de produit à créer
     * @return type de produit créé
     */
    @Override
    public TypeProduit createTypeProduit(TypeProduit typeProduit) {
        return typeProduitRepository.save(typeProduit);
    }

    /**
     * Récupère un type de produit par son identifiant.
     *
     * @param id identifiant du type de produit
     * @return type de produit correspondant
     */
    @Override
    public TypeProduit getTypeProduitById(long id) {
        return typeProduitRepository.findById(id).orElseThrow();
    }

    /**
     * Met à jour un type de produit existant.
     *
     * @param typeProduit type de produit à mettre à jour
     * @return type de produit mis à jour
     */
    @Override
    public TypeProduit updateTypeProduit(TypeProduit typeProduit) {
        return typeProduitRepository.save(typeProduit);
    }

    /**
     * Supprime un type de produit par son identifiant.
     *
     * @param id identifiant du type de produit à supprimer
     */
    @Override
    public void deleteTypeProduitById(long id) {
        typeProduitRepository.findById(id).orElseThrow();
        typeProduitRepository.deleteById(id);
    }
}
