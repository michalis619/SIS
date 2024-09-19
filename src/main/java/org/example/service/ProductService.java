package org.example.service;


import org.example.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

  private List<Product> products = new ArrayList<>();
  private List<String> allIds = new ArrayList<>();

  public List<Product> getAllProducts() {
    return products;
  }

  public Product getProductById(String id) {
    return products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
  }

  public Product createProduct(Product product) {
    if (product.getId() == null || allIds.contains(product.getId())) {
      product.setId(UUID.randomUUID().toString());
    } else {
      allIds.add(product.getId());
    }
    products.add(product);
    return product;
  }

  public Product updateProduct(String id, Product updatedProduct) {
    Product product = getProductById(id);

    product.setDescription(updatedProduct.getDescription());
    product.setCategory(updatedProduct.getCategory());
    product.setPrice(updatedProduct.getPrice());
    product.setSpecial(updatedProduct.isSpecial());

    return product;
  }

  public void deleteProduct(String id) {
    products.removeIf(p -> p.getId().equals(id));
  }

  void add(Product product) {
    products.add(product);
  }
}


