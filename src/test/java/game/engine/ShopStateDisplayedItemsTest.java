package game.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import game.items.Weapon;
import org.junit.jupiter.api.Test;

/**
 * Read-only shop listing for GUI (order matches console numbering).
 */
class ShopStateDisplayedItemsTest {

    @Test
    void displayedItemsMatchStockOrder() {
        GameContext ctx = new GameContext();
        ShopState shop = new ShopState(ctx);
        assertEquals(4, shop.getDisplayedItems().size());
        assertEquals("Knife", shop.getDisplayedItems().get(0).getName());
        assertEquals(15, ((Weapon) shop.getDisplayedItems().get(1)).getDamage());
    }
}
