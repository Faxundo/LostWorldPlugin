package faxu.lost_world.lostworld.data.race;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashMap;

@DatabaseTable(tableName = "race")
public class RaceData {

    @DatabaseField(id = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField (dataType = DataType.SERIALIZABLE)
    protected HashMap<String, Integer> defaultStats;

    public RaceData() {
        defaultStats = new HashMap<>();
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

    public HashMap<String, Integer> getDefaultStats() {
        return defaultStats;
    }

    public void setDefaultStats(HashMap<String, Integer> defaultStats) {
        this.defaultStats = defaultStats;
    }
}
