package de.tum.in.ase.eist;

//TODO Task 1.2: Implement the strategy pattern as stated in the UML Diagram
public class CalculateCar implements PriceStrategy {
    //TODO Task 2.1: Implement the price calculation method, you may refer to the methods in other strategies

    @Override
    public double calculatePrice(double distance) {
        // The method calculates the fee of a delivery delivered by car.
        // The form is given in the problem statement.
        if (distance <= 0) {
            throw new IllegalArgumentException("The input for the distance is invalid");
        }
        return 3.2 + distance/2.6;
    }
}
