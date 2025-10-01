package com.banque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banque.model.TypeProduit;

import java.util.ArrayList;

/**
 * Repository JPA pour gérer les entités {@link TypeProduit}.
 * Fournit des méthodes pour rechercher des types de produits bancaires selon différents critères.
 */
public interface TypeProduitRepository extends JpaRepository<TypeProduit, Long> {

    /**
     * Recherche un type de produit par son intitulé exact.
     *
     * @param intitule intitulé exact du type de produit
     * @return type de produit correspondant
     */
    TypeProduit findByIntitule(String intitule);

    /**
     * Recherche les types de produits dont l'intitulé contient la chaîne donnée (LIKE SQL).
     *
     * @param typeProduit chaîne partielle à rechercher dans l'intitulé
     * @return liste des types de produits correspondants
     */
    ArrayList<TypeProduit> findByIntituleLike(String typeProduit);

    /**
     * Recherche les types de produits dont l'intitulé contient la chaîne donnée.
     *
     * @param typeProduit chaîne partielle à rechercher
     * @return liste des types de produits correspondants
     */
    ArrayList<TypeProduit> findByIntituleContains(String typeProduit);

    /**
     * Récupère les 3 derniers types de produits créés, triés par ID décroissant.
     *
     * @return liste des 3 derniers types de produits
     */
    ArrayList<TypeProduit> findFirst3ByOrderByIdDesc();

    /**
     * Recherche les types de produits dont le taux d'intérêt agios est supérieur ou égal à la valeur donnée,
     * triés par taux d'intérêt croissant.
     *
     * @param taux valeur minimale du taux d'intérêt agios
     * @return liste des types de produits correspondants
     */
    ArrayList<TypeProduit> findByTauxInteretAgiosGreaterThanEqualOrderByTauxInteretAgiosAsc(float taux);
}
