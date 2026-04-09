package game.engine;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import java.util.Random;

/** Documents explore / shop transitions with fixed RNG (no duplicate combat pre-roll). */
public class StateTransitionTest {

    private static Random exploreRollReturns(int value) {
        return new Random() {
            @Override
            public int nextInt(int bound) {
                return value;
            }
        };
    }

    @Test
    void exploringLowRollGoesToCombat() {
        Random r = exploreRollReturns(0);
        GameContext ctx = new GameContext();
        ctx.setInteractive(false);
        ctx.setRandom(r);
        ctx.setPlayerCommands(new RandomPlayerCommandSource(r));
        ctx.getCurrentState().handle();
        assertInstanceOf(CombatState.class, ctx.getCurrentState());
    }

    @Test
    void exploringHighRollGoesToShop() {
        Random r = exploreRollReturns(9);
        GameContext ctx = new GameContext();
        ctx.setInteractive(false);
        ctx.setRandom(r);
        ctx.setPlayerCommands(new RandomPlayerCommandSource(r));
        ctx.getCurrentState().handle();
        assertInstanceOf(ShopState.class, ctx.getCurrentState());
    }

    @Test
    void shopHandleReturnsToExploring() {
        GameContext ctx = new GameContext();
        ctx.setInteractive(false);
        ctx.setState(new ShopState(ctx));
        ctx.getCurrentState().handle();
        assertInstanceOf(ExploringState.class, ctx.getCurrentState());
    }
}
