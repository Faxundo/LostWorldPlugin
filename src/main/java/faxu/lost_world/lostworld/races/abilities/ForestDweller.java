package faxu.lost_world.lostworld.races.abilities;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.races.Races;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForestDweller implements Listener {

    private final LostWorld plugin;
    private final FileConfiguration config;
    private final PlayerDataManager playerDataManager;
    private HashMap<String, Integer> forestDwellerModifier;
    private NamespacedKey hasForestDweller;

    public ForestDweller(LostWorld plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        playerDataManager = plugin.getPlayerDataManager();
        forestDwellerModifier = new HashMap<>();
        hasForestDweller = new NamespacedKey(plugin, "ForestDweller");
    }

    @EventHandler
    public void onMoveListener(PlayerMoveEvent event) {
        forestDwellerEffect(event);
    }

    //Identify biome of player and gave the effect of ability
    public void forestDwellerEffect(PlayerMoveEvent event) {
        setForestDwellerModifier();

        Player player = event.getPlayer();
        PersistentDataContainer data = player.getPersistentDataContainer();
        //Verify if gamemode of player is Survival and be in overworld
        if (player.getWorld().getEnvironment().equals(World.Environment.NORMAL)
                && player.getGameMode().equals(GameMode.SURVIVAL)) {
            PlayerData playerData = playerDataManager.getPlayerData(player);
            RaceData raceData = playerData.getRace();
            //Verify if player is Elf
            if (raceData.getName().equals(Races.ELF.name())) {



                if (!data.has(hasForestDweller, PersistentDataType.BOOLEAN)) {
                    data.set(hasForestDweller, PersistentDataType.BOOLEAN, false);
                }
                boolean databoolean = data.getOrDefault(hasForestDweller, PersistentDataType.BOOLEAN, false);

                Location from = event.getFrom();
                Location to = event.getTo();
                boolean inForestFrom = inForest(from);
                boolean inForestTo = inForest(to);

                if (inForestFrom && !inForestTo) {
                    leftForest(player);
                    data.set(hasForestDweller, PersistentDataType.BOOLEAN, false);

                } else if (inForestFrom && inForestTo && !databoolean) {
                    enterForest(player);
                    data.set(hasForestDweller, PersistentDataType.BOOLEAN, true);
                }
            }
        }
    }


    //Identify if player is in forest biome
    public boolean inForest(Location location) {
        Biome biome = location.getWorld().getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return forestBiomes().contains(biome);
    }

    //Add the modifiers
    public void enterForest(Player player) {
        List<String> keys = new ArrayList<>(forestDwellerModifier.keySet());

        for (String stat : keys) {
            int amount = forestDwellerModifier.get(stat);
            playerDataManager.updateStat(player, stat, "add", amount);
        }
        //Player need know when modifier is active.
        player.sendMessage(ChatColor.GREEN + config.getString("messages.abilities.forest-enter"));

    }

    //Remove the modifiers
    public void leftForest(Player player) {
        List<String> keys = new ArrayList<>(forestDwellerModifier.keySet());

        for (String stat : keys) {
            int amount = forestDwellerModifier.get(stat);
            playerDataManager.updateStat(player, stat, "sub", amount);
        }
        //Player need know when modifier is deleted.
        player.sendMessage(ChatColor.GREEN + config.getString("messages.abilities.forest-left"));
    }

    public void setForestDwellerModifier() {
        forestDwellerModifier.put("dextery", config.getInt("racial-abilities.forest-dweller.dextery"));
        forestDwellerModifier.put("intelligence", config.getInt("racial-abilities.forest-dweller.intelligence"));
        forestDwellerModifier.put("wisdom", config.getInt("racial-abilities.forest-dweller.wisdom"));
    }

    //Biomes where apply the ability
    public ArrayList<Biome> forestBiomes() {
        ArrayList<Biome> biomes = new ArrayList<>();
        biomes.add(Biome.FOREST);
        biomes.add(Biome.BIRCH_FOREST);
        biomes.add(Biome.DARK_FOREST);
        biomes.add(Biome.TAIGA);
        biomes.add(Biome.JUNGLE);
        biomes.add(Biome.BAMBOO_JUNGLE);
        biomes.add(Biome.OLD_GROWTH_BIRCH_FOREST);
        biomes.add(Biome.OLD_GROWTH_SPRUCE_TAIGA);
        biomes.add(Biome.SNOWY_TAIGA);
        biomes.add(Biome.WINDSWEPT_FOREST);
        return biomes;
    }
}
