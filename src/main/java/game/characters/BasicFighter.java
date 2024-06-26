package game.characters;

/**
 *
 * BasicFighter class implements the Fighter interface with basic capabilities.
 */
public class BasicFighter implements Fighter {
    private String name;
    private String skills;

    public BasicFighter(String name) {
        this.name = name;
        this.skills = "Punch Attack";
    }

    @Override
    public void attack() {
        System.out.println(name + " uses " + skills);
    }

    @Override
    public void addSkill(String skill) {
        this.skills += ", " + skill;
    }

    @Override
    public String getName() {
        return name;
    }
}
