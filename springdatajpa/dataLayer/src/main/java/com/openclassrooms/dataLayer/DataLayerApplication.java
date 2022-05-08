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

		/*System.out.println("**************************** Méthode save pour Catégorie *******************");
		System.out.println("Les anciennes Catégories : ");
		categoryService.getCategorys().forEach(category -> System.out.println(category.getName()));
		Category newCategory = new Category();
		newCategory.setName("Promotion");
		newCategory = categoryService.addCategory(newCategory);
		System.out.println("Toutes les catégories après avoir rajouté une nouvelle catégorie :");
		categoryService.getCategorys().forEach(category -> System.out.println(category.getName()));*/

		/*System.out.println("**************** Méthode save pour product *********************");
		System.out.println("Les anciens Products : ");
		productService.getProducts().forEach(product -> System.out.println(product.getName()));
		Product newProduct = new Product();
		newProduct.setName("AssuranceTierMaxi");
		newProduct.setCost(1000);
		newProduct.setDescription("Assurance couvre le dommage matériel et corporel des autres");
		//On rajoute cette ligne pour créer un lien entre cette newCategory et le newProduct
		newCategory.addProduct(newProduct);*/

		/*newProduct = productService.addProduct(newProduct);
		System.out.println("Tous les Produits après avoir rajouté une nouveau produit : ");
		productService.getProducts().forEach(product -> System.out.println(product.getName()));
		System.out.println("la relation entre newCategory et newProduct");
		//Vérifier la relation entre catégorie et le produit bien prise en compte
		newProduct.getCategories().forEach(category -> System.out.println(category.getName()));*/

		System.out.println("Afficher les commentaires associés au produit (id=5)");
		Product productId5 = productService.getProductById(5).get();
		/*Comment newComment = new Comment();*/
		//newComment.setContent("Il est super pour les jeunes conducteur");
		/*productId5.addComment(newComment);*/
		//afficher le commentaire associé avec le produit dont l'id = 5
		productId5.getComments().forEach(comment -> System.out.println(comment.getContent()));

		System.out.println("*************** Rajouter les objets simultanément *********************");
		/*Category newCategoryUnder30 = new Category();
		newCategoryUnder30.setName("Pour les -30 ans");
		Product newProductUnder30 = new Product();
		newProductUnder30.setName("Assurance-30");
		newProductUnder30.setDescription("Assurance pour les moins de 30 ans");
		newProductUnder30.setCost(600);
		newCategoryUnder30.addProduct(newProductUnder30);
		categoryService.addCategory(newCategoryUnder30);
*/
		System.out.println("**************** Afficher la catégorie et le product pour ces new objets *********************");
		/*newCategoryUnder30.getProducts().forEach(product -> System.out.println("Cette newCategoryUnder30 ["+newCategoryUnder30.getName()
				+"] comprend ce produit ["+product.getName()+"]"));
		newProductUnder30.getCategories().forEach(category -> System.out.println("Ce produit ["+newProductUnder30.getName()+"] " +
				"appartient à cette catégorie ["+category.getName()+"]"));
*/
		System.out.println("**************** Mettre à jour le nom du product dont id est 10 *******************");
		/*Product productId10 = productService.getProductById(10).get();
		productId10.setName("Assurance-25");
		productService.addProduct(productId10);
		Product newProductId10 = productService.getProductById(10).get();
		System.out.println(newProductId10.getName());*/

		System.out.println("***************** Supprimer un Produit et les commentaires associés et uniquement un commentaire**************************");
		//productService.deleteProductById(9);
		//Supprimer le commentaire d'Id 7 associé à produit dont Id est 5
		/*Comment commentId7 = commentService.getCommentById(7).get();
		Product productId7 = productService.getProductById(5).get();
		productId7.removeComment(commentId7);
*/
		System.out.println("**************** Supprimer le product qui impacte pas la catégorie *******************");
		//productService.deleteProductById(14);
		System.out.println("****************** Supprimer la catégorie mais qui ne impacte pas le produit **************************");
		//categoryService.deleteCategoryById(5);

		System.out.println("********************* Derived Query *******************************");
		Iterable<Product> productsByName = productService.getProductsByName("AssuranceTousRisques");
		productsByName.forEach(product -> System.out.println("Cherché par nom de l'assurance : ["+product.getName()+"], dont l'Id est["+product.getProductId()+"]."));

		System.out.println("********************** Associer plusieurs critères *****************************");
		Iterable<Product> productByNameAndCost = productService.getProductsByNameAndCost("AssuranceTousRisques",1500);
		productByNameAndCost.forEach(product -> System.out.println("Le produit est ["+product.getName()+"], le cout est ["+product.getCost()+"]"));

		System.out.println("******************* Requêtes dérivées à travers la relation ********************************");
		Iterable<Product> productsByCategory = productService.getProductsByCategory("Standard");
		System.out.println("Les produits par catégorie sont : ");
		productsByCategory.forEach(product -> System.out.println(product.getName()));

		System.out.println("************************** Chercher par le nom de la catégorie ************************************");
		Iterable<Category> getCategorysByName = categoryService.getCategoryByName("Standard");
		getCategorysByName.forEach(category -> System.out.println(category.getName()));
		System.out.println("************************** Chercher par le nom d’un produit de la catégorie ************************************");
		Iterable<Category> getCategorysByProduct = categoryService.getCategoryByProductName("AssuranceTousRisques");
		getCategorysByProduct.forEach(category -> System.out.println(category.getName()));

		System.out.println("************************* Rechercher les produits par coût en utilisant Native Query*********************************************");
		Iterable<Product> getProductByCostNativeQuery = productService.getProductByCoutLessThanNativeQuery(1000);
		getProductByCostNativeQuery.forEach(product -> System.out.println(product.getName()+"\n"));


		/*System.out.println("J'ai oublié d'associer la relation entre AssuranceTierMaxi avec une catégorie");
		Product productTierMaxi = productService.getProductById(5).get();
		Category categoryPromotion = categoryService.getCategoryById(4).get();
		categoryPromotion.addProduct(productTierMaxi);*/
		System.out.println("Chercher la catégorie pour le produit (AssuranceTierMaxi) ");
		Iterable<Category> getCategorysPourMaxiTier = categoryService.getCategoryByProductName("AssuranceTierMaxi");
		getCategorysPourMaxiTier.forEach(category -> System.out.println(category.getName()));

		System.out.println("************************ Chercher les produits par leur coût moins 1000 *******************************");
		Iterable<Product> getProductsByCoutLessThan = productService.getProductByCoutLessThan(1000);
		getProductsByCoutLessThan.forEach(product -> System.out.println("Le produit ["+product.getName()+"], le coût : ["+product.getCost()+"]."));

		System.out.println("****************************** Rechercher les commentaires qui comprennent le mot : déçu par requête JPQL ***********************************");
		Iterable<Comment> getCommentsByContentLike = commentService.getCommentsByContentLike("deçu");
		getCommentsByContentLike.forEach(comment -> System.out.println(comment.getContent()));

		System.out.println("********************* Rechercher les commentaires avec requête Dérivée ***********************************");
		//Requêtes dérivées
		//La recherche des commentaires : dont le contenu contient le mot xxx using "findByContentContaining"
		Iterable<Comment> getCommentByContentContaining = commentService.getCommentsByContentContaining("deçu");
		getCommentByContentContaining.forEach(comment -> System.out.println(comment.getContent()));


	}
}
