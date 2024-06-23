package game.characters;

/**
 * Skill2Decorator adds the "Nunchuck Strike" skill.
 */
class Skill2Decorator extends FighterDecorator {
    public Skill2Decorator(Fighter fighter) {
        super(fighter);
        fighter.addSkill("Nunchuck Strike");
    }

    @Override
    public void attack() {
        decoratedFighter.attack();
        System.out.println(" with a Nunchuck Strike!");
    }
}
