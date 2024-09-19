package org.example;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Product;
import org.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService; // Mock ProductService

  ObjectMapper objectMapper = new ObjectMapper();

  private List<Product> products;

  @BeforeEach
  public void setup() {

    // Initialize the product list
    products = new ArrayList<>();
  }

  @Test
  public void ReturnAllProductsTest() throws Exception {
    // Stub ProductService to return a list of products
    products.add(new Product("1", "Carrot", "vegetables", 1.5, false));
    products.add(new Product("2", "Tomato", "vegetables", 2.5, false));
    Mockito.when(productService.getAllProducts()).thenReturn(products);

    ResultActions resultActions = this.mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].description").value("Carrot"))
            .andExpect(jsonPath("$[1].description").value("Tomato"));
  }

  @Test
  public void CreateNewProductTest() throws Exception {
    Product newProduct = new Product("1", "Carrot", "vegetables", 1.5, false);

    Mockito.when(productService.createProduct((Product) Mockito.any(Product.class))).thenReturn(newProduct);

    ResultActions resultActions = this.mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(newProduct)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is("1")))
            .andExpect(jsonPath("$.description", is("Carrot")));
  }

  @Test
  public void UpdateExistingProductTest() throws Exception {
    String productId = "1";
    Product updatedProduct = new Product(productId, "Tomato", "vegetables", 3.5, true);

    Mockito.when(productService.updateProduct(eq(productId), Mockito.any(Product.class))).thenReturn(updatedProduct);

    ResultActions resultActions = this.mockMvc.perform(put("/api/products/{id}", productId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updatedProduct)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price", is(3.5)))
            .andExpect(jsonPath("$.special", is(true)));
  }


  @Test
  public void DeleteProductTest() throws Exception {
    String productId = "1";

    ResultActions resultActions = this.mockMvc.perform(delete("/api/products/{id}", productId))
            .andExpect(status().isOk());
    verify(productService, times(1)).deleteProduct(productId);
  }
}

