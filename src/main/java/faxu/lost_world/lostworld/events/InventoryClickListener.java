package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.races.Races;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public class InventoryClickListener implements Listener {

    private final LostWorld plugin;

    public InventoryClickListener(LostWorld plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        //Only for this inventories
        if (!event.getView().getTitle().equals("Races") && !event.getView().getTitle().equals("Stats")) return;
        if (event.getView().getTitle().equals("Races")) {
            int slot = event.getSlot();
            InventoryView inventoryView = event.getView();
            Player player = (Player) event.getWhoClicked();
            RaceData raceData;
            switch (slot) {
                case 10:
                    raceData = plugin.getRaceDataManager().getRaceByName(Races.HUMAN.name());
                    plugin.getPlayerDataManager().setRace(player, raceData);
                    inventoryView.close();
                    break;
                case 12:
                    raceData = plugin.getRaceDataManager().getRaceByName(Races.ORC.name());
                    plugin.getPlayerDataManager().setRace(player, raceData);
                    inventoryView.close();
                    break;
                case 14:
                    raceData = plugin.getRaceDataManager().getRaceByName(Races.ELF.name());
                    plugin.getPlayerDataManager().setRace(player, raceData);
                    inventoryView.close();
                    break;
                case 16:
                    raceData = plugin.getRaceDataManager().getRaceByName(Races.DWARF.name());
                    plugin.getPlayerDataManager().setRace(player, raceData);
                    inventoryView.close();
                    break;
                default:
            }
        }
        event.setCancelled(true);
    }
}
