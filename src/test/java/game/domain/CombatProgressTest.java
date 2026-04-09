package game.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import game.characters.BasicFighter;
import game.characters.Fighter;

import org.junit.jupiter.api.Test;

/** Unit tests for {@link CombatProgress}. */
public class CombatProgressTest {

    @Test
    void tierZeroAddsSkillOne() {
        Fighter f = new BasicFighter("X");
        Fighter upgraded = CombatProgress.applySkillForDefeatedTier(f, 0);
        assertTrue(upgraded.getSkills().contains("Flying Roundhouse Kick"));
    }

    @Test
    void tierOneAddsSkillTwo() {
        Fighter f = new BasicFighter("X");
        Fighter upgraded = CombatProgress.applySkillForDefeatedTier(f, 1);
        assertTrue(upgraded.getSkills().contains("Nunchuck Strike"));
    }

    @Test
    void invalidTierThrows() {
        Fighter f = new BasicFighter("X");
        assertThrows(IllegalArgumentException.class, () -> CombatProgress.applySkillForDefeatedTier(f, 2));
    }
}
