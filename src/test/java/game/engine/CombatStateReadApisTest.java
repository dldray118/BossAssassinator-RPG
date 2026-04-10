package game.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import game.characters.Enemy;
import org.junit.jupiter.api.Test;

/**
 * Read-only accessors used by the desktop UI during combat prompts.
 */
class CombatStateReadApisTest {

    @Test
    void currentEnemyAndTierExposeSpawnState() {
        GameContext ctx = new GameContext();
        ctx.getSession().setEnemyLevel(1);
        CombatState combat = new CombatState(ctx);
        Enemy e = combat.getCurrentEnemy();
        assertEquals("Medium Enemy", e.getName());
        assertEquals(1, combat.getCombatEnemyTier());
        assertEquals(100, e.getMaxHealth());
    }
}
