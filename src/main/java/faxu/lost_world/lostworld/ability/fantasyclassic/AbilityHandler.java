package faxu.lost_world.lostworld.ability.fantasyclassic;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class AbilityHandler {

    private final LostWorld plugin;
    private final FileConfiguration config;

    public AbilityHandler(LostWorld plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    public Map<String, Integer> getRacialModifiers(String abilityName) {
        Map<String, Integer> modifiers = new HashMap<>();
        String path = "racial-abilities." + abilityName.toLowerCase();
        modifiers.put("constitution", config.getInt(path + ".constitution"));
        modifiers.put("defense", config.getInt(path + ".defense"));
        modifiers.put("strength", config.getInt(path + ".strength"));
        modifiers.put("dexterity", config.getInt(path + ".dexterity"));
        modifiers.put("intelligence", config.getInt(path + ".intelligence"));
        modifiers.put("wisdom", config.getInt(path + ".wisdom"));
        modifiers.put("luck", config.getInt(path + ".luck"));
        modifiers.put("charisma", config.getInt(path + ".charisma"));

        return modifiers;
    }

    public void setRacialModifiers(String abilityName, Map<String, Integer> racialModifier) {
        Map<String, Integer> racialModifiers = getRacialModifiers(abilityName);
        racialModifier.putAll(racialModifiers);
    }
}
