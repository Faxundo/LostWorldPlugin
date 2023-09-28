package faxu.lost_world.lostworld.menusystem.menus;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.leveling.RaceLeveling;
import faxu.lost_world.lostworld.menusystem.Menu;
import faxu.lost_world.lostworld.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ProfileMenu extends Menu {

    private final LostWorld plugin;
    private final FileConfiguration config;
    private final PlayerDataManager playerDataManager;
    private final RaceLeveling raceLeveling;

    public ProfileMenu(LostWorld plugin, PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.plugin = plugin;
        config = plugin.getConfig();
        playerDataManager = plugin.getPlayerDataManager();
        raceLeveling = new RaceLeveling(this.plugin);
    }

    @Override
    public String getMenuName() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("names.inv-profile"));
    }

    @Override
    public int getSlots() {
        return 9 * 6;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        int slot = event.getSlot();
        if (slot == 25) {
            raceLeveling.updateLevel(player);
        }
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(22, getHead(playerMenuUtility.getOwner()));

        inventory.setItem(10, getItem(new ItemStack(Material.PINK_STAINED_GLASS_PANE),
                config.getString("names.constitution"), config.getString("descriptions.constitution")));
        inventory.setItem(11, getItem(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE),
                config.getString("names.defense"), config.getString("descriptions.defense")));
        inventory.setItem(19, getItem(new ItemStack(Material.RED_STAINED_GLASS_PANE),
                config.getString("names.strength"), config.getString("descriptions.strength")));
        inventory.setItem(20, getItem(new ItemStack(Material.GREEN_STAINED_GLASS_PANE),
                config.getString("names.dexterity"), config.getString("descriptions.dexterity")));
        inventory.setItem(28, getItem(new ItemStack(Material.BLUE_STAINED_GLASS_PANE),
                config.getString("names.intelligence"), config.getString("descriptions.intelligence")));
        inventory.setItem(29, getItem(new ItemStack(Material.PURPLE_STAINED_GLASS_PANE),
                config.getString("names.wisdom"), config.getString("descriptions.wisdom")));
        inventory.setItem(37, getItem(new ItemStack(Material.LIME_STAINED_GLASS_PANE),
                config.getString("names.luck"), config.getString("descriptions.luck")));
        inventory.setItem(38, getItem(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE),
                config.getString("names.charisma"), config.getString("descriptions.charisma")));

        inventory.setItem(24, getRaceIcon(playerMenuUtility.getOwner()));
        inventory.setItem(25, getItem(new ItemStack(Material.REDSTONE), "Level Up"));

    }

    public ItemStack getHead(Player player) {
        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
        Material skull = Material.PLAYER_HEAD;
        ItemStack item = new ItemStack(skull, 1);
        getItem(item, "&6&l" + player.getName(), " ",
                config.getString("names.race") + ": " + playerData.getRace().getName(),
                config.get("names.ability") + ": " + playerData.getRace().getPassiveAbility(),
                config.get("names.race-level") + ": " + playerData.getRaceLevel(),
                " ",
                config.get("names.constitution") + ": " + playerData.getConstitution(),
                config.get("names.defense") + ": " + playerData.getDefense(),
                config.get("names.strength") + ": " + playerData.getStrength(),
                config.get("names.dexterity") + ": " + playerData.getDexterity(),
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

    public ItemStack getRaceIcon(Player player) {
        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        return item;
    }
}
