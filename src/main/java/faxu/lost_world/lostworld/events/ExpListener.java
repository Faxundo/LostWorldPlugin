package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.stats.Wisdom;
import org.bukkit.event.Listener;

public class ExpListener implements Listener {

    private final LostWorld plugin;
    private final Wisdom wisdom;

    public ExpListener(LostWorld plugin) {
        this.plugin = plugin;
        wisdom = new Wisdom();
    }

//    @EventHandler
//    public void onObtainExp(PlayerExpChangeEvent event) throws SQLException {
//        wisdom.setWisdom(plugin, event);
//    }
}
