package com.banque.service;

import com.banque.model.ClientBancaire;

import java.util.List;

/**
 * Service pour gérer les opérations CRUD sur les clients bancaires.
 * Définit les méthodes à implémenter par le service concret.
 */
public interface ClientBancaireService {

    /**
     * Récupère tous les clients bancaires.
     *
     * @return liste de tous les clients
     */
    List<ClientBancaire> getAllClientsBancaires();

    /**
     * Crée un nouveau client bancaire.
     *
     * @param clientBancaire client à créer
     * @return client créé
     */
    ClientBancaire createClientBancaire(ClientBancaire clientBancaire);

    /**
     * Récupère un client bancaire par son ID.
     *
     * @param id identifiant du client
     * @return client correspondant
     */
    ClientBancaire getClientBancaireById(Long id);

    /**
     * Met à jour un client bancaire existant.
     *
     * @param clientBancaire client à mettre à jour
     * @return client mis à jour
     */
    ClientBancaire updateClientBancaire(ClientBancaire clientBancaire);

    /**
     * Supprime un client bancaire par son ID.
     *
     * @param id identifiant du client à supprimer
     */
    void deleteClientBancaireByID(Long id);
}
