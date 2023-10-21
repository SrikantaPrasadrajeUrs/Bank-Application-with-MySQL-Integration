package com.bank1;

public class CustomerAcc1 {
    // Private fields to store customer account information
    private String name;
    private final long accNo;
    private int pin;
    private String accType;
    private int accBal;

    // Constructor to initialize customer account
    public CustomerAcc1(String name, long accNo, int pin, String accType, int accBal) {
        this.name = name;
        this.accNo = accNo;
        this.pin = pin;
        this.accType = accType;
        this.accBal = accBal;
    }

    // Override toString method to provide a string representation of the object
    @Override
    public String toString() {
        return "CustomerAcc [name=" + name + ", accNo=" + accNo + ", pin=" + pin + ", accType=" + accType + ", accBal=" + accBal + "]";
    }

    // Getter method for the PIN
    public int getPin() {
        return pin;
    }

    // Getter method for the customer's name
    public String getName() {
        return name;
    }

    // Setter method to update the customer's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for the account type
    public String getAccType() {
        return accType;
    }

    // Setter method to update the account type
    public void setAccType(String accType) {
        this.accType = accType;
    }

    // Getter method for the account balance
    public int getAccBal() {
        return accBal;
    }

    // Setter method to update the account balance
    public void setAccBal(int accBal) {
        this.accBal = accBal;
    }

    // Getter method for the account number
    public long getAccNo() {
        return accNo;
    }

    // Setter method to update the PIN
    public void setPin(int pin) {
        this.pin = pin;
    }
}
