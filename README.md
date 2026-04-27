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

### 4. Import Sample Data

From the project root, run:

```bash
mysql -u root -p employee_db < employee_management_system.sql
```

This imports the `employees` table schema and sample rows from:
`employee_management_system.sql`.

---

## ▶️ How to Run

### Step 1: Start MySQL

Make sure MySQL is running and `employee_db` exists.

### Step 2: Configure DB Credentials

Create/update `config.properties` with your DB values:

```properties
db.url=jdbc:mysql://localhost:3306/employee_db
db.user=root
db.password=root
```

`config.properties` can be loaded from:
- classpath resources
- project root (`./config.properties`)
- `src/config.properties`

### Step 3: Run RMI Server

Run:

```
RMIServer.java
```

Expected output:

```
RMI Server Started...
```

### Step 4: Run Client

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
* If you change DB connection code or properties, restart `RMIServer` before opening the client.

---

## 🛠️ Troubleshooting

### UI opens but table is empty

Check the following:

1. `RMIServer` is running before starting `MainApp`.
2. `employee_db.employees` has rows.
3. `config.properties` values are correct (URL, user, password).
4. MySQL Connector/J is added to project libraries.
5. After any server-side code change, stop and start `RMIServer` again.

## 📜 License

This project is for educational purposes.
