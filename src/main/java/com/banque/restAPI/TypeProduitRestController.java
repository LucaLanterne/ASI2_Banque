package com.banque.restAPI;

import com.banque.model.TypeProduit;
import com.banque.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banque_rest/TypeProduit")
public class TypeProduitRestController {
    private final TypeProduitService typeProduitService;

    @Autowired
    public TypeProduitRestController(TypeProduitService typeProduitService) {
        this.typeProduitService = typeProduitService;
    }

    // exemple URL : http://localhost:8080/banque_rest/TypeProduit
    @GetMapping
    public ResponseEntity<List<TypeProduit>> getAllTypeProduit()
    {
        List<TypeProduit> liste= typeProduitService.getAllTypesProduits();
        return new ResponseEntity<List<TypeProduit>>(liste, HttpStatus.CREATED);
    }

    // exemple URL : http://localhost:8080/banque_rest/TypeProduit/1
    @GetMapping("{id}")
    public ResponseEntity<TypeProduit> getTypeProduitById(@PathVariable("id") long idTypeProduit)
    {
        TypeProduit cg= typeProduitService.getTypeProduitById(idTypeProduit);
        return new ResponseEntity<TypeProduit>(cg, HttpStatus.OK);
    }

    // exemple URL : http://localhost:8080/banque_rest/TypeProduit/add
    @PostMapping("/add")
    public ResponseEntity<TypeProduit> addTypeProduit(@RequestBody TypeProduit typeProduit)
    {
        TypeProduit tp= typeProduitService.createTypeProduit(typeProduit);
        return new ResponseEntity<TypeProduit>(tp, HttpStatus.CREATED);
    }

    // exemple URL : http://localhost:8080/banque_rest/TypeProduit/update
    @PutMapping("/update")
    public ResponseEntity<TypeProduit> updateTypeProduit(@RequestBody TypeProduit typeProduit)
    {
        TypeProduit tp=typeProduitService.updateTypeProduit(typeProduit);
        return new ResponseEntity<TypeProduit>(tp, HttpStatus.OK);
    }

    // exemple URL : http://localhost:8080/banque_rest/TypeProduit/delete/2
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTypeProduit(@PathVariable("id") long id)
    {
        typeProduitService.deleteTypeProduitById(id);
        return new ResponseEntity<String>("Suppression OK",HttpStatus.OK);
    }
}