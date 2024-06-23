package game.characters;

/**
 * SmallEnemy class implements the Enemy interface with basic attributes.
 */
class SmallEnemy implements Enemy {
    private int health;

    public SmallEnemy() {
        this.health = 50;
    }

    @Override
    public void attack() {
        System.out.println("Small Enemy attacks!");
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }
}
