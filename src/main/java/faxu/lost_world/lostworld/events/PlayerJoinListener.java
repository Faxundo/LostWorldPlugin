package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final LostWorld plugin;

    public PlayerJoinListener(LostWorld plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getPlayerDataManager().playerExists(player)) {
            plugin.getPlayerDataManager().addPlayer(player);
        }

//        new BukkitRunnable() {
//
//            @Override
//            public void run() {
//                if (!player.isOnline()) {
//                    this.cancel();
//                }
//                willpower.setWillPowerActionBar(player);
//            }
//        }.runTaskTimerAsynchronously(plugin, 40L, 40L);
    }

}
