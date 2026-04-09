package game.engine;

import game.characters.Fighter;
import game.domain.ShopPurchase;
import game.items.Item;
import game.items.Potion;
import game.items.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * The ShopState class handles the shop phase of the game.
 * Players can buy items and weapons to prepare for upcoming battles.
 */
public class ShopState implements GameState {

    private GameContext context;
    private GameState currentState;                                                 // For testing
    private List<Item> items;
    private List<Weapon> boughtWeapons; // List to store bought weapons

    /**
     * Constructs a new ShopState with the specified game context.
     * Initializes the list of items available in the shop and the list of bought weapons.
     *
     * @param context the game context
     */
    public ShopState(GameContext context) {
        this.context = context;
        this.items = new ArrayList<>();
        this.boughtWeapons = new ArrayList<>(); // Initialize bought weapons list
        generateItems();
    }

    /**
     * Set the current state of the game.
     * @param state The new state to set.
     */
    public void setState(GameState state) {                                      // For testing
        this.currentState = state;
    }

    /**
     * Get the current state of the game.
     * @return The current game state.
     */
    public GameState getCurrentState() {                                         // For testing
        return currentState;
    }

    /**
     * Generates the list of items available in the shop.
     */
    private void generateItems() {
        items.add(new Weapon("Knife", 5));
        items.add(new Weapon("Gun", 15));
        items.add(new Weapon("Freeze Spell", 10));
        items.add(new Potion());
    }

    /**
     * Display, shop intent and purchase, sync weapons, then return to exploration.
     */
    @Override
    public void handle() {
        System.out.println("Entering a shop...");
        displayItems();

        ShopIntent intent = context.getPlayerCommands().nextShopIntent(items.size());
        if (!intent.isLeave()) {
            int idx = intent.getItemIndexOneBased();
            if (idx >= 1 && idx <= items.size()) {
                applyPurchaseWithFeedback(items.get(idx - 1));
            }
        }

        context.getSession().getFighter().setWeapons(boughtWeapons);

        System.out.println("Leaving the shop.");
        context.setState(new ExploringState(context));
    }

    /** Applies {@link ShopPurchase}; console output stays in this state. */
    private void applyPurchaseWithFeedback(Item item) {
        Fighter fighter = context.getBossAssassinator();
        ShopPurchase.Outcome outcome = ShopPurchase.apply(fighter, item, boughtWeapons);
        if (outcome == ShopPurchase.Outcome.POTION_NOT_NEEDED) {
            System.out.println("Health is already full. Potion not used.");
        } else {
            item.use();
        }
    }

    /**
     * Displays the available items in the shop.
     */
    private void displayItems() {
        System.out.println("Available items in the shop:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }
    }
}
