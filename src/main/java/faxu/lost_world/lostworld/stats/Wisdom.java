package faxu.lost_world.lostworld.stats;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class Wisdom {

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
