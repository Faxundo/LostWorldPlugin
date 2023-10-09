package faxu.lost_world.lostworld.race.fantasyclassic;

import faxu.lost_world.lostworld.data.race.RaceData;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.stat.Stats;

import java.util.HashMap;

public class DwarfRace extends RaceData {

    public DwarfRace() {
        setId(Races.DWARF.getId());
        setName(Races.DWARF.getName());
        defaultStats = new HashMap<>();
        defaultStats.put(Stats.CONSTITUTION.getName(), 4);
        defaultStats.put(Stats.DEFENSE.getName(), 0);
        defaultStats.put(Stats.STRENGTH.getName(), 1);
        defaultStats.put(Stats.DEXTERITY.getName(), 0);
        defaultStats.put(Stats.INTELLIGENCE.getName(), 0);
        defaultStats.put(Stats.WISDOM.getName(), 2);
        defaultStats.put(Stats.LUCK.getName(), 0);
        defaultStats.put(Stats.CHARISMA.getName(), 3);
    }

}
