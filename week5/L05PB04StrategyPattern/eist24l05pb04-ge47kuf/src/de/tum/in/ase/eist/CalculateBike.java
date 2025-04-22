package de.tum.in.ase.eist;

//TODO: Task 1.2: Implement the strategy pattern as stated in the UML Diagram
public class CalculateBike implements PriceStrategy {

    @Override
    public double calculatePrice(double distance) {
        // The method calculates the fee of a delivery delivered by bike.
        DistanceChecking.checkDistance(distance);
        return 2.0 + distance / 2.8;
    }
}
