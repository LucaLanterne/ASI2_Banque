package com.banque.service;

import com.banque.model.Operation;
import com.banque.model.ProduitBancaire;

import java.util.List;

public interface ProduitBancaireService {
    public List<ProduitBancaire>  getAllProduitsBancaires();
    public ProduitBancaire createProduitBancaire(ProduitBancaire produitBancaire);
    public ProduitBancaire getProduitBancaireById(Long id);
    public ProduitBancaire updateProduitBancaire(ProduitBancaire produitBancaire);
    public void deleteProduitBancaireById(Long id);
    public ProduitBancaire getCompteChequeById(Long id);
}
