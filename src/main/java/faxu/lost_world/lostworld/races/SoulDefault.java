package faxu.lost_world.lostworld.races;

import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.stats.Stats;

import java.util.HashMap;

public class SoulDefault extends RaceData {

    public SoulDefault() {
        name = Races.SOUL.name();
        passiveAbility = "None";
        defaultStats = new HashMap<>();
        defaultStats.put(Stats.CONSTITUTION.name().toLowerCase(), 0);
        defaultStats.put(Stats.DEFENSE.name().toLowerCase(), 0);
        defaultStats.put(Stats.STRENGTH.name().toLowerCase(), 0);
        defaultStats.put(Stats.DEXTERY.name().toLowerCase(), 0);
        defaultStats.put(Stats.INTELLIGENCE.name().toLowerCase(), 0);
        defaultStats.put(Stats.WISDOM.name().toLowerCase(), 0);
        defaultStats.put(Stats.LUCK.name().toLowerCase(), 0);
        defaultStats.put(Stats.CHARISMA.name().toLowerCase(), 0);
    }
}