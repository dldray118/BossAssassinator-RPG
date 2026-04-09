package characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import game.characters.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Fighter decorators and EnemyFactory.
 */
public class CharacterTest {

    private Fighter basicFighter;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        basicFighter = new BasicFighter("Test Fighter");
    }

    /**
     * Tests the Skill1Decorator.
     */
    @Test
    public void testSkill1Decorator() {
        Fighter skill1Fighter = new Skill1Decorator(basicFighter);
        assertTrue(skill1Fighter.getSkills().contains("Flying Roundhouse Kick"));
    }

    /**
     * Tests the Skill2Decorator.
     */
    @Test
    public void testSkill2Decorator() {
        Fighter skill2Fighter = new Skill2Decorator(basicFighter);
        assertTrue(skill2Fighter.getSkills().contains("Nunchuck Strike"));
    }

    /**
     * Tests the creation of a SmallEnemy.
     */
    @Test
    public void testCreateSmallEnemy() {
        Enemy smallEnemy = EnemyFactory.createEnemy("small");
        assertTrue(smallEnemy instanceof SmallEnemy);
        assertEquals("Small Enemy", smallEnemy.getName());
    }

    /**
     * Tests the creation of a MediumEnemy.
     */
    @Test
    public void testCreateMediumEnemy() {
        Enemy mediumEnemy = EnemyFactory.createEnemy("medium");
        assertTrue(mediumEnemy instanceof MediumEnemy);
        assertEquals("Medium Enemy", mediumEnemy.getName());
    }

    /**
     * Tests the creation of a BossEnemy.
     */
    @Test
    public void testCreateBossEnemy() {
        Enemy bossEnemy = EnemyFactory.createEnemy("boss");
        assertTrue(bossEnemy instanceof BossEnemy);
        assertEquals("Boss Enemy", bossEnemy.getName());
    }

    /**
     * Tests the creation of an invalid enemy type.
     */
    @Test
    public void testCreateInvalidEnemy() {
        assertThrows(IllegalArgumentException.class, () -> EnemyFactory.createEnemy("invalid"));
    }

    @Test
    public void testCreateForTierMatchesSmall() {
        Enemy e = EnemyFactory.createForTier(0);
        assertTrue(e instanceof SmallEnemy);
        assertEquals("Small Enemy", e.getName());
    }

    @Test
    public void testCreateForTierInvalid() {
        assertThrows(IllegalArgumentException.class, () -> EnemyFactory.createForTier(99));
    }
}
