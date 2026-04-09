package game.domain;

import game.characters.Fighter;
import game.characters.Skill1Decorator;
import game.characters.Skill2Decorator;

/**
 * Skill decoration after defeating an enemy tier (no I/O).
 */
public final class CombatProgress {

    private CombatProgress() {
    }

    /**
     * @param defeatedEnemyLevel tier just defeated: 0 small, 1 medium
     */
    public static Fighter applySkillForDefeatedTier(Fighter fighter, int defeatedEnemyLevel) {
        switch (defeatedEnemyLevel) {
            case 0:
                return new Skill1Decorator(fighter);
            case 1:
                return new Skill2Decorator(fighter);
            default:
                throw new IllegalArgumentException("Unknown enemy level: " + defeatedEnemyLevel);
        }
    }
}
