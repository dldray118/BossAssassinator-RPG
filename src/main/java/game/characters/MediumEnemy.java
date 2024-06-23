package game.characters;

/**
 * MediumEnemy class implements the Enemy interface with moderate attributes.
 */
class MediumEnemy implements Enemy {
    private int health;

    public MediumEnemy() {
        this.health = 100;
    }

    @Override
    public void attack() {
        System.out.println("Medium Enemy attacks!");
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
