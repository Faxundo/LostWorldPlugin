package faxu.lost_world.lostworld.ability.fantasyclassic;

public enum StatsAbilities {

    SURPRISE_LUCK ("Surprise Luck", 10),
    RED_FURY ("Red Fury", 11),
    CALL_OF_THE_WILD ("Call of the Wild", 12),
    GREEDY_MADNESS ("Greedy Madness", 13);

    private String name;
    private int id;

    StatsAbilities(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public int getId () {
        return id;
    }
}
