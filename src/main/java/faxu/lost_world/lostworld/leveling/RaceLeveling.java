package faxu.lost_world.lostworld.leveling;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RaceLeveling {

    private final LostWorld plugin;
    private final FileConfiguration config;
    private final PlayerDataManager playerDataManager;

    public RaceLeveling(LostWorld plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        playerDataManager = plugin.getPlayerDataManager();
    }

    public void updateLevel(Player player) {
        PlayerData playerData = playerDataManager.getPlayerData(player);
        int level = playerData.getRaceLevel();
        int statPoints = playerData.getStatPoints();
        if (level != 0 && level < maxLevel() && player.getLevel() >= multiplierBase() + (multiplierLevel() * level)) {

            playerDataManager.setRaceLevel(player, level + 1);
            playerDataManager.setStatPoint(player, statPoints + 1);
            player.setLevel(player.getLevel() - (multiplierBase() + (multiplierLevel() * level)));
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5F, 0.5F);
            player.sendMessage(Common.colorize(config.getString("messages.level-up")));

        } else {
            player.sendMessage(Common.colorize(config.getString("messages.no-have-level")));
        }
    }

    public int multiplierBase() {
        return config.getInt("leveling.multiplier-base");
    }

    public int multiplierLevel() {
        return config.getInt("leveling.multiplier-level");
    }

    public int maxLevel() {
        return config.getInt("leveling.max-level");
    }

}
