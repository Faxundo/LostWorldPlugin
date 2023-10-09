package faxu.lost_world.lostworld.ability.fantasyclassic;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import org.bukkit.entity.Player;

public class IncreaseStatsAbility extends AbilityHandler{

    private final LostWorld plugin;
    private final PlayerDataManager playerDataManager;

    public IncreaseStatsAbility(LostWorld plugin) {
        super(plugin);
        this.plugin = plugin;
        playerDataManager = plugin.getPlayerDataManager();
    }

    public void applyEffect(Player player) {
        PlayerData playerData = playerDataManager.getPlayerData(player);
    }
}
