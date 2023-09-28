package faxu.lost_world.lostworld.menusystem.menus;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.races.RaceDataManager;
import faxu.lost_world.lostworld.menusystem.Menu;
import faxu.lost_world.lostworld.menusystem.PlayerMenuUtility;
import faxu.lost_world.lostworld.willpower.Willpower;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfirmRaceSelection extends Menu {

    private final LostWorld plugin;
    private final FileConfiguration config;
    private final PlayerDataManager playerDataManager;
    private final RaceDataManager raceDataManager;
    private final Willpower willpower;

    public ConfirmRaceSelection(LostWorld plugin, PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.plugin = plugin;
        config = plugin.getConfig();
        playerDataManager = plugin.getPlayerDataManager();
        raceDataManager = plugin.getRaceDataManager();
        willpower = new Willpower(this.plugin);
    }

    @Override
    public String getMenuName() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("names.inv-confirm"));
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        PlayerData playerData = playerDataManager.getPlayerData(player);

        switch (event.getCurrentItem().getType()) {

            case EMERALD:

                String race = playerMenuUtility.getRaceSelected();

                //Avoid you set the same race has you have.
                if (!playerData.getRace().getName().equals(race)) {
                    playerDataManager.setRace(player, raceDataManager.getRaceByName(race));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.choose-new-race")));
                    willpower.setWillPower(player, 0, true);
                    playerDataManager.setRaceLevel(player, 1);
                    player.closeInventory();
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.already-have-race")));
                    new RaceSelectionMenu(plugin, LostWorld.getPlayerMenuUtility(player)).openMenu();
                }

                break;
            case BARRIER:

                new RaceSelectionMenu(plugin, LostWorld.getPlayerMenuUtility(player)).openMenu();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack yes = new ItemStack(Material.EMERALD, 1);
        ItemMeta yesMeta = yes.getItemMeta();
        yesMeta.setDisplayName(ChatColor.GREEN + "Yes");
        yes.setItemMeta(yesMeta);

        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta noMeta = no.getItemMeta();
        noMeta.setDisplayName(ChatColor.RED + "No");
        no.setItemMeta(noMeta);

        inventory.setItem(3, yes);
        inventory.setItem(5, no);

    }
}
