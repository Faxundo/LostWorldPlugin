package faxu.lost_world.lostworld.stats;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;

public class Luck {

    public void applyLuck(LostWorld plugin, Player player) {

        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_LUCK);

        if (attribute == null) return;

        boolean hasAttribute = false;
        for (AttributeModifier modifier: attribute.getModifiers()) {
            if (modifier.getName().equals("LostRaces-Luck")) {
                attribute.removeModifier(modifier);
                hasAttribute = true;
            }
        }

        double luck = plugin.getPlayerDataManager().getPlayerData(player).getLuck();
        attribute.addModifier(new AttributeModifier("LostRaces-Luck", luck, AttributeModifier.Operation.ADD_NUMBER));

    }
}
