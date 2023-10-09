package faxu.lost_world.lostworld.event;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.item.CustomItem;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class CustomItemListener implements Listener {

    private final LostWorld plugin;

    public CustomItemListener (LostWorld plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) && isCustomItem(item)) {
            CustomItem customItem = LostWorld.customItemMap.get(getItemId(item));
                customItem.handleRightClick(player, item, event);
        }

        if ((event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_AIR)) && isCustomItem(item)) {
            CustomItem customItem = LostWorld.customItemMap.get(getItemId(item));
            customItem.handleLeftClick(player, item, event);
        }
    }

    @EventHandler
    public void onPlayerDropItem (PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (isCustomItem(item) && isRacialItem(item)) {
            CustomItem customItem = LostWorld.customItemMap.get(getItemId(item));
            customItem.handleDrop(player, item, event);
            event.setCancelled(true);
        }
    }

    private boolean isCustomItem(ItemStack itemStack) {
        if (itemStack.hasItemMeta()){
            return itemStack.getItemMeta().getPersistentDataContainer().has(LostWorld.lostItemKey, PersistentDataType.STRING);
        }
        return false;
    }

    private boolean isRacialItem(ItemStack itemStack) {
        if (itemStack.hasItemMeta()){
            return itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "lost_item_racial"), PersistentDataType.STRING);
        }
        return false;
    }

    private String getItemId(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().get(LostWorld.lostItemKey, PersistentDataType.STRING);
    }
}
