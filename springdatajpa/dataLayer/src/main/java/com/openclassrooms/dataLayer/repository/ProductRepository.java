package com.openclassrooms.dataLayer.repository;

import com.openclassrooms.dataLayer.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
     //Derived Query
     Iterable<Product> findByName(String name);
     //Associer plusieurs critères
     Iterable<Product> findByNameAndCost(String name, Integer cost);
     //Requêtes dérivées à travers la relation
     Iterable<Product> findByCategoriesName(String name);
     //Chercher les produits par coût (lessThan or gratterThan)
     Iterable<Product> findByCostLessThan(Integer cout);

     @Query("FROM Product WHERE name = ?1")
     Iterable<Product> findByNameJPQL(String name);

     @Query(value = "SELECT * FROM produit WHERE cout <= :cout", nativeQuery = true)
     Iterable<Product> findByCostNative(@Param("cout") Integer cost);

}
