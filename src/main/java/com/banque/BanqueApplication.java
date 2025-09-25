/*
package com.banque;

import com.banque.model.TypeProduit;
import com.banque.repository.TypeProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class BanqueApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BanqueApplication.class, args);
    }

    @Bean
    CommandLineRunner testerBackend(TypeProduitRepository typeProduitRepository)
    {
        return args -> {
            // On vide la table pour ne pas créer de doublons
            typeProduitRepository.deleteAll();

            // On crée une liste de types de produits que l'on insèrera d'un coup en base
            ArrayList<TypeProduit> listeTypeProduit = new ArrayList<TypeProduit>();

            // On crée quelques types de produits
            listeTypeProduit.add(new TypeProduit(
                    0,
                    "Mastercard",
                    20));
            listeTypeProduit.add(new TypeProduit(
                    0.3f,
                    "Livret épargne",
                    0));
            listeTypeProduit.add(new TypeProduit(
                    0.1f,
                    "Compte rémunéré",
                    0));
            listeTypeProduit.add(new TypeProduit(
                    0.5f,
                    "Prêt consommation",
                    0));
            listeTypeProduit.add(new TypeProduit(
                    0.3f,
                    "Prêt immobilier",
                    0));

            // On insère la liste dans la table
            typeProduitRepository.saveAll(listeTypeProduit);

            // Testons maintenant les requetes par defaut
            listeTypeProduit = new ArrayList<TypeProduit>();

            // Nombre de types de produits
            long nbTypeProduits = typeProduitRepository.count();
            System.out.println("\n***************** test count");
            System.out.println("\nil y a " + nbTypeProduits + " types de produits dans la base");

            // Rechercher un type de produit par id
            long id = 100;
            Optional<TypeProduit> typeProduit = typeProduitRepository.findById(id);
            System.out.println("\n***************** test findById");
            if (typeProduit.isPresent()) System.out.println("\nType de produit avec l'id : " + id + typeProduit);
            else System.out.println("Pas de type de produit avec la clé : " + id);

            // On récupère et on affiche les types de produits en base
            System.out.println("\n***************** Tous les types de produits non triees");
            System.out.println(typeProduitRepository.findAll());

            // On récupère les types de produits  en base triées par ordre alphabétique de l'intitulé
            System.out.println("\n***************** Tous les typesz de produits dans l'ordre alphabetique du typeProduit");
            System.out.println(typeProduitRepository.findAll(Sort.by(Sort.Direction.ASC, "intitule")));

            // Pagination
            int numeroPage = 3; // numéro de la page chargée. Attention, le numéro commence à 0. Ici, on récupère la quatrième page
            int taillePage = 1; // Nombre de types de produits par page.

            // L'instruction suivante récupère la 4ième page de données, chaque page contenant une seule donnée. Les données sont ici aussi triées par ordre alphabétique du type
            Page<TypeProduit> page = typeProduitRepository.findAll(PageRequest.of(3, 1, Sort.by(Sort.Direction.ASC, "intitule")));
            System.out.println("\n***************** Test pagination");
            System.out.println(page + "\n Contenu de la page" + page.getContent());

            // Test des queries
            System.out.println("*******************************\nRechercher les types de produits dont le type contient compte");
            listeTypeProduit=typeProduitRepository.findByIntituleContains("compte");
            System.out.println(listeTypeProduit);
            System.out.println("*******************************\nRechercher les types de produits dont le type contient compte");
            listeTypeProduit=typeProduitRepository.findByIntituleLike("%compte%");
            System.out.println(listeTypeProduit);
            System.out.println("*******************************\nRechercher les 3 derniers types de produits saisis en base");
            listeTypeProduit=typeProduitRepository.findFirst3ByOrderByIdDesc();
            System.out.println(listeTypeProduit);
            System.out.println("*******************************\nListe des produits dont la rentabilité est >=0.3 pour la banque");
            listeTypeProduit=typeProduitRepository.findByTauxInteretAgiosGreaterThanEqualOrderByTauxInteretAgiosAsc(0.3f);
            System.out.println(listeTypeProduit);
        };
    }
}
*/

package com.banque;

import com.banque.model.*;
import com.banque.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class BanqueApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BanqueApplication.class, args);
    }

    @Bean
    CommandLineRunner testerBackend(TypeProduitRepository typeProduitRepository,
                                    PersonneMoraleRepository personneMoraleRepository,
                                    PersonnePhysiqueRepository personnePhysiqueRepository,
                                    ProduitBancaireRepository produitBancaireRepository,
                                    TypePersonneMoraleRepository typePersonneMoraleRepository,
                                    ClientBancaireRepository clientBancaireRepository,
                                    OperationRepository operationRepository)
    {
        return args -> {
            //////////////////// Code précédent
            ////////////////////////////////////////

//            List<TypePersonneMorale> typesPersonneMorale;
//            List<PersonneMorale> personnesMorales;
//            TypePersonneMorale tpm1 = new TypePersonneMorale("Etat");
//            typePersonneMoraleRepository.save(tpm1);
//            TypePersonneMorale tpm2 = new TypePersonneMorale("Société privée");
//            typePersonneMoraleRepository.save(tpm2);
//            TypePersonneMorale tpm3 = new TypePersonneMorale("Société civile");
//            typePersonneMoraleRepository.save(tpm3);
//
//
//            // Création de trois personnes morales
//            tpm1 = typePersonneMoraleRepository.findById(tpm1.getId()).orElseThrow();
//            PersonneMorale pm1 = new PersonneMorale("102bis rue du Vesuve", "SIRET1", "Pizza Tonio", tpm1);
//            personneMoraleRepository.save(pm1);
//            tpm2 = typePersonneMoraleRepository.findById(tpm2.getId()).orElseThrow();
//            PersonneMorale pm2 = new PersonneMorale("45 Boulevard du fleuve", "SIRET2", "Meubles cosy", tpm2);
//            personneMoraleRepository.save(pm2);
//            tpm3 = typePersonneMoraleRepository.findById(tpm3.getId()).orElseThrow();
//            PersonneMorale pm3 = new PersonneMorale("14 allee des platanes", "SIRET3", "Espaces tres verts", tpm1);
//            personneMoraleRepository.save(pm3);
//            System.out.println(personneMoraleRepository.findAll());
//
//            // Ajout de personnes physiques
//            PersonnePhysique pp1 = new PersonnePhysique("19 rue des fleurs, 80000 Amiens", "Dupont", "Jean");
//            personnePhysiqueRepository.save(pp1);
//            PersonnePhysique pp2 = new PersonnePhysique("143 boulevard des landes, 64200 Anglet", "Eche", "Piou");
//            personnePhysiqueRepository.save(pp2);
//            PersonnePhysique pp3 = new PersonnePhysique("56 avenue de Paris, 60000 Beauvais", "Tristan", "Jacques");
//            personnePhysiqueRepository.save(pp3);
//            System.out.println(personnePhysiqueRepository.findAll());
//
//            List<TypeProduit> typesProduits;
//            List<ProduitBancaire> produitBancaires;
//            TypeProduit tp1=new TypeProduit((float)0.2,"tp1",0);
//            typeProduitRepository.save(tp1);
//            TypeProduit tp2=new TypeProduit(3,"tp2",0);
//            typeProduitRepository.save(tp2);
//            TypeProduit tp3=new TypeProduit(0,"tp3",15);
//            typeProduitRepository.save(tp3);
//
//            tp3= typeProduitRepository.findById(tp3.getId()).orElseThrow();
//            ProduitBancaire pb1 = new ProduitBancaire(1,"num1", tp3);
//            produitBancaireRepository.save(pb1);
//            tp2= typeProduitRepository.findById(tp2.getId()).orElseThrow();
//            ProduitBancaire pb2 = new ProduitBancaire(2,"num2",tp2);
//            produitBancaireRepository.save(pb2);
//            tp3= typeProduitRepository.findById(tp3.getId()).orElseThrow();
//            ProduitBancaire pb3 = new ProduitBancaire(3,"num3",tp3);
//            produitBancaireRepository.save(pb3);
//
//            typesProduits=typeProduitRepository.findAll();
//            produitBancaires=produitBancaireRepository.findAll();
//            System.out.println(produitBancaires);
//
//            pb1=produitBancaireRepository.findById(pb1.getId()).orElseThrow();
//            produitBancaireRepository.deleteById(pb1.getId());
//            if (typeProduitRepository.existsById(tp3.getId())){
//                System.out.println("tp3 est toujours dans la base");
//            }
//            else{
//                System.out.println("tp3 n'existe plus");
//            }
//            if (produitBancaireRepository.existsById(pb1.getId())) {
//                System.out.println("pb1 est toujours dans la base");
//            }
//            else {
//                System.out.println("pb1 n'existe plus");
//            }
//
//            if (produitBancaireRepository.existsById(pb3.getId())) {
//                System.out.println("pb3 est toujours dans la base");
//            }
//            else {
//                System.out.println("pb3 n'existe plus");
//            }
//
//            //Tests delete pour PersonneMorale
//            pm1 = personneMoraleRepository.findById(pm1.getId()).orElseThrow();
//            personneMoraleRepository.deleteById(pm1.getId());
//            if (typePersonneMoraleRepository.existsById(tpm1.getId())){
//                System.out.println("tpm1 est toujours dans la base");
//            }
//            else{
//                System.out.println("tpm1 n'existe plus");
//            }
//            if (personneMoraleRepository.existsById(pm1.getId())) {
//                System.out.println("pm1 est toujours dans la base");
//            }
//            else {
//                System.out.println("pm1 n'existe plus");
//            }
//
//            if (personneMoraleRepository.existsById(pm3.getId())) {
//                System.out.println("pm3 est toujours dans la base");
//            }
//            else {
//                System.out.println("pm3 n'existe plus");
//            }

//            List<TypeProduit> typesProduits;
//            List<ProduitBancaire> produitBancaires;
//            TypeProduit tp1=new TypeProduit((float)0.2,"tp1",0);
//            typeProduitRepository.save(tp1);
//            TypeProduit tp2=new TypeProduit(3,"tp2",0);
//            typeProduitRepository.save(tp2);
//            TypeProduit tp3=new TypeProduit(0,"tp3",15);
//            typeProduitRepository.save(tp3);
//
//            // Création de produits bancaires
//            tp3= typeProduitRepository.findById(tp3.getId()).orElseThrow();
//            ProduitBancaire pb1 = new ProduitBancaire(3,"num6",tp3);
//            produitBancaireRepository.save(pb1);
//            tp2= typeProduitRepository.findById(tp2.getId()).orElseThrow();
//            ProduitBancaire pb2 = new ProduitBancaire(1,"num4",tp2);
//            produitBancaireRepository.save(pb2);
//            tp3= typeProduitRepository.findById(tp3.getId()).orElseThrow();
//            ProduitBancaire pb3 = new ProduitBancaire(2,"num5",tp3);
//            produitBancaireRepository.save(pb3);
//
//            // Création de personnes physiques
//            PersonnePhysique pp1=new PersonnePhysique("adresse_pp1","nom_pp1","prenom_pp1");
//            personnePhysiqueRepository.save(pp1);
//            PersonnePhysique pp2=new PersonnePhysique("adresse_pp2","nom_pp2","prenom_pp2");
//            personnePhysiqueRepository.save(pp2);
//            PersonnePhysique pp3=new PersonnePhysique("adresse_pp3","nom_pp3","prenom_pp3");
//            personnePhysiqueRepository.save(pp3);
//
//            // Création de types de personnes morales
//            TypePersonneMorale tpm1=new TypePersonneMorale("SA");
//            typePersonneMoraleRepository.save(tpm1);
//            TypePersonneMorale tpm2=new TypePersonneMorale("SARL");
//            typePersonneMoraleRepository.save(tpm2);
//            TypePersonneMorale tpm3=new TypePersonneMorale("Auto Entrepreneur");
//            typePersonneMoraleRepository.save(tpm3);
//
//            // Création de personnes morales
//            PersonneMorale pm1=new PersonneMorale("pm1", "SiRET pm1","raisonsoc pm1", tpm1);
//            personneMoraleRepository.save(pm1);
//            PersonneMorale pm2=new PersonneMorale("pm2", "SiRET pm2","raisonsoc pm2", tpm2);
//            personneMoraleRepository.save(pm2);
//            PersonneMorale pm3=new PersonneMorale("pm3", "SiRET pm3","raisonsoc pm3", tpm1);
//            personneMoraleRepository.save(pm3);
//
//            // Création de client bancaires
//            ClientBancaire cb1 =new ClientBancaire();
//            clientBancaireRepository.save(cb1);
//            ClientBancaire cb2=new ClientBancaire();
//            clientBancaireRepository.save(cb2);
//            ClientBancaire cb3=new ClientBancaire();
//            clientBancaireRepository.save(cb3);
//
//            // Ajout des participants aux clients
//            pm1=personneMoraleRepository.findById(pm1.getId()).orElseThrow();
//            cb1.addPersonne(pm1);
//            clientBancaireRepository.save(cb1);
//            cb1=clientBancaireRepository.findById(cb1.getId()).orElseThrow();
//            pp2=personnePhysiqueRepository.findById(pp2.getId()).orElseThrow();
//            cb1.addPersonne(pp2);
//            clientBancaireRepository.save(cb1);
//            pp1=personnePhysiqueRepository.findById(pp1.getId()).orElseThrow();
//            cb2=clientBancaireRepository.findById(cb2.getId()).orElseThrow();
//            pp1.addClientBancaire(cb2);
//            personnePhysiqueRepository.save(pp1);
//            cb3=clientBancaireRepository.findById(cb3.getId()).orElseThrow();
//            pp3=personnePhysiqueRepository.findById(pp3.getId()).orElseThrow();
//            cb3.addPersonne(pp3);
//            clientBancaireRepository.save(cb3);
//            System.out.println("***************************************************" +
//                    "\nLes personnes physiques" +
//                    "***********************************************************");
//            System.out.println(personnePhysiqueRepository.findAll());
//            System.out.println("***************************************************" +
//                    "\nLes personnes morales" +
//                    "***********************************************************");
//            System.out.println(personneMoraleRepository.findAll());

            // === Types de produits ===
            TypeProduit compteCheque = new TypeProduit(0.0f, "Compte chèque", 15f);
            TypeProduit livretA = new TypeProduit(0.02f, "Livret A", 0f);
            TypeProduit pretImmo = new TypeProduit(0.03f, "Prêt immobilier", 0f);
            typeProduitRepository.saveAll(List.of(compteCheque, livretA, pretImmo));

            compteCheque = typeProduitRepository.findById(compteCheque.getId()).orElseThrow();
            livretA = typeProduitRepository.findById(livretA.getId()).orElseThrow();
            pretImmo = typeProduitRepository.findById(pretImmo.getId()).orElseThrow();

            // === Clients bancaires ===
            ClientBancaire cb1 = new ClientBancaire();
            ClientBancaire cb2 = new ClientBancaire();
            clientBancaireRepository.saveAll(List.of(cb1, cb2));

            cb1 = clientBancaireRepository.findById(cb1.getId()).orElseThrow();
            cb2 = clientBancaireRepository.findById(cb2.getId()).orElseThrow();

            // === Produits bancaires rattachés aux clients ===
            ProduitBancaire pb1 = new ProduitBancaire(1000f, "FR76-0001", compteCheque, cb1);
            ProduitBancaire pb2 = new ProduitBancaire(5000f, "FR76-0002", livretA, cb1);
            ProduitBancaire pb3 = new ProduitBancaire(-200000f, "FR76-0003", pretImmo, cb2);
            produitBancaireRepository.saveAll(List.of(pb1, pb2, pb3));

            pb1 = produitBancaireRepository.findById(pb1.getId()).orElseThrow();
            pb2 = produitBancaireRepository.findById(pb2.getId()).orElseThrow();
            pb3 = produitBancaireRepository.findById(pb3.getId()).orElseThrow();

            // === Personnes physiques ===
            PersonnePhysique pp1 = new PersonnePhysique("10 rue des fleurs", "Dupont", "Jean");
            PersonnePhysique pp2 = new PersonnePhysique("12 rue Victor Hugo", "Martin", "Alice");
            personnePhysiqueRepository.saveAll(List.of(pp1, pp2));

            pp1 = personnePhysiqueRepository.findById(pp1.getId()).orElseThrow();
            pp2 = personnePhysiqueRepository.findById(pp2.getId()).orElseThrow();

            // === Types de personnes morales ===
            TypePersonneMorale sa = new TypePersonneMorale("SA");
            TypePersonneMorale sarl = new TypePersonneMorale("SARL");
            typePersonneMoraleRepository.saveAll(List.of(sa, sarl));

            sa = typePersonneMoraleRepository.findById(sa.getId()).orElseThrow();
            sarl = typePersonneMoraleRepository.findById(sarl.getId()).orElseThrow();

            // === Personnes morales ===
            PersonneMorale pm1 = new PersonneMorale("20 avenue Paris", "SIRET001", "Boulangerie du coin", sa);
            PersonneMorale pm2 = new PersonneMorale("30 rue Nationale", "SIRET002", "Menuiserie artisanale", sarl);
            personneMoraleRepository.saveAll(List.of(pm1, pm2));

            pm1 = personneMoraleRepository.findById(pm1.getId()).orElseThrow();
            pm2 = personneMoraleRepository.findById(pm2.getId()).orElseThrow();

            // === Association personnes ↔ clients ===
            cb1.addPersonne(pp1);
            cb1.addPersonne(pm1);
            clientBancaireRepository.save(cb1);

            cb2.addPersonne(pp2);
            cb2.addPersonne(pm2);
            clientBancaireRepository.save(cb2);

            // === Opérations sur pb1 ===
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

            System.out.println("=== Jeu de données initialisé ===");
            System.out.println("Clients : " + clientBancaireRepository.findAll());
            System.out.println("Produits : " + produitBancaireRepository.findAll());
            System.out.println("5 dernières opérations du compte chèque : " +
                    operationRepository.findTop5ByProduitBancaireIdOrderByDateOperationDesc(pb1.getId()));
        };
    }
}
