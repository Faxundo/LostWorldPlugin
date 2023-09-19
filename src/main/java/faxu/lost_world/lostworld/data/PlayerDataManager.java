package faxu.lost_world.lostworld.data;

import com.j256.ormlite.dao.Dao;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class PlayerDataManager {

    private Dao<PlayerData, String> playerDataDao;

    public PlayerDataManager(Database database) {
        try {
            playerDataDao = database.getPlayerDataDao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PlayerData getPlayerData(Player player) {
        try {
            return playerDataDao.queryForId(player.getUniqueId().toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public PlayerData addPlayer(Player player) {
        PlayerData playerData = new PlayerData();
        playerData.setUuid(player.getUniqueId().toString());
        try {
            playerDataDao.create(playerData);
            return playerData;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean playerExists(Player player) {
        try {
            return playerDataDao.idExists(player.getUniqueId().toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void setStat(Player player, String stat, Integer amount) {
        PlayerData playerData = getPlayerData(player);
        playerData.setStat(stat, amount);
        try {
            playerDataDao.update(playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
