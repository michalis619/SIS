package org.example.service;

import org.example.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

  @Test
  public void getProductByIdTest() {
    ProductService productService = new ProductService();

    productService.add(new Product("1", "Carrot", "vegetables", 1.5, false));
    productService.add(new Product("2", "Carrot", "vegetables", 1.5, false));
    Product retrievedProduct = productService.getProductById("1");

    // Assertions
    assertNotNull(retrievedProduct);
    assertEquals("1", retrievedProduct.getId());
    assertEquals("Carrot", retrievedProduct.getDescription());
    assertEquals("vegetables", retrievedProduct.getCategory());
    assertEquals(1.5, retrievedProduct.getPrice(), 0.01);
    assertFalse(retrievedProduct.isSpecial());
  }


  @Test
  public void deleteProductTest() {
    ProductService productService = new ProductService();

    productService.add(new Product("1", "Carrot", "vegetables", 1.5, false));
    productService.add(new Product("2", "Carrot", "vegetables", 1.5, false));
    productService.deleteProduct("1");

    // Verify that the product was deleted by attempting to retrieve it
    assertThrows(ResponseStatusException.class, () -> {
      productService.getProductById("1");
    });

  }

  @Test
  public void createProductTest() {
    ProductService productService = new ProductService();

    productService.createProduct(new Product("1", "Carrot", "vegetables", 1.5, false));
    Product retrievedProduct = productService.getProductById("1");

    // Assertions
    assertNotNull(retrievedProduct);
    assertEquals("1", retrievedProduct.getId());
    assertEquals("Carrot", retrievedProduct.getDescription());
    assertEquals("vegetables", retrievedProduct.getCategory());
    assertEquals(1.5, retrievedProduct.getPrice(), 0.01);
    assertFalse(retrievedProduct.isSpecial());
  }

  @Test
  public void updateProductTest() {
    ProductService productService = new ProductService();

    productService.add(new Product("1", "Carrot", "vegetables", 1.5, false));
    Product updateProduct = new Product("1", "Tomato", "vegetables", 1.5, false);
    productService.updateProduct("1", updateProduct);
    Product retrievedProduct = productService.getProductById("1");

    // Assertions
    assertNotNull(retrievedProduct);
    assertEquals("1", retrievedProduct.getId());
    assertEquals("Tomato", retrievedProduct.getDescription());
    assertEquals("vegetables", retrievedProduct.getCategory());
    assertEquals(1.5, retrievedProduct.getPrice(), 0.01);
    assertFalse(retrievedProduct.isSpecial());
  }

}
