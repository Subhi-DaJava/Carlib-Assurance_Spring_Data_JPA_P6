package com.openclassrooms.dataLayer.service;

import com.openclassrooms.dataLayer.model.Category;
import com.openclassrooms.dataLayer.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getCategorys(){
        return categoryRepository.findAll();
    }
    public Optional<Category> getCategoryById(Integer id){
        return categoryRepository.findById(id);
    }
    public Category addCategory(Category category){
       return categoryRepository.save(category);
    }
    public void deleteCategoryById(Integer id){
        categoryRepository.deleteById(id);
    }

    //Requêtes dérivées
    //par le nom de la catégorie
    public Iterable<Category> getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }
    //par le nom d’un produit de la catégorie
    public Iterable<Category> getCategoryByProductName(String name){
        return categoryRepository.findByProductsName(name);
    }

}
