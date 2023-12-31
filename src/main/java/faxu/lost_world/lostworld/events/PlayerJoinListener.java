package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.stats.Constitution;
import faxu.lost_world.lostworld.stats.Luck;
import faxu.lost_world.lostworld.willpower.Willpower;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener {

    private final LostWorld plugin;
    private Constitution constitution;
    private Luck luck;
    private Willpower willpower;
    private PlayerDataManager playerDataManager;

    public PlayerJoinListener(LostWorld plugin) {
        this.plugin = plugin;
        playerDataManager = plugin.getPlayerDataManager();
        constitution = new Constitution();
        luck = new Luck();
        willpower = new Willpower(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!playerDataManager.playerExists(player)) {
            playerDataManager.addPlayer(player);
        }

        constitution.applyConstitution(plugin, player);
        luck.applyLuck(plugin, player);
        willpower.setWillPower(player, 0, true);

        new BukkitRunnable() {

            @Override
            public void run() {
                if (!player.isOnline()) {
                    this.cancel();
                }
                willpower.willPowerBar(player);
            }
        }.runTaskTimerAsynchronously(plugin, 40L, 40L);
    }

}
