package com.banque.service;

import com.banque.model.TypePersonneMorale;
import java.util.List;

public interface TypePersonneMoraleService {
    public List<TypePersonneMorale> getAllTypesPersonnesMorales();
    public TypePersonneMorale createTypePersonneMorale(TypePersonneMorale typePersonneMorale);
    public TypePersonneMorale getTypePersonneMoraleById(Long id);
    public TypePersonneMorale updateTypePersonneMorale(TypePersonneMorale typePersonneMorale);
    public void deleteTypePersonneMoraleById(Long id);
}