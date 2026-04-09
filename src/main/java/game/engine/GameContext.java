package game.engine;

import game.characters.BasicFighter;
import game.characters.Fighter;

import java.util.Random;
import java.util.Scanner;

/**
 * GameContext class manages the state and flow of the game.
 */
public class GameContext {
    private GameState currentState;
    private GameSession session;
    private Random random;
    private Scanner scanner;
    private boolean isInteractive;
    private PlayerCommandSource playerCommands;

    /**
     * Constructs a new GameContext object.
     * This constructor initializes the game state to ExploringState,
     * creates a basic fighter named "Boss Assassinator",
     * and initializes the random number generator
     * and the scanner for user input.
     */

    public GameContext() {
        this.session = new GameSession(new BasicFighter("Boss Assassinator"));
        this.currentState = new ExploringState(this);
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        this.playerCommands = new RandomPlayerCommandSource(this.random);
    }

    /**
     * Get the active command source (intents for shop, combat, exploration).
     * @return The command source.
     */
    public PlayerCommandSource getPlayerCommands() {
        return playerCommands;
    }

    /**
     * @param playerCommands command source for shop, combat, and exploration
     */
    public void setPlayerCommands(PlayerCommandSource playerCommands) {
        this.playerCommands = playerCommands;
    }

    /**
     * Get the game session (fighter, enemy tier).
     * @return The game session.
     */
    public GameSession getSession() {
        return session;
    }

    /**
     * Set the current state of the game.
     * @param state The new state to set.
     */
    public void setState(GameState state) {
        this.currentState = state;
    }

    /**
     * Get the current state of the game.
     * @return The current game state.
     */
    public GameState getCurrentState() {
        return currentState;
    }


    /**
     * Start the game loop.
     */
    public void startGame() {
        while (!(currentState instanceof GameOverState)) {
            currentState.handle();
        }
        System.out.println("Game Over! Thanks for playing.");
    }

    /**
     * Get the main character (Boss Assassinator).
     * @return The main character.
     */
    public Fighter getBossAssassinator() {
        return session.getFighter();
    }

    /**
     * Set the main character (Boss Assassinator).
     * @param bossAssassinator The main character to set.
     */
    public void setBossAssassinator(Fighter bossAssassinator) {
        session.setFighter(bossAssassinator);
    }

    /**
     * Get the random number generator.
     * @return The random number generator.
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Get the scanner for user input.
     * @return The scanner for user input.
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Set the game to interactive or automatic mode.
     * @param isInteractive True for interactive mode, false for automatic mode.
     */
    public void setInteractive(boolean isInteractive) {
        this.isInteractive = isInteractive;
    }

    /**
     * Check if the game is in interactive mode.
     * @return True if interactive mode, false if automatic mode.
     */
    public boolean isInteractive() {
        return isInteractive;
    }

}
