package de.tum.in.ase.eist.pizzaheaven;
import de.tum.in.ase.eist.foodpalace.Employee;
import de.tum.in.ase.eist.foodpalace.ShopManager;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
public class PizzaHeavenTest {
    private final PizzaHeaven restaurant = new PizzaHeaven();
    @Mock
    private Employee maliciousEmployee;
    @TestSubject
    private ShopManager shopManager;


    @Test
    public void testThatTheCorrectTypeOfPizzaIsCreated() {
        final var orderedPizza = restaurant.orderPizza("Margherita", true);

        // TODO check that the ordered pizza is of the type that we want
        assertEquals("Margherita Pizza", orderedPizza.getName());
        // TODO check that we can not order a pizza that is not on the menu
        assertThrows(NullPointerException.class, () -> restaurant.orderPizza("chai", false));
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
        // TODO change name. erstelle ein employee und eigenschaft
        expect(maliciousEmployee.getName()).andReturn("Aniruddh's son");
        expect(maliciousEmployee.isQualified()).andReturn(true);

        // TODO prepare the mock. replay damit eigenschaft gesetz ist
        replay(maliciousEmployee);
        shopManager = new ShopManager(List.of(maliciousEmployee));

        // TODO test for yourself that the mock works as expected by observing the output of this method
        shopManager.testCurry(); // muss darf nicht exeption werfen
        // TODO Don't forget to verify the mock! beende den mock test
        verify(maliciousEmployee);
    }
}