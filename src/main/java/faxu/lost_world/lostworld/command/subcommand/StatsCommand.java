package faxu.lost_world.lostworld.command.subcommand;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.command.SubCommand;
import faxu.lost_world.lostworld.menusystem.menus.ProfileMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StatsCommand extends SubCommand {

    private final LostWorld plugin;

    public StatsCommand(LostWorld plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "stats";
    }

    @Override
    public String getDescription() {
        return "Shows stats of selected player";
    }

    @Override
    public String getSyntax() {
        return "/lostworld stats <player>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 1) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target != null && hasPerm(plugin, player, "lostworld.seestats")) {

            }
        } else if (args.length == 1) {
            new ProfileMenu(plugin, LostWorld.getPlayerMenuUtility(player)).openMenu();
        }
    }
}
