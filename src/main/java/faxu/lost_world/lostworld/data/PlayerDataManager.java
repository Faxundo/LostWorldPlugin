package faxu.lost_world.lostworld.data;

import com.j256.ormlite.dao.Dao;
import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.ability.fantasyclassic.initialability.InitialAbilities;
import faxu.lost_world.lostworld.data.race.RaceData;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.stat.Constitution;
import faxu.lost_world.lostworld.stat.Luck;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.HashMap;

public class PlayerDataManager {

    private final LostWorld plugin;
    private Dao<PlayerData, String> playerDataDao;

    public PlayerDataManager(LostWorld plugin, Database database) {
        this.plugin = plugin;
        try {
            playerDataDao = database.getPlayerDataDao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Can be null
    public PlayerData getPlayerData(Player player) {
        try {
            return playerDataDao.queryForId(player.getUniqueId().toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new PlayerData();
    }

    public void addPlayer(Player player) {
        PlayerData playerData = new PlayerData();
        playerData.setUuid(player.getUniqueId().toString());
        playerData.setRace(plugin.getRaceDataManager().getRaceByName(Races.SOUL.getName()));
        playerData.setRaceLevel(1);

        HashMap<String, Integer> initialAbilities = new HashMap<>();
        initialAbilities.put(InitialAbilities.NONE.getName(), 1);
        playerData.setRacialAbilities(initialAbilities);
        try {
            playerDataDao.create(playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean playerExists(Player player) {
        try {
            return playerDataDao.idExists(player.getUniqueId().toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    //Adds to default stat of race + amount arg
    public void setStat(Player player, String stat, Integer amount) {

        PlayerData playerData = getPlayerData(player);

        RaceData raceData = plugin.getRaceDataManager().getRaceByName(playerData.getRace().getName());
        HashMap<String, Integer> raceStats = raceData.getDefaultStats();

        for (HashMap.Entry<String, Integer> entry : raceStats.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (key.equals(stat)) {
                playerData.setStat(key, value + amount);
            }
        }

        try {
            playerDataDao.update(playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Adds amount to selected stat
    public void updateStat(Player player, String stat, String operator, Integer amount) {
        PlayerData playerData = getPlayerData(player);

        int value = playerData.getStat(stat);
        if (operator.equals("add")) {
            playerData.setStat(stat, value + amount);
        } else if (operator.equals("sub")) {
            playerData.setStat(stat, value - amount);
        }

        try {
            playerDataDao.update(playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Restart all stats of Player, because set default stats of race
    public void setRace(Player player, RaceData race) {
        PlayerData playerData = getPlayerData(player);
        HashMap<String, Integer> raceStats = race.getDefaultStats();

        // Deleted all racial abilities
        if (playerData.getRacialAbilities() != null) {
            clearRacialAbilities(player);
        }
        // Set race
        playerData.setRace(race);
        // Set race level in 1
        setRaceLevel(player, 1);
        // Set stats points in 0
        setStatPoint(player, 0);
        // Set default stats
        for (HashMap.Entry<String, Integer> entry : raceStats.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            playerData.setStat(key, value);
        }
        // Refresh stats
        refreshStats(player);

        // Add Initial Abilities
        HashMap<String, Integer> racialAbilities = new HashMap<>();
        if (race.getName().equals(Races.HUMAN.getName())) {
            racialAbilities.put(InitialAbilities.CIVILIZATION_WALKER.getName(), 1);
        } else if (race.getName().equals(Races.ORC.getName())) {
            racialAbilities.put(InitialAbilities.PATH_OF_THE_REAVER.getName(), 1);
        } else if (race.getName().equals(Races.ELF.getName())) {
            racialAbilities.put(InitialAbilities.FOREST_DWELLER.getName(), 1);
        } else if (race.getName().equals(Races.DWARF.getName())) {
            racialAbilities.put(InitialAbilities.CAVE_AND_CLIFF_LIFE.getName(), 1);
        }
        playerData.setRacialAbilities(racialAbilities);


        try {
            playerDataDao.update(playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setRaceLevel(Player player, int amount) {
        PlayerData playerData = getPlayerData(player);

        playerData.setRaceLevel(amount);
        try {
            playerDataDao.update(playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setStatPoint(Player player, int amount) {
        PlayerData playerData = getPlayerData(player);

        playerData.setStatPoints(amount);

        try {
            playerDataDao.update(playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void clearRacialAbilities(Player player) {
        PlayerData playerData = getPlayerData(player);
        playerData.getRacialAbilities().clear();

        try {
            playerDataDao.update(playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Update Stats at same time.
    public void refreshStats(Player player) {
        Constitution.applyConstitution(plugin, player);
        Luck.applyLuck(plugin, player);
    }
}
