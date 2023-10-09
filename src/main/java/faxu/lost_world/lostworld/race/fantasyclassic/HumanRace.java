package faxu.lost_world.lostworld.race.fantasyclassic;

import faxu.lost_world.lostworld.data.race.RaceData;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.stat.Stats;

import java.util.HashMap;

public class HumanRace extends RaceData {

    public HumanRace() {
        setId(Races.HUMAN.getId());
        setName(Races.HUMAN.getName());
        defaultStats = new HashMap<>();
        defaultStats.put(Stats.CONSTITUTION.getName(), 1);
        defaultStats.put(Stats.DEFENSE.getName(), 1);
        defaultStats.put(Stats.STRENGTH.getName(), 1);
        defaultStats.put(Stats.DEXTERITY.getName(), 1);
        defaultStats.put(Stats.INTELLIGENCE.getName(), 1);
        defaultStats.put(Stats.WISDOM.getName(), 1);
        defaultStats.put(Stats.LUCK.getName(), 3);
        defaultStats.put(Stats.CHARISMA.getName(), 1);
    }
}
