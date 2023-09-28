package faxu.lost_world.lostworld;

import faxu.lost_world.lostworld.commands.CommandManager;
import faxu.lost_world.lostworld.data.Database;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.races.RaceDataManager;
import faxu.lost_world.lostworld.events.*;
import faxu.lost_world.lostworld.menusystem.PlayerMenuUtility;
import faxu.lost_world.lostworld.races.abilities.CaveAndCliffsLife;
import faxu.lost_world.lostworld.races.abilities.CivilizationWalker;
import faxu.lost_world.lostworld.races.abilities.ForestDweller;
import faxu.lost_world.lostworld.races.abilities.PathOfTheReaver;
import faxu.lost_world.lostworld.stats.Luck;
import faxu.lost_world.lostworld.stats.Wisdom;
import faxu.lost_world.lostworld.util.DelayedTask;
import faxu.lost_world.lostworld.willpower.Willpower;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;

public final class LostWorld extends JavaPlugin {

    private final PluginManager plugin = Bukkit.getServer().getPluginManager();
    private Database database;
    private PlayerDataManager playerDataManager;
    private RaceDataManager raceDataManager;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    private final Willpower willpower = new Willpower(this);

    @Override
    public void onEnable() {

        //Database
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }

            database = new Database(getDataFolder().getAbsolutePath() + "/lostworlddb.db");
            //Player Data
            playerDataManager = new PlayerDataManager(this, database);

            //Races Data
            raceDataManager = new RaceDataManager(this, database);
            raceDataManager.createRaces();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect to the database" + ex.getMessage());
            plugin.disablePlugin(this);
        }


        //Events
        plugin.registerEvents(new MenuListener(), this);
        plugin.registerEvents(new PlayerJoinListener(this), this);
        plugin.registerEvents(new PlayerDamageListener(this), this);
        plugin.registerEvents(new Wisdom(this), this);
        plugin.registerEvents(new Luck(this), this);
        willpower.willPowerBar();

        //Commands
        getCommand("lostworld").setExecutor(new CommandManager(this));

        //Abilities
        plugin.registerEvents(new ForestDweller(this), this);
        plugin.registerEvents(new CivilizationWalker(this), this);
        plugin.registerEvents(new CaveAndCliffsLife(this), this);
        plugin.registerEvents(new PathOfTheReaver(this), this);

        //Process
        saveDefaultConfig();
        new DelayedTask(this);

    }

    @Override
    public void onDisable() {

    }

    public Database getDatabase() {
        return this.database;
    }

    public PlayerDataManager getPlayerDataManager() {
        return this.playerDataManager;
    }

    public RaceDataManager getRaceDataManager() {
        return this.raceDataManager;
    }

    // Each player only can have one menu at same time
    public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;

        if (playerMenuUtilityMap.containsKey(player)) {
            return playerMenuUtilityMap.get(player);
        } else {
            playerMenuUtility = new PlayerMenuUtility(player);
            playerMenuUtilityMap.put(player, playerMenuUtility);

            return playerMenuUtility;
        }
    }

}
