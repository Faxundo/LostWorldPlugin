package faxu.lost_world.lostworld.races;

public enum Races {

    SOUL("Soul"),
    HUMAN("Human"),
    ORC("Orc"),
    ELF("Elf"),
    DWARF("Dwarf");

    private final String name;

    Races(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
