package faxu.lost_world.lostworld.command.subcommand;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.command.SubCommand;
import faxu.lost_world.lostworld.menusystem.menus.RaceSelectionMenu;
import faxu.lost_world.lostworld.util.PermissionManager;
import org.bukkit.entity.Player;

public class RacesCommand extends SubCommand {

    private final LostWorld plugin;

    public RacesCommand(LostWorld plugin) {
        this.plugin = plugin;
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
                PermissionManager.removePermission(plugin, player, "lostworld.races");
                new RaceSelectionMenu(plugin, LostWorld.getPlayerMenuUtility(player)).openMenu();
            }
        }
    }
}