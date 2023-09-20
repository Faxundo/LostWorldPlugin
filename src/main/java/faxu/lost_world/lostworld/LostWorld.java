package faxu.lost_world.lostworld;

import faxu.lost_world.lostworld.commands.Commands;
import faxu.lost_world.lostworld.data.Database;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.races.RaceDataManager;
import faxu.lost_world.lostworld.events.*;
import faxu.lost_world.lostworld.util.DelayedTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class LostWorld extends JavaPlugin {

    private final PluginManager plugin = Bukkit.getServer().getPluginManager();
    private Database database;
    private PlayerDataManager playerDataManager;
    private RaceDataManager raceDataManager;

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
        plugin.registerEvents(new PlayerJoinListener(this), this);
        plugin.registerEvents(new PlayerDamageListener(this), this);
        plugin.registerEvents(new ExpListener(this), this);
        plugin.registerEvents(new InventoryClickListener(this), this);
        plugin.registerEvents(new BlockBreakListener(this), this);

        //Commands
        getCommand("lostworld").setExecutor(new Commands(this));

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

    public PlayerDataManager getPlayerDataManager () {
        return this.playerDataManager;
    }

    public RaceDataManager getRaceDataManager () {
        return this.raceDataManager;
    }

}
