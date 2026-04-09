package items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import game.items.Potion;
import game.items.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Item subclasses Potion and Weapon.
 */
public class ItemTest {

    private Potion potion;
    private Weapon weapon;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        potion = new Potion("Health Potion", 50);
        weapon = new Weapon("Sword", 10);
    }

    /**
     * Tests the properties of the Potion class.
     */
    @Test
    public void testPotion() {
        assertEquals("Health Potion", potion.getName());
        assertEquals(50, potion.getHealingAmount());
    }

    /**
     * Tests the properties of the Weapon class.
     */
    @Test
    public void testWeapon() {
        assertEquals("Sword", weapon.getName());
        assertEquals(10, weapon.getDamage());
    }
}
