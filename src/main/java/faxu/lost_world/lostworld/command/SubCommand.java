package faxu.lost_world.lostworld.command;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public boolean hasPerm(LostWorld plugin, CommandSender sender, String perm) {
        if (sender.hasPermission(perm)) return true;
        sender.sendMessage(Common.colorize(plugin.getConfig().getString("messages.no-perms")));
        return false;
    }

    public abstract void perform(Player player, String[] args);
}
