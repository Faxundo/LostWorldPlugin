package faxu.lost_world.lostworld.menus;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RaceMenu extends MenuHandler {

    private final LostWorld plugin;

    public RaceMenu(LostWorld plugin) {
        super("Races", 9 * 3);
        this.plugin = plugin;
    }

    public void createMenu(LostWorld plugin, Player player, CommandSender sender) {

        Inventory inv = Bukkit.createInventory(player, invSize, invName);

        fillMenu(invSize, inv);
        inv.setItem(10, getItem(new ItemStack(Material.DIAMOND),
                "&6&lHuman", "&eChange you race to Human"));
        inv.setItem(12, getItem(new ItemStack(Material.DIAMOND),
                "&6&lOrc", "&eChange you race to Orc"));
        inv.setItem(14, getItem(new ItemStack(Material.DIAMOND),
                "&6&lElf", "&eChange you race to Elf"));
        inv.setItem(16, getItem(new ItemStack(Material.DIAMOND),
                "&6&lDwarf", "&eChange you race to Dwarf"));

        if (sender instanceof Player) {
            Player senderPlayer = (Player) sender;
            senderPlayer.openInventory(inv);
        }

    }
}
