package faxu.lost_world.lostworld.races;

import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.races.abilities.Abilities;
import faxu.lost_world.lostworld.stats.Stats;

import java.util.HashMap;

public class HumanRace extends RaceData {

    public HumanRace() {
        name = Races.HUMAN.name();
        passiveAbility = Abilities.HARDWORK.name();
        defaultStats = new HashMap<>();
        defaultStats.put(Stats.CONSTITUTION.name().toLowerCase(), 1);
        defaultStats.put(Stats.DEFENSE.name().toLowerCase(), 1);
        defaultStats.put(Stats.STRENGTH.name().toLowerCase(), 1);
        defaultStats.put(Stats.DEXTERY.name().toLowerCase(), 1);
        defaultStats.put(Stats.INTELLIGENCE.name().toLowerCase(), 1);
        defaultStats.put(Stats.WISDOM.name().toLowerCase(), 1);
        defaultStats.put(Stats.LUCK.name().toLowerCase(), 1);
        defaultStats.put(Stats.CHARISMA.name().toLowerCase(), 3);
    }
}
