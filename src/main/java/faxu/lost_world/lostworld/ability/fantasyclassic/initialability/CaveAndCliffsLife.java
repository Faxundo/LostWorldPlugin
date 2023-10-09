package faxu.lost_world.lostworld.ability.fantasyclassic.initialability;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.ability.fantasyclassic.BiomeAbility;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.ability.AbilityData;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.race.fantasyclassic.DwarfRace;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class CaveAndCliffsLife extends AbilityData implements Listener {

    private Map<String, Integer> modifiers;
    private final BiomeAbility biomeAbility;
    private final String abilityName;

    public CaveAndCliffsLife(LostWorld plugin) {
        setId(InitialAbilities.CAVE_AND_CLIFF_LIFE.getId());
        setName(InitialAbilities.CAVE_AND_CLIFF_LIFE.getName());
        setOrigin(new DwarfRace());
        setDisplayName(Common.colorize(LangConfig.get().getString("name.cave-and-cliff-life")));
        setDescription(Common.colorize(LangConfig.get().getString("description.cave-and-cliff-life")));
        modifiers = new HashMap<>();
        biomeAbility = new BiomeAbility(plugin);
        abilityName = InitialAbilities.CIVILIZATION_WALKER.getName().replace(" ", "-");
    }

    @EventHandler
    public void onMoveListener(PlayerMoveEvent event) {
        biomeAbility.setRacialModifiers(abilityName, modifiers);
        biomeAbility.applyEffect(event, Races.DWARF.getName(), abilityName,
                modifiers, "cave-or-cliffs-enter","cave-or-cliffs-left");
    }
}