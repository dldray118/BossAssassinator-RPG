package game.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import game.characters.BasicFighter;
import game.items.Potion;
import game.items.Weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/** Unit tests for {@link ShopPurchase}. */
public class ShopPurchaseTest {

    private BasicFighter fighter;
    private List<Weapon> loadout;

    @BeforeEach
    void setUp() {
        fighter = new BasicFighter("Test");
        loadout = new ArrayList<>();
    }

    @Test
    void potionHealsWhenDamaged() {
        fighter.takeDamage(30);
        ShopPurchase.Outcome o = ShopPurchase.apply(fighter, new Potion("P", 50), loadout);
        assertSame(ShopPurchase.Outcome.HEALED, o);
        assertEquals(100, fighter.getHealth());
    }

    @Test
    void potionSkippedWhenFull() {
        ShopPurchase.Outcome o = ShopPurchase.apply(fighter, new Potion("P", 50), loadout);
        assertSame(ShopPurchase.Outcome.POTION_NOT_NEEDED, o);
        assertEquals(100, fighter.getHealth());
    }

    @Test
    void weaponAddedToLoadout() {
        Weapon w = new Weapon("Axe", 7);
        ShopPurchase.Outcome o = ShopPurchase.apply(fighter, w, loadout);
        assertSame(ShopPurchase.Outcome.WEAPON_ADDED, o);
        assertEquals(1, loadout.size());
        assertSame(w, loadout.get(0));
    }
}
