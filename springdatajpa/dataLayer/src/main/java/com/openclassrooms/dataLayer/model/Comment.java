package com.openclassrooms.dataLayer.model;

import javax.persistence.*;

@Entity
@Table(name = "commentaire")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentaire_id")
    private int commentId;

    @Column(name = "contenu")
    private String content;
    /*
    Cette annotation se rapproche du @OneToMany, dans la mesure où on lui associe également un @JoinColumn.
    La valeur de la propriété name du @JoinColumn est la clé étrangère dans la table Comment, en l'occurrence produit_id.
     */
    //Implémente la bidirectionnalité ManyToOne/OneToMany
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE}
    )
    @JoinColumn(name = "produit_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
