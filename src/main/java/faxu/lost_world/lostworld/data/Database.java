package faxu.lost_world.lostworld.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database {

    private final Dao<PlayerData, String> playerDataDao;
    private final ConnectionSource connectionSource;

    public Database(String path) throws SQLException {
        connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + path);
        TableUtils.createTableIfNotExists(connectionSource, PlayerData.class);
        playerDataDao = DaoManager.createDao(connectionSource, PlayerData.class);
    }

    public Dao<PlayerData, String> getPlayerDataDao() throws SQLException {
        return playerDataDao;
    }
}
