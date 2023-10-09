package faxu.lost_world.lostworld.ability.fantasyclassic.initialability;

import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.ability.AbilityData;
import faxu.lost_world.lostworld.race.SoulDefault;
import faxu.lost_world.lostworld.util.Common;

public class None extends AbilityData {

    public None() {
        setId(InitialAbilities.NONE.getId());
        setName(InitialAbilities.NONE.getName());
        setOrigin(new SoulDefault());
        setDisplayName(Common.colorize(LangConfig.get().getString("name.none")));
        setDescription(Common.colorize(LangConfig.get().getString("description.none")));
    }
}
