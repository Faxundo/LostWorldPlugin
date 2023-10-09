package faxu.lost_world.lostworld.ability.fantasyclassic.thirdability;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.ability.fantasyclassic.IncreaseStatsAbility;
import faxu.lost_world.lostworld.ability.fantasyclassic.StatsAbilities;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.ability.AbilityData;
import faxu.lost_world.lostworld.race.fantasyclassic.ElfRace;
import faxu.lost_world.lostworld.util.Common;

import java.util.HashMap;
import java.util.Map;

public class CallOfTheWild extends AbilityData {

    private Map<String, Integer> modifiers;
    private final IncreaseStatsAbility increaseStatsAbility;

    public CallOfTheWild(LostWorld plugin) {
        setId(StatsAbilities.CALL_OF_THE_WILD.getId());
        setName(StatsAbilities.CALL_OF_THE_WILD.getName());
        setOrigin(new ElfRace());
        setDisplayName(Common.colorize(LangConfig.get().getString("name.call-of-the-wild")));
        setDescription(Common.colorize(LangConfig.get().getString("description.call-of-the-wild")));
        modifiers = new HashMap<>();
        increaseStatsAbility = new IncreaseStatsAbility(plugin);
    }
}
