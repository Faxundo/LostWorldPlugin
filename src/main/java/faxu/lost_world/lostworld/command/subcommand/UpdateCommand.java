package faxu.lost_world.lostworld.command.subcommand;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.command.SubCommand;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class UpdateCommand extends SubCommand {

    private final LostWorld plugin;
    private final FileConfiguration config;

    public UpdateCommand(LostWorld plugin) {
        this.plugin = plugin;
        config = this.plugin.getConfig();
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
            if (target != null && hasPerm(plugin, player, "lostworld.updatestats")) {
                plugin.getPlayerDataManager().updateStat(player, args[2], args[3], Integer.parseInt(args[4]));
                player.sendMessage(Common.colorize("&eWas " + args[3].toUpperCase() + " to " + args[2] + " stat, of the player " + player.getName() + " in " + args[4] + " levels"));
            }
        } else if (args.length == 1) {
            player.sendMessage(Common.colorize(config.getString("messages.commands.need-player")));
        } else if (args[2] == null) {
            player.sendMessage(Common.colorize(config.getString("messages.commands.need-stat")));
        } else if (args[3] == null) {
            player.sendMessage(Common.colorize(config.getString("messages.commands.need-number")));
        }
    }
}
