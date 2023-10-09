package faxu.lost_world.lostworld.ability.fantasyclassic;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.race.RaceDataManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActiveAbility extends AbilityHandler{

    private final PlayerDataManager playerDataManager;
    private final RaceDataManager raceDataManager;
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownTime = 30000;

    public ActiveAbility(LostWorld plugin) {
        super(plugin);
        playerDataManager = plugin.getPlayerDataManager();
        raceDataManager = plugin.getRaceDataManager();
    }

    public void applyEffect (Player player, String race, String abilityName, String cooldown) {

        if (cooldowns.containsKey(player.getUniqueId())) {
            long secondsLeft = (cooldowns.get(player.getUniqueId()) + cooldownTime - System.currentTimeMillis()) / 1000;
            if (secondsLeft > 0) {
                return;
            }
        }


    }
}
