package faxu.lost_world.lostworld.data.race;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.Database;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.race.SoulDefault;
import faxu.lost_world.lostworld.race.fantasyclassic.DwarfRace;
import faxu.lost_world.lostworld.race.fantasyclassic.ElfRace;
import faxu.lost_world.lostworld.race.fantasyclassic.HumanRace;
import faxu.lost_world.lostworld.race.fantasyclassic.OrcRace;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class RaceDataManager {

    private Dao<RaceData, String> raceDataDao;
    private final LostWorld plugin;
    private final SoulDefault soulDefault;

    public RaceDataManager(LostWorld plugin, Database database) {
        this.plugin = plugin;
        soulDefault = new SoulDefault();

        try {
            raceDataDao = database.getRaceDataDao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createRaces() throws SQLException {
        RaceData[] races = {soulDefault, new HumanRace(), new OrcRace(), new ElfRace(), new DwarfRace()};

        for (RaceData race : races) {
            if (!raceExists(String.valueOf(race.getId()))) {
                try {
                    raceDataDao.create(race);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public RaceData getRaceByName(String name) {
        try {
            QueryBuilder<RaceData, String> queryBuilder = raceDataDao.queryBuilder();
            Where<RaceData, String> where = queryBuilder.where();
            where.eq("name", name);
            PreparedQuery<RaceData> preparedQuery = queryBuilder.prepare();
            return raceDataDao.queryForFirst(preparedQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return soulDefault;
        }
    }

    public boolean raceExists(String raceData) {
        try {
            return raceDataDao.idExists(raceData);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isRace(Player player, String race) {
        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
        RaceData raceData = playerData.getRace();
        return raceData.getName().equals(race);
    }
}
