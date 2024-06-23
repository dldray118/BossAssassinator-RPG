package game.characters;

/**
 * Fighter interface for characters in the game.
 */
public interface Fighter {
    void attack();
    void addSkill(String skill);
    String getName();
}
