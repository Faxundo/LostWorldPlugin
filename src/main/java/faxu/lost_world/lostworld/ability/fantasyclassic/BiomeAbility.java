package faxu.lost_world.lostworld.ability.fantasyclassic;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.race.RaceDataManager;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class BiomeAbility extends AbilityHandler{

    private final PlayerDataManager playerDataManager;
    private final RaceDataManager raceDataManager;
    private final FileConfiguration config;
    private NamespacedKey hasEffect;
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownTime = 3000;

    //Add a cooldown to avoid multiple updates at a single time

    public BiomeAbility(LostWorld plugin) {
        super(plugin);
        playerDataManager = plugin.getPlayerDataManager();
        raceDataManager = plugin.getRaceDataManager();
        config = plugin.getConfig();
        hasEffect = new NamespacedKey(plugin, "InitialAbility");
    }

    public void applyEffect(PlayerMoveEvent event, String race, String abilityName, Map<String, Integer> racialModifier, String enter, String left) {
        setRacialModifiers(abilityName, racialModifier);
        Player player = event.getPlayer();

        if (cooldowns.containsKey(player.getUniqueId())) {
            long secondsLeft = (cooldowns.get(player.getUniqueId()) + cooldownTime - System.currentTimeMillis()) / 1000;
            if (secondsLeft > 0) {
                return;
            }
        }

        //Verify gamemode of player and his race
        if (player.getGameMode().equals(GameMode.SURVIVAL) && raceDataManager.isRace(player, race)) {
            PersistentDataContainer data = player.getPersistentDataContainer();
            if (!data.has(hasEffect, PersistentDataType.BOOLEAN)) {
                data.set(hasEffect, PersistentDataType.BOOLEAN, false);
            }
            boolean dataBoolean = data.getOrDefault(hasEffect, PersistentDataType.BOOLEAN, false);

            Location from = event.getFrom();
            Location to = event.getTo();
            boolean inBiomeFrom = inBiome(from, race);
            boolean inBiomeTo = inBiome(to, race);

            String path = "messages.abilities.";
            if (inBiomeFrom && !inBiomeTo) {
                leftBiome(player, racialModifier, config.getString(path + left));
                data.set(hasEffect, PersistentDataType.BOOLEAN, false);

            } else if (inBiomeFrom && inBiomeTo && !dataBoolean) {
                enterBiome(player, racialModifier, config.getString(path + enter));
                data.set(hasEffect, PersistentDataType.BOOLEAN, true);
            }
        }
    }

    //Add the modifiers
    public void enterBiome(Player player, Map<String, Integer> modifier, String message) {
        List<String> keys = new ArrayList<>(modifier.keySet());

        for (String stat : keys) {
            int amount = modifier.get(stat);
            playerDataManager.updateStat(player, stat, "add", amount);
        }
        //Player need know when modifier is active.
        player.sendMessage(Common.colorize(message));

    }

    //Remove the modifiers
    public void leftBiome(Player player, Map<String, Integer> modifier, String message) {
        List<String> keys = new ArrayList<>(modifier.keySet());

        for (String stat : keys) {
            int amount = modifier.get(stat);
            playerDataManager.updateStat(player, stat, "sub", amount);
        }
        //Player need know when modifier is deleted.
        player.sendMessage(Common.colorize(message));
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
}
