package faxu.lost_world.lostworld.races;

import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.races.abilities.Abilities;
import faxu.lost_world.lostworld.stats.Stats;

import java.util.HashMap;

public class OrcRace extends RaceData {

    public OrcRace() {
        name = Races.ORC.getName();
        passiveAbility = Abilities.PATH_OF_THE_REAVER.getName();
        defaultStats = new HashMap<>();
        defaultStats.put(Stats.CONSTITUTION.name().toLowerCase(), 4);
        defaultStats.put(Stats.DEFENSE.name().toLowerCase(), 2);
        defaultStats.put(Stats.STRENGTH.name().toLowerCase(), 3);
        defaultStats.put(Stats.DEXTERITY.name().toLowerCase(), 0);
        defaultStats.put(Stats.INTELLIGENCE.name().toLowerCase(), 1);
        defaultStats.put(Stats.WISDOM.name().toLowerCase(), 0);
        defaultStats.put(Stats.LUCK.name().toLowerCase(), 0);
        defaultStats.put(Stats.CHARISMA.name().toLowerCase(), 0);
    }
}
