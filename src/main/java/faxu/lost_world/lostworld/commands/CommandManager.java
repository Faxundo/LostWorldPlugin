package faxu.lost_world.lostworld.commands;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.commands.subcommands.RacesCommand;
import faxu.lost_world.lostworld.commands.subcommands.SetCommand;
import faxu.lost_world.lostworld.commands.subcommands.StatsCommand;
import faxu.lost_world.lostworld.commands.subcommands.UpdateCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final LostWorld plugin;
    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(LostWorld plugin) {
        this.plugin = plugin;
        subCommands.add(new StatsCommand(this.plugin));
        subCommands.add(new SetCommand(this.plugin));
        subCommands.add(new UpdateCommand(this.plugin));
        subCommands.add(new RacesCommand(this.plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.isOnline()) return true;

            if (label.equals("lostworld")) {
                if (args.length > 0) {
                    for (int i = 0; i < getSubCommands().size(); i++) {
                        if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                            getSubCommands().get(i).perform(player, args);
                        }
                    }
                }
            } else if (args.length == 0) {
                player.sendMessage("———————[ Lost World ]———————");
                for (int i = 0; i < getSubCommands().size(); i++) {
                    player.sendMessage(getSubCommands().get(i).getSyntax() + " - " + getSubCommands().get(i).getDescription());
                }
                player.sendMessage("————————————————————————————");
            }
        }

        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
