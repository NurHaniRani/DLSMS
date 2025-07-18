##Distributed Laundry Service Management System (DLSMS)

Introduction:

The Distributed Laundry Service Management System (DLSMS) is a digital solution designed to streamline laundry operations for both customers and staff. The system eliminates manual order handling by providing:
A Customer App for placing laundry orders, checking service prices, and tracking order status.
A Staff App for managing and updating orders efficiently.
This system promotes faster service, reduces errors, and enhances customer satisfaction.

Commercial Value / Third-Party Integration:

This project has the potential to be deployed by local laundromats to modernize their operations. In real-world applications, integrations with payment gateways, notification services, or delivery tracking APIs could further expand its capabilities.
Currently, the system interacts with a custom REST API to handle all transactions between the frontend apps and the database. This approach ensures secure and controlled data management.

System Architecture Type:

Client-Server (3 Tier Architecture)

<img width="544" height="321" alt="architecture diagram" src="https://github.com/user-attachments/assets/17a8ca44-e3f8-4611-abe9-0f466742f0c4" />

Centralized Data Control: Both apps communicate through the same REST API for data consistency.
Separation of Concerns: Frontend handles user interface; backend handles business logic and data.
Scalability: Easily add new services, users, or additional apps without major system changes.

Backend Application:

Technology Stack
Component	Technology
Language	PHP
API	RESTful API (Custom PHP scripts)
Database	MySQL (phpMyAdmin)
Server	XAMPP (Apache)

API Documentation:

Endpoint	Method	Description
/register	POST	Customer Registration
/login	POST	Login for Customer/Staff
/orders	GET	Retrieve all orders (Staff)
/orders	POST	Place new order (Customer)
/order/status	PUT	Update order status (Staff)
/services	GET	Retrieve service prices

Request/Response Examples:

Register (Customer)
POST /register
Request Body:

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "123456"
}

Response (Success):

{
  "status": "success",
  "message": "Registration successful"
}

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
