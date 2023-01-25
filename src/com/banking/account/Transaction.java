package com.banking.account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;


public class Transaction {

    static HashMap < Integer, Account > listOfAccounts = new HashMap < > ();
    static int previousDay = 0;
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        File file = new File("./src/InputFile/Input.txt");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        String input;
        while ((input = br.readLine()) != null) {
            System.out.println("Input given - " + input);
            resetNumberOfDepositAndWithdrawalOnDayChange();                   //this method will reset Number of Deposit and Withdrawal for all accounts to 0 every new day.
            try {
                String input_split[] = input.split(" ");
                switch (input_split[0].toLowerCase()) {
                    case "create":
                        System.out.println(createNewAccount(input_split[1] + " " + input_split[2]));
                        break;

                    case "deposit":
                        deposit(input_split[1], input_split[2], false);
                        break;

                    case "withdraw":
                        withdraw(input_split[1], input_split[2], false);
                        break;

                    case "transfer":
                        transfer(input_split[1], input_split[2], input_split[3]);
                        break;

                    case "balance":
                        System.out.println(checkBalance(input_split[1]));
                        break;

                    default:
                        System.out.println("Wrong Input");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please provide correct parameters");
            }
            System.out.println();
        }
    }

    private static boolean checkifAccountExists(int accountNumber) {
        return listOfAccounts.containsKey(accountNumber);
    }

    public static boolean checkDepositAmountLessThan500(int depositAmount) {
        return depositAmount < 500;
    }

    public static boolean checkDepositAmountMoreThan50000(int depositAmount) {
        return depositAmount > 50000;
    }

    private static boolean checkWithdrawalAmountLessThan1000(int withdrawAmount) {
        return withdrawAmount < 1000;
    }

    private static boolean checkWithdrawalAmountMoreThan25000(int withdrawAmount) {
        return withdrawAmount > 25000;
    }

    private static boolean checkifNumberOfDepositIs3(int accountNumber) {
        return listOfAccounts.get(accountNumber).getNumberofDepositsDoneInADay() == 3;
    }

    private static boolean checkifNumberOfWithdrawIs3(int accountNumber) {
        return listOfAccounts.get(accountNumber).getNumberofWithdrawalsDoneInADay() == 3;
    }

    public static boolean transfer(String accountFromString, String accountToString, String transferAmountString) {                 //transfer has all rules of withdrawal and deposit except we can transfer any number of times for an account
        try {
            int accountFrom = Integer.parseInt(accountFromString);
            int accountTo = Integer.parseInt(accountToString);
            if (checkifAccountExists(accountFrom) && checkifAccountExists(accountTo)) {
                if (withdraw(accountFromString, transferAmountString, true))
                    if (deposit(accountToString, transferAmountString, true)) {
                        System.out.println("Successful");
                        return true;
                    }
                else
                    return false;
            } else
                System.out.println("Account Number not found, try again!");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Parameter given is wrong");
        }
        return false;
    }

    private static String checkBalance(String accountNumberString) {
        int accountNumber = Integer.parseInt(accountNumberString);
        if (checkifAccountExists(accountNumber))
            return String.valueOf(listOfAccounts.get(accountNumber).getAccountBalance());
        else
            return "Invalid Account Number";
    }

    public static int createNewAccount(String name) {

        Account newAccount = new Account(name);
        listOfAccounts.put(newAccount.getAccountNumber(), newAccount);
        return newAccount.getAccountNumber();
    }

    public static boolean deposit(String accountNumberString, String depositAmountString, boolean transfer) {

        try {
            int accountNumber = Integer.parseInt(accountNumberString);
            int depositAmount = Integer.parseInt(depositAmountString);

            if (checkifAccountExists(accountNumber)) {
                if (checkDepositAmountLessThan500(depositAmount)) System.out.println("Minimum deposit amount is 500");
                else if (checkDepositAmountMoreThan50000(depositAmount)) System.out.println("Maximum deposit amount is 50000");
                else if (checkifNumberOfDepositIs3(accountNumber) && !transfer) System.out.println("Only 3 deposits are allowed in a day");           //disable checking for number of deposits if it is a transfer
                else {
                    int currentAccountBalance = listOfAccounts.get(accountNumber).getAccountBalance();
                    int newAccountBalance = currentAccountBalance + depositAmount;
                    if (newAccountBalance <= 100000) {
                        listOfAccounts.get(accountNumber).setAccountBalance(newAccountBalance);
                        listOfAccounts.get(accountNumber).setNumberofDepositsDoneInADay(listOfAccounts.get(accountNumber).getNumberofDepositsDoneInADay() + 1);
                        if (!transfer)
                            System.out.println(newAccountBalance);
                        return true;
                    } else
                        System.out.println("Account balance cannot exceed ₹100000");
                }
            } else System.out.println("Account Number not found, try again!");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Parameter given is wrong");
        }
        return false;
    }

    public static boolean withdraw(String accountNumberString, String withdrawAmountString, boolean transfer) {
        try {
            int accountNumber = Integer.parseInt(accountNumberString);
            int withdrawAmount = Integer.parseInt(withdrawAmountString);
            if (checkifAccountExists(accountNumber)) {
                if (checkWithdrawalAmountLessThan1000(withdrawAmount)) System.out.println("Minimum withdrawal amount is 1000");
                else if (checkWithdrawalAmountMoreThan25000(withdrawAmount)) System.out.println("Maximum withdrawal amount is 25000");
                else if (checkifNumberOfWithdrawIs3(accountNumber) && !transfer) System.out.println("Only 3 withdrawals are allowed in a day");           //disable checking for number of withdrawal if it is a transfer
                else {
                    int currentAccountBalance = listOfAccounts.get(accountNumber).getAccountBalance();
                    int newAccountBalance = currentAccountBalance - withdrawAmount;
                    if (newAccountBalance >= 0) {
                        listOfAccounts.get(accountNumber).setAccountBalance(newAccountBalance);
                        listOfAccounts.get(accountNumber).setNumberofWithdrawalsDoneInADay(listOfAccounts.get(accountNumber).getNumberofWithdrawalsDoneInADay() + 1);
                        if (!transfer)
                            System.out.println(newAccountBalance);
                        return true;
                    } else
                        System.out.println("Insufficient balance");
                }
            } else System.out.println("Account Number not found, try again!");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Parameter given is wrong");
        }
        return false;
    }

    public static void resetNumberOfDepositAndWithdrawalOnDayChange() {
        try {
            if (LocalDate.now().getDayOfMonth() != previousDay) {
                previousDay = LocalDate.now().getDayOfMonth();
                listOfAccounts.forEach((k, v) -> v.setNumberofDepositsDoneInADay(0));
                listOfAccounts.forEach((k, v) -> v.setNumberofWithdrawalsDoneInADay(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayAccounts() {

        listOfAccounts.forEach((k, v) -> System.out.println(v.getAccountNumber() + " " + v.getAccountBalance() + " " + v.getName() + v.getNumberofDepositsDoneInADay()));

    }
}