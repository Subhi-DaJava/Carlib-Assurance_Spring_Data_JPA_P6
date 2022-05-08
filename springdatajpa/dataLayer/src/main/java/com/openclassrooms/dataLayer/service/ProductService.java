package com.openclassrooms.dataLayer.service;

import com.openclassrooms.dataLayer.model.Product;
import com.openclassrooms.dataLayer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id){
        return productRepository.findById(id);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProductById(Integer id){
        productRepository.deleteById(id);
    }
    //Derived Query
    public Iterable<Product> getProductsByName(String name){
        return productRepository.findByName(name);
    }
    //Associer plusieurs critères
    public Iterable<Product> getProductsByNameAndCost(String name, Integer cost){
        return productRepository.findByNameAndCost(name,cost);
    }
    //Les requêtes dérivées à travers la relation
    public Iterable<Product> getProductsByCategory(String name){
        return productRepository.findByCategoriesName(name);
    }

    //Chercher par coût using Derived Queries
    public Iterable<Product> getProductByCoutLessThan(Integer cout){
        return productRepository.findByCostLessThan(cout);
    }

    //Chercher par coût using Natives Queries
    public Iterable<Product> getProductByCoutLessThanNativeQuery(Integer cout){
        return productRepository.findByCostNative(cout);
    }
}
