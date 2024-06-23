package game.characters;

/**
 * Skill1Decorator adds the "Flying Roundhouse Kick" skill.
 */
class Skill1Decorator extends FighterDecorator {
    public Skill1Decorator(Fighter fighter) {
        super(fighter);
        fighter.addSkill("Flying Roundhouse Kick");
    }

    @Override
    public void attack() {
        decoratedFighter.attack();
        System.out.println(" with a Flying Roundhouse Kick!");
    }
}
