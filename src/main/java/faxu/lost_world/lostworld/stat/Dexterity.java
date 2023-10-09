package faxu.lost_world.lostworld.stat;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Dexterity {

    public void applyDexterity(LostWorld plugin, EntityDamageByEntityEvent event, Player player) {

        double dexterity = plugin.getPlayerDataManager().getPlayerData(player).getDexterity();

        if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (event.getEntity().getType() == EntityType.TRIDENT) {
                event.setDamage(event.getDamage() + (dexterity / 1.5));
            } else if (event.getDamager() instanceof Snowball || event.getDamager() instanceof Egg) {
                event.setDamage(event.getDamage() + (dexterity / 4));
            } else {
                event.setDamage(event.getDamage() + (dexterity / 2));
            }
        }

    }
}
