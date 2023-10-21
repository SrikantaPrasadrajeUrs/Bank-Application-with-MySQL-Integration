package com.bank1;

import java.util.Scanner;

public class AppLauncher1 {
    // Creating a Bank instance
    static Bank b = new Bank("AxisBank", "btm layout");

    // Creating a Bank1 instance
    Bank1 b1 = new Bank1("AxisBank", "btm layout");

    // Initializing a Scanner for user input
    private static Scanner sc = new Scanner(System.in);

    // the menu for the application
    public static void menu() {
        System.out.println("---------------------------------");
        System.out.println("Enter 1 to add a customer to the bank");
        System.out.println("Enter 2 to search for a customer in the bank");
        System.out.println("Enter 3 to deposit money into the bank");
        System.out.println("Enter 4 to delete your account");
        System.out.println("Enter 5 to view all customers of the bank");
        System.out.println("Enter 6 to delete the entire database");
        System.out.println("Enter 7 to update mobile number");
        System.out.println("Enter 8 to exit");
        System.out.println("---------------------------------");
    }

    // Launch the bank application
    public void launch() {
        while (true) {
            menu();
            System.out.println("Enter the number to perform a function accordingly");
            int i = sc.nextInt();
            Bank1 b2 = new Bank1("AxisBank", "btm layout");
            switch (i) {
                case 1:
                    // Add a customer to the bank
                    System.out.println("Enter your name");
                    String name = sc.next();
                    System.out.println("Enter account number");
                    String accNo = sc.next();
                    System.out.println("Enter PIN");
                    int pin = sc.nextInt();
                    System.out.println("Enter account type");
                    String accType = sc.next();
                    System.out.println("Insert initial deposit amount");
                    double accbal = sc.nextDouble();
                    b2.addCustomer(name, accNo, pin, accType, accbal);
                    break;
                case 2:
                    // Search for a customer in the bank
                    System.out.println("Enter account number");
                    long accNo3 = sc.nextLong();
                    System.out.println("Enter PIN");
                    int pin3 = sc.nextInt();
                    b2.searchCustomer(accNo3, pin3);
                    break;
                case 3:
                    // Deposit money into the bank
                    System.out.println("Enter account number");
                    long accNo1 = sc.nextLong();
                    System.out.println("Enter PIN");
                    int pin1 = sc.nextInt();
                    System.out.println("Enter deposit amount");
                    int bal = sc.nextInt();
                    b2.depositMoney(accNo1, pin1, bal);
                    break;
                case 4:
                    // Delete an account
                    System.out.println("Enter account number");
                    long accNo2 = sc.nextLong();
                    System.out.println("Enter PIN");
                    int pin2 = sc.nextInt();
                    System.out.println("Enter your name");
                    String name1 = sc.next();
                    System.out.println("Enter Database management PIN");
                    int pin6 = sc.nextInt();
                    b2.deleteAcc(accNo2, pin2, name1, pin6);
                    break;
                case 5:
                    // View all customers of the bank
                    System.out.println("Enter manager's secret PIN");
                    int secretPin = sc.nextInt();
                    b2.viewAllCustomer(secretPin);
                    break;
                case 6:
                    // Delete the entire database
                    System.err.println("Are you sure you want to delete the entire database? It can't be recovered. If yes, enter YES");
                    String res = sc.next();
                    res = res.toUpperCase();
                    int x = 3;
                    String ori = "YES";
                    System.out.println("Enter your manager's secret PIN");
                    int secPin = sc.nextInt();
                    if (res.equals(ori)) {
                        boolean b = b2.deleteDB(secPin);
                        if (b == true)
                            System.out.println("Entire bank data is deleted!");
                        else if (x != 0) {
                            System.err.println("You are not authorized. Try again. Remaining chances: " + (--x));
                        }
                    } else {
                        System.out.println("Deletion operation terminated");
                    }
                    break;
                case 7:
                    // Update mobile number
                    System.out.println("Enter your new account PIN");
                    int pin4 = sc.nextInt();
                    System.out.println("Enter your account number");
                    long AccNo = sc.nextLong();
                    System.out.println("Enter manager's PIN");
                    int mPin = sc.nextInt();
                    b2.updatePin(pin4, AccNo, mPin);
                    break;
                case 8:
                    // Exit the application
                    System.exit(0);
                    System.out.println("Thank you for choosing our bank");
                    break;
                default:
                    System.err.println("Please choose from the given options only");
            }
        }
    }
}
