package faxu.lost_world.lostworld.menus;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class StatsMenu extends MenuHandler {

    private final LostWorld plugin;
    private final FileConfiguration config;

    public StatsMenu(LostWorld plugin) {
        super(plugin.getConfig().getString("names.invstats"), 9 * 5);
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    public void createMenu(Player player, CommandSender sender) {

        Inventory inv = Bukkit.createInventory(player, invSize, invName);

        fillMenu(invSize, inv);


        inv.setItem(22, getHead(player));
        inv.setItem(10, getItem(new ItemStack(Material.PINK_STAINED_GLASS),
                config.getString("names.constitution"), config.getString("descriptions.constitution")));
        inv.setItem(12, getItem(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS),
                config.getString("names.defense"), config.getString("descriptions.defense")));
        inv.setItem(14, getItem(new ItemStack(Material.RED_STAINED_GLASS),
                config.getString("names.strength"), config.getString("descriptions.strength")));
        inv.setItem(16, getItem(new ItemStack(Material.GREEN_STAINED_GLASS),
                config.getString("names.dextery"), config.getString("descriptions.dextery")));
        inv.setItem(28, getItem(new ItemStack(Material.BLUE_STAINED_GLASS),
                config.getString("names.intelligence"), config.getString("descriptions.intelligence")));
        inv.setItem(30, getItem(new ItemStack(Material.PURPLE_STAINED_GLASS),
                config.getString("names.wisdom"), config.getString("descriptions.wisdom")));
        inv.setItem(32, getItem(new ItemStack(Material.LIME_STAINED_GLASS),
                config.getString("names.luck"), config.getString("descriptions.luck")));
        inv.setItem(34, getItem(new ItemStack(Material.ORANGE_STAINED_GLASS),
                config.getString("names.charisma"), config.getString("descriptions.charisma")));

        if (sender instanceof Player) {
            Player senderPlayer = (Player) sender;
            senderPlayer.openInventory(inv);
        }

    }

    public ItemStack getHead(Player player) {
        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
        Material skull = Material.PLAYER_HEAD;
        ItemStack item = new ItemStack(skull, 1);
        getItem(item, "&6&l" + player.getName(), " ",
                config.getString("names.race") + ": " + playerData.getRace().getName(),
                config.get("names.ability") + ": " + playerData.getRace().getPassiveAbility(),
                " ",
                config.get("names.constitution") + ": " + playerData.getConstitution(),
                config.get("names.defense") + ": " + playerData.getDefense(),
                config.get("names.strength") + ": " + playerData.getStrength(),
                config.get("names.dextery") + ": " + playerData.getDextery(),
                config.get("names.intelligence") + ": " + playerData.getIntelligence(),
                config.get("names.wisdom") + ": " + playerData.getWisdom(),
                config.get("names.luck") + ": " + playerData.getLuck(),
                config.get("names.charisma") + ": " + playerData.getCharisma()
        );
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwningPlayer(player);

        item.setItemMeta(meta);
        return item;
    }
}
