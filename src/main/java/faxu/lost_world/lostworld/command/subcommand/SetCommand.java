package faxu.lost_world.lostworld.command.subcommand;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.command.SubCommand;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetCommand extends SubCommand {

    private final LostWorld plugin;
    private final FileConfiguration config;

    public SetCommand(LostWorld plugin) {
        this.plugin = plugin;
        config = this.plugin.getConfig();
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
            if (target != null && hasPerm(plugin, player, "lostworld.setstats")) {
                plugin.getPlayerDataManager().setStat(player, args[2], Integer.parseInt(args[3]));
                player.sendMessage(Common.colorize("&eWas Set " + args[2].toUpperCase() + " to " + args[3] + " level"));
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
