# Bank-Application-with-MySQL-Integration
This Java application is a simple banking system that interacts with a MySQL database to perform various operations, including adding customers, searching for customers, depositing money, and more. Below are instructions on how to set up and use this application.

**Prerequisites**
Before you can run the application, ensure you have the following:

Java Development Kit (JDK): You need Java installed on your system. You can download it from here.

MySQL Database: The application uses MySQL to store customer data. You should have MySQL installed on your system. If not, you can download it from here.

MySQL Connector/Jdbc: The Java application uses MySQL Connector/Jdbc to connect to the MySQL database.
I am using "my-sql connecter version 28" you can download from mvn repository.

**Setup**
Create a MySQL Database:

Using your preferred MySQL client (e.g., phpMyAdmin or MySQL Command-Line), create a new database named raj_database/any name.
Run the SQL Script:

In your MySQL client, run the following SQL script to create the required table and insert sample data. This script will create a table called BANK with sample customer records:
sql
Copy code
CREATE TABLE BANK (
    NAME VARCHAR(255) NOT NULL,
    ACCNO BIGINT NOT NULL PRIMARY KEY,
    PIN INT NOT NULL,
    ACCTYPE VARCHAR(255) NOT NULL,
    ACCBAL DOUBLE NOT NULL
);

Open the Bank1.java class in your Java application.
Modify the following lines with your MySQL database credentials:
java
Copy code
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/raj_database", "root", "tiger");
Replace "raj_database", "root", and "tiger" with your database name, username, and password.

**Running the Application
Compile and Run:**

Use your preferred Java IDE or command-line tools to compile and run the BankApp1 class. This class is the entry point to the application.
Using the Application
Application Menu:

The application displays a menu with options to perform various banking operations.
Choose an option by entering the corresponding number.
Additional Information
This application is designed for educational purposes and demonstrates a simple interaction with a MySQL database. Feel free to explore and modify the code as needed.

For more details about the Java code and how to use it, please refer to the code comments in the source files.

With these instructions, users can set up and run your Java banking application with MySQL integration. Make sure to include the appropriate links for downloading Java, MySQL, and MySQL Connector/Jdbc. Users can follow these steps to set up the application and start using it.
**Contact:** For any questions or feedback, please contact srikantaprasadrajeurs456@gmail.com
