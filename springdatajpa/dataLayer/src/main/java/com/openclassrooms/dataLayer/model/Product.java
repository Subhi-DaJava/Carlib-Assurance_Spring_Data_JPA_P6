package com.openclassrooms.dataLayer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produit")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id")
    private int productId;

    @Column(name = "nom")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cout")
    private int cost;
    /*
    La propriété ‘cascade’ permet de définir quel impact l’action sur une entité aura sur son entité associée.
    Le type ‘ALL’ signifie que toutes les actions sur l’entité Produit seront propagées sur l’entité Commentaires.
    Exemple : si on supprime le produit, les commentaires associés seront également supprimés.
     */
    /*
    La propriété orphanRemoval=true permet d’activer un mécanisme qui garantit la non-existence de commentaire orphelin de son produit.
    Si on supprime un commentaire de la liste des commentaires du Product, alors le commentaire devient orphelin, et il est supprimé de la base de données.
     */
    /*
    La propriété fetch possède la valeur EAGER, et cela signifie qu’à la récupération du produit, tous les commentaires seront également récupérés.
     */
    /*
    @JoinColumn : Cette annotation permet d’indiquer le nom de la clé étrangère dans la table de l’entité concernée.
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "produit_id")

    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "categorie_produit",
            joinColumns = @JoinColumn(name = "produit_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private List<Category> categories = new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int product) {
        this.productId = product;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    /*
    Nous utilisons 2 propriétés de @ManyToMany :
    La propriété fetch possède la valeur LAZY, et cela signifie qu’à la récupération de la catégorie, les produits ne sont pas récupérés.
    Par voie de conséquence, les performances sont meilleures (la requête est plus légère) ;
    cependant, lorsqu'ultérieurement dans votre code vous accéderez aux produits à partir de l’objet Catégorie en question, une nouvelle requête sera exécutée.
    Cascade : contrairement aux exemples précédents, nous ne voulons pas un CascadeType.ALL qui impliquerait une cascade dans le cas de la suppression.
    Je spécifie donc uniquement PERSIST et MERGE, la cascade s’applique donc tant en création qu’en modification.
    Il est également nécessaire d’utiliser l’annotation @JoinTable avec les propriétés suivantes :
    name : correspond au nom de la table de jointure en base de données ;
    joinColumns correspond à la clé étrangère dans la table de jointure ;
    inverseJoinColumns correspond à la clé étrangère dans la table de jointure de la seconde entité concernée par la relation.
    Nous utilisons 2 propriétés de @ManyToMany :
    La propriété fetch possède la valeur LAZY, et cela signifie qu’à la récupération de la catégorie, les produits ne sont pas récupérés. Par voie de conséquence, les performances sont meilleures (la requête est plus légère) ; cependant, lorsqu'ultérieurement dans votre code vous accéderez aux produits à partir de l’objet Catégorie en question, une nouvelle requête sera exécutée.
    Cascade : contrairement aux exemples précédents, nous ne voulons pas un CascadeType.ALL qui impliquerait une cascade dans le cas de la suppression. Je spécifie donc uniquement PERSIST et MERGE, la cascade s’applique donc tant en création qu’en modification.
    Il est également nécessaire d’utiliser l’annotation @JoinTable avec les propriétés suivantes :
    name : correspond au nom de la table de jointure en base de données ;
    joinColumns correspond à la clé étrangère dans la table de jointure ;
    inverseJoinColumns correspond à la clé étrangère dans la table de jointure de la seconde entité concernée par la relation.
     */

}
