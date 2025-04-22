package EIST24L03PB03;
import EIST24L03PB03.Client.Client;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

import java.util.Scanner;

public class Main {
    private final Client client = new Client();

    public static void main(String[] args) throws ParseException {
        Client client1 = new Client();
        Main main = new Main();
        main.operate();
    }
    public void operate(){
        //TODO: test your implementation here
    }

}
