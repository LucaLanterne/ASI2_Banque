package com.banque.service.impl;

import com.banque.model.ProduitBancaire;
import com.banque.repository.ProduitBancaireRepository;
import com.banque.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service pour gérer les produits bancaires.
 * Fournit les opérations CRUD et des méthodes de recherche spécifiques.
 */
@Service
public class ProduitBancaireServiceImpl implements ProduitBancaireService {

    private final ProduitBancaireRepository produitBancaireRepository;

    /**
     * Constructeur pour l'injection du repository ProduitBancaireRepository.
     *
     * @param produitBancaireRepository repository pour les produits bancaires
     */
    @Autowired
    public ProduitBancaireServiceImpl(ProduitBancaireRepository produitBancaireRepository) {
        this.produitBancaireRepository = produitBancaireRepository;
    }

    /**
     * Récupère tous les produits bancaires.
     *
     * @return liste de tous les produits bancaires
     */
    @Override
    public List<ProduitBancaire> getAllProduitsBancaires() {
        return produitBancaireRepository.findAll();
    }

    /**
     * Crée un nouveau produit bancaire.
     *
     * @param produitBancaire produit bancaire à créer
     * @return produit bancaire créé
     */
    @Override
    public ProduitBancaire createProduitBancaire(ProduitBancaire produitBancaire) {
        return produitBancaireRepository.save(produitBancaire);
    }

    /**
     * Récupère un produit bancaire par son identifiant.
     *
     * @param id identifiant du produit bancaire
     * @return produit bancaire correspondant
     */
    @Override
    public ProduitBancaire getProduitBancaireById(Long id) {
        return produitBancaireRepository.findById(id).orElseThrow();
    }

    /**
     * Met à jour un produit bancaire existant.
     *
     * @param produitBancaire produit bancaire à mettre à jour
     * @return produit bancaire mis à jour
     */
    @Override
    public ProduitBancaire updateProduitBancaire(ProduitBancaire produitBancaire) {
        return produitBancaireRepository.save(produitBancaire);
    }

    /**
     * Supprime un produit bancaire par son identifiant.
     *
     * @param id identifiant du produit bancaire à supprimer
     */
    @Override
    public void deleteProduitBancaireById(Long id) {
        produitBancaireRepository.findById(id).orElseThrow();
        produitBancaireRepository.deleteById(id);
    }

    /**
     * Récupère un compte bancaire de type "Compte chèque" par son identifiant.
     *
     * @param id identifiant du produit bancaire
     * @return produit bancaire de type "Compte chèque"
     */
    @Override
    public ProduitBancaire getCompteChequeById(Long id) {
        return produitBancaireRepository.findByIdAndTypeProduitIntitule(id, "Compte chèque");
    }

    /**
     * Recherche les produits bancaires dont le numéro commence par une chaîne donnée (insensible à la casse).
     *
     * @param numeroComptePartiel début du numéro de compte
     * @return liste des produits bancaires correspondants
     */
    @Override
    public List<ProduitBancaire> getProduitBancaireByNumeroCompteStartsWithIgnoreCase(String numeroComptePartiel) {
        return produitBancaireRepository.findByNumeroCompteStartsWithIgnoreCase(numeroComptePartiel);
    }

    /**
     * Recherche le premier produit bancaire correspondant exactement au numéro de compte donné (insensible à la casse).
     *
     * @param numeroCompte numéro de compte exact
     * @return produit bancaire correspondant
     */
    @Override
    public ProduitBancaire findFirstByNumeroCompteIgnoreCase(String numeroCompte) {
        return produitBancaireRepository.findFirstByNumeroCompteIgnoreCase(numeroCompte);
    }
}
