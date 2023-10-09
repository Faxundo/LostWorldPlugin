package faxu.lost_world.lostworld.ability.fantasyclassic;

public enum ActiveAbilities {

    HORIZONTAL_CUT ("Horizontal Cut", 6),
    FURY_STRIKE ("Fury Strike", 7),
    FOREST_ARROW ("Forest Arrow", 8),
    STONE_FORTRESS ("Stone Fortress", 9);

    private String name;
    private int id;

    ActiveAbilities(String name, int id) {
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
