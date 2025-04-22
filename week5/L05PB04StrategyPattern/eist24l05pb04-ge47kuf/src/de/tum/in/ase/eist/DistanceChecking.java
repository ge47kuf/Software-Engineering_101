package de.tum.in.ase.eist;

public class DistanceChecking {
    public static void checkDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException(Delivery.ILLEGAL_DISTANCE_MESSAGE);
        }
    }
}
