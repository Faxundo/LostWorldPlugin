package faxu.lost_world.lostworld.leveling;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.data.PlayerDataManager;
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
        if (level != 0 && level < maxLevel() &&
                player.getLevel() >= multiplierBase()*(multiplierLevel()*level)) {
            playerDataManager.setRaceLevel(player, level + 1);
            player.setLevel(player.getLevel() - multiplierBase()*(multiplierLevel()*level));
            System.out.println(player.getName());
        } else {
            player.sendMessage("Te faltan niveles pa");
        }
    }

    public int multiplierBase() {
        return config.getInt("levleing.multiplier-base");
    }

    public int multiplierLevel() {
        return config.getInt("levleing.multiplier-level");
    }

    public int maxLevel() {
        return config.getInt("leveling.max-level");
    }

}
