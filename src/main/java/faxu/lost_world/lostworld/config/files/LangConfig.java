package faxu.lost_world.lostworld.config.files;

import faxu.lost_world.lostworld.config.ConfigUtil;

public class LangConfig extends ConfigUtil {

    public static void setDefault () {
        String[] options = {

                //Stats
                "constitution:&dConstitution:&eIncreases your health.",
                "defense:&bDefense:&eReduces the incoming damage.",
                "strength:&cStrength:&eIncreases the melee damage.",
                "dexterity:&2Dexterity:&eIncreases the range damage.",
                "intelligence:&9Intelligence:&eDefine your willpower",
                "wisdom:&5Wisdom:&eIncreases the experience obtained.",
                "luck:&aLuck:&eUseful for fishing, some loot tables of chests and for obtain more loot at mine blocks.",
                "charisma:&6Charisma:&eCharisma",
                "willpower:&3Willpower",

                //Races
                "race:&eRace",
                "race-level:&eRace Level",
                "soul:&6Soul:&eA simple Soul.",
                "human:&6&lHuman:&eA balanced race with a lot of luck.:&6All stats 1\n&aLuck 3",
                "orc:&6&lOrc:&eA conflictive race focused on combat.:&dConstitution 4\n&bDefense 2\n&cStrength 3\n&9Intelligence 1",
                "elf:&6&lElf:&eAn ancient race with a lot of knowledge to share.:&2Dexterity 2\n&9Intelligence 4\n&5Wisdom 4",
                "dwarf:&6&lDwarf:&eA resilient and rather greedy race.:&dConstitution 4\n&cStrength 1\n&5Wisdom 2\n&6Charisma 3",

                //Abilities
                "ability:&eAbility",
                "none:&eNone:&fNone",
                "civilization-walker:&eCivilization Walker:&fWhen you are in biome where village can spawn, you obtain &6+1 &fthat all stats.",
                "path-of-the-reaver:&ePeath of the Reaver:&fWhen you are on a biome abandoned by life, you obtain &d+4 &fConstitution, &c+2 &fStrength and &b+2 &fDefense.",
                "forest-dweller:&eForest Dweller:&fWhen you are in a forest biome, you obtain &2+4 &fDexterity, &9+2 &fIntelligence and &5+2 &fWisdom.",
                "cave-and-cliff-life:&eCave and Cliff Life:&fWhen you are on a mountain or cave biome, you obtain &d+2 &fConstitution, &c+1 &fStrength, &5+2 &fWisdom and &6+3 &fCharisma.",

                "call-of-the-wild:&eCall of the Wild:&fDescription",

                //GUIs
                "inv-profile:&6Profile",
                "inv-leveling-race:&6Race Profile",
                "inv-race-selection:&6Race Selection",
                "inv-confirm:&6Are you sure?",
                "inv-race-ability:&6Race Abiltiy Selection",
                "back:&6Back",
                "next:&6Next",
                "race-information:&aRace Information",
                "level-up:&aLevel up!",
                "stats-points:&aStats Points",
                "change-race:&eChange you race to ",
                "initial-stats:&eInitial stats",
                "yes:&aYes",
                "no:&cNo",

                //Items
                "lucky-coin:&eLucky Coin:&eDrop this item for activate a ability.",
                "axe-war:&eAxe of War:&eDrop this item for activate a ability.",
                "branch-life:&eBranch of Life:&eDrop this item for activate a ability.",
                "beer-greed:&eBeer of Greed:&eDrop this item for activate a ability."
        };

        set(options);
    }
}
