package faxu.lost_world.lostworld.data.ability;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.ability.fantasyclassic.initialability.*;
import faxu.lost_world.lostworld.data.Database;

import java.sql.SQLException;

public class AbilityDataManager {

    private final LostWorld plugin;
    private Dao<AbilityData, String> abilityDataDao;

    public AbilityDataManager(LostWorld plugin, Database database) {
        this.plugin = plugin;
        try {
            abilityDataDao = database.getAbilityData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createAbilities() throws SQLException {
        AbilityData[] abilities = {
                new None(),
                new CivilizationWalker(plugin),
                new PathOfTheReaver(plugin),
                new ForestDweller(plugin),
                new CaveAndCliffsLife(plugin)};
        for (AbilityData ability : abilities) {
            if (!abilityExists(String.valueOf(ability.getId()))) {
                try {
                    abilityDataDao.create(ability);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public AbilityData getAbilityByName(String name) {
        try {
            QueryBuilder<AbilityData, String> queryBuilder = abilityDataDao.queryBuilder();
            Where<AbilityData, String> where = queryBuilder.where();
            where.eq("name", name);
            PreparedQuery<AbilityData> preparedQuery = queryBuilder.prepare();
            return abilityDataDao.queryForFirst(preparedQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new None();
        }
    }

    public boolean abilityExists(String abilityData) {
        try {
            return abilityDataDao.idExists(abilityData);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
