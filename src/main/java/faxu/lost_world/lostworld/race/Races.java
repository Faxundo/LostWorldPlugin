package faxu.lost_world.lostworld.race;

public enum Races {

    SOUL("Soul", 1),
    HUMAN("Human", 2),
    ORC("Orc", 3),
    ELF("Elf",4 ),
    DWARF("Dwarf", 5);

    private final int id;
    private final String name;


    Races(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
