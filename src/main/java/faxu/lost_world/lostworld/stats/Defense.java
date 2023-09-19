package faxu.lost_world.lostworld.stats;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Defense {

//    public int getDefense(LostWorld plugin, Player player) throws SQLException {
//        return plugin.getDatabase().getOneSkill(player, "defense");
//    }
//
//    public void setDefense(LostWorld plugin, EntityDamageByEntityEvent event, Player player) throws SQLException {
//
//        double defense = plugin.getDatabase().getOneSkill(player, "defense");
//
//        //First detect if Player has armor
//        if (hasArmor(player)) {
//            //Then detect complete set of armor what he has
//            if (detectTypeArmor(player, player.getInventory().getHelmet().getType(),
//                    player.getInventory().getChestplate().getType(), player.getInventory().getLeggings().getType(),
//                    player.getInventory().getBoots().getType())) {
//                if (detectTypeArmor(player, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE,
//                        Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS)) {
//                    event.setDamage(event.getDamage() - (defense / 2));
//                } else if (detectTypeArmor(player, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE,
//                        Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS)) {
//                    event.setDamage(event.getDamage() - (defense / 1.5));
//                } else if (detectTypeArmor(player, Material.IRON_HELMET, Material.IRON_CHESTPLATE,
//                        Material.IRON_LEGGINGS, Material.IRON_BOOTS)) {
//                    event.setDamage(event.getDamage() - defense);
//                } else if (detectTypeArmor(player, Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE,
//                        Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS)) {
//                    event.setDamage(event.getDamage() - defense);
//                } else if (detectTypeArmor(player, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE,
//                        Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS)) {
//                    event.setDamage(event.getDamage() - (defense * 1.5));
//                } else if (detectTypeArmor(player, Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE,
//                        Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS)) {
//                    event.setDamage(event.getDamage() - (defense * 2));
//                }
//            }
//
//        } else {
//            event.setDamage(event.getDamage() - (defense / 2.5));
//        }
//
//
//    }

    public boolean hasArmor(Player player) {
        return player.getInventory().getHelmet() != null &&
                player.getInventory().getChestplate() != null &&
                player.getInventory().getLeggings() != null &&
                player.getInventory().getBoots() != null;
    }

    public boolean detectTypeArmor(Player player, Material helmet, Material chestplate, Material leggings, Material boots) {
        return detectTypeHelmet(player, helmet) && detectTypeChestplate(player, chestplate)
                && detectTypeLeggings(player, leggings) && detectTypeBoots(player, boots);
    }

    private boolean detectTypeHelmet(Player player, Material item) {
        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet != null) {
            return item == helmet.getType();
        }
        return false;
    }

    private boolean detectTypeChestplate(Player player, Material item) {
        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate != null) {
            return item == chestplate.getType();
        }
        return false;
    }

    private boolean detectTypeLeggings(Player player, Material item) {
        ItemStack leggings = player.getInventory().getLeggings();
        if (leggings != null) {
            return item == leggings.getType();
        }
        return false;
    }

    private boolean detectTypeBoots(Player player, Material item) {
        ItemStack boots = player.getInventory().getBoots();
        if (boots != null) {
            return item == boots.getType();
        }
        return false;
    }
}
