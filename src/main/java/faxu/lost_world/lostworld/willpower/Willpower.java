package faxu.lost_world.lostworld.willpower;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.stats.Intelligence;
import faxu.lost_world.lostworld.util.ActionBar;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class Willpower {
    private final NamespacedKey willpower;
    private final Intelligence intelligence;

    public Willpower(LostWorld plugin) {
        this.willpower = new NamespacedKey(plugin, "willpower");
        this.intelligence = new Intelligence();
    }

//    public void setWillPower(LostWorld plugin, Player player) throws SQLException {
//        PersistentDataContainer data = player.getPersistentDataContainer();
//
//        int willPowerAmount = intelligence.getIntelligence(plugin, player);
//        data.set(willpower, PersistentDataType.INTEGER, willPowerAmount * 2);
//    }

    public int getWillPower(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        return data.getOrDefault(willpower, PersistentDataType.INTEGER, 0);
    }

    public void setWillPowerActionBar(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        int willPowerAmount = data.getOrDefault(willpower, PersistentDataType.INTEGER, 0);
        ActionBar.sendActionBar(player, ChatColor.BLUE + "WillPower: " + willPowerAmount);
    }
}
