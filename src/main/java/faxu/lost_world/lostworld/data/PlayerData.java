package faxu.lost_world.lostworld.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import faxu.lost_world.lostworld.data.races.RaceData;
import faxu.lost_world.lostworld.stats.Stats;

import java.util.HashMap;

@DatabaseTable(tableName = "player")
public class PlayerData {

    @DatabaseField(id = true)
    private String uuid;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int constitution;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int defense;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int strength;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int dexterity;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int intelligence;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int wisdom;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int luck;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private int charisma;
    @DatabaseField(foreign = true, foreignColumnName = "id", foreignAutoRefresh = true)
    private RaceData race;
    @DatabaseField(canBeNull = false, defaultValue = "1")
    private int raceLevel;
    private HashMap<String, Integer> statsModifiers;


    public PlayerData() {
        statsModifiers = new HashMap<>();
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

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
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

    public RaceData getRace() {
        return race;
    }

    public void setRace(RaceData race) {
        this.race = race;
    }

    public HashMap<String, Integer> getstatsModifiers () {
        return statsModifiers;
    }

    public void setStatsModifiers (HashMap<String, Integer> statsModifiers) {
        this.statsModifiers = statsModifiers;
    }

    public int getRaceLevel () {
        return raceLevel;
    }

    public void setRaceLevel (int raceLevel) {
        this.raceLevel = raceLevel;
    }

    public HashMap<String, Integer> getStats() {
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put(Stats.CONSTITUTION.name().toLowerCase(), this.constitution);
        stats.put(Stats.DEFENSE.name().toLowerCase(), this.defense);
        stats.put(Stats.STRENGTH.name().toLowerCase(), this.strength);
        stats.put(Stats.DEXTERITY.name().toLowerCase(), this.dexterity);
        stats.put(Stats.INTELLIGENCE.name().toLowerCase(), this.intelligence);
        stats.put(Stats.WISDOM.name().toLowerCase(), this.wisdom);
        stats.put(Stats.LUCK.name().toLowerCase(), this.luck);
        stats.put(Stats.CHARISMA.name().toLowerCase(), this.charisma);
        return stats;
    }

    public int getStat(String stat) {
        switch (stat.toLowerCase()) {
            case "constitution":
                return this.constitution;
            case "defense":
                return this.defense;
            case "strength":
                return this.strength;
            case "dexterity":
                return this.dexterity;
            case "intelligence":
                return this.intelligence;
            case "wisdom":
                return this.wisdom;
            case "luck":
                return this.luck;
            case "charisma":
                return this.charisma;
            default:
                return 0;
        }
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
            case "dexterity":
                this.dexterity = amount;
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
}
