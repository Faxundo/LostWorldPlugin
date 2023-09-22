package faxu.lost_world.lostworld.commands.subcommands;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class UpdateCommand extends SubCommand {

    private final LostWorld plugin;

    public UpdateCommand(LostWorld plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "Increase the stat of the selected player";
    }

    @Override
    public String getSyntax() {
        return "/lostworld update <player> <stat> <add/sub> <amount>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 1) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target != null && !hasPerm(plugin, player, "lostworld.updatestats")) {
                plugin.getPlayerDataManager().updateStat(player, args[2], args[3], Integer.parseInt(args[4]));
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
