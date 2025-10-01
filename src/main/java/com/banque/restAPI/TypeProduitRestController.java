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

/**
 * Contrôleur REST pour gérer les types de produits bancaires.
 * Fournit des endpoints pour récupérer, créer, mettre à jour et supprimer des types de produits.
 */
@RestController
@RequestMapping("/banque_rest/TypeProduit")
public class TypeProduitRestController {

    private final TypeProduitService typeProduitService;
    private final TypeProduitMapper typeProduitMapper;

    /**
     * Constructeur pour l'injection des services et du mapper.
     *
     * @param typeProduitService service pour gérer les types de produits
     * @param typeProduitMapper mapper pour convertir les entités TypeProduit en DTO et inversement
     */
    @Autowired
    public TypeProduitRestController(TypeProduitService typeProduitService, TypeProduitMapper typeProduitMapper) {
        this.typeProduitService = typeProduitService;
        this.typeProduitMapper = typeProduitMapper;
    }

    /**
     * Récupère tous les types de produits bancaires.
     * Mapping GET sur /banque_rest/TypeProduit
     *
     * @return liste des types de produits convertis en DTO
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TypeProduitDto>> getAllTypesProduits() {
        List<TypeProduitDto> liste = typeProduitService.getAllTypesProduits()
                .stream()
                .map(typeProduitMapper::toDto)
                .collect(toList());
        return new ResponseEntity<>(liste, HttpStatus.CREATED);
    }

    /**
     * Récupère un type de produit par son ID.
     * Mapping GET sur /banque_rest/TypeProduit/{id}
     *
     * @param id identifiant du type de produit
     * @return type de produit converti en DTO
     */
    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<TypeProduitDto> getTypeProduitById(@PathVariable("id") long id) {
        TypeProduit typeProduit = typeProduitService.getTypeProduitById(id);
        return new ResponseEntity<>(typeProduitMapper.toDto(typeProduit), HttpStatus.OK);
    }

    /**
     * Crée un nouveau type de produit bancaire.
     * Mapping POST sur /banque_rest/TypeProduit/add
     * Redirige vers la création du DTO correspondant.
     *
     * @param typeProduitDto DTO contenant les données du type de produit à créer
     * @return DTO du type de produit créé
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<TypeProduitDto> addTypeProduit(@RequestBody TypeProduitDto typeProduitDto) {
        TypeProduit typeProduit = typeProduitMapper.toEntity(typeProduitDto);
        typeProduitService.createTypeProduit(typeProduit);
        return new ResponseEntity<>(typeProduitMapper.toDto(typeProduit), HttpStatus.CREATED);
    }

    /**
     * Met à jour un type de produit existant.
     * Mapping PUT sur /banque_rest/TypeProduit/update
     * Redirige vers la mise à jour du type de produit.
     *
     * @param typeProduit entité contenant les nouvelles valeurs du type de produit
     * @return type de produit mis à jour
     */
    @PutMapping("/update")
    public ResponseEntity<TypeProduit> updateTypeProduit(@RequestBody TypeProduit typeProduit) {
        TypeProduit tp = typeProduitService.updateTypeProduit(typeProduit);
        return new ResponseEntity<>(tp, HttpStatus.OK);
    }

    /**
     * Supprime un type de produit par son ID.
     * Mapping DELETE sur /banque_rest/TypeProduit/delete/{id}
     *
     * @param id identifiant du type de produit à supprimer
     * @return message confirmant la suppression
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTypeProduit(@PathVariable("id") long id) {
        typeProduitService.deleteTypeProduitById(id);
        return new ResponseEntity<>("Suppression OK", HttpStatus.OK);
    }
}
