package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.data.races.RaceDataManager;
import faxu.lost_world.lostworld.races.Races;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public class InventoryClickListener implements Listener {

    private final LostWorld plugin;
    FileConfiguration config;
    PlayerDataManager playerDataManager;
    RaceDataManager raceDataManager;

    public InventoryClickListener(LostWorld plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        playerDataManager = plugin.getPlayerDataManager();
        raceDataManager = plugin.getRaceDataManager();
    }

    @EventHandler
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
                raceData = raceDataManager.getRaceByName(Races.HUMAN.name());
            } else if (slot == 12) {
                raceData = raceDataManager.getRaceByName(Races.ORC.name());
            } else if (slot == 14) {
                raceData = raceDataManager.getRaceByName(Races.ELF.name());
            } else if (slot == 16) {
                raceData = raceDataManager.getRaceByName(Races.DWARF.name());
            }
            //Avoid you set the same race has you have.
            if (!playerDataManager.getPlayerData(player).getRace().equals(raceData)) {
                playerDataManager.setRace(player, raceData);
            }
            inventoryView.close();
        }
        event.setCancelled(true);
    }
}
