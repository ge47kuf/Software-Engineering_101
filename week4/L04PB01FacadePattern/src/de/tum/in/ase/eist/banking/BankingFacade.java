package de.tum.in.ase.eist.banking;

//TODO 1 Implement all the public methods from the classes TransactionController and BankAccountController

import de.tum.in.ase.eist.Client;

public class BankingFacade {
    private BankAccountController bankAccountController;
    private TransactionController transactionController;

    public BankingFacade() {
        this.bankAccountController = new BankAccountController();
        this.transactionController = new TransactionController();
    }

    public void depositCash(Client client, String pinCode, double cash) {
        if (!bankAccountController.authenticate(client, pinCode)) {
            System.out.println("Incorrect PIN Code. Access denied.");
        } else if (cash <= 0) {
            System.out.println("Bad request!");
        } else {
            transactionController.depositCash(client, cash);
            System.out.println("Deposit of $" + cash + " completed. " +
                    "Your current balance: " + client.getBalance());
        }
    }

    public void withdrawCash(Client client, String pinCode, double cash) {
        if (!bankAccountController.authenticate(client, pinCode)) {
            System.out.println("Incorrect PIN Code. Access denied.");
        } else if (client.getBalance() < cash) {
            System.out.println("Bad request!");
        } else {
            transactionController.withdrawCash(client, cash);
            System.out.println("Withdraw of $" + cash + " completed. " +
                    "Your current balance: " + client.getBalance());
        }
    }

    public void openBankAccount(Client client) {
        bankAccountController.openBankAccount(client);
    }
}
