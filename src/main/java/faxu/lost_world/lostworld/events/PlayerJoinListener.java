package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.stats.Constitution;
import faxu.lost_world.lostworld.stats.Luck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final LostWorld plugin;
    private Constitution constitution;
    private Luck luck;

    public PlayerJoinListener(LostWorld plugin) {
        this.plugin = plugin;
        constitution = new Constitution();
        luck = new Luck();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getPlayerDataManager().playerExists(player)) {
            plugin.getPlayerDataManager().addPlayer(player);
        }
        constitution.applyConstitution(plugin, player);
        luck.applyLuck(plugin, player);

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
