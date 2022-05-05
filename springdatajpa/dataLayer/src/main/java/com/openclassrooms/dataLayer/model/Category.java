package com.openclassrooms.dataLayer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorie_id")
    private int categoryId;

    @Column(name = "nom")
    private String name;

    public int getCategoryId() {
        return categoryId;
    }

    // Commenté ci-dessous parce qu'on implémente la relation @ManyToMany côté Product
   /* @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "categorie_produit",
            joinColumns = @JoinColumn(name = "categorie_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private List<Product> products = new ArrayList<>();*/

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }*/
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
