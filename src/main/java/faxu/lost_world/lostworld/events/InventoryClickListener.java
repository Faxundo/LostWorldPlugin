package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.data.races.RaceDataManager;
import faxu.lost_world.lostworld.races.Races;
import faxu.lost_world.lostworld.util.PermissionManager;
import faxu.lost_world.lostworld.willpower.Willpower;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public class InventoryClickListener implements Listener {

    private final LostWorld plugin;
    private final FileConfiguration config;
    private final PlayerDataManager playerDataManager;
    private final RaceDataManager raceDataManager;
    private final PermissionManager permissionManager;
    private Willpower willpower;

    public InventoryClickListener(LostWorld plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        playerDataManager = plugin.getPlayerDataManager();
        raceDataManager = plugin.getRaceDataManager();
        permissionManager = new PermissionManager(this.plugin);
        willpower = new Willpower(this.plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        //Only for this inventories
        if (!event.getView().getTitle().equals(config.getString("names.invstats")) &&
                !event.getView().getTitle().equals(config.getString("names.invrace")))
            return;
        if (event.getView().getTitle().equals(config.getString("names.invrace"))) {
            int slot = event.getSlot();
            InventoryView inventoryView = event.getView();
            Player player = (Player) event.getWhoClicked();
            RaceData raceData = new RaceData();

            if (slot == 10) {
                raceData = raceDataManager.getRaceByName(Races.HUMAN.getName());
            } else if (slot == 12) {
                raceData = raceDataManager.getRaceByName(Races.ORC.getName());
            } else if (slot == 14) {
                raceData = raceDataManager.getRaceByName(Races.ELF.getName());
            } else if (slot == 16) {
                raceData = raceDataManager.getRaceByName(Races.DWARF.getName());
            }
            //Avoid you set the same race has you have.
            if (!playerDataManager.getPlayerData(player).getRace().equals(raceData)) {
                playerDataManager.setRace(player, raceData);
            }
            if (slot == 10 || slot == 12 || slot == 14 || slot == 16) {
                player.sendMessage(ChatColor.GOLD + "You choose a new race.");
                willpower.setWillPower(player, 0, true);
                inventoryView.close();
            }
        }
        event.setCancelled(true);
    }
}
