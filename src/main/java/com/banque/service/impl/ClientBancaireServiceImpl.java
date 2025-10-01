package com.banque.service.impl;

import com.banque.model.ClientBancaire;
import com.banque.repository.ClientBancaireRepository;
import com.banque.service.ClientBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation du service pour gérer les clients bancaires.
 * Fournit les opérations CRUD via le repository ClientBancaireRepository.
 */
@Service
public class ClientBancaireServiceImpl implements ClientBancaireService {

    @Autowired
    private ClientBancaireRepository clientBancaireRepository;

    /**
     * Récupère tous les clients bancaires.
     *
     * @return liste des clients bancaires
     */
    @Override
    public List<ClientBancaire> getAllClientsBancaires() {
        return clientBancaireRepository.findAll();
    }

    /**
     * Crée un nouveau client bancaire.
     *
     * @param clientBancaire client à créer
     * @return client créé
     */
    @Override
    public ClientBancaire createClientBancaire(ClientBancaire clientBancaire) {
        return clientBancaireRepository.save(clientBancaire);
    }

    /**
     * Récupère un client bancaire par son identifiant.
     *
     * @param id identifiant du client
     * @return client correspondant
     */
    @Override
    public ClientBancaire getClientBancaireById(Long id) {
        return clientBancaireRepository.findById(id).orElseThrow();
    }

    /**
     * Met à jour un client bancaire existant.
     *
     * @param clientBancaire client à mettre à jour
     * @return client mis à jour
     */
    @Override
    public ClientBancaire updateClientBancaire(ClientBancaire clientBancaire) {
        return clientBancaireRepository.save(clientBancaire);
    }

    /**
     * Supprime un client bancaire par son identifiant.
     *
     * @param id identifiant du client à supprimer
     */
    @Override
    public void deleteClientBancaireByID(Long id) {
        clientBancaireRepository.findById(id).orElseThrow();
        clientBancaireRepository.deleteById(id);
    }
}
