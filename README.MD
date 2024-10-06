# Spring Boot API

This is an API developed in Java using Spring Boot. It includes classes such as `Category`, `Product`, `Supplier`, and `User`.

## Prerequisites

- **MySQL:** Make sure you have MySQL installed and running on your machine. You will need the credentials to configure the database connection.

## Configuration

1. **Set up MySQL:**
   - Create a database in MySQL that will be used by the API.

2. **Configure the `application.properties` file:**
   - In the `src/main/resources` directory, open the `application.properties` file.
   - Edit the following properties to match your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

## Running the API

1. After configuring the database, navigate to the main class `ApiApplication.java`.
2. Run the application using an IDE like Eclipse, IntelliJ IDEA, or directly from the terminal with the command:
   ```bash
   ./mvnw spring-boot:run

3. The API will be running at: `http://localhost:8080`.

## Testing the API

- Use tools like [Postman](https://www.postman.com/) or [cURL](https://curl.se/) to send HTTP requests (GET, POST, PUT, DELETE) to the API endpoints.

## Default Endpoints

- **Category:** `/api/categories`
- **Product:** `/api/products`
- **Supplier:** `/api/suppliers`
- **User:** `/api/users`

These endpoints support CRUD (Create, Read, Update, Delete) operations for their respective entities.

## Final Considerations

- Make sure MySQL is running before starting the API. If there are any connection issues, double-check the credentials and the database name in the `application.properties` file.
