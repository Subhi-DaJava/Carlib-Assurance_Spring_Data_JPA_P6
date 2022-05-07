package com.openclassrooms.dataLayer.repository;

import com.openclassrooms.dataLayer.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {

    //public Iterable<Product> findByName(String name);
}
