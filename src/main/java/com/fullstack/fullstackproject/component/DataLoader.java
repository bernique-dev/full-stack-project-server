package com.fullstack.fullstackproject.component;

import com.fullstack.fullstackproject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
public class DataLoader {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;

    @PostConstruct
    public void loadData() {
        loadShops();
        loadCategories();
        loadProducts();
    }

    private void loadShops() {
        Map<DayOfWeek, LocalTime> randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("Burger Cringe", false,
                randomOpeningSchedule,
                generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                        getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));

        randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("HLM", false,
                        randomOpeningSchedule,
                        generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                                getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));

        randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("Heptathlon", false,
                randomOpeningSchedule,
                generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                        getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));

        randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("Pâtissier", false,
                randomOpeningSchedule,
                generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                        getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));

        randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("Leader Cost", false,
                randomOpeningSchedule,
                generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                        getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));

        randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("DownAchat", false,
                randomOpeningSchedule,
                generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                        getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));

        randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("Moussaillon", false,
                randomOpeningSchedule,
                generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                        getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));

        randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("Rasoir", false,
                randomOpeningSchedule,
                generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                        getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));

        randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("Lemek", false,
                randomOpeningSchedule,
                generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                        getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));

        randomOpeningSchedule = generateRandomSchedule(LocalTime.of(8,30), LocalTime.of(10,30), null);
        shopRepository.save(new Shop("Ducromania", false,
                randomOpeningSchedule,
                generateRandomSchedule(LocalTime.of(16,30), LocalTime.of(19,30),
                        getNonNullDaysOfWeek(randomOpeningSchedule)), LocalDate.now()
        ));
    }

    private void loadCategories() {
        categoryRepository.save(new Category("Alimentaire"));
        categoryRepository.save(new Category("Informatique"));
        categoryRepository.save(new Category("Textile"));
        categoryRepository.save(new Category("Clavier"));
        categoryRepository.save(new Category("Ventirad"));
        categoryRepository.save(new Category("Jouet"));
        categoryRepository.save(new Category("Enfants"));
        categoryRepository.save(new Category("Souris"));
        categoryRepository.save(new Category("Casque"));
        categoryRepository.save(new Category("Équipement sportif"));
        categoryRepository.save(new Category("Chaussure"));
        categoryRepository.save(new Category("Électroménager"));
        categoryRepository.save(new Category("Téléphonie"));
        categoryRepository.save(new Category("Écran"));
        categoryRepository.save(new Category("Caméra"));
        categoryRepository.save(new Category("Haut-parleurs"));
        categoryRepository.save(new Category("Jeu vidéo"));
        categoryRepository.save(new Category("Manette"));
    }

    private void loadProducts() {
        List<Shop> shops = new ArrayList<>();
        shopRepository.findAll().forEach(shops::add);

        /*
            SHOP 1
         */
        {
            Shop shop = shops.get(0);
            // PRODUCT 1
            Product product1 = new Product("Petit Texas Bahnon", 10.99f,
                    "Laissez-vous séduire par le Petit Texas Bahnon ! Une sauce goût fumée, une viande grillée " +
                            "à la flamme et deux croustillantes tranches de… BAH NON EN FAIT !", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("King Yaourt", 1.99f,
                    "Quasiment comme un Actimel mais en cringe", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("King JR +", 8.99f,
                    "Le menu qui voit les choses en grand", shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("King Junior", 5.49f,
                    "Le menu des petits gourmands", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("Wrap Croutes Chèvres", 10.99f,
                    "Une recette veggie avec de délicieuses bouchées croustillantes à base de fromage de chèvre," +
                            " des tranches de cheddar fondu, de la salade, des tomates, des oignons croustillants et une" +
                            " sauce légèrement relevée. Le tout enveloppé dans une tortilla !", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("Master Chantal", 11.99f,
                    "Une viande de bœuf 150g française, de la roquette, de la Chantal, de la sauce à la moutarde " +
                            "à l'ancienne, des oignons fondants, des oignons croustillants et un pain brioché.", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("Master Poulet", 10.99f,
                    "Du poulet 100% filet enrobé d’une panure ultra croustillante, de la roquette, de l’Emmental" +
                            " Français, une sauce aux herbes, des oignons croustillants et des oignons fondants, " +
                            "des cornichons et un pain brioché.", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("Sauce BBQ Bullseye", 0.49f,
                    "Sauce BBQ Bulls Eye", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("Sauce BBQ Bullseye", 0.49f,
                    "Sauce BBQ Bulls Eye", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("Sauce 1954", 10.99f,
                    "Il n’y a pas que nos viandes qui ont un goût de grillé à la flamme … pour en être sûr," +
                            " découvrez notre sauce 1954 !", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product10);
        }

        /*
            SHOP 2
         */
        {
            Shop shop = shops.get(1);
            // PRODUCT 1
            Product product1 = new Product("Manteau en laine mixée", 79.99f,
                    "Manteau en feutre doux de laine mélangée. Modèle avec col à revers cranté et" +
                            "boutonnage devant. Boutonnage décoratif en bas des manches longues. Poches à rabat" +
                            "devant et deux poches intérieures. Fente dans le dos. Doublure en twill.", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("Chemise Slim Fit", 17.99f,
                    "Chemise en tissu repassage facile. Modèle avec col rabattu et boutonnage classique." +
                            "Manches longues terminées par fente boutonnée et poignet à boutonnage ajustable." +
                            "Empiècement et pinces dans le dos. Base légèrement arrondie.", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("Veste-chemise oversize en laine mélangée", 129.00f,
                    "Nova Fades x HLM. Veste-chemise en douce laine mélangée et non teintée. Coupe" +
                            "oversize avec col et boutonnage devant. Poche de poitrine. Longues manches doublées," +
                            " terminées par fente et poignet boutonné. Empiècement avec plis dans le dos.", shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("Sweat Relaxed Fit avec col zippé", 29.99f,
                    "Sweat en molleton de coton mélangé à l’intérieur doux et brossé. Coupe décontractée" +
                            "avec col droit et fermeture à glissière en haut. Emmanchures descendues et manches" +
                            "longues. Finition bord-côte aux poignets et à la base.", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("Robe de chambre gaufrée", 10.99f,
                    "Robe de chambre en tissu souple et gaufré de coton mélangé. Modèle avec ceinture à" +
                            "nouer à la taille et deux poches devant.", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("Écharpe côtelée en cachemire", 49.99f,
                    "Écharpe en douce maille côtelée de cachemire.", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("Chaussettes en maille fine", 3.99f,
                    "Blank Staples est une collection streetwear intemporelle de pièces minimalistes " +
                            "de haute qualité. Ces chaussettes sont réalisées en maille fine de coton mélangé. " +
                            "Modèle avec tige montante côtelée, terminée par élastique.", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("Chaussettes, lot de 20 paires", 39.99f,
                    "Chaussettes en maille fine de doux coton mélangé. Modèle avec tige à " +
                            "bord élastique.", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("Parka matelassée", 79.99f,
                    "Parka matelassée en matière coupe-vent et déperlante. Modèle à capuche avec col" +
                            "droit et fermeture à glissière sous rabat coupe-vent pressionné devant. Poche" +
                            "passepoilée oblique de chaque côté et fente dans le dos. Une poche intérieure." +
                            "Doublée.", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("Lot de 2 pantalons en molleton Regular Fit", 34.99f,
                    "Pantalons en molleton de coton mélangé. Coupe classique avec élastique habillé et" +
                            "lien de serrage à la taille. Poches couture. Finition bord-côte en bas de jambe " +
                            "Intérieur doux et brossé.", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product10);
        }

        /*
            SHOP 3
         */
        {
            Shop shop = shops.get(2);
            // PRODUCT 1
            Product product1 = new Product("CHAUSSURES DE MARCHE URBAINE ADIDAS BREAKNET BLANC/NOIR", 60f,
                    "Cette chaussure retro tennis apporte un style classique avec les 3 bandes sans oublié" +
                            " le confort grâce à sa semelle intermédiaire en EVA.", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Chaussure"),
                    categoryRepository.findCategoryByName("Équipement Sportif")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("SAC À DOS DE RANDONNÉE 30L - NH ARPENAZ 100", 18f,
                    "Vous cherchez un sac à dos pour vous évader en pleine nature ? Notre modèle NH" +
                            " Arpenaz100 30 L, confortable et accessoirisé, accompagne vos randonnées avec peu " +
                            "de dénivelé.", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Équipement sportif")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("VESTE A CAPUCHE DE RUNNING HOMME KALENJI WARM + BEIGE FICELLE", 25f,
                    "Technique, confortable et chaude, cette veste à capuche pour homme deviendra un " +
                            "indispensable pour vos sorties courses à pied en hiver et même au quotidien grâce à son style moderne et urbain.", shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile"),
                    categoryRepository.findCategoryByName("Équipement Sportif"))));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("PANTALON DE SURVÊTEMENT ADULTE SQUADRA 2021 NOIR", 5.49f,
                    "Restez au chaud et au sec avec ce pantalon de football adidas. " +
                            "Sa coupe vous permettra d'être libre de vos mouvements dans vos déplacements.", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile"),
                    categoryRepository.findCategoryByName("Équipement Sportif")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("TABLE DE PING PONG EXTÉRIEURE PPT 500.2 BLEUE", 350f,
                    "Facile à plier et facile à déplacer, la table de ping pong PPT 500.2 est stable sur" +
                            " tout type de sol et devient l'alliée de vos parties animées à l'extérieur comme" +
                            " à l'intérieur.", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Équipement sportif")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("3 BOULES DE PÉTANQUE COMPÉTITION OBUT MATCH IT", 133.50f,
                    "Conçues pour jouer à la pétanque en compétition. Boules homologuées FIPJP.\n" +
                            "Boules en inox efficaces sur tous les terrains.", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Équipement sportif")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("BALLON DE FOOTBALL COUPE DU MONDE 2022 QATAR AL RIHLA TRN", 28f,
                    "Inspiré du ballon de match officiel FIFA World Cup™ Qatar 2022, ce ballon d'entrainement" +
                            " adidas Al Rihla est concu pour des sessions de football intenses.", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Équipement sportif")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("TRAMPOLINE ROND 420 AVEC FILET DE PROTECTION", 299f,
                    "Ce trampoline Decathlon de 4m20 garantit une parfaite sécurité grâce à ses coussins" +
                            " et filet de protection. Compatibilité échelle 3 marches et kit d'ancrage. Garantie" +
                            " 5 ans offerte sur la structure", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Équipement sportif")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("KIT TIR A L'ARC DISCOVERY 100", 45f,
                    "Vous cherchez à découvrir le tir à l'arc ? Nous avons développé le kit Disco 100 :" +
                            " arc, 2 flèches pointes acier, cible. Il conviendra aux droitiers et aux gauchers grâce" +
                            " à sa poignée visée centrale !", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Équipement sportif")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("BABY FOOT CATENACCIO SPORTIVO", 649.99f,
                    "Le Baby Foot Catenaccio Sportivo vous permettra de retrouver tous les codes" +
                            " des babys à la française avec sa caisse en bois si caractéristique des modèles" +
                            " de bar.", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Équipement sportif")
            )));
            productRepository.save(product10);
        }

        /*
            SHOP 4
         */
        {
            Shop shop = shops.get(3);
            //  PRODUCT 2
            Product product1 = new Product("Clavier sans fil Logitech MX Keys Advanced Wireless Graphite", 98.90f,
                    "Logitech présente MX Keys: la clé de la maîtrise de votre prochain grand projet. " +
                            "Il s'agit du premier clavier MX étudié pour les designers et conçu pour les codeurs. " +
                            "Rien n'est impossible avec la série Master.", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("Réfrigérateur combiné Samsung RB36T602CSA", 949f,
                    "Avec son design moderne et épuré, le combiné Samsung RB36T602CSA s'intègre " +
                            "parfaitement dans tous types de cuisines. Réversibles, ses poignées de porte vous " +
                            "garantissent une installation facile, qu'importe la configuration de votre pièce.", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Électroménager")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("Chromebook Packard bell CB314-002 Tactile 14", 299f,
                    "Les Chromebooks, dont le démarrage ne demande pas plus de quelques secondes 1, " +
                            "ne ralentissent pas au fil du temps grâce à des mises à jour logicielles automatiques 2."
                    , shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique")
            )));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("Lave linge hublot Samsung WW11BGA046AE", 599f,
                    "Grâce à la technologie SpaceMax™, le lave-linge Samsung WW11BGA046AE vous offre" +
                            " une grande capacité de 11 kg dans des dimensions standard.", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Électroménager")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("Ecran PC Asus TUF VG247Q1A", 179f,
                    "Produit neuf", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("Souris gamer Logitech G PRO X SUPERLIGHT- Noire", 113.99f,
                    "Conçue avec et pour les Gamers Pro : conçue en collaboration avec les meilleurs " +
                            "professionnels du gaming au monde, cette souris Logitech G est faite pour atteindre le " +
                            "plus haut niveau de performance", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Souris")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("Smartphone Apple iPhone 11 Noir 64 Go", 529f,
                    "Avec l'iPhone 11 d'APPLE, profitez d'un appareil performant, résistant, avec une bonne" +
                            " autonomie et un appareil photo de grande qualité. Ses 64 Go de mémoire vous permettront" +
                            " de stocker aisément toutes vos photos, vidéos ou encore musique.", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Téléphonie")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("Raclette Bron coucke BREZ05BA breziere", 195f,
                    "Produit neuf", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Électroménager")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("Wok Ballarini 30 cm manche Salina by Zwilling", 94.99f,
                    "Réalisez tous les woks que vous désirez grâce à une poêle spécifiquement conçue pour" +
                            " cette utilisation. Parfaite pour faire griller vos légumes ou dorer vos viandes," +
                            " la poêle Wok s'utilise avec ou sans matière grasse, pour une cuisson" +
                            " saine et agréable.", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("Souris sans fil Hp Wireless 220", 16.99f,
                    "Produit neuf", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Souris")
            )));
            productRepository.save(product10);
        }

        /*
            SHOP 5
         */
        {
            Shop shop = shops.get(4);
            //  PRODUCT 2
            Product product1 = new Product("Après- Ski Canada Wanabee Noir Fille - Taille 33", 12.5f,
                    "Après ski enfant, parfait pour se balader lorsque les températures sont négatives.", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Chaussure"),
                    categoryRepository.findCategoryByName("Enfants")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("Coca-cola zéro - 1L", 0.69f,
                    "Le goût du Coca-Cola sans sucres et sans calories.", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("Petits cookies pépites de chocolat Bonne Maman - 250g", 1.65f,
                    "De bons petits sans colorant et sans conservateur, cuisiné par Bonne Maman.", shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("Ravioli sauce bolognaise Leader Price - 800g", 1.84f,
                    "Raviolis gourmands et savoureux !", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("Cacahuètes grillées & salées - 500g", 2.17f,
                    "A grignoter seul ou à plusieurs !", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("Sauce pesto alla genovese au basilic frais Barilla - 190g", 1.82f,
                    "La sauce Pesto alla Genovese Barilla est préparée avec du basilic frais 100% italien, " +
                            "il apporte un goût intense et unique à vos plats.", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("Céréales Lion Nestlé - 400g", 2.21f,
                    "Des céréales au caramel et chocolat pour un petit-déjeuner qui aide à bien démarrer" +
                            " la journée !", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Alimentaire")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("Ensemble Tokyo Laundry sweat à capuche zippé + bas de jogging bleu" +
                    " marine homme taille M", 12.39f,
                    "Homme Ensemble Sweat à capuche zippé manches longues + Jogging bleu marine uni", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Textile")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("Ballon de basket Lay Up de Spalding", 0f,
                    "Un ballon adapté au street basket et offre une durabilité, afin d'éprouver plus de" +
                            " joie à faire des paniers.", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Équipement sportif")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("Corps humain jeu éducatif STEM University", 11.77f,
                    "Pour que votre enfant découvre les parties internes du corps humain.", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product10);
        }

        /*
            SHOP 6
         */
        {
            Shop shop = shops.get(5);
            //  PRODUCT 2
            Product product1 = new Product("Cooler Master Hyper TX3 EVO", 22.55f,
                    "3 caloducs à contact direct avec une base conçue pour minimiser l'espace avec le" +
                            " processeur pour fournir une excellente conduction de chaleur.", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Ventirad")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("Be Quiet! Dark Rock Slim", 59.21f,
                    "Silence et performance, sans aucun compromis", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Ventirad")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("Corsair K55 RGB PRO XT (AZERTY)", 75.19f,
                    "Personnalisable, précis et prêt pour le jeu", shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("Rasoir Ornata V2 (AZERTY)", 84.59f,
                    "Profitez du meilleur des deux mondes avec le Rasoir Ornata V2, un clavier doté" +
                            " de switchs hybrides qui associent les avantages des touches à membrane et des" +
                            " switchs mécaniques, le tout, dans un seul design.", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("Ducky Channel One 3 Fuji (Cherry MX Silent Red) (AZERTY)", 150.39f,
                    "Avec le Ducky Channel One 3 profitez d'une qualité ultime avec des touches bénéficiant " +
                            "d'un revêtement en PBT.", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("Corsair K57 RGB Wireless (AZERTY)", 103.39f,
                    "Illuminez vos sessions de gaming sans fil grâce au clavier gaming K57 RGB Wireless" +
                            " pouvant se connecter avec la technologie ultrarapide SLIPSTREAM WIRELESS inférieure" +
                            " à 1 ms, Bluetooth ou câble USB.", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("Rasoir Deathadder v2", 46.99f,
                    "Avec plus de 10 millions de Rasoir DeathAdders vendues, la souris de jeu la plus célèbre" +
                            " et primée au monde a gagné sa popularité grâce à sa conception ergonomique" +
                            " exceptionnelle.", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Souris")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("Pack Asus TUF H3 + K1 + M3", 122.19f,
                    "Y'en a des choses dans ce pack!", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier"),
                    categoryRepository.findCategoryByName("Casque"),
                    categoryRepository.findCategoryByName("Souris")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("Logitech Zone Vibe 100", 109.99f,
                    "Le casque Logitech Zone Vibe 100 est idéal pour vos réunions vidéo pour un usage quotidien.", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Casque")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("MSI GeForce GTX 1660 SUPER VENTUS XS OC", 310.19f,
                    "Jouez avec style et en toute discrétion !", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique")
            )));
            productRepository.save(product10);
        }

        /*
            SHOP 7
         */
        {
            Shop shop = shops.get(6);
            //  PRODUCT 2
            Product product1 = new Product("Ventirad à double ventilateur A500", 99.90f,
                    "Abaissez la température de votre processeur avec le ventirad à double" +
                            " ventilateur haute performance MOUSSAILLON A500 qui est doté de quatre caloducs en cuivre" +
                            " à contact direct et de deux ventilateurs MOUSSAILLON ML120.", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Ventirad")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("Souris gaming sans fil DARK CORE RGB PRO", 89.99f,
                    "La MOUSSAILLON DARK CORE RGB PRO vous aide à remporter la victoire sans fil, grâce " +
                            "à sa technologie SLIPSTREAM WIRELESS inférieure à 1 ms, son capteur optique personnalisé " +
                            "de 18 000 DPI optimisé pour les souris sans fil." +
                            "\n", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Souris")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("Souris de jeu M65 PRO RGB FPS — Black (EU)", 69.99f,
                    "Hautement technologique, flexible et durable, la M65 PRO RGB réunit toutes les " +
                            "conditions requises pour battre vos adversaires en compétition.", shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Souris")
            )));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("Casque gaming premium VOID RGB ELITE USB avec son surround 7.1", 89.99f,
                    "Plongez dans l’action avec le casque MOUSSAILLON VOID RGB ELITE USB, doté de transducteurs" +
                            " audio en néodyme de 50 mm personnalisés, de coussinets confortables en maille microfibre" +
                            " et en mousse à mémoire de forme ainsi que d’un microphone omnidirectionnel.", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Casque")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("Clavier gaming mécanique K70 RGB PRO avec touches PBT DOUBLE SHOT" +
                    " PRO - CHERRY MX Red (FR)", 149.99f,
                    "Le clavier gaming mécanique K70 RGB PRO de MOUSSAILLON dispose d’un cadre en aluminium" +
                            " emblématique et offre des performances encore meilleures grâce à la technologie" +
                            " d’hypertraitement MOUSSAILLON AXON.", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("Clavier mécanique K65 RGB MINI 60 % - Élixir rose", 139.99f,
                    "Doté de la technologie d’hyper-traitement AXON et de switchs de touche mécaniques " +
                            "CHERRY MX SPEED, le clavier gaming mécanique MOUSSAILLON K65 RGB MINI 60 % allie performances" +
                            " de pointe, personnalisation extrême et portabilité dans un format compact.", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("Écran gaming MOUSSAILLON XENEON 32UHD144", 0f,
                    "UNE VUE SPECTACULAIRE", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Écran")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("Câble USB Type-C vers HDMI", 29.99f,
                    "Le câble MOUSSAILLON USB Type-C vers HDMI convertit facilement un port USB Type-C" +
                            " en port de sortie HDMI pour vous permettre de profiter d'une sortie vidéo 4K" +
                            " jusqu'à 60Hz en HDR.", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("Facecam", 139.99f,
                    "", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Caméra")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("Casque gaming filaire HS70 avec Bluetooth", 119.99f,
                    "Le casque gaming MOUSSAILLON HS70 BLUETOOTH est à la fois garant de confort et de " +
                            "qualité, avec ses coussinets à mémoire de forme, ses transducteurs audio en néodyme" +
                            " de 50 mmm personnalisés ses deux connexions simultanées." +
                            "", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Casque")
            )));
            productRepository.save(product10);
        }

        /*
            SHOP 8
         */
        {
            Shop shop = shops.get(7);
            //  PRODUCT 2
            Product product1 = new Product("Rasoir Kraken Chaton", 124.99f,
                    "Casque USB avec oreilles de chat compatible Chroma", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("Rasoir Vipère V2 Pro", 159.99f,
                    "Souris Esports sans fil, ultra-légère et ultra rapide", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Souris")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("Rasoir Naga Pro", 169.99f,
                    "Souris sans fil avec plaques latérales interchangeables", shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Souris")
            )));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("Rasoir Chasseur d'élite", 0f,
                    "", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("Rasoir Veuve Noire V3 Sans pavé numérique", 109.99f,
                    "Clavier mécanique compact avec Rasoir Chroma RGB", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Clavier")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("Rasoir Barracuda X - Mercure", 99.99f,
                    "Casque sans fil multiplateforme pour le jeu et les appareils mobiles", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Casque")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("Rasoir Nommo", 109.99f,
                    "Haut-parleurs 2.0 pour PC pour une couverture", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Haut-parleurs")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("Rasoir Basilic Ultime avec station d’accueil", 0f,
                    "Souris de jeu sans fil dotée de 11 boutons programmables", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Souris")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("Razer Opus", 209.99f,
                    "Écouteurs sans fil certifiés THX® avec ANC avancée pour une expérience audio sans" +
                            " interruption", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Casque")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("Razer Kiyo", 109.99f,
                    "Montre au monde qui tu es", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Informatique"),
                    categoryRepository.findCategoryByName("Caméra")
            )));
            productRepository.save(product10);
        }

        /*
            SHOP 9
         */
        {
            Shop shop = shops.get(8);
            //  PRODUCT 2
            Product product1 = new Product("Le Poudlard Express - Edition Collector", 499.99f,
                    "En voiture!", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("Le calendrier de l’Avent Les Gardiens de la Galaxie", 24.49f,
                    "Les enfants peuvent réunir les différentes surprises pour recréer leurs scènes de" +
                            " film préférées et les aventures Marvel de leur choix.", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("Optimus Prime", 143.99f,
                    "Donne vie au commandant des Autobots comme jamais auparavant", shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("Le masque de Batman™ – Série TV classique", 41.99f,
                    "Un superbe modèle pour les passionnés de Batman™", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("La grande pyramide de Gizeh", 111.99f,
                    "Remontez le temps jusqu’à l’Égypte antique en construisant et en exposant cette" +
                            " réplique en briques LEGO® de la grande pyramide de Gizeh.", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("La moto de cascade du Biker", 5.59f,
                    "Encouragez des heures de jeu d’imagination avec cette moto de cascade à " +
                            "rétrofriction réalisant des figures époustouflantes.", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("La moto de cascade Démolition", 5.59f,
                    "Pimentez le jeu des enfants avec ce set « fracassant » qui inclut une moto à" +
                            " rétrofriction Démolition pour effectuer de vraies cascades. Cascadeur professionnel." +
                            " Les enfants ne doivent pas essayer de reproduire ces cascades sur une vraie moto.", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("Le donjon du squelette", 29.99f,
                    "Un set LEGO® Minecraft® plein d’action, avec des cavernes à explorer, des squelettes" +
                            " à affronter et des heures de jeu créatif.", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("Le bastion du Nether", 34.99f,
                    "*bruit de cochon pas content*", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("Le village Lama", 129.99f,
                    "Les aventures Minecraft® mettant en scène des lamas prennent vie dans le monde réel" +
                            " avec ce set amusant, transformable à l’infini.", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jouet")
            )));
            productRepository.save(product10);
        }

        /*
            SHOP 10
         */
        {
            Shop shop = shops.get(9);
            //  PRODUCT 2
            Product product1 = new Product("Call Of Duty Modern Warfare II XBOX SERIES X", 79.99f,
                    "Bienvenue dans la nouvelle ère de Call of Duty®", shop);
            product1.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jeu vidéo")
            )));
            productRepository.save(product1);

            //  PRODUCT 2
            Product product2 = new Product("God Of War Ragnarok PS5", 79.99f,
                    "Partez en quête de réponses et d'alliés avant le Ragnarök.", shop);
            product2.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jeu vidéo")
            )));
            productRepository.save(product2);

            //  PRODUCT 3
            Product product3 = new Product("The Witcher III : Wild Hunt - GOTY XBOX ONE", 19.99f,
                    "Incarnez un tueur de monstres professionnel, lancé sur la piste d’une" +
                            " enfant prophétique dans un monde ouvert fantastique, marqué par des troubles et" +
                            " une indifférence morale.", shop);
            product3.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jeu vidéo")
            )));
            productRepository.save(product3);

            //  PRODUCT 4
            Product product4 = new Product("Fortnite", 0f,
                    "W X C C C V V B M5 V X", shop);
            product4.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jeu vidéo")
            )));
            productRepository.save(product4);

            //  PRODUCT 5
            Product product5 = new Product("Pokémon Ecarlate SWITCH", 59.99f,
                    "La nouvelle génération de Pokémon arrive sur Nintendo Switch le 18 novembre !", shop);
            product5.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jeu vidéo")
            )));
            productRepository.save(product5);

            //  PRODUCT 6
            Product product6 = new Product("Casque gaming sans fil à réduction de bruit Sony INZONE H9 (WH-G900N)",
                    249.99f,
                    "Votre jeu, rien d’autre. Immergez-vous totalement sans être dérangé " +
                            "grâce à la combinaison de la réduction de bruit et de la technologie 360 Spatial" +
                            " Sound for Gaming.", shop);
            product6.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Casque")
            )));
            productRepository.save(product6);

            //  PRODUCT 7
            Product product7 = new Product("Casque stereo sans fil", 99.99f,
                    "Entendez et faites-vous entendre haut et fort pendant que vous jouez avec le casque " +
                            "sans fil Xbox, profitez de technologies de son spatial, notamment Windows Sonic, " +
                            "Dolby Atmos et DTS Headphone:X.", shop);
            product7.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Casque")
            )));
            productRepository.save(product7);

            //  PRODUCT 8
            Product product8 = new Product("Manette DualSense PlayStation 5", 49.99f,
                    "Découvrez une expérience de gaming plus intense1 avec la nouvelle manette" +
                            " PS5™ novatrice. ", shop);
            product8.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Manette")
            )));
            productRepository.save(product8);

            //  PRODUCT 9
            Product product9 = new Product("The Last Of Us Part II - Versions PS5 et PS4", 9.99f,
                    "Faites face aux terribles répercutions physiques et émotionnelles des actions d'Ellie !!!", shop);
            product9.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Jeu vidéo")
            )));
            productRepository.save(product9);

            //  PRODUCT 10
            Product product10 = new Product("Casque teal exclusive turtle beach recon 70", 17.49f,
                    "Conçu pour la victoire sur toutes les plateformes ! ", shop);
            product10.setCategories(new HashSet<>(Arrays.asList(
                    categoryRepository.findCategoryByName("Casque")
            )));
            productRepository.save(product10);
        }




    }

    private Map<DayOfWeek, LocalTime> generateRandomSchedule(LocalTime minTime, LocalTime maxTime, Set<DayOfWeek> dayOfWeeks) {
        Map<DayOfWeek, LocalTime> schedule = new HashMap<>();
        Random random =  new Random();
        for (DayOfWeek dayOfWeek: DayOfWeek.values()) {
            if (dayOfWeeks != null ? dayOfWeeks.contains(dayOfWeek) : random.nextBoolean()) {
                int minMinutes = minTime.toSecondOfDay();
                int maxMinutes = maxTime.toSecondOfDay();
                int randomSecondOfDay = minMinutes + random.nextInt(maxMinutes - minMinutes);
                LocalTime time = LocalTime.ofSecondOfDay(randomSecondOfDay - randomSecondOfDay % 900);
                schedule.put(dayOfWeek, time);
            } else {
                schedule.put(dayOfWeek, null);
            }
        }

        return schedule;
    }

    private Set<DayOfWeek> getNonNullDaysOfWeek(Map<DayOfWeek, LocalTime> schedule) {
        Set<DayOfWeek> dayOfWeeks = new HashSet<>();
        for (Map.Entry<DayOfWeek, LocalTime> entry : schedule.entrySet()) {
            if (entry.getValue() != null) {
                dayOfWeeks.add(entry.getKey());
            }
        }
        return dayOfWeeks;
    }

}
