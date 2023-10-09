package faxu.lost_world.lostworld.config.files;

import faxu.lost_world.lostworld.ability.fantasyclassic.initialability.InitialAbilities;
import faxu.lost_world.lostworld.config.ConfigUtil;

public class RaceAbilityConfig extends ConfigUtil {

    public static void setDefault() {
        String[] options = {
                InitialAbilities.NONE.getName() + ":0:0:0:0:0:0:0:0",
                InitialAbilities.CIVILIZATION_WALKER.getName() + ":1:1:1:1:1:1:1:1",
                InitialAbilities.PATH_OF_THE_REAVER.getName() + ":4:2:2:0:0:0:0:0",
                InitialAbilities.FOREST_DWELLER.getName() + ":0:0:0:4:2:2:0",
                InitialAbilities.CAVE_AND_CLIFF_LIFE.getName() + ":2:0:1:0:0:2:3"
        };
        setRaceAbility(options);
    }
}
