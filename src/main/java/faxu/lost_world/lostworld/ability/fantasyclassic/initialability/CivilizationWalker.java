package faxu.lost_world.lostworld.ability.fantasyclassic.initialability;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.ability.fantasyclassic.BiomeAbility;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.ability.AbilityData;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.race.fantasyclassic.HumanRace;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class CivilizationWalker extends AbilityData implements Listener {

    private Map<String, Integer> modifiers;
    private final BiomeAbility biomeAbility;
    private final String abilityName;

    public CivilizationWalker(LostWorld plugin) {
        setId(InitialAbilities.CIVILIZATION_WALKER.getId());
        setName(InitialAbilities.CIVILIZATION_WALKER.getName());
        setOrigin(new HumanRace());
        setDisplayName(Common.colorize(LangConfig.get().getString("name.civilization-walker")));
        setDescription(Common.colorize(LangConfig.get().getString("description.civilization-walker")));
        modifiers = new HashMap<>();
        biomeAbility = new BiomeAbility(plugin);
        abilityName = InitialAbilities.CIVILIZATION_WALKER.getName().replace(" ", "-");
    }

    @EventHandler
    public void onMoveListener(PlayerMoveEvent event) {
        biomeAbility.setRacialModifiers(abilityName, modifiers);
        biomeAbility.applyEffect(event, Races.HUMAN.getName(), abilityName,
                modifiers, "civilization-enter","civilization-left");
    }
}
