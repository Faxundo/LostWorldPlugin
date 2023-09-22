package faxu.lost_world.lostworld.races.abilities;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.races.Races;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class CivilizationWalker implements Listener {

    private final LostWorld plugin;
    FileConfiguration config;
    private Map<String, Integer> modifiers;
    private NamespacedKey hasEffect;
    private final BiomeAbilityHandler biomeAbilityHandler;
    private final String abilityName = "Civilization-Walker";

    public CivilizationWalker(LostWorld plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        modifiers = new HashMap<>();
        hasEffect = new NamespacedKey(plugin, "CivilizationWalker");
        biomeAbilityHandler = new BiomeAbilityHandler(plugin);
    }

    @EventHandler
    public void onMoveListener(PlayerMoveEvent event) {
        biomeAbilityHandler.setRacialModifiers(abilityName, modifiers);
        biomeAbilityHandler.applyEffect(event, Races.HUMAN.getName(), abilityName,
                modifiers, hasEffect, "civilization-enter","civilization-left");
    }
}
