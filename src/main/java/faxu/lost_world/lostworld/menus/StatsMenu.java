package faxu.lost_world.lostworld.menus;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class StatsMenu extends MenuHandler {

    private final LostWorld plugin;

    public StatsMenu(LostWorld plugin) {
        super("Stats", 9 * 5);
        this.plugin = plugin;
    }

    public void createMenu(LostWorld plugin, Player player, CommandSender sender) {

        Inventory inv = Bukkit.createInventory(player, invSize, invName);

        fillMenu(invSize, inv);

        inv.setItem(22, getHead(player));
        inv.setItem(10, getItem(new ItemStack(Material.PINK_STAINED_GLASS),
                "&d&lConstitution", plugin.getConfig().getString("descriptions.constitution")));
        inv.setItem(12, getItem(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS),
                "&b&lDefense", plugin.getConfig().getString("descriptions.defense")));
        inv.setItem(14, getItem(new ItemStack(Material.RED_STAINED_GLASS),
                "&c&lStrength", plugin.getConfig().getString("descriptions.strength")));
        inv.setItem(16, getItem(new ItemStack(Material.GREEN_STAINED_GLASS),
                "&2&lDextery", plugin.getConfig().getString("descriptions.dextery")));
        inv.setItem(28, getItem(new ItemStack(Material.BLUE_STAINED_GLASS),
                "&9&lIntelligence", plugin.getConfig().getString("descriptions.intelligence")));
        inv.setItem(30, getItem(new ItemStack(Material.PURPLE_STAINED_GLASS),
                "&5&lWisdom", plugin.getConfig().getString("descriptions.wisdom")));
        inv.setItem(32, getItem(new ItemStack(Material.LIME_STAINED_GLASS),
                "&a&lLuck", plugin.getConfig().getString("descriptions.luck")));
        inv.setItem(34, getItem(new ItemStack(Material.ORANGE_STAINED_GLASS),
                "&6&lCharisma", ""));

        if (sender instanceof Player) {
            Player senderPlayer = (Player) sender;
            senderPlayer.openInventory(inv);
        }

    }

    public ItemStack getHead(Player player) {
        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
        Material skull = Material.PLAYER_HEAD;
        ItemStack item = new ItemStack(skull, 1);
        getItem(item, "&6&l" + player.getName() + "'s Stats", "",
                "&eRace: " + playerData.getRace().getName(),
                " ",
                "&dConstitution: " + playerData.getConstitution(),
                "&bDefense: " + playerData.getDefense(),
                "&cStrength: " + playerData.getStrength(),
                "&2Dextery: " + playerData.getDextery(),
                "&9Intelligence: " + playerData.getIntelligence(),
                "&5Wisdom: " + playerData.getWisdom(),
                "&aLuck: " + playerData.getLuck(),
                "&6Charisma: " + playerData.getCharisma()
        );
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwningPlayer(player);

        item.setItemMeta(meta);
        return item;
    }
}
