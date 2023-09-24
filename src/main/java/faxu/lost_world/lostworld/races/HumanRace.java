package faxu.lost_world.lostworld.races;

import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.races.abilities.Abilities;
import faxu.lost_world.lostworld.stats.Stats;

import java.util.HashMap;

public class HumanRace extends RaceData {

    public HumanRace() {
        name = Races.HUMAN.getName();
        passiveAbility = Abilities.CIVILIZATION_WALKER.getName();
        defaultStats = new HashMap<>();
        defaultStats.put(Stats.CONSTITUTION.name().toLowerCase(), 1);
        defaultStats.put(Stats.DEFENSE.name().toLowerCase(), 1);
        defaultStats.put(Stats.STRENGTH.name().toLowerCase(), 1);
        defaultStats.put(Stats.DEXTERITY.name().toLowerCase(), 1);
        defaultStats.put(Stats.INTELLIGENCE.name().toLowerCase(), 1);
        defaultStats.put(Stats.WISDOM.name().toLowerCase(), 1);
        defaultStats.put(Stats.LUCK.name().toLowerCase(), 3);
        defaultStats.put(Stats.CHARISMA.name().toLowerCase(), 1);
    }
}
