package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.stats.Constitution;
import org.bukkit.event.Listener;

public class WorldChangeListener implements Listener {

    private final LostWorld plugin;
    private final Constitution constitution;

    public WorldChangeListener (LostWorld plugin) {
        this.plugin = plugin;
        constitution = new Constitution();
    }

//    @EventHandler
//    public void onChangeWorld (PlayerChangedWorldEvent event) throws SQLException {
//        Player player = (Player) event.getPlayer();
//
//        constitution.setConstitution(plugin, player);
//    }
}
