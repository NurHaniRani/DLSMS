# Distributed Laundry Service Management System (DLSMS)

## **Introduction**

The **Distributed Laundry Service Management System (DLSMS)** is a digital solution designed to streamline laundry operations for both customers and staff.  
The system eliminates manual order handling by providing:

- **Customer App**: Place laundry orders, check service prices, and track order status.  
- **Staff App**: Manage and update orders efficiently.

This system promotes faster service, reduces errors, and enhances customer satisfaction.

---

## **Commercial Value / Third-Party Integration**

This project has the potential to be deployed by local laundromats to modernize their operations.  
In real-world applications, future integrations could include:

- Payment Gateways (for online payments)
- Notification Services (SMS/Email updates)
- Delivery Tracking APIs (for laundry pickup and delivery)

Currently, the system interacts with a **custom REST API** to handle all transactions between the frontend apps and the database. This ensures secure and controlled data management.

---

## **System Architecture**

### **Architecture Type: Client-Server (3-Tier Architecture)**

![Architecture Diagram](https://github.com/user-attachments/assets/17a8ca44-e3f8-4611-abe9-0f466742f0c4)

**Key Characteristics:**

- **Centralized Data Control**  
  Both apps communicate through the same REST API for consistent and real-time data.

- **Separation of Concerns**  
  The frontend handles the user interface, while the backend manages logic and data.

- **Scalability**  
  New services, users, or additional apps can be added without major system changes.

---

## **Backend Application**

### **Technology Stack**

| Component   | Technology           |
|-------------|----------------------|
| Language    | PHP                  |
| API         | RESTful API (PHP)    |
| Database    | MySQL (phpMyAdmin)   |
| Server      | XAMPP (Apache)       |

---

### **API Documentation**

| Endpoint        | Method | Description                   |
|----------------|--------|-------------------------------|
| /register       | POST   | Customer Registration          |
| /login          | POST   | Login for Customer/Staff       |
| /orders         | GET    | Retrieve all orders (Staff)    |
| /orders         | POST   | Place new order (Customer)     |
| /order/status   | PUT    | Update order status (Staff)    |
| /services       | GET    | Retrieve service prices        |

#### **Request/Response Example**

##### **Register (Customer)**

**POST** `/register`

**Request Body:**


Security:

API communication is secured via server-side validation and parameter checks.
Authentication is handled with simple session tokens (in future versions, can be replaced with JWT or OAuth 2.0 for scalability and security).

Frontend Application:

Laundry Customer App
Purpose:

Allows customers to register, place laundry orders, view services and prices, and track order statuses.
Technology Stack:
Java (Swing GUI) via Eclipse IDE
API Integration:
The Customer App sends HTTP requests (GET/POST) to the REST API for all transactions.

Laundry Staff App
Purpose:

Enables staff to view all orders and update their status.
Technology Stack:
Java (Swing GUI) via Eclipse IDE
API Integration:
The Staff App communicates with the REST API for order management and updates.

Database Design
Entity-Relationship Diagram (ERD)

<img width="461" height="381" alt="erd" src="https://github.com/user-attachments/assets/f246cb57-1b49-41f5-a80d-bbffdceb0f1d" />

Schema Justification:

Customer Table: Stores customer information for login and orders.
Orders Table: Records each laundry order with details like service type, clothing type, price, and status.
Staff Table: Stores staff credentials for order management.

Business Logic and Data Validation:

Customer App Flow:

Register / Login -> View services and prices -> Place an order (select service, clothing type) -> Track order status

Staff App Flow:

Login -> View all incoming orders -> Update order statuses (e.g., "In Progress", "Completed")

Data Validation:

Frontend:

Prevent empty form submissions
Validate email format

Backend:

Check for existing emails during registration
Sanitize inputs to prevent SQL Injection

How to Run the System
1. Install XAMPP
2. Place API file (laundry_api.php) in htdocs
3. Start Apache and MySQL via XAMPP
4. Import the database SQL file(laundry.sql) in phpMyAdmin
5. Run the Java Apps in Eclipse IDE
6. Add the org.json file if needed.
