package game.characters;

public class BossEnemy implements Enemy {
    private int health;
    private String name = "Boss Enemy";

    public BossEnemy() {
        this.health = 200;
    }

    @Override
    public void attack(Fighter fighter) {
        int damage = getDamage();
        System.out.println(name + " attacks " + fighter.getName() + " for " + damage + " damage!");
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
        return name;
    }

    @Override
    public int getDamage() {
        return 20; // Boss enemy attack damage
    }
}
