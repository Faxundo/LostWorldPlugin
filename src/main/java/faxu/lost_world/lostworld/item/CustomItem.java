package faxu.lost_world.lostworld.item;

import faxu.lost_world.lostworld.util.Common;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CustomItem {

    public abstract String getName();

    public abstract Material getMaterial();

    public abstract List<String> getLore();

    public String getId() {
        return getClass().getSimpleName().toLowerCase();
    }

    public abstract void handleLeftClick(Player player, ItemStack itemStack, PlayerInteractEvent event);

    public abstract void handleRightClick(Player player, ItemStack itemStack, PlayerInteractEvent event);

    public abstract void handleDrop (Player player, ItemStack itemStack, PlayerDropItemEvent event);

    public static ItemStack getItem(ItemStack item, String name, String... lore) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Common.colorize(name));

        List<String> lores = new ArrayList<>();

        for (String string : lore) {
            lores.addAll(Arrays.asList(Common.colorize(string).split("\n")));
        }

        meta.setLore(lores);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getItem(ItemStack item, String name, int customModelData, String... lore) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Common.colorize(name));

        List<String> lores = new ArrayList<>();

        for (String string : lore) {
            lores.addAll(Arrays.asList(Common.colorize(string).split("\n")));
        }

        meta.setLore(lores);
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getItem(ItemStack item, String name, int customModelData, NamespacedKey key, String... lore) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Common.colorize(name));

        List<String> lores = new ArrayList<>();

        for (String string : lore) {
            lores.addAll(Arrays.asList(Common.colorize(string).split("\n")));
        }

        meta.setLore(lores);
        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(key, PersistentDataType.STRING, getId());
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);

        return item;
    }
}
