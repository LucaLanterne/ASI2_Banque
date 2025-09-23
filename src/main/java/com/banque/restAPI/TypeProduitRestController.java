package com.banque.restAPI;

import com.banque.dataTransfertObjects.TypeProduitDto;
import com.banque.mapper.TypeProduitMapper;
import com.banque.model.TypeProduit;
import com.banque.service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/banque_rest/TypeProduit")
public class TypeProduitRestController
{
    private final TypeProduitService typeProduitService;
    private final TypeProduitMapper typeProduitMapper;

    @Autowired
    public TypeProduitRestController(TypeProduitService typeProduitService, TypeProduitMapper typeProduitMapper)
    {
        this.typeProduitService = typeProduitService;
        this.typeProduitMapper = typeProduitMapper;
    }

    //    // exemple URL : http://localhost:8080/banque_rest/TypeProduit
//    @GetMapping
//    public ResponseEntity<List<TypeProduit>> getAllTypeProduit()
//    {
//        List<TypeProduit> liste= typeProduitService.getAllTypesProduits();
//        return new ResponseEntity<List<TypeProduit>>(liste, HttpStatus.CREATED);
//    }
    // Version du getAll qui utilise le mapper et les dtos
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TypeProduitDto>> getAllTypesProduits()
    {
        List<TypeProduitDto> liste= typeProduitService.getAllTypesProduits()
                .stream()
                .map(typeProduitMapper::toDto)
                .collect(toList());
        return new ResponseEntity<List<TypeProduitDto>>(liste, HttpStatus.CREATED);
    }

    // exemple URL : http://localhost:8080/banque_rest/TypeProduit/1
//    @GetMapping("{id}")
//    public ResponseEntity<TypeProduit> getTypeProduitById(@PathVariable("id") long idTypeProduit)
//    {
//        TypeProduit typeProduit= typeProduitService.getTypeProduitById(idTypeProduit);
//        return new ResponseEntity<TypeProduit>(typeProduit, HttpStatus.OK);
//    }
    // Version du getById qui utilise le mapper et les dtos
    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<TypeProduitDto> getTypeProduitByIdWithMapper(@PathVariable("id") long id)
    {
        TypeProduit typeProduit= typeProduitService.getTypeProduitById(id);
        return new ResponseEntity<TypeProduitDto>(typeProduitMapper.toDto(typeProduit), HttpStatus.OK);
    }

    // exemple URL : http://localhost:8080/banque_rest/TypeProduit/add
//    @PostMapping("/add")
//    public ResponseEntity<TypeProduit> addTypeProduit(@RequestBody TypeProduit typeProduit)
//    {
//        TypeProduit tp= typeProduitService.createTypeProduit(typeProduit);
//        return new ResponseEntity<TypeProduit>(tp, HttpStatus.CREATED);
//    }
    // Version du add qui utilise le mapper et les dtos
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<TypeProduitDto> addTypeProduit(@RequestBody TypeProduitDto typeProduitDto)
    {
        TypeProduit typeProduit= typeProduitMapper.toEntity(typeProduitDto);
        typeProduitService.createTypeProduit(typeProduit);
        return new ResponseEntity<TypeProduitDto>(typeProduitMapper.toDto(typeProduit), HttpStatus.CREATED);
    }

//    // exemple URL : http://localhost:8080/banque_rest/TypeProduit/update
//    @PutMapping("/update")
//    public ResponseEntity<TypeProduit> updateTypeProduit(@RequestBody TypeProduit typeProduit)
//    {
//        TypeProduit tp=typeProduitService.updateTypeProduit(typeProduit);
//        return new ResponseEntity<TypeProduit>(tp, HttpStatus.OK);
//    }
    // Version du update qui utilise le mapper et les dtos
    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<TypeProduitDto> updateTypeProduit(@RequestBody TypeProduitDto typeProduitDto)
    {
        TypeProduit typeProduit =  typeProduitMapper.toEntity(typeProduitDto);
        typeProduitService.updateTypeProduit(typeProduit);
        return new ResponseEntity<TypeProduitDto>(typeProduitMapper.toDto(typeProduit), HttpStatus.OK);
    }

    // exemple URL : http://localhost:8080/banque_rest/TypeProduit/delete/2
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTypeProduit(@PathVariable("id") long id)
    {
        typeProduitService.deleteTypeProduitById(id);
        return new ResponseEntity<String>("Suppression OK",HttpStatus.OK);
    }
}