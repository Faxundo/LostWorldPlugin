package faxu.lost_world.lostworld.stats;

public enum Stats {

    CONSTITUTION("Constitution"),
    DEFENSE("Defense"),
    STRENGTH("Strength"),
    DEXTERITY("Dexterity"),
    INTELLIGENCE("Intelligence"),
    WISDOM("Wisdom"),
    LUCK("Luck"),
    CHARISMA("Charisma");

    private final String name;

    Stats(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
