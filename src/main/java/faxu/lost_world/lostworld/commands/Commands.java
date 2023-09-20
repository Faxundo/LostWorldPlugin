package faxu.lost_world.lostworld.commands;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.menus.RaceMenu;
import faxu.lost_world.lostworld.menus.StatsMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private final LostWorld plugin;
    private final StatsMenu statsMenu;
    private final RaceMenu raceMenu;

    public Commands(LostWorld plugin) {
        this.plugin = plugin;
        statsMenu = new StatsMenu(this.plugin);
        raceMenu = new RaceMenu(this.plugin);
    }

    public boolean noPerm(CommandSender sender, String perm) {
        if (sender.hasPermission(perm)) return false;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.no-perms")));
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("lostworld".equals(label)) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.no-arguments")));
                return true;
            }
            Player player = plugin.getServer().getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.invalid-player")));
                return true;
            }

            switch (args[0]) {
                case "stats":
                    if (noPerm(sender, "lostworld.seestats")) {
                        return true;
                    }
                    statsMenu.createMenu(plugin, player, sender);
                    break;
                case "set":
                    if (noPerm(sender, "lostworld.setstat")) {
                        return true;
                    }
                    plugin.getPlayerDataManager().setStat(player, args[2], Integer.parseInt(args[3]));
                    sender.sendMessage(ChatColor.GREEN + "Was Set " + args[2].toUpperCase() + " to " + args[3] + " level");
                    break;
                case "update":
                    if (noPerm(sender, "lostworld.updatestat")) {
                        return true;
                    }
                    plugin.getPlayerDataManager().updateStat(player, args[2], Integer.parseInt(args[3]));
                    break;
                case "races":
                    raceMenu.createMenu(plugin, player, sender);
                    break;
                case "help":
                    sender.sendMessage(plugin.getConfig().getString("messages.help"));
                    break;
                default:
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.help")));
            }
        }
        return true;
    }
}
