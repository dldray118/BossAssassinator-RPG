package game.engine;

import java.util.Scanner;

/**
 * Reads intents from {@link Scanner} (stdin).
 */
public class ScannerPlayerCommandSource implements PlayerCommandSource {

    private final Scanner scanner;

    public ScannerPlayerCommandSource(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public ShopIntent nextShopIntent(int itemCount) {
        System.out.println("Choose an item to buy (1-" + itemCount + ") or 0 to leave:");
        int choice = scanner.nextInt();
        if (choice == ShopIntent.LEAVE) {
            return ShopIntent.leave();
        }
        if (choice >= 1 && choice <= itemCount) {
            return ShopIntent.selectItem(choice);
        }
        return ShopIntent.leave();
    }

    @Override
    public CombatStepIntent nextCombatStepIntent() {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        return CombatStepIntent.PROCEED;
    }

    @Override
    public ExploringIntent nextExploringIntent() {
        System.out.println("Press enter to continue exploring...");
        scanner.nextLine();
        return ExploringIntent.PROCEED;
    }
}
