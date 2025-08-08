# PasswordManager

A simple Java-based Password Manager with a graphical user interface (GUI) for securely storing, updating, and retrieving passwords. The application uses MySQL (via XAMPP) for persistent storage and demonstrates core Object-Oriented Programming (OOP) concepts.

## Features
- Add, update, and view passwords for various domains
- Auto-generate strong passwords
- Data stored securely in a MySQL database
- User-friendly Java Swing GUI
- Demonstrates OOP concepts: encapsulation, abstraction, composition, and inheritance

## Requirements
- Java JDK 8 or higher
- XAMPP (for MySQL server)
- MySQL Connector/J (JDBC driver)

## Setup Instructions

### 1. Clone the Repository
```
git clone https://github.com/yourusername/PasswordManager.git
cd PasswordManager
```

### 2. Set Up MySQL Database
1. Start XAMPP and ensure MySQL is running.
2. Open phpMyAdmin and create a database named `test` (or change the name in `JdbcDB.java`).
3. Create the required table:
   ```sql
   CREATE TABLE passwordsdb (
       domain VARCHAR(255),
       username VARCHAR(255),
       password VARCHAR(255)
   );
   ```

### 3. Add MySQL Connector/J to Classpath
- Download the MySQL Connector/J JAR (e.g., `mysql-connector-j-9.3.0.jar`).
- Place it in the project directory (already included if you see the JAR file).
- When compiling/running, include it in your classpath:
  ```sh
  javac -cp .;mysql-connector-j-9.3.0.jar PassManager.java DataBase/JdbcDB.java PasswordGenerator.java StrongPasswordGenerator.java
  java -cp .;mysql-connector-j-9.3.0.jar PassManager
  ```
  (On Linux/Mac, use `:` instead of `;` for classpath separation.)

### 4. Run the Application
- Compile and run as shown above.
- The GUI will launch. Add, update, or view passwords as needed.

## OOP Concepts Demonstrated
- **Encapsulation:** Data and methods are bundled in classes (`PassManager`, `PasswordGenerator`, `JdbcDB`).
- **Abstraction:** Database and password logic are abstracted into their own classes.
- **Composition:** `PassManager` uses `PasswordGenerator` and `JdbcDB` objects.
- **Inheritance:** `StrongPasswordGenerator` extends `PasswordGenerator` to enforce stronger password policies.

## Customization
- To use a different database or table, edit the connection string and table name in `DataBase/JdbcDB.java`.
- To change password generation rules, modify or extend `PasswordGenerator` or `StrongPasswordGenerator`.

## License
MIT License 