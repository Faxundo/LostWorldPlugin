package faxu.lost_world.lostworld.stats;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.entity.Player;

public class Constitution {

    public void applyConstitution(LostWorld plugin, Player player) {

        int base = plugin.getConfig().getInt("stats.default-constitution");
        int constitution = plugin.getPlayerDataManager().getPlayerData(player).getConstitution();

        player.setHealthScale(base + constitution);
    }

}
