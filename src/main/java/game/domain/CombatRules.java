package game.domain;

import game.characters.Enemy;
import game.characters.Fighter;

/**
 * One combat exchange and end-state checks (no I/O).
 */
public final class CombatRules {

    private CombatRules() {
    }

    /** Player attacks, then enemy if still alive. */
    public static void exchangeRound(Fighter player, Enemy enemy) {
        player.attack(enemy);
        if (enemy.getHealth() > 0) {
            enemy.attack(player);
        }
    }

    public static boolean combatOngoing(Fighter player, Enemy enemy) {
        return player.getHealth() > 0 && enemy.getHealth() > 0;
    }

    public static boolean enemyDefeated(Enemy enemy) {
        return enemy.getHealth() <= 0;
    }
}
