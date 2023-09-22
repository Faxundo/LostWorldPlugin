package faxu.lost_world.lostworld.data.races;

import com.j256.ormlite.dao.Dao;
import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.Database;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.races.*;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class RaceDataManager {

    private Dao<RaceData, String> raceDataDao;
    private final LostWorld plugin;
    private final SoulDefault soulDefault;
    private final HumanRace humanRace;
    private final OrcRace orcRace;
    private final ElfRace elfRace;
    private final DwarfRace dwarfRace;


    public RaceDataManager(LostWorld plugin, Database database) {
        this.plugin = plugin;
        soulDefault = new SoulDefault();
        humanRace = new HumanRace();
        orcRace = new OrcRace();
        elfRace = new ElfRace();
        dwarfRace = new DwarfRace();

        try {
            raceDataDao = database.getRaceDataDao();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createRaces() throws SQLException {
        boolean block = true;
        for (int i = 0; i < listRaces().size(); i++) {
            if (!raceExist(listRaces().get(i).toString())) {
                block = false;
            }
        }
        if (block) {
            addRace(soulDefault);
            addRace(humanRace);
            addRace(orcRace);
            addRace(elfRace);
            addRace(dwarfRace);
        }
    }

    public void addRace(RaceData raceData) {
        try {
            raceDataDao.create(raceData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public RaceData getRaceByName(String name) {
        for (RaceData race : Arrays.asList(humanRace, orcRace, elfRace, dwarfRace)) {
            if (race.getName().equalsIgnoreCase(name)) {
                return race;
            }
        }
        return soulDefault;
    }

    public boolean raceExist(String raceData) {
        try {
            return raceDataDao.idExists(raceData);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public List listRaces() throws SQLException {
        return raceDataDao.queryForAll();
    }

    public boolean isRace (Player player, String race) {
        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
        RaceData raceData = playerData.getRace();
        return raceData.getName().equals(race);
    }
}
