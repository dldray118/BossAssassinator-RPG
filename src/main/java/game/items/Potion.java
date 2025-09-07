package game.items;

/**
 * Potion class representing a potion item.
 */
public class Potion implements Item {
    private String name;
    private int healingAmount;

    public Potion(){
    }

    public Potion(String name, int healingAmount) {
        this.name = name;
        this.healingAmount = healingAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void use() {
        System.out.println("Using potion.");
    }

    /**
     * Gets the amount of healing provided by the potion.
     * @return the healing amount.
     */
    public int getHealingAmount() {
        return healingAmount;
    }
}
