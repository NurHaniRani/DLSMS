# Distributed Laundry Service Management System (DLSMS)

## Introduction

The **Distributed Laundry Service Management System (DLSMS)** is a digital solution designed to streamline laundry operations for both customers and staff.

The system eliminates manual order handling by providing:

- **Customer App**  
  Place laundry orders, check service prices, and track order status.

- **Staff App**  
  Manage and update orders efficiently.

This system promotes faster service, reduces errors, and enhances customer satisfaction.

---

## Commercial Value / Third-Party Integration

This project can be deployed by local laundromats to modernize their daily operations.

### Potential Future Integrations:

- **Payment Gateways** – For online payments  
- **Notification Services** – SMS/Email order updates  
- **Delivery Tracking APIs** – For laundry pickup and delivery tracking

Currently, the system interacts with a **custom REST API** to handle all transactions between the frontend apps and the database. This ensures **secure and controlled data management**.

---

## System Architecture

### Architecture Type:  
**Client-Server (3-Tier Architecture)**

![Architecture Diagram](https://github.com/user-attachments/assets/17a8ca44-e3f8-4611-abe9-0f466742f0c4)

### Key Characteristics:

- **Centralized Data Control**  
  Both apps communicate through the same REST API for consistent and real-time data.

- **Separation of Concerns**  
  - **Frontend**: User Interface  
  - **Backend**: Logic & Data Management

- **Scalability**  
  New services, users, or additional apps can be added without major system changes.

---

## Backend Application

### Technology Stack

| Component   | Technology           |
|-------------|----------------------|
| Language    | PHP                  |
| API         | RESTful API (PHP)    |
| Database    | MySQL (phpMyAdmin)   |
| Server      | XAMPP (Apache)       |

---

### API Documentation

| Endpoint          | Method | Description                     |
|------------------|--------|---------------------------------|
| `/register`       | POST   | Customer Registration           |
| `/login`          | POST   | Login for Customer/Staff        |
| `/orders`         | GET    | Retrieve all orders (Staff)     |
| `/orders`         | POST   | Place new order (Customer)      |
| `/order/status`   | PUT    | Update order status (Staff)     |
| `/services`       | GET    | Retrieve service prices         |

#### Example: Register (Customer)

**Endpoint:**  
`POST /register`

**Request Body:**

{
"name": "John Doe",
"email": "john@example.com",
"password": "password123"
}


---

## Security

- **Server-side Validation** and **Parameter Checks** are implemented to secure the API.  
- Authentication is handled via **simple session tokens**.  
- (Future versions can use **JWT** or **OAuth 2.0** for enhanced security and scalability.)

---

## Frontend Applications

### Laundry Customer App

#### Purpose

- Register and log in  
- Place laundry orders  
- View services and prices  
- Track order statuses

#### Technology Stack

- **Java (Swing GUI)**  
- **Eclipse IDE**

#### API Integration

The Customer App sends **HTTP GET/POST requests** to the REST API for all transactions.

---

### Laundry Staff App

#### Purpose

- Login  
- View all incoming orders  
- Update order statuses (e.g., "In Progress", "Completed")

#### Technology Stack

- **Java (Swing GUI)**  
- **Eclipse IDE**

#### API Integration

The Staff App communicates with the **REST API** for order management.

---

## Database Design

### Entity-Relationship Diagram (ERD)

<img width="461" height="381" alt="erd" src="https://github.com/user-attachments/assets/f246cb57-1b49-41f5-a80d-bbffdceb0f1d" />

### Schema Justification

- **Customer Table** – Stores customer info for login and orders.  
- **Orders Table** – Records laundry orders with service type, clothing type, price, and status.  
- **Staff Table** – Stores staff credentials for order management.

---

## Business Logic & Data Validation

### Customer App Flow

`Register / Login` → `View Services & Prices` → `Place Order` → `Track Order Status`

### Staff App Flow

`Login` → `View Orders` → `Update Order Status`

---

### Data Validation

#### Frontend

- Prevent empty form submissions  
- Validate email formats

#### Backend

- Check for existing emails during registration  
- Sanitize inputs to prevent **SQL Injection**

---

## How to Run the System

1. Install **XAMPP**  
2. Place the API file (`laundry_api.php`) in the **htdocs** folder  
3. Start **Apache** and **MySQL** via XAMPP  
4. Import the **database SQL file** (`laundry.sql`) into **phpMyAdmin**  
5. Run the **Java Apps** in **Eclipse IDE**  
6. Add the **org.json** library to the project if needed

---
