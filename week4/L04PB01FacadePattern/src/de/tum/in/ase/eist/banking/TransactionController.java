package de.tum.in.ase.eist.banking;

import de.tum.in.ase.eist.Client;

public class TransactionController {

    public void withdrawCash(Client client, double amount) {
        if (amount > 0 && amount <= client.getBalance()) {
            client.setBalance(client.getBalance() - amount);
            return;
        }
        System.out.println("Invalid amount or not enough money");
    }

    public void depositCash(Client client, double amount) {
        if (amount > 0) {
            client.setBalance(client.getBalance() + amount);
            return;
        }
        System.out.println("Invalid amount");
    }
}


