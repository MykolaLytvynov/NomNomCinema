# 🎬 NomNomCinema

NomNomCinema is a **multi-module microservices application** allowing cinema viewers to order food and drinks directly from seat-embedded tablets.

## Tech Stack
- **Java 21**
- **Spring Boot**
- **Kafka**
- **MongoDB**
- **Docker**
- **SLF4J & Logback** (Logging)

## Features
- **Order Service**: Handles food and drink orders from users and stores them in **MongoDB**.
- **Buffet Service**: Processes orders from **Kafka** and generates **receipts**.
- **Common Module**: Contains shared models and configurations used across microservices, ensuring consistency and reducing code duplication.
- **Logging**: Key actions, such as receiving orders, saving them to the database, and sending messages to Kafka, are logged for better traceability and debugging.

### Example Receipt
```
================================
           ORDER DETAILS         
================================
Order ID: 8b626d89-fdca-4504-8ec1-93d63dae359e

ITEMS TO PREPARE:
  - Popcorn x 2
  - Soda x 1
--------------------------------
DELIVERY INFO FOR WAITER:
  → Hall: 1
  → Row: 5
  → Seat: 10
================================
```

## Getting Started

### Clone the Repository

First, clone the repository to your local machine

### Environment Configuration
Create a .env file in the root of the project with the following configuration:

```
# MONGO DB
MONGO_DB_DATABASE=nom-nom-db
MONGO_DB_USERNAME=admin
MONGO_DB_PASSWORD=1234
```

## 🐳 Docker Compose Setup

The project uses **Docker Compose** to manage multiple services, including the **Order Service, Buffet Service, Kafka, and MongoDB**. The `docker-compose.yml` file defines the setup for each service.

### Running the Application

To start all microservices, use the following command:

```bash
docker-compose up --build -d
```

### Services:
- **order-service**: Handles customer orders, stores them in **MongoDB**, and sends them to Kafka.
- **buffet-service**: Processes orders from **Kafka** and generates **receipts**.
- **mongo**: Stores order data.
- **kafka**: Message broker for event-driven communication.

After running this command, the application will be accessible at http://localhost:8080.

## API Endpoints

### Create an order

**POST** `/api/orders`

**Request body:**

```json
{
  "hall": 3,
  "row": 7,
  "seat": 15,
  "items": [
    { "itemName": "Nachos", "quantity": 1 },
    { "itemName": "Coca-Cola", "quantity": 2 }
  ]
}
```

**Response:**

```json
{
    "id": "799ff52c-41a1-4568-85d3-2ccd531a7c3d",
    "hall": 3,
    "row": 7,
    "seat": 15,
    "createdAt": "2025-02-27T17:28:06.820268667",
    "items": [
        {
            "itemName": "Nachos",
            "quantity": 1
        },
        {
            "itemName": "Coca-Cola",
            "quantity": 2
        }
    ]
}
```

### Get All orders

**GET** `/api/orders`

**Query Parameters:**
- `page` (default: 0): The page number to fetch.
- `size` (default: 100): The number of orders per page.

**Response:**

```json
{
    "content": [
        {
            "id": "8b626d89-fdca-4504-8ec1-93d63dae359e",
            "hall": 1,
            "row": 5,
            "seat": 10,
            "createdAt": "2025-02-27T17:28:23.927",
            "items": [
                {
                    "itemName": "Popcorn",
                    "quantity": 2
                },
                {
                    "itemName": "Soda",
                    "quantity": 1
                }
            ]
        },
        {
            "id": "799ff52c-41a1-4568-85d3-2ccd531a7c3d",
            "hall": 3,
            "row": 7,
            "seat": 15,
            "createdAt": "2025-02-27T17:28:06.82",
            "items": [
                {
                    "itemName": "Nachos",
                    "quantity": 1
                },
                {
                    "itemName": "Coca-Cola",
                    "quantity": 2
                }
            ]
        }
    ],
    "page": 0,
    "size": 100,
    "totalElements": 2,
    "totalPages": 1,
    "lastPage": true
}
```


## Author

Mykola Lytvynov
