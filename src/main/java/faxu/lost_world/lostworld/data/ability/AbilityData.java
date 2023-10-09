package faxu.lost_world.lostworld.data.ability;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import faxu.lost_world.lostworld.data.race.RaceData;

@DatabaseTable(tableName = "ability")
public class AbilityData {

    @DatabaseField(id = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(foreign = true, foreignColumnName = "id", foreignAutoRefresh = true)
    private RaceData origin;

    private String display_name;
    private String description;


    public AbilityData() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RaceData getOrigin() {
        return origin;
    }

    public void setOrigin(RaceData origin) {
        this.origin = origin;
    }

    public String getDisplayName () {
        return display_name;
    }

    public void setDisplayName (String display_name) {
        this.display_name = display_name;
    }
}
