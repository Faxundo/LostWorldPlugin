package faxu.lost_world.lostworld.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.bukkit.entity.Player;

import java.util.HashMap;

@DatabaseTable(tableName = "player_data")
public class PlayerData {

    @DatabaseField(id = true)
    private String uuid;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int constitution;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int defense;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int strength;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int dextery;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int intelligence;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int wisdom;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int luck;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int charisma;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int willpower;

    public PlayerData() {
    }

    public PlayerData(Player player) {
        uuid = player.getUniqueId().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDextery() {
        return dextery;
    }

    public void setDextery(int dextery) {
        this.dextery = dextery;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public HashMap<String, Integer> getStats () {
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put("constitution", this.constitution);
        stats.put("defense", this.defense);
        stats.put("strength", this.strength);
        stats.put("dextery", this.dextery);
        stats.put("intelligence", this.intelligence);
        stats.put("wisdom", this.wisdom);
        stats.put("luck", this.luck);
        stats.put("charisma", this.charisma);
        return stats;
    }

    public void setStat(String stat, Integer amount) {
        switch (stat.toLowerCase()) {
            case "constitution":
                this.constitution = amount;
                break;
            case "defense":
                this.defense = amount;
                break;
            case "strength":
                this.strength = amount;
                break;
            case "dextery":
                this.dextery = amount;
                break;
            case "intelligence":
                this.intelligence = amount;
                break;
            case "wisdom":
                this.wisdom = amount;
                break;
            case "luck":
                this.luck = amount;
                break;
            case "charisma":
                this.charisma = amount;
                break;
            default:
                System.out.println("Invalid stat name: " + stat);
                break;
        }
    }


    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }
}
