package game.engine;

import game.items.Item;
import game.items.Potion;
import game.items.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ShopState class handles the shop phase of the game.
 */
class ShopState implements GameState {
    private GameContext context;
    private List<Item> items;
    private List<Weapon> boughtWeapons; // List to store bought weapons

    public ShopState(GameContext context) {
        this.context = context;
        this.items = new ArrayList<>();
        this.boughtWeapons = new ArrayList<>(); // Initialize bought weapons list
        generateItems();
    }

    private void generateItems() {
        items.add(new Weapon("Knife", 5));
        items.add(new Weapon("Gun", 15));
        items.add(new Weapon("Freeze Spell", 10));
        items.add(new Potion());
    }

    @Override
    public void handle() {
        System.out.println("Entering a shop...");
        displayItems();

        if (context.isInteractive()) {
            Scanner scanner = context.getScanner();
            System.out.println("Choose an item to buy (1-4) or 0 to leave:");
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= items.size()) {
                Item selectedItem = items.get(choice - 1);
                selectedItem.use();
                if (selectedItem instanceof Weapon) {
                    boughtWeapons.add((Weapon) selectedItem); // Add to bought weapons if it's a weapon
                }
            }
        } else {
            // Automatic selection for testing purposes
            Item selectedItem = items.get(context.getRandom().nextInt(items.size()));
            selectedItem.use();
            if (selectedItem instanceof Weapon) {
                boughtWeapons.add((Weapon) selectedItem); // Add to bought weapons if it's a weapon
            }
        }

        // Set bought weapons to BossAssassinator in CombatState
        CombatState combatState = new CombatState(context, 0); // Pass initial enemyLevel 0
        combatState.getBossAssassinator().setWeapons(boughtWeapons);

        System.out.println("Leaving the shop.");
        context.setState(new ExploringState(context));
    }


    private void displayItems() {
        System.out.println("Available items in the shop:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }
    }
}
