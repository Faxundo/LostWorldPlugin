package faxu.lost_world.lostworld.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        playerMenuUtility.getOwner().playSound(playerMenuUtility.getOwner().getLocation(),
                Sound.UI_LOOM_SELECT_PATTERN, 0.3F, 0.3F);

        this.setMenuItems();

        playerMenuUtility.getOwner().openInventory(inventory);
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
