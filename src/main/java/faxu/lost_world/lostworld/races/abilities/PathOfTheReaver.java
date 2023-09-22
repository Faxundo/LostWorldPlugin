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

public class PathOfTheReaver implements Listener {

    private final LostWorld plugin;
    FileConfiguration config;
    private Map<String, Integer> modifiers;
    private NamespacedKey hasEffect;
    private final BiomeAbilityHandler biomeAbilityHandler;
    private final String abilityName = "Path-Of-The-Reaver";

    public PathOfTheReaver(LostWorld plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        modifiers = new HashMap<>();
        hasEffect = new NamespacedKey(plugin, "PathOfTheReaver");
        biomeAbilityHandler = new BiomeAbilityHandler(plugin);
    }

    @EventHandler
    public void onMoveListener(PlayerMoveEvent event) {
        biomeAbilityHandler.setRacialModifiers(abilityName, modifiers);
        biomeAbilityHandler.applyEffect(event, Races.ORC.getName(), abilityName,
                modifiers, hasEffect, "path-reaver-enter", "path-reaver-left");
    }
}