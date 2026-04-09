package game.engine;

/**
 * Shop phase: leave or buy a slot (1-based index matching displayed list).
 */
public final class ShopIntent {

    /** No purchase. */
    public static final int LEAVE = 0;

    private final int itemIndexOneBased;

    private ShopIntent(int itemIndexOneBased) {
        this.itemIndexOneBased = itemIndexOneBased;
    }

    /** Exit without buying. */
    public static ShopIntent leave() {
        return new ShopIntent(LEAVE);
    }

    /** Buy the item at this 1-based position in the shop list. */
    public static ShopIntent selectItem(int indexOneBased) {
        return new ShopIntent(indexOneBased);
    }

    public boolean isLeave() {
        return itemIndexOneBased == LEAVE;
    }

    public int getItemIndexOneBased() {
        return itemIndexOneBased;
    }
}
