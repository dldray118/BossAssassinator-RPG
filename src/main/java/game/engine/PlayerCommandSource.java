package game.engine;

/**
 * Produces player intents for CLI, automation, or a future UI.
 */
public interface PlayerCommandSource {

    /**
     * @param itemCount number of purchasable rows in the shop
     */
    ShopIntent nextShopIntent(int itemCount);

    CombatStepIntent nextCombatStepIntent();

    ExploringIntent nextExploringIntent();
}
