# Employee Management System (JavaFX + RMI + JDBC)

## 📌 Overview

This project is a **distributed Employee Management System** developed using:

* **JavaFX** → User Interface (Client)
* **RMI (Remote Method Invocation)** → Communication between client and server
* **JDBC + MySQL** → Database operations (Server-side)

The system allows users to **add, view, update, and delete employees** through a graphical interface.

---

## 👨‍💻 Contributors (GROUP-3)

- Emandoyesus Tesfay (UGR/188057/16)
- Dawit Gerezgiher (UGR/188001/16)
- Edilawit Kalau (UGR/188034/16)
- Kiros Gebremariam (UGR/188336/16)
- Abeba Gebru (EITM/TUR182021/17)
- Haftom Gebrehiwot (UGR/188215/16)
- Akebom Gebreab(Mit/ur187839/16)
---

## 🏗️ System Architecture

The system follows a **client-server architecture**:

* **Client (JavaFX App)**
  Handles UI and sends requests to the server.

* **Server (RMI Server)**
  Handles business logic and database operations.

* **Database (MySQL)**
  Stores employee data.

---


## ⚙️ Technologies Used

* Java (JDK 17+)
* JavaFX
* MySQL
* JDBC (MySQL Connector/J)
* RMI (Remote Method Invocation)
* IntelliJ IDEA

---

## 📂 Project Structure

```
src/
 ├── MainApp.java                // JavaFX Client
 ├── RMIServer.java              // RMI Server
 ├── Employee.java               // Model (Serializable)
 ├── EmployeeService.java        // RMI Interface
 ├── EmployeeServiceImpl.java    // Server Implementation
 ├── DBConnection.java           // Database Connection
```

---

## 🚀 Features

* ✅ Add Employee
* ✅ View Employees (TableView)
* ✅ Update Employee
* ✅ Delete Employee
* ✅ Input Validation
* ✅ Client-Server Communication using RMI
* ✅ Alerts for user feedback

---

## 🗄️ Database Setup

### 1. Start MySQL

```bash
sudo service mysql start
```

### 2. Create Database

```sql
CREATE DATABASE employee_db;
USE employee_db;
```

### 3. Create Table

```sql
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    position VARCHAR(100),
    salary DOUBLE
);
```

---

## ▶️ How to Run

### Step 1: Start MySQL

### Step 2: Run RMI Server

Run:

```
RMIServer.java
```

Expected output:

```
RMI Server Started...
```

### Step 3: Run Client

Run:

```
MainApp.java
```

---

## ⚠️ Important Notes

* Ensure MySQL is running before starting the server.
* The `Employee` class implements `Serializable` (required for RMI).
* MySQL Connector/J must be added to project libraries.
* JavaFX must be configured properly in IntelliJ.

---

## 🧠 Learning Outcomes

This project demonstrates:

* Distributed systems using RMI
* Database connectivity using JDBC
* GUI development with JavaFX
* Client-server architecture design

---

## 📈 Future Improvements

* 🔐 Login/Authentication system
* 🔍 Search and filtering
* 📄 Export data to PDF/Excel
* 🎨 Improved UI/UX design

---


## 📜 License

This project is for educational purposes.
