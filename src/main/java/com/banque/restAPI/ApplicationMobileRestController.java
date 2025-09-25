package com.banque.restAPI;

import com.banque.dataTransfertObjects.OperationDto;
import com.banque.mapper.OperationMapper;
import com.banque.model.Operation;
import com.banque.service.OperationService;
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

    @Autowired
    public ApplicationMobileRestController(OperationMapper operationMapper,  OperationService operationService)
    {
        this.operationMapper = operationMapper;
        this.operationService = operationService;
    }

    @GetMapping("produit/{produitBancaireId}/operations/last5")
    @ResponseBody
    public ResponseEntity<List<OperationDto>> getLast5Operations(@PathVariable("produitBancaireId") long produitBancaireId)
    {
        List<Operation> operations = operationService.get5DerniereOperationsByProduitBancaireId(produitBancaireId);
        return new ResponseEntity<List<OperationDto>>(operationMapper.toDtoList(operations), HttpStatus.OK);

    }
}
