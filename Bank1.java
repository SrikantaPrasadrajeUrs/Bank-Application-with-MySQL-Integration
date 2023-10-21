package com.bank1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bank1 {
    private String name;
    private String address;
    private static int managersPin = 1234;

    private Connection con = null;
    private PreparedStatement pst = null;
    private Statement st = null;
    private ResultSet res = null;

    public Bank1(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Establishes a connection to the MySQL database.
     */
    public void execute() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/raj_database", "root", "tiger");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection to the MySQL database and associated resources.
     */
    public void executeClose() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Adds a customer to the database.
     *
     * @param name     Customer's name
     * @param accno    Account number
     * @param pin      PIN for the account
     * @param acctype  Account type
     * @param accbal   Initial account balance
     */
    public void addCustomer(String name, String accno, int pin, String acctype, double accbal) {
        try {
            pst = con.prepareStatement("INSERT INTO BANK VALUES(?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, accno);
            pst.setInt(3, pin);
            pst.setString(4, acctype);
            pst.setDouble(5, accbal);
            int r = pst.executeUpdate();
            System.out.println(r + " ROWS AFFECTED");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executeClose();
    }

    /**
     * Searches for a customer in the database based on account number and PIN.
     *
     * @param accNo Account number
     * @param pin   PIN for the account
     */
    public void searchCustomer(long accNo, int pin) {
        try {
            st = con.createStatement();
            String qry = "SELECT NAME, ACCBAL FROM BANK WHERE ACCNO=" + accNo + " AND PIN=" + pin + ";";
            res = st.executeQuery(qry);
            while (res.next()) {
                String name = res.getString("NAME");
                double accBal = res.getDouble("ACCBAL");
                System.out.println("Name: " + name);
                System.out.println("Account Balance: " + accBal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executeClose();
    }

    /**
     * Allows a customer to deposit money into their account.
     *
     * @param accNo     Account number
     * @param pin       PIN for the account
     * @param depoMoney Amount to deposit
     */
    public void depositMoney(long accNo, int pin, int depoMoney) {
        double remainingMoney = 0;
        execute();
        try {
            st = con.createStatement();
            res = st.executeQuery("SELECT ACCBAL FROM BANK WHERE PIN=" + pin + " AND ACCNO=" + accNo + ";");
            res.next();
            remainingMoney = res.getDouble(1);
            System.out.println("PREVIOUS BALANCE WAS: " + remainingMoney);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        double newMoney = remainingMoney + depoMoney;
        String qry = "UPDATE BANK SET ACCBAL=" + newMoney + " WHERE PIN=" + pin;
        try {
            int r = st.executeUpdate(qry);
            if (r > 0) {
                System.out.println("TRANSACTION SUCCESSFUL");
                System.out.println("CURRENT ACCOUNT BALANCE IS: " + (remainingMoney + depoMoney));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executeClose();
    }

    /**
     * Deletes a customer's account based on account number, PIN, name, and a secret manager PIN.
     *
     * @param accNo      Account number
     * @param pin        PIN for the account
     * @param name       Customer's name
     * @param secretPin  Manager's secret PIN
     */
    public void deleteAcc(long accNo, int pin, String name, int secretPin) {
        execute();
        if (managersPin == secretPin) {
            String qry = "DELETE FROM BANK WHERE PIN=? AND NAME=? AND ACCNO=?;";
            try {
                pst = con.prepareStatement(qry);
                pst.setInt(1, pin);
                pst.setString(2, name);
                pst.setLong(3, accNo);
                int r = pst.executeUpdate();
                if (r > 0) {
                    System.out.println("ACCOUNT DELETED SUCCESSFULLY");
                } else {
                    System.out.print("OPERATION UNSUCCESSFUL");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.err.println("YOU ARE NOT AUTHORIZED FOR THIS OPERATION");
        executeClose();
    }

    /**
     * Lists all customer accounts if a manager provides the correct manager PIN.
     *
     * @param secretPin Manager's secret PIN
     */
    public void viewAllCustomer(int secretPin) {
        execute();
        if (managersPin == secretPin) {
            try {
                st = con.createStatement();
                res = st.executeQuery("SELECT * FROM BANK;");
                while (res.next()) {
                    System.out.print("NAME: " + (res.getString(1)) + " ");
                    System.out.print("ACCNO: " + (res.getString(2)) + " ");
                    System.out.print("ACC-TYPE: " + (res.getString(4)) + " ");
                    System.out.print("BALANCE: " + (res.getDouble(5)) + " ");
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.err.println("WRONG PIN");
        executeClose();
    }

    /**
     * Deletes all data from the "Bank" table if provided with the correct manager PIN.
     *
     * @param secPin Manager's secret PIN
     * @return true if the data is deleted, false if the manager PIN is incorrect
     */
    public boolean deleteDB(int secPin) {
        if (managersPin == secPin) {
            execute();
            String qry = "TRUNCATE TABLE BANK;";
            try {
                st = con.createStatement();
                st.execute(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            executeClose();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the PIN number for a customer's account if authorized by the manager.
     *
     * @param pin         PIN for the customer's account
     * @param accNo       Account number
     * @param managerPin  Manager's secret PIN for authorization
     */
    public void updatePin(int pin, long accNo, int managerPin) {
        if (managerPin == managersPin) {
            execute();
            String query = "UPDATE BANK SET PIN=? WHERE ACCNO=?";
            try {
                pst = con.prepareStatement(query);
                pst.setInt(1, pin);
                pst.setLong(2, accNo);
                int r = pst.executeUpdate();
                if (r > 0) {
                    System.out.println("PIN UPDATED");
                } else {
                    System.err.println("PIN NOT UPDATED");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                executeClose();
            }
        } else {
            System.err.println("YOU ARE NOT AUTHORIZED FOR THIS OPERATION");
        }
    }
}
