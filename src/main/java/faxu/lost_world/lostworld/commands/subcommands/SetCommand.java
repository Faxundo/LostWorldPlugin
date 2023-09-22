package faxu.lost_world.lostworld.commands.subcommands;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SetCommand extends SubCommand {

    private final LostWorld plugin;

    public SetCommand(LostWorld plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "Sets the stat amount of the selected player";
    }

    @Override
    public String getSyntax() {
        return "/lostworld set <player> <stat> <amount>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 1) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target != null && !hasPerm(plugin, player, "lostworld.setstats")) {
                plugin.getPlayerDataManager().setStat(player, args[2], Integer.parseInt(args[3]));
                player.sendMessage(ChatColor.GREEN + "Was Set " + args[2].toUpperCase() + " to " + args[3] + " level");
            }
        } else if (args.length == 1) {
            player.sendMessage(ChatColor.RED + "You need provide a player name.");
        } else if (args[2] == null) {
            player.sendMessage(ChatColor.RED + "You need provide a name of stat in lower case.");
        } else if (args[3] == null) {
            player.sendMessage(ChatColor.RED + "You need provide a number.");
        }
    }
}
