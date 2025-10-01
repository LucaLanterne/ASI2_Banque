package com.banque;

import com.banque.model.*;
import com.banque.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.List;

/**
 * Classe principale de l'application Banque.
 * Cette classe initialise le contexte Spring Boot et configure un jeu de données
 * pour tester le backend via un CommandLineRunner.
 */
@SpringBootApplication
public class BanqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanqueApplication.class, args);
    }

    /**
     * Bean permettant de créer et d'initialiser un jeu de données en base
     * au démarrage de l'application. Utile pour tester les services et les REST controllers.
     *
     * @param typeProduitRepository Repository pour gérer les types de produits bancaires
     * @param personneMoraleRepository Repository pour gérer les personnes morales
     * @param personnePhysiqueRepository Repository pour gérer les personnes physiques
     * @param produitBancaireRepository Repository pour gérer les produits bancaires
     * @param typePersonneMoraleRepository Repository pour gérer les types de personnes morales
     * @param clientBancaireRepository Repository pour gérer les clients bancaires
     * @param operationRepository Repository pour gérer les opérations bancaires
     * @return CommandLineRunner qui initialise les données
     */
    @Bean
    CommandLineRunner testerBackend(
            TypeProduitRepository typeProduitRepository,
            PersonneMoraleRepository personneMoraleRepository,
            PersonnePhysiqueRepository personnePhysiqueRepository,
            ProduitBancaireRepository produitBancaireRepository,
            TypePersonneMoraleRepository typePersonneMoraleRepository,
            ClientBancaireRepository clientBancaireRepository,
            OperationRepository operationRepository) {

        return args -> {

            // ------------------- Création des types de produits -------------------

            TypeProduit compteCheque = new TypeProduit(0.0f, "Compte chèque", 15f);
            TypeProduit livretA = new TypeProduit(0.02f, "Livret A", 0f);
            TypeProduit pretImmo = new TypeProduit(0.03f, "Prêt immobilier", 0f);

            typeProduitRepository.saveAll(List.of(compteCheque, livretA, pretImmo));

            // Récupération depuis la base pour être sûr que les entités sont persistées
            compteCheque = typeProduitRepository.findById(compteCheque.getId()).orElseThrow();
            livretA = typeProduitRepository.findById(livretA.getId()).orElseThrow();
            pretImmo = typeProduitRepository.findById(pretImmo.getId()).orElseThrow();

            // ------------------- Création des clients bancaires -------------------

            ClientBancaire cb1 = new ClientBancaire();
            ClientBancaire cb2 = new ClientBancaire();
            clientBancaireRepository.saveAll(List.of(cb1, cb2));

            cb1 = clientBancaireRepository.findById(cb1.getId()).orElseThrow();
            cb2 = clientBancaireRepository.findById(cb2.getId()).orElseThrow();

            // ------------------- Création des produits bancaires rattachés aux clients -------------------

            ProduitBancaire pb1 = new ProduitBancaire(1000f, "FR76-0001", compteCheque, cb1);
            ProduitBancaire pb2 = new ProduitBancaire(5000f, "FR76-0002", livretA, cb1);
            ProduitBancaire pb3 = new ProduitBancaire(-200000f, "FR76-0003", pretImmo, cb2);

            produitBancaireRepository.saveAll(List.of(pb1, pb2, pb3));

            pb1 = produitBancaireRepository.findById(pb1.getId()).orElseThrow();
            pb2 = produitBancaireRepository.findById(pb2.getId()).orElseThrow();
            pb3 = produitBancaireRepository.findById(pb3.getId()).orElseThrow();

            // ------------------- Création des personnes physiques -------------------

            PersonnePhysique pp1 = new PersonnePhysique("10 rue des fleurs", "Dupont", "Jean");
            PersonnePhysique pp2 = new PersonnePhysique("12 rue Victor Hugo", "Martin", "Alice");

            personnePhysiqueRepository.saveAll(List.of(pp1, pp2));

            pp1 = personnePhysiqueRepository.findById(pp1.getId()).orElseThrow();
            pp2 = personnePhysiqueRepository.findById(pp2.getId()).orElseThrow();

            // ------------------- Création des types de personnes morales -------------------

            TypePersonneMorale sa = new TypePersonneMorale("SA");
            TypePersonneMorale sarl = new TypePersonneMorale("SARL");

            typePersonneMoraleRepository.saveAll(List.of(sa, sarl));

            sa = typePersonneMoraleRepository.findById(sa.getId()).orElseThrow();
            sarl = typePersonneMoraleRepository.findById(sarl.getId()).orElseThrow();

            // ------------------- Création des personnes morales -------------------

            PersonneMorale pm1 = new PersonneMorale("20 avenue Paris", "SIRET001", "Boulangerie du coin", sa);
            PersonneMorale pm2 = new PersonneMorale("30 rue Nationale", "SIRET002", "Menuiserie artisanale", sarl);

            personneMoraleRepository.saveAll(List.of(pm1, pm2));

            pm1 = personneMoraleRepository.findById(pm1.getId()).orElseThrow();
            pm2 = personneMoraleRepository.findById(pm2.getId()).orElseThrow();

            // ------------------- Association personnes ↔ clients -------------------

            cb1.addPersonne(pp1);
            cb1.addPersonne(pm1);
            clientBancaireRepository.save(cb1);

            cb2.addPersonne(pp2);
            cb2.addPersonne(pm2);
            clientBancaireRepository.save(cb2);

            // ------------------- Création des opérations pour le compte pb1 -------------------

            Operation op1 = new Operation(Date.valueOf("2023-01-10"), -50.0f, "Débit", "Retrait DAB");
            Operation op2 = new Operation(Date.valueOf("2023-02-05"), 1500.0f, "Crédit", "Virement salaire");
            Operation op3 = new Operation(Date.valueOf("2023-03-12"), -120.5f, "Débit", "Paiement CB");
            Operation op4 = new Operation(Date.valueOf("2023-04-01"), -600.0f, "Débit", "Loyer");
            Operation op5 = new Operation(Date.valueOf("2023-05-20"), -40.0f, "Débit", "Internet");
            Operation op6 = new Operation(Date.valueOf("2023-06-15"), 75.0f, "Crédit", "Remboursement ami");

            op1.setProduitBancaire(pb1);
            op2.setProduitBancaire(pb1);
            op3.setProduitBancaire(pb1);
            op4.setProduitBancaire(pb1);
            op5.setProduitBancaire(pb1);
            op6.setProduitBancaire(pb1);

            operationRepository.saveAll(List.of(op1, op2, op3, op4, op5, op6));
        };
    }
}
