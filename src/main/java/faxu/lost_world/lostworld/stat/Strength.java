package faxu.lost_world.lostworld.stat;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Strength {

    public void applyStrength(LostWorld plugin, EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            double strength = plugin.getPlayerDataManager().getPlayerData(player).getStrength();

            if (player.getInventory().getItemInMainHand().getType().name().contains("SWORD") ||
                    player.getInventory().getItemInMainHand().getType().name().contains("AXE")) {
                event.setDamage(event.getDamage() + strength);
            } else {
                event.setDamage(event.getDamage() + (strength / 2));
            }
        }

    }
}
