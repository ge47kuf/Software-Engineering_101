package de.tum.in.ase.eist.pizzaheaven;

import de.tum.in.ase.eist.pizzaheaven.pizza.Pizza;

public abstract class Baker {
    public abstract Pizza createPizza(String type);

    public Pizza orderPizza(String type, boolean isTakeaway) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.box(isTakeaway);
        return pizza;
    }
}