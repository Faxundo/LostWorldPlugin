package faxu.lost_world.lostworld.event;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.willpower.Willpower;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final LostWorld plugin;
    private Willpower willpower;
    private PlayerDataManager playerDataManager;

    public PlayerJoinListener(LostWorld plugin) {
        this.plugin = plugin;
        playerDataManager = plugin.getPlayerDataManager();
        willpower = new Willpower(this.plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!playerDataManager.playerExists(player)) {
            playerDataManager.addPlayer(player);
        }
        willpower.setWillPower(player, 0, true);
    }

}
