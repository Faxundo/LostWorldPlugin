package faxu.lost_world.lostworld.races.abilities;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.races.RaceDataManager;
import faxu.lost_world.lostworld.races.Races;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeAbilityHandler {

    private final LostWorld plugin;
    private final PlayerDataManager playerDataManager;
    private final RaceDataManager raceDataManager;
    private final FileConfiguration config;

    public BiomeAbilityHandler(LostWorld plugin) {
        this.plugin = plugin;
        playerDataManager = plugin.getPlayerDataManager();
        raceDataManager = plugin.getRaceDataManager();
        config = plugin.getConfig();
    }

    public void applyEffect(PlayerMoveEvent event, String race, String abilityName, Map<String, Integer> racialModifier, NamespacedKey nameKey, String enter, String left) {
        setRacialModifiers(abilityName, racialModifier);
        Player player = event.getPlayer();

        //Verify gamemode of player and his race
        if (player.getGameMode().equals(GameMode.SURVIVAL) && raceDataManager.isRace(player, race)) {
            PersistentDataContainer data = player.getPersistentDataContainer();
            if (!data.has(nameKey, PersistentDataType.BOOLEAN)) {
                data.set(nameKey, PersistentDataType.BOOLEAN, false);
            }
            boolean dataBoolean = data.getOrDefault(nameKey, PersistentDataType.BOOLEAN, false);

            Location from = event.getFrom();
            Location to = event.getTo();
            boolean inBiomeFrom = inBiome(from, race);
            boolean inBiomeTo = inBiome(to, race);

            String path = "messages.abilities.";
            if (inBiomeFrom && !inBiomeTo) {
                leftBiome(player, racialModifier, config.getString(path + left));
                data.set(nameKey, PersistentDataType.BOOLEAN, false);

            } else if (inBiomeFrom && inBiomeTo && !dataBoolean) {
                enterBiome(player, racialModifier, config.getString(path + enter));
                data.set(nameKey, PersistentDataType.BOOLEAN, true);
            }
        }
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

    //Add the modifiers
    public void enterBiome(Player player, Map<String, Integer> modifier, String message) {
        List<String> keys = new ArrayList<>(modifier.keySet());

        for (String stat : keys) {
            int amount = modifier.get(stat);
            playerDataManager.updateStat(player, stat, "add", amount);
        }
        //Player need know when modifier is active.
        player.sendMessage(ChatColor.GREEN + message);

    }

    //Remove the modifiers
    public void leftBiome(Player player, Map<String, Integer> modifier, String message) {
        List<String> keys = new ArrayList<>(modifier.keySet());

        for (String stat : keys) {
            int amount = modifier.get(stat);
            playerDataManager.updateStat(player, stat, "sub", amount);
        }
        //Player need know when modifier is deleted.
        player.sendMessage(ChatColor.GREEN + message);
    }

    public boolean inBiome(Location location, String race) {
        Biome biome = location.getWorld().getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return getBiomes(race).contains(biome);
    }

    //Biomes where apply the ability
    public ArrayList<Biome> getBiomes(String race) {
        ArrayList<Biome> biomes = new ArrayList<>();
        if (race.equals(Races.HUMAN.getName())) {
            biomes.add(Biome.DESERT);
            biomes.add(Biome.PLAINS);
            biomes.add(Biome.MEADOW);
            biomes.add(Biome.SAVANNA);
            biomes.add(Biome.SNOWY_PLAINS);
            biomes.add(Biome.TAIGA);
            biomes.add(Biome.SNOWY_TAIGA);
        } else if (race.equals(Races.ELF.getName())) {
            biomes.add(Biome.FOREST);
            biomes.add(Biome.BIRCH_FOREST);
            biomes.add(Biome.DARK_FOREST);
            biomes.add(Biome.TAIGA);
            biomes.add(Biome.JUNGLE);
            biomes.add(Biome.OLD_GROWTH_BIRCH_FOREST);
            biomes.add(Biome.OLD_GROWTH_SPRUCE_TAIGA);
        } else if (race.equals(Races.ORC.getName())) {
            biomes.add(Biome.DESERT);
            biomes.add(Biome.BADLANDS);
            biomes.add(Biome.PLAINS);
            biomes.add(Biome.SAVANNA);
            biomes.add(Biome.SAVANNA_PLATEAU);
            biomes.add(Biome.ERODED_BADLANDS);
            biomes.add(Biome.WOODED_BADLANDS);
        } else if (race.equals(Races.DWARF.getName())) {
            biomes.add(Biome.JAGGED_PEAKS);
            biomes.add(Biome.SNOWY_SLOPES);
            biomes.add(Biome.STONY_PEAKS);
            biomes.add(Biome.FROZEN_PEAKS);
            biomes.add(Biome.GROVE);
            biomes.add(Biome.DRIPSTONE_CAVES);
            biomes.add(Biome.LUSH_CAVES);
        }
        return biomes;
    }

    public void setRacialModifiers(String abilityName, Map<String, Integer> racialModifier) {
        Map<String, Integer> racialModifiers = getRacialModifiers(abilityName);
        racialModifier.putAll(racialModifiers);
    }
}
