package game.items;

/**
 * Weapon class representing a weapon item.
 */
public class Weapon implements Item {
    private String name;
    private int damage;

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {
        System.out.println("Using weapon: " + getName());
    }
}
