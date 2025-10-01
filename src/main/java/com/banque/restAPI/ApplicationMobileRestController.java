package com.banque.restAPI;

import com.banque.dataTransfertObjects.OperationDto;
import com.banque.dataTransfertObjects.ProduitBancaireDto;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur REST pour l'application mobile de la banque.
 * Fournit des endpoints pour récupérer les opérations, informations sur les comptes
 * et effectuer des virements entre produits bancaires.
 */
@RestController
@RequestMapping("banque_rest/ApplicationMobile")
public class ApplicationMobileRestController {

    private final OperationMapper operationMapper;
    private final OperationService operationService;
    private final ProduitBancaireMapper produitBancaireMapper;
    private final ProduitBancaireService produitBancaireService;

    /**
     * Constructeur pour l'injection des services et mappers nécessaires.
     *
     * @param operationMapper mapper pour transformer les opérations en DTO
     * @param operationService service pour gérer les opérations
     * @param produitBancaireMapper mapper pour transformer les produits bancaires en DTO
     * @param produitBancaireService service pour gérer les produits bancaires
     */
    @Autowired
    public ApplicationMobileRestController(OperationMapper operationMapper,
                                           OperationService operationService,
                                           ProduitBancaireMapper produitBancaireMapper,
                                           ProduitBancaireService produitBancaireService) {
        this.operationMapper = operationMapper;
        this.operationService = operationService;
        this.produitBancaireMapper = produitBancaireMapper;
        this.produitBancaireService = produitBancaireService;
    }

    /**
     * Récupère les 5 dernières opérations pour un produit bancaire donné.
     * Mapping GET sur /produits/{produitBancaireId}/operations/last5
     *
     * @param produitBancaireId ID du produit bancaire
     * @return liste des 5 dernières opérations sous forme de DTO
     */
    @GetMapping("produits/{produitBancaireId}/operations/last5")
    public ResponseEntity<List<OperationDto>> getLast5Operations(@PathVariable("produitBancaireId") long produitBancaireId) {
        List<Operation> operations = operationService.get5DerniereOperationsByProduitBancaireId(produitBancaireId);
        return new ResponseEntity<>(operationMapper.toDtoList(operations), HttpStatus.OK);
    }

    /**
     * Récupère les informations d'un compte chèque spécifique.
     * Mapping GET sur /comptes/chèque/{id}
     *
     * @param id ID du compte chèque
     * @return produit bancaire sous forme de DTO
     */
    @GetMapping("/comptes/chèque/{id}")
    public ResponseEntity<ProduitBancaireDto> getCompteChequeInfo(@PathVariable Long id) {
        ProduitBancaire produitBancaire = produitBancaireService.getCompteChequeById(id);
        return new ResponseEntity<>(produitBancaireMapper.toDto(produitBancaire), HttpStatus.OK);
    }

    /**
     * Récupère les informations d'un produit bancaire par son ID.
     * Mapping GET sur /comptes/{id}
     *
     * @param id ID du produit bancaire
     * @return produit bancaire sous forme de DTO
     */
    @GetMapping("/comptes/{id}")
    public ResponseEntity<ProduitBancaireDto> getProduitBancaire(@PathVariable Long id) {
        ProduitBancaire produitBancaire = produitBancaireService.getProduitBancaireById(id);
        return new ResponseEntity<>(produitBancaireMapper.toDto(produitBancaire), HttpStatus.OK);
    }

    /**
     * Effectue un virement entre deux produits bancaires.
     * Mapping POST sur /virements/{sourceId}/{destId}/{montant}.
     * Crée une opération de débit sur le compte source et une opération de crédit sur le compte destination.
     * Met à jour les soldes des deux comptes.
     *
     * @param sourceId ID du produit bancaire source
     * @param destId ID du produit bancaire destinataire
     * @param montant montant du virement
     * @return liste des opérations créées sous forme de DTO
     */
    @PostMapping("/virements/{sourceId}/{destId}/{montant}")
    public ResponseEntity<List<OperationDto>> makeVirement(@PathVariable long sourceId,
                                                           @PathVariable long destId,
                                                           @PathVariable float montant) {
        ProduitBancaire source = produitBancaireService.getProduitBancaireById(sourceId);
        ProduitBancaire dest = produitBancaireService.getProduitBancaireById(destId);

        // Création des opérations de débit et crédit
        Operation debit = new Operation(new Date(System.currentTimeMillis()), -montant, "Débit", "Virement");
        debit.setProduitBancaire(source);
        Operation credit = new Operation(new Date(System.currentTimeMillis()), montant, "Crédit", "Virement");
        credit.setProduitBancaire(dest);

        List<Operation> operations = new ArrayList<>();
        operations.add(debit);
        operations.add(credit);

        // Sauvegarde des opérations
        for (Operation operation : operations) {
            operationService.createOperation(operation);
        }

        // Mise à jour des soldes
        source.setSolde_courant(source.getSolde_courant() - montant);
        dest.setSolde_courant(dest.getSolde_courant() + montant);

        produitBancaireService.updateProduitBancaire(source);
        produitBancaireService.updateProduitBancaire(dest);

        return new ResponseEntity<>(operationMapper.toDtoList(operations), HttpStatus.OK);
    }
}
