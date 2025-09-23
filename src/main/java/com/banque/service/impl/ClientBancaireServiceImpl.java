package com.banque.service.impl;

import com.banque.model.ClientBancaire;
import com.banque.repository.ClientBancaireRepository;
import com.banque.service.ClientBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientBancaireServiceImpl implements ClientBancaireService {

    @Autowired
    private ClientBancaireRepository clientBancaireRepository;

    @Override
    public List<ClientBancaire> getAllClientsBancaires() {
        return clientBancaireRepository.findAll();
    }

    @Override
    public ClientBancaire createClientBancaire(ClientBancaire clientBancaire) {
        return clientBancaireRepository.save(clientBancaire);
    }

    @Override
    public ClientBancaire getClientBancaireById(Long id) {
        return clientBancaireRepository.findById(id).orElseThrow();
    }

    @Override
    public ClientBancaire updateClientBancaire(ClientBancaire clientBancaire) {
        return clientBancaireRepository.save(clientBancaire);
    }

    @Override
    public void deleteClientBancaireByID(Long id) {
        clientBancaireRepository.findById(id).orElseThrow();
        clientBancaireRepository.deleteById(id);
    }
}
