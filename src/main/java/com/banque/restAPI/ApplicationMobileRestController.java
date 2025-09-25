package com.banque.restAPI;

import com.banque.dataTransfertObjects.OperationDto;
import com.banque.dataTransfertObjects.ProduitBancaireDto;
import com.banque.mapper.ClientBancaireMapper;
import com.banque.mapper.OperationMapper;
import com.banque.mapper.ProduitBancaireMapper;
import com.banque.model.Operation;
import com.banque.model.ProduitBancaire;
import com.banque.service.OperationService;
import com.banque.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("banque_rest/ApplicationMobile")
public class ApplicationMobileRestController
{
    private final OperationMapper operationMapper;
    private final OperationService operationService;
    private final ProduitBancaireMapper produitBancaireMapper;
    private final ProduitBancaireService produitBancaireService;

    @Autowired
    public ApplicationMobileRestController(OperationMapper operationMapper,  OperationService operationService,
                                           ProduitBancaireMapper produitBancaireMapper,
                                           ProduitBancaireService produitBancaireService) {
        this.operationMapper = operationMapper;
        this.operationService = operationService;
        this.produitBancaireMapper = produitBancaireMapper;
        this.produitBancaireService = produitBancaireService;
    }

    @GetMapping("produit/{produitBancaireId}/operations/last5")
    @ResponseBody
    public ResponseEntity<List<OperationDto>> getLast5Operations(@PathVariable("produitBancaireId") long produitBancaireId) {
        List<Operation> operations = operationService.get5DerniereOperationsByProduitBancaireId(produitBancaireId);
        return new ResponseEntity<List<OperationDto>>(operationMapper.toDtoList(operations), HttpStatus.OK);
    }

    @GetMapping("/comptes/chèque/{id}")
    public ResponseEntity<ProduitBancaireDto> getCompteChequeInfo(@PathVariable Long id) {
        ProduitBancaire produitBancaire = produitBancaireService.getCompteChequeById(id);
        return new ResponseEntity<ProduitBancaireDto>(produitBancaireMapper.toDto(produitBancaire), HttpStatus.OK);
    }

}
