package game.items;

/**
 * Weapon class represents a weapon item in the game.
 */
class Weapon implements Item {
    private String name;

    public Weapon(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {
        System.out.println("Using " + name);
    }
}
