package de.tum.in.ase.eist;

import java.util.Scanner;

public final class Main {

    public static void main(String[] args) {

        Client newClient = new Client("Phuong", 10.0);
        newClient.openBankAccount();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the pin code: ");
        String pinCode = scanner.nextLine();
        System.out.print("Deposit or withdraw? ");
        String transaction = scanner.nextLine();
        System.out.print("How much? ");
        double amount = scanner.nextDouble();

        if (transaction.equalsIgnoreCase("deposit")){
            newClient.depositCash(pinCode, amount);
        }
        else if (transaction.equalsIgnoreCase("withdraw")){
            newClient.withdrawCash(pinCode, amount);
        }
    }
}