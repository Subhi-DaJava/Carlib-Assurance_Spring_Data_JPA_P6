package com.openclassrooms.dataLayer;

import com.openclassrooms.dataLayer.model.Category;
import com.openclassrooms.dataLayer.model.Comment;
import com.openclassrooms.dataLayer.model.Product;
import com.openclassrooms.dataLayer.service.CategoryService;
import com.openclassrooms.dataLayer.service.CommentService;
import com.openclassrooms.dataLayer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootApplication
public class DataLayerApplication implements CommandLineRunner {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CommentService commentService;

	public static void main(String[] args) {
		SpringApplication.run(DataLayerApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Iterable<Product> products = productService.getProducts();
		products.forEach(product -> System.out.println(product.getName()));
		System.out.println("*************deux loops pour afficher tous commentaires***********************");
		for (Product product :products){
			for (Comment comment : product.getComments()){
				System.out.println(comment.getContent());
			}
		}
		System.out.println("***********************************************");
		Optional<Product> optProduct = productService.getProductById(1);
		Product productId = optProduct.get();
		System.out.println(productId.getName());
		productId.getComments().forEach(comment -> System.out.println(comment.getContent()));
		System.out.println("****************Catégorie-Product : implémenté côté product*********************");
		productId.getCategories().forEach(category -> System.out.println("["+productId.getName()+"] appartient à cette catégorie : ["+category.getName()+"]."));
		System.out.println("*****************************************************");
		optProduct.get().getComments().forEach(comment -> System.out.println(comment.getContent()));

		System.out.println("**************************************************");
		Iterable<Category> categories = categoryService.getCategorys();
		categories.forEach(categorie -> System.out.println(categorie.getName()));
		System.out.println("***************Catégorie et Produit ************************************");
		Optional<Category> optCategory = categoryService.getCategoryById(1);
		Category categoryId = optCategory.get();
		System.out.println(categoryId.getName());

		categoryId.getProducts().forEach(product -> System.out.println(categoryId.getName() +" comprend ["+product.getName()+"], ce produit"));
		System.out.println("*****************************************************");
		Iterable<Comment> comments = commentService.getComments();
		comments.forEach(comment -> System.out.println(comment.getContent()));
		System.out.println("************************************************************");
		Optional<Comment> optComment = commentService.getCommentById(1);
		System.out.println(optComment.get().getContent());

		System.out.println("*************** Test pour la Bidirectionnalité ****************************************");
		//Tester la bidirectionnalité
		/*
		1.	Récupérer 1 catégorie et afficher les produits associés.
		2.	Récupérer 1 produit et afficher les catégories associées.
		 */
		System.out.println("**************************** 1 Catégorie affiche produits associés *******************************************");
		Optional<Category> optCategoryIdBidirectionnel = categoryService.getCategoryById(3);
		Category categoryIdBi = optCategoryIdBidirectionnel.get();
		categoryIdBi.getProducts().forEach(product -> System.out.println("Cette catégorie ["+categoryIdBi.getName()+"] comprend ce produit :["+product.getName()+"]."));
		System.out.println("************************ 1 Produit affiche la catégorie associées **************************************************");
		Optional<Product> optProductIdBidirectionnel = productService.getProductById(4);
		Product productIdBi = optProductIdBidirectionnel.get();
		productIdBi.getCategories().forEach(category -> System.out.println("Ce produit ["+productIdBi.getName()+"] appartient à cette catégorie ["+category.getName()+"]."));

		System.out.println("***************** Prouver la validité de la bidirectionnalité *********************");
		System.out.println("**************************** Commentaires affiche le Produit associé ***********************************************");
		Optional<Comment> optCommentId = commentService.getCommentById(1);
		Comment commentId = optCommentId.get();
		System.out.println("Ce commentaire ["+commentId.getContent()+"] est associé avec ce ["+commentId.getProduct().getName()+"].");
		System.out.println("********************** Produit affiche les Commentaires associés ************************************");
		Optional<Product> optProductIdBi = productService.getProductById(1);
		Product productIdBiDir = optProductIdBi.get();
		productIdBiDir.getComments().forEach(comment -> System.out.println("Ce produit ["+productIdBiDir.getName()+"] a ce commentaire ["+comment.getContent()+"]."));


	}
}
