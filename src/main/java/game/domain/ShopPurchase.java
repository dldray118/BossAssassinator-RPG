package game.domain;

import game.characters.Fighter;
import game.items.Item;
import game.items.Potion;
import game.items.Weapon;

import java.util.List;

/**
 * Applies a shop item to the fighter / loadout (no I/O).
 */
public final class ShopPurchase {

    public enum Outcome {
        HEALED,
        POTION_NOT_NEEDED,
        WEAPON_ADDED,
        OTHER_USED
    }

    private ShopPurchase() {
    }

    /**
     * @param fighter      player receiving the item
     * @param item         item being bought
     * @param weaponLoadout mutable list of owned weapons from the shop visit
     */
    public static Outcome apply(Fighter fighter, Item item, List<Weapon> weaponLoadout) {
        if (item instanceof Potion) {
            if (fighter.getHealth() < fighter.getMaxHealth()) {
                fighter.setHealth(100);
                return Outcome.HEALED;
            }
            return Outcome.POTION_NOT_NEEDED;
        }
        if (item instanceof Weapon) {
            weaponLoadout.add((Weapon) item);
            return Outcome.WEAPON_ADDED;
        }
        return Outcome.OTHER_USED;
    }
}
