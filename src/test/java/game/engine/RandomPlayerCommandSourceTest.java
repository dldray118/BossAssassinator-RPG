package game.engine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Random;

/** {@link RandomPlayerCommandSource} maps automation to {@link ShopIntent} slots. */
public class RandomPlayerCommandSourceTest {

    @Test
    void shopIntentAlwaysSelectsValidSlot() {
        Random random = new Random(42L);
        RandomPlayerCommandSource src = new RandomPlayerCommandSource(random);
        for (int i = 0; i < 30; i++) {
            ShopIntent intent = src.nextShopIntent(4);
            assertFalse(intent.isLeave());
            int slot = intent.getItemIndexOneBased();
            assertTrue(slot >= 1 && slot <= 4);
        }
    }

    @Test
    void combatAndExploringReturnProceed() {
        RandomPlayerCommandSource src = new RandomPlayerCommandSource(new Random());
        assertSame(CombatStepIntent.PROCEED, src.nextCombatStepIntent());
        assertSame(ExploringIntent.PROCEED, src.nextExploringIntent());
    }
}
