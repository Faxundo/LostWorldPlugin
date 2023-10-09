package faxu.lost_world.lostworld.ability.fantasyclassic.initialability;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.ability.fantasyclassic.BiomeAbility;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.ability.AbilityData;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.race.fantasyclassic.ElfRace;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class ForestDweller extends AbilityData implements Listener {

    private Map<String, Integer> modifiers;
    private final BiomeAbility biomeAbility;
    private final String abilityName;

    public ForestDweller(LostWorld plugin) {
        setId(InitialAbilities.FOREST_DWELLER.getId());
        setName(InitialAbilities.FOREST_DWELLER.getName());
        setOrigin(new ElfRace());
        setDisplayName(Common.colorize(LangConfig.get().getString("name.forest-dweller")));
        setDescription(Common.colorize(LangConfig.get().getString("description.forest-dweller")));
        modifiers = new HashMap<>();
        biomeAbility = new BiomeAbility(plugin);
        abilityName = InitialAbilities.FOREST_DWELLER.getName().replace(" ", "-");
    }

    @EventHandler
    public void onMoveListener(PlayerMoveEvent event) {
        biomeAbility.setRacialModifiers(abilityName, modifiers);
        biomeAbility.applyEffect(event, Races.ELF.getName(), abilityName,
                modifiers, "forest-enter", "forest-left");
    }
}