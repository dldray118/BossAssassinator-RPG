package game.characters;

/**
 * Abstract decorator class for adding skills to a Fighter.
 */
abstract class FighterDecorator implements Fighter {
    protected Fighter decoratedFighter;

    public FighterDecorator(Fighter fighter) {
        this.decoratedFighter = fighter;
    }

    @Override
    public void attack(Enemy enemy) {
        decoratedFighter.attack(enemy);
    }

    @Override
    public void addSkill(String skill) {
        decoratedFighter.addSkill(skill);
    }

    @Override
    public String getName() {
        return decoratedFighter.getName();
    }
}
