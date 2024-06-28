package game.characters;

public class SmallEnemy implements Enemy {
    private int health;

    public SmallEnemy() {
        this.health = 50;
    }

    @Override
    public void attack(Fighter fighter) {
        int damage = getDamage();
        System.out.println(getName() + " attacks " + fighter.getName() + " for " + damage + " damage!");
        fighter.takeDamage(damage);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String getName() {
        return "Small Enemy";
    }

    @Override
    public int getDamage() {
        return 5; // Small enemy attack damage
    }
}
