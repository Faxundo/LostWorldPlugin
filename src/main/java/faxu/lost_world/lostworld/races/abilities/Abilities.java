package faxu.lost_world.lostworld.races.abilities;

public enum Abilities {

    NONE("None"),
    CIVILIZATION_WALKER("Civilization Walker"),
    FOREST_DWELLER("Forest Dweller"),
    CAVE_AND_CLIFFS_LIFE("Cave and Cliffs Life"),
    PATH_OF_THE_REAVER("Path of the Reaver");

    private final String name;

    Abilities(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
