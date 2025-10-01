package com.banque.restAPI;

import com.banque.dataTransfertObjects.ProduitBancaireDto;
import com.banque.mapper.ProduitBancaireMapper;
import com.banque.model.ClientBancaire;
import com.banque.model.ProduitBancaire;
import com.banque.model.TypeProduit;
import com.banque.repository.ClientBancaireRepository;
import com.banque.repository.ProduitBancaireRepository;
import com.banque.repository.TypeProduitRepository;
import com.banque.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Contrôleur REST pour gérer les produits bancaires.
 * Fournit des endpoints pour récupérer tous les produits et créer des produits d'exemple.
 */
@RestController
@RequestMapping("/banque_rest/ProduitBancaire")
public class ProduitBancaireRestController {

    private final ProduitBancaireService produitBancaireService;
    private final TypeProduitRepository typeProduitRepository;
    private final ClientBancaireRepository clientBancaireRepository;
    private final ProduitBancaireRepository produitBancaireRepository;
    private final ProduitBancaireMapper produitBancaireMapper;

    /**
     * Constructeur pour l'injection des services et repositories nécessaires.
     *
     * @param produitBancaireService service pour gérer les produits bancaires
     * @param typeProduitRepository repository pour accéder aux types de produits
     * @param clientBancaireRepository repository pour accéder aux clients bancaires
     * @param produitBancaireRepository repository pour accéder aux produits bancaires
     * @param produitBancaireMapper mapper pour convertir les produits bancaires en DTO
     */
    @Autowired
    public ProduitBancaireRestController(ProduitBancaireService produitBancaireService,
                                         TypeProduitRepository typeProduitRepository,
                                         ClientBancaireRepository clientBancaireRepository,
                                         ProduitBancaireRepository produitBancaireRepository,
                                         ProduitBancaireMapper produitBancaireMapper) {
        this.produitBancaireService = produitBancaireService;
        this.typeProduitRepository = typeProduitRepository;
        this.clientBancaireRepository = clientBancaireRepository;
        this.produitBancaireRepository = produitBancaireRepository;
        this.produitBancaireMapper = produitBancaireMapper;
    }

    /**
     * Récupère tous les produits bancaires existants.
     * Mapping GET sur /banque_rest/ProduitBancaire
     *
     * @return liste des produits bancaires sous forme de DTO
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProduitBancaireDto>> getAllProduitsBancaires() {
        List<ProduitBancaireDto> liste = produitBancaireService.getAllProduitsBancaires()
                .stream()
                .map(produitBancaireMapper::toDto)
                .collect(toList());
        return new ResponseEntity<>(liste, HttpStatus.CREATED);
    }

    /**
     * Crée des produits bancaires d'exemple pour la base de données à des fins de tests.
     * Mapping POST sur /banque_rest/ProduitBancaire/createProduitsBancairesTest
     *
     * <p>
     * - Récupère les types de produits et clients existants.
     * - Crée trois produits bancaires avec des valeurs prédéfinies.
     * - Sauvegarde les produits en base.
     * </p>
     * @return liste des produits bancaires créés
     */
    @PostMapping("/createProduitsBancairesTests")
    @ResponseBody
    public ResponseEntity<List<ProduitBancaire>> createProduitsBancairesTests() {
        // Récupération des types de produits
        TypeProduit compteCheque = typeProduitRepository.findByIntitule("Compte chèque");
        TypeProduit livretA = typeProduitRepository.findByIntitule("Livret A");
        TypeProduit pretImmo = typeProduitRepository.findByIntitule("Prêt immobilier");

        // Récupération des clients bancaires (id 1 et 2)
        ClientBancaire cb1 = clientBancaireRepository.findById(1L).orElseThrow();
        ClientBancaire cb2 = clientBancaireRepository.findById(2L).orElseThrow();

        // Création des produits bancaires avec nouvelles valeurs
        ProduitBancaire pb1 = new ProduitBancaire(2500f, "FR76-1001", compteCheque, cb1);
        ProduitBancaire pb2 = new ProduitBancaire(8000f, "FR76-1002", livretA, cb1);
        ProduitBancaire pb3 = new ProduitBancaire(-150000f, "FR76-1003", pretImmo, cb2);

        // Sauvegarde en base
        List<ProduitBancaire> produits = Arrays.asList(pb1, pb2, pb3);
        produitBancaireRepository.saveAll(produits);

        return new ResponseEntity<>(produits, HttpStatus.CREATED);
    }
}
