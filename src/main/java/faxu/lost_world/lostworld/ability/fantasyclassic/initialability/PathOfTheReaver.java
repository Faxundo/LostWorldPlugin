package faxu.lost_world.lostworld.ability.fantasyclassic.initialability;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.ability.fantasyclassic.BiomeAbility;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.ability.AbilityData;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.race.fantasyclassic.OrcRace;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class PathOfTheReaver extends AbilityData implements Listener {

    private Map<String, Integer> modifiers;
    private final BiomeAbility biomeAbility;
    private final String abilityName;

    public PathOfTheReaver(LostWorld plugin) {
        setId(InitialAbilities.PATH_OF_THE_REAVER.getId());
        setName(InitialAbilities.PATH_OF_THE_REAVER.getName());
        setOrigin(new OrcRace());
        setDisplayName(Common.colorize(LangConfig.get().getString("name.path-of-the-reaver")));
        setDescription(Common.colorize(LangConfig.get().getString("description.path-of-the-reaver")));
        modifiers = new HashMap<>();
        biomeAbility = new BiomeAbility(plugin);
        abilityName = InitialAbilities.PATH_OF_THE_REAVER.getName().replace(" ", "-");
    }

    @EventHandler
    public void onMoveListener(PlayerMoveEvent event) {
        biomeAbility.setRacialModifiers(abilityName, modifiers);
        biomeAbility.applyEffect(event, Races.ORC.getName(), abilityName,
                modifiers, "path-reaver-enter", "path-reaver-left");
    }
}