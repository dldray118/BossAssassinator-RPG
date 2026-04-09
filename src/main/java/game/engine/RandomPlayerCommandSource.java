package game.engine;

import java.util.Random;

/**
 * Maps automation to the same intents as interactive play.
 */
public class RandomPlayerCommandSource implements PlayerCommandSource {

    private final Random random;

    public RandomPlayerCommandSource(Random random) {
        this.random = random;
    }

    @Override
    public ShopIntent nextShopIntent(int itemCount) {
        int slot = 1 + random.nextInt(itemCount);
        return ShopIntent.selectItem(slot);
    }

    @Override
    public CombatStepIntent nextCombatStepIntent() {
        return CombatStepIntent.PROCEED;
    }

    @Override
    public ExploringIntent nextExploringIntent() {
        return ExploringIntent.PROCEED;
    }
}
