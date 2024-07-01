import game.engine.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the GameContext class.
 */
public class GameContextTest {

    private GameContext gameContext;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        gameContext = new GameContext();
    }

    /**
     * Tests that the initial state is ExploringState.
     */
    @Test
    public void testInitialState() {
        assertTrue(gameContext.getCurrentState() instanceof ExploringState);
    }

    /**
     * Tests the transition to CombatState.
     */
    @Test
    public void testStateTransitionToCombat() {
        gameContext.setState(new CombatState(gameContext, 0));
        assertTrue(gameContext.getCurrentState() instanceof CombatState);
    }

    /**
     * Tests the transition to ShopState.
     */
    @Test
    public void testStateTransitionToShop() {
        gameContext.setState(new ShopState(gameContext));
        assertTrue(gameContext.getCurrentState() instanceof ShopState);
    }

    /**
     * Tests the transition to GameOverState.
     */
    @Test
    public void testGameOverState() {
        gameContext.setState(new GameOverState(gameContext));
        assertTrue(gameContext.getCurrentState() instanceof GameOverState);
    }
}

