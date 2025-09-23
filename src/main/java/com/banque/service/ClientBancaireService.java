package com.banque.service;

import com.banque.model.ClientBancaire;

import java.util.List;

public interface ClientBancaireService {
    public List<ClientBancaire> getAllClientsBancaires();
    public ClientBancaire createClientBancaire(ClientBancaire clientBancaire);
    public ClientBancaire getClientBancaireById(Long id);
    public ClientBancaire updateClientBancaire(ClientBancaire clientBancaire);
    public void deleteClientBancaireByID(Long id);
}
