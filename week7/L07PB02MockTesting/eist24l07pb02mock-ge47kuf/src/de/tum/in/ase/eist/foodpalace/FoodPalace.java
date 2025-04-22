package de.tum.in.ase.eist.foodpalace;

public class FoodPalace {
    private final SaladBar saladBar;
    private final Curry curry;
    private final IceCreamStand iceCreamStand;

    public FoodPalace(SaladBar saladBar, Curry curry, IceCreamStand iceCreamStand) {
        this.saladBar = saladBar;
        this.curry = curry;
        this.iceCreamStand = iceCreamStand;
    }

    public void makeSalad() {
        saladBar.makeSalad();
    }

    public void makeCurry(final Employee employee) {
        System.out.printf("%s is preparing curry %n", employee.getName());
        if (employee.isQualified()) {
            curry.makeCurry();
        } else {
            throw new RuntimeException("Unauthorized access!");
        }
    }

    public void makeIceCream() {
        iceCreamStand.makeIceCream();
    }

    public SaladBar getSaladBar() {
        return saladBar;
    }

    public Curry getCurry() {
        return curry;
    }

    public IceCreamStand getIceCreamStand() {
        return iceCreamStand;
    }
}