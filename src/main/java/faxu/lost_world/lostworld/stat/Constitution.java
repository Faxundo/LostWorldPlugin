package faxu.lost_world.lostworld.stat;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Constitution implements Listener {

    private final LostWorld plugin;
    private final PlayerDataManager playerDataManager;

    public Constitution(LostWorld plugin) {
        this.plugin = plugin;
        playerDataManager = plugin.getPlayerDataManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (playerDataManager.playerExists(player)) {
            applyConstitution(plugin, player);
        }

    }

    public static void applyConstitution(LostWorld plugin, Player player) {

        int base = plugin.getConfig().getInt("stats.default-constitution");
        int constitution = plugin.getPlayerDataManager().getPlayerData(player).getConstitution();

        player.setHealthScale(base + constitution);
    }

}
