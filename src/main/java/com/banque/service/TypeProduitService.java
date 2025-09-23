package com.banque.service;

import com.banque.model.TypeProduit;
import java.util.List;

public interface TypeProduitService {
    public List<TypeProduit> getAllTypesProduits();
    public TypeProduit createTypeProduit(TypeProduit typeProduit);
    public TypeProduit getTypeProduitById(long id);
    public TypeProduit updateTypeProduit(TypeProduit typeProduit);
    public void deleteTypeProduitById(long id);
}