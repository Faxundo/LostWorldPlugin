package faxu.lost_world.lostworld.data.races;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashMap;

@DatabaseTable(tableName = "race")
public class RaceData {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    protected String name;
    @DatabaseField(canBeNull = false)
    protected String passiveAbility;
    @DatabaseField (dataType = DataType.SERIALIZABLE)
    protected HashMap<String, Integer> defaultStats;

    public RaceData() {
    }

    public RaceData(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassiveAbility() {
        return passiveAbility;
    }

    public void setPassiveAbility(String passiveAbility) {
        this.passiveAbility = passiveAbility;
    }

    public HashMap<String, Integer> getDefaultStats() {
        return defaultStats;
    }

    public void setDefaultStats(HashMap<String, Integer> defaultStats) {
        this.defaultStats = defaultStats;
    }
}
