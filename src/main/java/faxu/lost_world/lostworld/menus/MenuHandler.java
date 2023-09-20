package faxu.lost_world.lostworld.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuHandler {

    protected final String invName;
    protected final int invSize;

    public MenuHandler(String invName, int invSize) {
        this.invName = invName;
        this.invSize = invSize;
    }

    public ItemStack getItem(ItemStack item, String name, String... lore) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> lores = new ArrayList<>();

        for (String s : lore) {
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);

        item.setItemMeta(meta);

        return item;
    }

    public void fillMenu (int invSize, Inventory inv) {
        for (int i = 0; i < invSize; i++) {
            inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }
    }
}
