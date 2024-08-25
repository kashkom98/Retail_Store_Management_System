# Retail Store Management System

## Project Overview
The **Retail Store Management System (RSMS)** is a comprehensive application designed to streamline the management of small-scale retail stores. It provides tools for handling customer data, cashier management, inventory tracking, order processing, and supplier information—all within a user-friendly interface.

## Features
- **Admin & Cashier Roles**: Separate functionalities for Admin and Cashier roles, enhancing security and role-based access.
- **CRUD Operations**: Perform Create, Read, Update, and Delete operations on Cashiers, Customers, Items, and Suppliers.
- **Order Management**: Cashiers can easily place orders, manage customers, and handle transactions.
- **Dashboard Overview**: Admins can view key statistics such as total sales, number of orders, customer count, and total quantity.
- **User-Friendly Interface**: Simplified navigation with a clean and intuitive UI designed using JavaFX and Scene Builder.
- **Database Integration**: Robust back-end support with MySQL, ensuring reliable data management through JDBC and DAO patterns.

## Technical Stack
- **Frontend**: JavaFX, FXML
- **Backend**: Java, JDBC, MySQL
- **IDE**: IntelliJ IDEA
- **Tools**: MySQL Workbench, Scene Builder

## System Architecture
The system follows the **Model-View-Controller (MVC)** architectural pattern, ensuring a clean separation of concerns and easy maintainability:
- **Model**: Handles data logic and business operations.
- **View**: Manages the graphical interface, created using FXML.
- **Controller**: Controls the application's workflow and interacts with the Model to process data and update the View.

## Database Design
The application uses a MySQL database with the following key tables:
- **Customers**: Stores customer details.
- **Cashiers**: Stores cashier details and login credentials.
- **Items**: Stores item inventory details.
- **Suppliers**: Stores supplier information.
- **Orders**: Manages customer orders and related details.
