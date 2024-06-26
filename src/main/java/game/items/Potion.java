package game.items;

/**
 * Potion class represents a health potion item in the game.
 */
public class Potion implements Item {
    @Override
    public String getName() {
        return "Health Potion";
    }

    @Override
    public void use() {
        System.out.println("Using Health Potion");
    }
}
