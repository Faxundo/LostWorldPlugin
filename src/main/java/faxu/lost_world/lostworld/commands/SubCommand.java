package faxu.lost_world.lostworld.commands;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public boolean hasPerm(LostWorld plugin, CommandSender sender, String perm) {
        if (sender.hasPermission(perm)) return false;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.no-perms")));
        return true;
    }

    public abstract void perform(Player player, String[] args);
}
