package faxu.lost_world.lostworld.race;

import faxu.lost_world.lostworld.data.race.RaceData;
import faxu.lost_world.lostworld.stat.Stats;

import java.util.HashMap;

public class SoulDefault extends RaceData {

    public SoulDefault() {
        setId(Races.SOUL.getId());
        setName(Races.SOUL.getName());
        defaultStats = new HashMap<>();
        defaultStats.put(Stats.CONSTITUTION.getName(), 0);
        defaultStats.put(Stats.DEFENSE.getName(), 0);
        defaultStats.put(Stats.STRENGTH.getName(), 0);
        defaultStats.put(Stats.DEXTERITY.getName(), 0);
        defaultStats.put(Stats.INTELLIGENCE.getName(), 0);
        defaultStats.put(Stats.WISDOM.getName(), 0);
        defaultStats.put(Stats.LUCK.getName(), 0);
        defaultStats.put(Stats.CHARISMA.getName(), 0);
    }
}
