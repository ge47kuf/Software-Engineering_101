package de.tum.in.ase.eist.pizzaheaven;


import de.tum.in.ase.eist.foodpalace.Employee;
import de.tum.in.ase.eist.foodpalace.ShopManager;
import de.tum.in.ase.eist.pizzaheaven.pizza.Pizza;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
public class PizzaHeavenTest {

    @Mock
    private Employee maliciousEmployee;

    @TestSubject
    private ShopManager shopManager;

    private final PizzaHeaven restaurant = new PizzaHeaven();

    @Test
    public void testThatTheCorrectTypeOfPizzaIsCreated() {
        final var orderedPizza = restaurant.orderPizza("Margherita", true);

        // TODO check that the ordered pizza is of the type that we want
        assertEquals("Margherita Pizza", orderedPizza.getName());
        // TODO check that we can not order a pizza that is not on the menu
        assertThrows(NullPointerException.class,
                // not test the return value, tested what methode does by calling it
                () -> restaurant.orderPizza("invalidType", true));

    }

    @Test
    public void testThatTakeawayPizzasAreBoxed() {
        final var pizza = restaurant.orderPizza("Bufalina", true);
        final var unboxedPizza = restaurant.orderPizza("Bufalina", false);

        // TODO check that pizzas are boxed/unboxed
        assertTrue(pizza.isBoxed());
        assertFalse(unboxedPizza.isBoxed());
    }

    @Test
    public void infiltrateAniruddhsRestaurant() {
        //idea: try to put a invalid employee in chopManager testCurry function to check if its work
        // TODO change name
        expect(maliciousEmployee.getName()).andReturn("invalid employee");

        // TODO prepare the mock here (don't forget to replay it)
        // make the employee invalid by not qualified it
        expect(maliciousEmployee.isQualified()).andReturn(false);
        // make the mock object ready
        replay(maliciousEmployee);
        this.shopManager = new ShopManager(List.of(maliciousEmployee));
        // TODO test for yourself that the mock works as expected by observing the output of this method
        shopManager.testCurry();

        verify(maliciousEmployee);
    }
}