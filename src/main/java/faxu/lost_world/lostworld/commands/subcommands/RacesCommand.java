package faxu.lost_world.lostworld.commands.subcommands;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.commands.SubCommand;
import faxu.lost_world.lostworld.menus.RaceMenu;
import org.bukkit.entity.Player;

public class RacesCommand extends SubCommand {

    private final LostWorld plugin;
    private final RaceMenu raceMenu;

    public RacesCommand(LostWorld plugin) {
        this.plugin = plugin;
        raceMenu = new RaceMenu(this.plugin);
    }

    @Override
    public String getName() {
        return "races";
    }

    @Override
    public String getDescription() {
        return "Shows existing breeds for you to choose one";
    }

    @Override
    public String getSyntax() {
        return "/lostworld races";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            if (hasPerm(plugin, player, "lostworld.races")) {
                raceMenu.createMenu(player, player);
            }
        }
    }
}
