package characters;

import static org.junit.Assert.*;

import game.characters.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Fighter decorators and EnemyFactory.
 */
public class CharacterTest {

    private Fighter basicFighter;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        basicFighter = new BasicFighter("Test Fighter");
    }

    /**
     * Tests the Skill1Decorator.
     */
    @Test
    public void testSkill1Decorator() {
        Fighter skill1Fighter = new Skill1Decorator(basicFighter);
        //assertEquals("Test Fighter with Skill 1", skill1Fighter.getName());
        assertTrue(skill1Fighter.getSkills().contains("Flying Roundhouse Kick"));
    }

    /**
     * Tests the Skill2Decorator.
     */
    @Test
    public void testSkill2Decorator() {
        Fighter skill2Fighter = new Skill2Decorator(basicFighter);
        //assertEquals("Test Fighter with Skill 2", skill2Fighter.getName());
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
    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidEnemy() {
        EnemyFactory.createEnemy("invalid");
    }
}
