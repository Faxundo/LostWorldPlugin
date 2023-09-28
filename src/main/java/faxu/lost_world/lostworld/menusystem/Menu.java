package faxu.lost_world.lostworld.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;
    protected PlayerMenuUtility playerMenuUtility;

    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent event);

    public abstract void setMenuItems();

    public void openMenu() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        this.setMenuItems();

        playerMenuUtility.getOwner().openInventory(inventory);
    }

    public ItemStack getItem(ItemStack item, String name, String... lore) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> lores = new ArrayList<>();

        for (String string : lore) {
            lores.addAll(Arrays.asList(ChatColor.translateAlternateColorCodes('&', string).split("\n")));
        }

        meta.setLore(lores);

        item.setItemMeta(meta);

        return item;
    }

    public void fillMenu(int invSize, Inventory inv) {
        ItemStack blank = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        for (int i = 0; i < invSize; i++) {
            ItemMeta meta = blank.getItemMeta();
            meta.setCustomModelData(32);
            meta.setDisplayName(" ");
            blank.setItemMeta(meta);
            inv.setItem(i, blank);
        }
    }
}
