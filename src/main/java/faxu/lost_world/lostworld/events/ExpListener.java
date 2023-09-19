package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.stats.Wisdom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ExpListener implements Listener {

    private final LostWorld plugin;
    private final Wisdom wisdom;

    public ExpListener(LostWorld plugin) {
        this.plugin = plugin;
        wisdom = new Wisdom();
    }

    @EventHandler
    public void onObtainExp(PlayerExpChangeEvent event) {
        wisdom.applyWisdom(plugin, event);
    }
}
