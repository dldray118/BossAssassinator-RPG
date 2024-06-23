package game.characters;

/**
 * BossEnemy class implements the Enemy interface with high attributes.
 */
class BossEnemy implements Enemy {
    private int health;

    public BossEnemy() {
        this.health = 200;
    }

    @Override
    public void attack() {
        System.out.println("Boss Enemy attacks!");
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
