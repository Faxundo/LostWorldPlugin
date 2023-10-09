package faxu.lost_world.lostworld;

import faxu.lost_world.lostworld.ability.fantasyclassic.initialability.CaveAndCliffsLife;
import faxu.lost_world.lostworld.ability.fantasyclassic.initialability.CivilizationWalker;
import faxu.lost_world.lostworld.ability.fantasyclassic.initialability.ForestDweller;
import faxu.lost_world.lostworld.ability.fantasyclassic.initialability.PathOfTheReaver;
import faxu.lost_world.lostworld.command.CommandManager;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.Database;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.ability.AbilityDataManager;
import faxu.lost_world.lostworld.data.race.RaceDataManager;
import faxu.lost_world.lostworld.event.CustomItemListener;
import faxu.lost_world.lostworld.event.MenuListener;
import faxu.lost_world.lostworld.event.PlayerDamageListener;
import faxu.lost_world.lostworld.event.PlayerJoinListener;
import faxu.lost_world.lostworld.item.CustomItem;
import faxu.lost_world.lostworld.item.racialitem.AxeOfWar;
import faxu.lost_world.lostworld.item.racialitem.BeerOfGreed;
import faxu.lost_world.lostworld.item.racialitem.BranchOfLife;
import faxu.lost_world.lostworld.item.racialitem.LuckyCoin;
import faxu.lost_world.lostworld.menusystem.PlayerMenuUtility;
import faxu.lost_world.lostworld.stat.Constitution;
import faxu.lost_world.lostworld.stat.Defense;
import faxu.lost_world.lostworld.stat.Luck;
import faxu.lost_world.lostworld.stat.Wisdom;
import faxu.lost_world.lostworld.willpower.Willpower;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class LostWorld extends JavaPlugin {

    private final PluginManager plugin = Bukkit.getServer().getPluginManager();

    private Database database;
    private PlayerDataManager playerDataManager;
    private RaceDataManager raceDataManager;
    private AbilityDataManager abilityDataManager;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    private final Willpower willpower = new Willpower(this);

    public static NamespacedKey lostItemKey;
    public static Map<String, CustomItem> customItemMap;

    @Override
    public void onEnable() {

        //Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        LangConfig.setDefault();
        LangConfig.get().options().copyDefaults(true);
        LangConfig.save();

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

            //Ability Data
            abilityDataManager = new AbilityDataManager(this, database);
            abilityDataManager.createAbilities();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect to the database" + ex.getMessage());
            plugin.disablePlugin(this);
        }

        //Events
        plugin.registerEvents(new MenuListener(), this);
        plugin.registerEvents(new CustomItemListener(this), this);
        plugin.registerEvents(new PlayerJoinListener(this), this);
        plugin.registerEvents(new PlayerDamageListener(this), this);

        //Stats
        plugin.registerEvents(new Constitution(this), this);
        plugin.registerEvents(new Defense(this), this);
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

        //Items
        lostItemKey = new NamespacedKey(this, "lost_item");
        customItemMap = new HashMap<>();
        registerItems();

        //Process
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        if (database != null) {
            database.closeConnection();
        }
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

    public AbilityDataManager getAbilityDataManager() {
        return this.abilityDataManager;
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

    private void registerItems() {
        CustomItem[] items = {
                new LuckyCoin(),
                new AxeOfWar(),
                new BranchOfLife(),
                new BeerOfGreed()
        };

        for (CustomItem item : items) {
            customItemMap.put(item.getId(), item);
        }
    }
}
