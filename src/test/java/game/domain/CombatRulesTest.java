package game.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import game.characters.BasicFighter;
import game.characters.SmallEnemy;

import org.junit.jupiter.api.Test;

/** Unit tests for {@link CombatRules}. */
public class CombatRulesTest {

    @Test
    void exchangeRoundsUntilCombatEnds() {
        BasicFighter player = new BasicFighter("Hero");
        SmallEnemy enemy = new SmallEnemy();
        int safety = 0;
        while (CombatRules.combatOngoing(player, enemy) && safety++ < 50) {
            CombatRules.exchangeRound(player, enemy);
        }
        assertFalse(CombatRules.combatOngoing(player, enemy));
        assertTrue(CombatRules.enemyDefeated(enemy) || player.getHealth() <= 0);
    }
}
