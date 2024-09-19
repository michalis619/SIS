package org.example.model;


public class Product {
  private String id;
  private String description;
  private String category;
  private double price;
  private boolean isSpecial;

  // Constructor
  public Product(String id, String description, String category, double price, boolean isSpecial) {
    this.id = id;
    this.description = description;
    this.category = category;
    this.price = price;
    this.isSpecial = isSpecial;
  }

  // Default Constructor
  public Product() {}

  // Getters and Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public boolean isSpecial() {
    return isSpecial;
  }

  public void setSpecial(boolean isSpecial) {
    this.isSpecial = isSpecial;
  }

  // Override toString() for better logging/debugging output
  @Override
  public String toString() {
    return "Product{" +
            "id='" + id + '\'' +
            ", description='" + description + '\'' +
            ", category='" + category + '\'' +
            ", price=" + price +
            ", special=" + isSpecial +
            '}';
  }
}

