package de.tum.in.ase.eist;

public class Pigeon implements Bird{

    @Override
    public void walk() {
        System.out.println("Pigeon walking");
    }

    @Override
    public void fly() {
        System.out.println("Pigeon flying");
    }

    @Override
    public void eat(Berries berries, Seeds seeds, Fish fish) {
        if (berries != null) {
            System.out.println("Eating " + berries.getQuantity() + " " + berries.getType() + " berries. ");
        }
        
        if (seeds != null) {
            System.out.println("Eating " + seeds.getQuantity() + " " + seeds.getType() + " seeds. " );
        }
        
        if (fish != null) {
            System.out.println("Eating " + fish.getQuantity() + " " + fish.getType() + " fish. ");
        }
        
    }
}
