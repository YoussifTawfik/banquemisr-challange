# Task Management Project

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Prerequisites](#prerequisites)
5. [Setup and Installation](#setup-and-installation)
6. [Database Configuration](#database-configuration)
7. [Running the Application](#running-the-application)
8. [API Endpoints](#api-endpoints)

---

## Introduction
The **Task Management Project** is a Spring Boot application designed to manage tasks efficiently. It provides CRUD operations for tasks, with the ability to assign priorities, deadlines, and statuses.

---

## Features
- Create, Read, Update, and Delete todos.
- Assign priorities (Low, Medium, High).
- Set deadlines for todo.
- Manage task statuses (Pending, In Progress, Completed).
- RESTful APIs for seamless integration.
- Authentication and authorization based on user roles and permissions.
- Notification system to alert users one day before their task's due date.

---

## Technologies Used
- **Java 17**
- **Spring Boot 3.4.1**
- **MySQL**
- **Gradle**
- **Postman** (for API testing)

---

## Prerequisites
Before running the application, ensure you have:
- Java 17 installed.
- MySQL database installed and running.
- Gradle installed.

---

## Setup and Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/YoussifTawfik/banquemisr-challange.git
   cd task-management
   ```

2. **Build the Project**:
   ```bash
   ./gradlew clean build
   ```

3. **Configure Database** (see [Database Configuration](#database-configuration)).

4. **Run the Application**:
   ```bash
   ./gradlew bootRun
   ```

---

## Database Configuration
1. Create a MySQL database named `task-management`.
   ```sql
   CREATE DATABASE task-management;
   ```

2. Run the application and Liquibase will run all the required scripts.

---

## Running the Application
To run the application locally:
1. Ensure MySQL is running.
2. Execute the following command:
   ```bash
   ./gradlew bootRun
   ```
3. Access the application at `http://localhost:8081`.

---

## API Endpoints (Postman collection is included in the project)
Below are the main endpoints available:

### Task Endpoints
| HTTP Method | Endpoint                        | Description              |
|-------------|---------------------------------|--------------------------|
| GET         | `/task-management/todos`        | Retrieve all todos       |
| GET         | `/task-management/todos{uuid}`  | Retrieve a specific todo |
| POST        | `/task-management/todos`        | Create a new todo        |
| PATCH       | `/task-management/todos/{uuid}` | Update an existing todo  |
| DELETE      | `/task-management/todos{uuid}`  | Delete a todo            |

### Example JSON Payload for Creating/Updating a Task
```json
{
  "title": "Complete documentation",
  "description": "Finalize the project documentation.",
  "priority": "High",
  "status": "IN_PROGRESS",
  "dueDate": "2025-01-15T20:30"
}
```

