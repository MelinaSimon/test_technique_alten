# Consignes

- Vous êtes développeur front-end : vous devez réaliser les consignes décrites dans le chapitre [Front-end](#Front-end)

- Vous êtes développeur back-end : vous devez réaliser les consignes décrites dans le chapitre [Back-end](#Back-end) (*)

- Vous êtes développeur full-stack : vous devez réaliser les consignes décrites dans le chapitre [Front-end](#Front-end) et le chapitre [Back-end](#Back-end) (*)

(*) Afin de tester votre API, veuillez proposer une stratégie de test appropriée.

## Front-end

Le site de e-commerce d'Alten a besoin de s'enrichir de nouvelles fonctionnalités.

### Partie 1 : Shop

- Afficher toutes les informations pertinentes d'un produit sur la liste
- Permettre d'ajouter un produit au panier depuis la liste 
- Permettre de supprimer un produit du panier
- Afficher un badge indiquant la quantité de produits dans le panier
- Permettre de visualiser la liste des produits qui composent le panier.

### Partie 2

- Créer un nouveau point de menu dans la barre latérale ("Contact")
- Créer une page "Contact" affichant un formulaire
- Le formulaire doit permettre de saisir son email, un message et de cliquer sur "Envoyer"
- Email et message doivent être obligatoirement remplis, message doit être inférieur à 300 caractères.
- Quand le message a été envoyé, afficher un message à l'utilisateur : "Demande de contact envoyée avec succès".

### Bonus : 

- Ajouter un système de pagination et/ou de filtrage sur la liste des produits
- On doit pouvoir visualiser et ajuster la quantité des produits depuis la liste et depuis le panier 

## Back-end

### Partie 1

Développer un back-end permettant la gestion de produits définis plus bas.
Vous pouvez utiliser la technologie de votre choix parmi la liste suivante :

- Node.js/Express
- Java/Spring Boot
- C#/.net Core
- PHP/Symphony : Utilisation d'API Platform interdite


Le back-end doit gérer les API suivantes : 

| Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**      | Create a new product  | Retrieve all products          | X                                        | X   |     X            |
| **/products/:id**  | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |

Un produit a les caractéristiques suivantes : 

``` typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: "INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK";
  rating: number;
  createdAt: number;
  updatedAt: number;
}
```

Le back-end créé doit pouvoir gérer les produits dans une base de données SQL/NoSQL ou dans un fichier json.

### Partie 2

- Imposer à l'utilisateur de se connecter pour accéder à l'API.
  La connexion doit être gérée en utilisant un token JWT.  
  Deux routes devront être créées :
  * [POST] /account -> Permet de créer un nouveau compte pour un utilisateur avec les informations fournies par la requête.   
    Payload attendu : 
    ```
    {
      username: string,
      firstname: string,
      email: string,
      password: string
    }
    ```
  * [POST] /token -> Permet de se connecter à l'application.  
    Payload attendu :  
    ```
    {
      email: string,
      password: string
    }
    ```
    Une vérification devra être effectuée parmi tout les utilisateurs de l'application afin de connecter celui qui correspond aux infos fournies. Un token JWT sera renvoyé en retour de la requête.
- Faire en sorte que seul l'utilisateur ayant le mail "admin@admin.com" puisse ajouter, modifier ou supprimer des produits. Une solution simple et générique devra être utilisée. Il n'est pas nécessaire de mettre en place une gestion des accès basée sur les rôles.
- Ajouter la possibilité pour un utilisateur de gérer un panier d'achat pouvant contenir des produits.
- Ajouter la possibilité pour un utilisateur de gérer une liste d'envie pouvant contenir des produits.

## Bonus

Vous pouvez ajouter des tests Postman ou Swagger pour valider votre API

# Avancée du projet

## Technologies utilisées

- **Back-end** : Java/SpringBoot
- **Base de données** : MariaDB
- **Authentification** : JWT Token
- **Front-end** : Angular

---

## Back-end

Le back-end permet de gérer les produits, les comptes utilisateurs, l'authentification et la gestion du panier et de la liste d'envie.

### Routes disponibles

#### Produits

| Méthode    | URL                 | Description                     | Authentification                               |
| ---------- | ------------------- | ------------------------------- | ---------------------------------------------- |
| **GET**    | `/api/products`     | Récupérer tous les produits     | ❌                                              |
| **POST**   | `/api/products`     | Créer un produit                | ✅ ([admin@admin.com](mailto\:admin@admin.com)) |
| **GET**    | `/api/products/:id` | Récupérer un produit spécifique | ❌                                              |
| **PATCH**  | `/api/products/:id` | Modifier un produit             | ✅ ([admin@admin.com](mailto\:admin@admin.com)) |
| **DELETE** | `/api/products/:id` | Supprimer un produit            | ✅ ([admin@admin.com](mailto\:admin@admin.com)) |

#### Utilisateurs

| Méthode  | URL            | Description                      |
| -------- | -------------- | -------------------------------- |
| **POST** | `/api/account` | Créer un compte utilisateur      |
| **POST** | `/api/token`   | Générer un token JWT (connexion) |

#### Panier

| Méthode    | URL                                    | Description                          | Authentification |
| ---------- | -------------------------------------- | ------------------------------------ | ---------------- |
| **GET**    | `/api/cart/:userid`                    | Récupérer le panier d’un utilisateur | ✅ (Token JWT)    |
| **POST**   | `/api/cart/:userid?productid&quantity` | Ajouter un produit au panier         | ✅ (Token JWT)    |
| **DELETE** | `/api/cart/:userid?productid&quantity` | Retirer un produit du panier         | ✅ (Token JWT)    |
| **PUT**    | `/api/cart/:userid?productid&quantity` | Modifier la quantité d’un produit    | ✅ (Token JWT)    |
| **DELETE** | `/api/cart/:userid/clear`              | Vider le panier                      | ✅ (Token JWT)    |

#### Liste d’envie

| Méthode    | URL                               | Description                            | Authentification |
| ---------- | --------------------------------- | -------------------------------------- | ---------------- |
| **GET**    | `/api/wishlist/:userid`           | Récupérer la liste d’envie             | ✅ (Token JWT)    |
| **POST**   | `/api/wishlist/:userid?productid` | Ajouter un produit à la liste d’envie  | ✅ (Token JWT)    |
| **DELETE** | `/api/wishlist/:userid?productid` | Retirer un produit de la liste d’envie | ✅ (Token JWT)    |
| **DELETE** | `/api/wishlist/:userid/clear`     | Vider la liste d’envie                 | ✅ (Token JWT)    |

### Authentification et sécurité

- JWT est utilisé pour l'authentification.
- Seul l'utilisateur ayant l'email `admin@admin.com` peut ajouter, modifier ou supprimer des produits.
- Chaque utilisateur ne peut accéder qu'à ses propres données (panier et liste d’envie).

### Tests

- Postman et/ou Swagger peuvent être utilisés pour tester l’API.

---

## Front-end

Le front-end permet d'afficher les produits et de gérer l’interaction avec l’utilisateur.

### Fonctionnalités implémentées

✅ **Afficher toutes les informations pertinentes d’un produit sur la liste**

### Fonctionnalités en cours

🚧 **Ajouter un produit au panier depuis la liste** (Le token est bien récupéré via un formulaire de connexion, mais l’envoi des requêtes vers le back ne contient ni le token ni l’ID utilisateur)

### Fonctionnalités restantes

- Permettre de supprimer un produit du panier
- Afficher un badge indiquant la quantité de produits dans le panier
- Visualiser la liste des produits du panier
- Créer une page "Contact" avec un formulaire de contact
- Ajouter un système de pagination et/ou de filtrage sur la liste des produits
- Permettre d’ajuster la quantité des produits depuis la liste et le panier

---

## Instructions pour lancer le projet

### Back-end

1. Installer les dépendances :
   ```sh
   cd back
   mvn clean install
   ```
2. Configurer la base de données et les variables d’environnement.
3. Lancer le serveur :
   ```sh
   mvn spring-boot:run
   ```

### Front-end

1. Cloner le projet (si ce n'est pas déjà fait).
2. Installer les dépendances :
   ```sh
   cd front
   npm install
   ```
3. Lancer l'application :
   ```sh
   ng serve
   ```


