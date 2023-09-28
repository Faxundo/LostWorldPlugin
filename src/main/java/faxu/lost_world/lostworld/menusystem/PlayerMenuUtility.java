package faxu.lost_world.lostworld.menusystem;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private Player owner;
    private String raceSelected;

    public PlayerMenuUtility(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getRaceSelected() {
        return raceSelected;
    }

    public void setRaceSelected(String raceSelected) {
        this.raceSelected = raceSelected;
    }
}
