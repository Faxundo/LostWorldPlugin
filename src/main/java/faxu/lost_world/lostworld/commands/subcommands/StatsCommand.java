package faxu.lost_world.lostworld.commands.subcommands;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.commands.SubCommand;
import faxu.lost_world.lostworld.menus.StatsMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StatsCommand extends SubCommand {

    private final LostWorld plugin;
    private final StatsMenu statsMenu;

    public StatsCommand(LostWorld plugin) {
        this.plugin = plugin;
        statsMenu = new StatsMenu(this.plugin);
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
            if (target != null && !hasPerm(plugin, player, "lostworld.seestats")) {
                statsMenu.createMenu(target, player);
            }
        } else if (args.length == 1) {
            statsMenu.createMenu(player, player);
        }
    }
}
