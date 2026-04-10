package game.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import game.items.Potion;
import game.items.Weapon;
import org.junit.jupiter.api.Test;

class VisualGameplayPaneTest {

    @Test
    void formatWeaponLineIncludesDamage() {
        String line = VisualGameplayPane.formatItemLine(new Weapon("Knife", 5));
        assertTrue(line.contains("Knife"));
        assertTrue(line.contains("+5"));
    }

    @Test
    void formatPotionLineIsReadable() {
        String line = VisualGameplayPane.formatItemLine(new Potion());
        assertEquals("Potion · restore to full HP", line);
    }
}
