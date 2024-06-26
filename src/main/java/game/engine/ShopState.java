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

    public ShopState(GameContext context) {
        this.context = context;
        this.items = new ArrayList<>();
        generateItems();
    }

    private void generateItems() {
        items.add(new Weapon("Knife"));
        items.add(new Weapon("Gun"));
        items.add(new Weapon("Freeze Spell"));
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
                items.get(choice - 1).use();
            }
        } else {
            // Automatic selection for testing purposes
            items.get(context.getRandom().nextInt(items.size())).use();
        }

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
