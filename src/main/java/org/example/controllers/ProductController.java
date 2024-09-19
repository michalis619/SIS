package org.example.controllers;



import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product createProduct(@RequestBody Product product) {
    return productService.createProduct(product);
  }

  @PutMapping("/{id}")
  public Product updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
    return productService.updateProduct(id, updatedProduct);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable String id) {
    productService.deleteProduct(id);
  }
}

