package faxu.lost_world.lostworld.data;

import com.j256.ormlite.dao.Dao;
import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.races.Races;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PlayerDataManager {

    private LostWorld plugin;
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
        return null;
    }

    public void addPlayer(Player player) {
        PlayerData playerData = new PlayerData();
        playerData.setUuid(player.getUniqueId().toString());
        playerData.setRace(plugin.getRaceDataManager().getRaceByName(Races.SOUL.getName()));
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

        for (Map.Entry<String, Integer> entry : raceStats.entrySet()) {
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

        if (raceStats != null) {
            playerData.setRace(race);
            for (Map.Entry<String, Integer> entry : raceStats.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                playerData.setStat(key, value);
            }
        }
        try {
            playerDataDao.update(playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
