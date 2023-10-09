package faxu.lost_world.lostworld.event;

import faxu.lost_world.lostworld.menusystem.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        // Check if clicked inventory is null
        if (event.getClickedInventory() == null) {
            return;
        }

        InventoryHolder holder = event.getClickedInventory().getHolder();

        if (holder instanceof Menu) {

            event.setCancelled(true);

            if (event.getCurrentItem() == null) {
                return;
            }

            Menu menu = (Menu) holder;
            menu.handleMenu(event);
        }
    }
}