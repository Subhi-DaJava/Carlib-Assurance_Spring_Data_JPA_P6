package com.openclassrooms.dataLayer.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//la requête SQL update exécutera un set uniquement sur les valeurs qui ont changé.
@DynamicUpdate
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
    Une relation bidirectionnelle :
    1.	L’annotation @JoinColumn sur l’attribut comments a été enlevée.
    2.	La propriété mappedBy = “product” dans l’annotation @OneToMany a été ajoutée.
    3.	Le fetch = FetchType.EAGER a également été enlevé. Je laisse la valeur par défaut LAZY.
        Comme pour la relation bidirectionnelle en ManyToMany, le mappedBy permet de faire référence à l’attribut dans la seconde entité.

     */
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    //Créé une liste de catégories
    @ManyToMany(
            mappedBy = "products",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
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

    /*
    Une bonne pratique dans le contexte des relations bidirectionnelles est d’ajouter des méthodes utilitaires (dites helpers methods, en anglais) pour aider à la synchronisation de nos objets.
    Au sein de la classe Product
     */
    public void addComment(Comment comment){
        comments.add(comment);
        comment.setProduct(this);
    }
    public void removeComment(Comment comment){
        comments.remove(comment);
        comment.setProduct(null);
    }
}
