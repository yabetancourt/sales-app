package com.yabetancourt.sales.controllers;

import com.yabetancourt.sales.entities.Product;
import com.yabetancourt.sales.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService service;
    private final Logger logger = Logger.getLogger(ProductController.class.getName());

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = service.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return saveProduct(product, "Product created");
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return saveProduct(product, "Product updated");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Long id) {
        service.deleteById(id);
        logger.info("Product deleted");
    }

    private ResponseEntity<Product> saveProduct(Product product, String info) {
        service.save(product);
        logger.info(info);
        return ResponseEntity.ok(product);
    }

}
