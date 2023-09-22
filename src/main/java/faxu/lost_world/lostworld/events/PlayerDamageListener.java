package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.stats.Defense;
import faxu.lost_world.lostworld.stats.Dextery;
import faxu.lost_world.lostworld.stats.Strength;
import faxu.lost_world.lostworld.util.DamageType;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

public class PlayerDamageListener implements Listener {

    private final LostWorld plugin;
    private final Strength strength;
    private final Defense defense;
    private final Dextery dextery;

    public PlayerDamageListener(LostWorld plugin) {
        this.plugin = plugin;
        strength = new Strength();
        defense = new Defense();
        dextery = new Dextery();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        //Apply Defense
        if (event.getEntity() instanceof Player) {
            Player defender = (Player) event.getEntity();
            defense.applyDefense(plugin, event, defender);
        }

        //Apply Strength
        if (event.getDamager() instanceof Player) {
            Player attacker = getDamager(event.getDamager());

            if (attacker != null) {
                DamageType damageType = getDamageType(attacker);

                ArrayList<DamageType> melee = new ArrayList<>();
                melee.add(DamageType.SWORD);
                melee.add(DamageType.AXE);
                melee.add(DamageType.HOE);
                melee.add(DamageType.PICKAXE);
                melee.add(DamageType.HAND);
                melee.add(DamageType.SHOVEL);

                for (int i = 0; i < melee.size(); i++) {
                    if (melee.contains(damageType)) {
                        strength.applyStrength(plugin, event);
                    }
                }

                //Apply Dextery
                ArrayList<DamageType> range = new ArrayList<>();
                range.add(DamageType.BOW);
                range.add(DamageType.CROSSBOW);
                range.add(DamageType.TRIDENT);
                if (plugin.getConfig().getBoolean("stats.dextery-affects-egg")) {
                    range.add(DamageType.EGG);
                }
                if (plugin.getConfig().getBoolean("stats.dextery-affects-snowball")) {
                    range.add(DamageType.SNOWBALL);
                }

                for (int i = 0; i < range.size(); i++) {
                    if (range.contains(damageType)) {
                        dextery.applyDextery(plugin, event, attacker);
                    }
                }
            }
        }
    }

    private DamageType getDamageType(Player player) {

        Material item = player.getInventory().getItemInMainHand().getType();

        if (item.name().contains("AXE")) {
            return DamageType.AXE;
        } else if (item.name().contains("BOW")) {
            return DamageType.BOW;
        } else if (item.name().contains("CROSSBOW")) {
            return DamageType.CROSSBOW;
        } else if (item.equals(Material.AIR)) {
            return DamageType.HAND;
        } else if (item.name().contains("HOE")) {
            return DamageType.HOE;
        } else if (item.name().contains("PICKAXE")) {
            return DamageType.PICKAXE;
        } else if (item.name().contains("SHOVEL") || item.name().contains("SPADE")) {
            return DamageType.SHOVEL;
        } else if (item.name().contains("SWORD")) {
            return DamageType.SWORD;
        } else if (item.name().contains("TRIDENT")) {
            return DamageType.TRIDENT;
        } else if (item.name().contains("EGG")) {
            return DamageType.EGG;
        } else if (item.name().contains("SNOWBALL")) {
            return DamageType.SNOWBALL;
        } else {
            return DamageType.OTHER;
        }
    }

    private Player getDamager(Entity entity) {
        Player player = null;

        if (entity instanceof Player) {
            player = (Player) entity;
        } else if (entity instanceof Projectile) {
            Projectile projectile = (Projectile) entity;
            player = (Player) projectile.getShooter();
        }

        return player;
    }
}
