package de.tum.in.ase.eist;

//TODO Task 1.2: Implement the strategy pattern as stated in the UML Diagram
public class CalculatePrime implements PriceStrategy {

    @Override
    public double calculatePrice(double distance) {
        // The method calculates the fee of a delivery from VIP customer, which is free.
        DistanceChecking.checkDistance(distance);
        return 0;
    }
}
