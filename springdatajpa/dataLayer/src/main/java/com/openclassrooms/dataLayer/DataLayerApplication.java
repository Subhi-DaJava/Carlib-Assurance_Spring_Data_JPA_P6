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
		System.out.println(optProduct.get().getName());
		optProduct.get().getComments().forEach(comment -> System.out.println(comment.getContent()));

		System.out.println("*****************************************************");
		optProduct.get().getComments().forEach(comment -> System.out.println(comment.getContent()));

		System.out.println("**************************************************");
		Iterable<Category> categories = categoryService.getCategorys();
		categories.forEach(categorie -> System.out.println(categorie.getName()));
		System.out.println("***************Catégorie et Produit ************************************");
		Optional<Category> optCategory = categoryService.getCategoryById(1);
		Category categoryId = optCategory.get();
		System.out.println(categoryId.getName());
		categoryId.getProducts().forEach(product -> System.out.println(product.getName()));
		System.out.println("*****************************************************");
		Iterable<Comment> comments = commentService.getComments();
		comments.forEach(comment -> System.out.println(comment.getContent()));
		System.out.println("************************************************************");
		Optional<Comment> optComment = commentService.getCommentById(1);
		System.out.println(optComment.get().getContent());

	}
}
