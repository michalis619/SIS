

# Product Manager

## Overview

The Product Manager project is a simple web application designed for managing products. It allows users to perform various operations such as creating, updating, deleting, and viewing products. The application uses a REST API for backend operations and includes features like product listing, filtering by category, and form-based operations for managing product data.

## Features

- **Create New Product**: Allows users to add new products.
- **Update Existing Product**: Allows users to update details of an existing product.
- **Delete Product**: Allows users to remove a product.
- **Filter Products**: Enables users to filter the product list by category.
- **View Products**: Displays a list of all products with their details.

## Backend

The backend of this application is built using [Spring Boot](https://spring.io/projects/spring-boot), which provides:

- **Convenient way to create RESTful services**.
- **Auto-Configuration**: Reduces the need for manual configuration and setup.
- **Ease of Testing**: Simplifies the testing process with built-in support.

## Running the Application

1. **Build the Project**

   Run the following command to build the project:

   ```
   mvn clean install
   ```

2. **Run the Spring Boot Application**

   Run the following command to run the application:

   ```
   mvn spring-boot:run
   ```
3. **Access the Application**

Open the HTML file (e.g., index.html) using any web browser to interact with the frontend of the application.      

The backend API endpoints are available at http://localhost:8080/api/products
