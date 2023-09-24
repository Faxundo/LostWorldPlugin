package faxu.lost_world.lostworld.menus;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RaceMenu extends MenuHandler {

    private final LostWorld plugin;
    private final FileConfiguration config;

    public RaceMenu(LostWorld plugin) {
        super(plugin.getConfig().getString("names.invrace"), 9 * 3);
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    public void createMenu(Player player, CommandSender sender) {

        Inventory inv = Bukkit.createInventory(player, invSize, invName);

        inv.setItem(10, getItem(new ItemStack(Material.DIAMOND), config.getString("names.human"),
                config.getString("descriptions.human-description"),
                " ",
                config.getString("descriptions.human-ability"),
                " ",
                config.getString("descriptions.human-stats")));

        inv.setItem(12, getItem(new ItemStack(Material.DIAMOND), config.getString("names.orc"),
                config.getString("descriptions.orc-description"),
                " ",
                config.getString("descriptions.orc-ability"),
                " ",
                config.getString("descriptions.orc-stats")));

        inv.setItem(14, getItem(new ItemStack(Material.DIAMOND), config.getString("names.elf"),
                config.getString("descriptions.elf-description"),
                " ",
                config.getString("descriptions.elf-ability"),
                " ",
                config.getString("descriptions.elf-stats")));

        inv.setItem(16, getItem(new ItemStack(Material.DIAMOND), config.getString("names.dwarf"),
                config.getString("descriptions.dwarf-description"),
                " ",
                config.getString("descriptions.dwarf-ability"),
                " ",
                config.getString("descriptions.dwarf-stats")));

        if (sender instanceof Player) {
            Player senderPlayer = (Player) sender;
            senderPlayer.openInventory(inv);
        }

    }
}
