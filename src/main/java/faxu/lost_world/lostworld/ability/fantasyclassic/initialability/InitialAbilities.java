package faxu.lost_world.lostworld.ability.fantasyclassic.initialability;

public enum InitialAbilities {

    NONE ("None", 1),
    CIVILIZATION_WALKER ("Civilization Walker", 2),
    PATH_OF_THE_REAVER ("Path of the Reaver", 3),
    FOREST_DWELLER ("Forest Dweller", 4),
    CAVE_AND_CLIFF_LIFE ("Cave and Cliff Life", 5);

    private String name;
    private int id;

    InitialAbilities (String name, int id) {
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
