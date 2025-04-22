package de.tum.in.ase.eist.banking;

import de.tum.in.ase.eist.Client;

import java.util.HashMap;


public class BankAccountController {
    private final HashMap<Client, String> accountNumberToPinMap;

    public BankAccountController() {
        accountNumberToPinMap = new HashMap<>();
    }

    public void openBankAccount(Client client) {
        createAccountNumber(client);
        System.out.println("Your bank number is: " + client.getCardNumber()
                + " Your PIN code is: " + this.accountNumberToPinMap.get(client));
    }

    private void createAccountNumber(Client client){
        // create a random number for bank number and pin code
        // bank number duplication problem will not be addressed in this exercise
        String accountNumber = String.format("%014d", (int) (Math.random() * 1000000));
        String pinCode = String.format("%04d", (int) (Math.random() * 10000));

        client.setCardNumber(accountNumber);
        client.setPinCode(pinCode);

        accountNumberToPinMap.put(client, pinCode);
    }

    public boolean authenticate(Client client, String enteredPin) {
        return enteredPin.equals(accountNumberToPinMap.get(client));
    }
}