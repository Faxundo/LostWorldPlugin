package faxu.lost_world.lostworld.stats;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class Wisdom implements Listener {

    private final LostWorld plugin;

    public Wisdom (LostWorld plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onObtainExp(PlayerExpChangeEvent event) {
        applyWisdom(plugin, event);
    }

    public void applyWisdom(LostWorld plugin, PlayerExpChangeEvent event) {

        Player player = event.getPlayer();

        int wisdom = plugin.getPlayerDataManager().getPlayerData(player).getWisdom();

        if (wisdom != 0) {
            int experienceBase = event.getAmount();
            int experienceIncrease = (int) ((experienceBase * (wisdom * 2.5)) / 10);
            event.setAmount(experienceBase + experienceIncrease);
        }

    }
}
