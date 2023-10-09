package faxu.lost_world.lostworld.willpower;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.util.ActionBar;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class Willpower {
    private final LostWorld plugin;
    private final FileConfiguration config;
    private final NamespacedKey willpower;
    private final NamespacedKey willpowerMax;

    public Willpower(LostWorld plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        willpower = new NamespacedKey(this.plugin, "willpower");
        willpowerMax = new NamespacedKey(this.plugin, "willpowerMax");
    }

    public void setWillPower(Player player, int amount, boolean synchronize) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        if (synchronize) {
            PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
            data.set(willpower, PersistentDataType.INTEGER, playerData.getIntelligence() * 2);
            data.set(willpowerMax, PersistentDataType.INTEGER, playerData.getIntelligence() * 2);
        } else {
            data.set(willpower, PersistentDataType.INTEGER, amount);
        }
    }

    public int getWillPower(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        return data.getOrDefault(willpower, PersistentDataType.INTEGER, 0);
    }

    public int getMaxWillPower(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        return data.getOrDefault(willpowerMax, PersistentDataType.INTEGER, 0);
    }

    public void useWillPower(Player player, int amount) {
        int currentWillPower = getWillPower(player);
        if (currentWillPower >= amount) {
            setWillPower(player, currentWillPower - amount, false);
        } else {
            player.sendMessage("" + config.getString("messages.sufficient-willpower"));
        }
    }

    public void willPowerBar() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isOnline()) {
                        int willPowerAmount = getWillPower(player);
                        int willPowerMax = getMaxWillPower(player);
                        ActionBar.sendActionBar(player, Common.colorize(LangConfig.get().getString("name.willpower") + ": " + willPowerAmount + "/" + willPowerMax));

                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, 40L, 40L);
    }
}
