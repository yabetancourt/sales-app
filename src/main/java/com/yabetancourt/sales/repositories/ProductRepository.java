package com.yabetancourt.sales.repositories;

import com.yabetancourt.sales.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainsIgnoreCase(String name);
}
