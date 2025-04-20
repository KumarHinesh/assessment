# Billing and Currency Exchange Service with Test Cases

This Spring Boot application calculates billing amounts for users based on item categories, applies specific discount rules based on user types, and converts the final amount to a target currency using real-time exchange rates fetched from an external API.


## Features
- Calculate total and final bill amounts after applying discounts.
- Apply percentage-based discounts based on user type.
- Apply flat $5 discount for every $100 on the bill.
- Convert bill total to a target currency using real-time exchange rates.
- Cache exchange rates to reduce API calls.
- Secure endpoints with Basic Authentication.
- Easy configuration through `application.properties`.
- Full test coverage with JUnit and Mockito.
- Gracefully handles errors, exceptions such as missing exchange rates or reflection field access issues.


## 📋 Project Overview
This project provides a Billing and Currency Exchange service that calculates the final bill after applying various discounts, including percentage-based and flat discounts, and converts the total bill to a target currency. The service supports user authentication with basic authentication and caches exchange rates to minimize unnecessary API calls.


## 🛠️ Technologies Used

- **Java** - Programming language
- **Spring Boot** - Framework for building web applications
- **JUnit** - Testing framework
- **Mockito** - For mocking objects in unit tests
- **RestTemplate** - For making HTTP requests
- **H2 Database** - For testing purposes (if you’re using a database)
- **SonarQube** - For code quality and static analysis

## ⚙️ Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/KumarHinesh/assessment.git
   ```
2. Navigate into the project directory:
   ```bash
   cd project-name
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

You can also run the tests with:
```bash
  mvn test
  ``` 


## API Endpoint
- URL: /api/calculate
- Method: POST
- Request Body
   {
    "userType": "CUSTOMER",
    "customerTenureInYears": 1,
    "originalCurrency": "USD",
    "targetCurrency": "AED",
    "items": [
        {
            "name": "Laptop",
            "price": 1200,
            "category": "ELECTRONICS"
        },
        {
            "name": "Bread",
            "price": 20,
            "category": "GROCERY"
        },
        {
            "name": "Shoes",
            "price": 150,
            "category": "CLOTHING"
        }
    ]
}

## Usage with cURL

curl --location 'http://localhost:8080/api/calculate' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW46YWRtaW4xMjM=' \
--header 'Cookie: JSESSIONID=00BD47D0E52FCC1844FF154C74182613' \
--data '{
    "userType": "CUSTOMER",
    "customerTenureInYears": 1,
    "originalCurrency": "USD",
    "targetCurrency": "AED",
    "items": [
        {
            "name": "Laptop",
            "price": 1200,
            "category": "ELECTRONICS"
        },
        {
            "name": "Bread",
            "price": 20,
            "category": "GROCERY"
        },
        {
            "name": "Shoes",
            "price": 150,
            "category": "CLOTHING"
        }
    ]
}'



