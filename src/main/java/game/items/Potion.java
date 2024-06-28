package game.items;

/**
 * Potion class representing a potion item.
 */
public class Potion implements Item {
    private String name;

    public Potion() {
        this.name = "Potion";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {
        System.out.println("Using potion.");
    }
}
