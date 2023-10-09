package faxu.lost_world.lostworld.menusystem.menus;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.race.RaceDataManager;
import faxu.lost_world.lostworld.item.CustomItem;
import faxu.lost_world.lostworld.menusystem.Menu;
import faxu.lost_world.lostworld.menusystem.PlayerMenuUtility;
import faxu.lost_world.lostworld.stat.Constitution;
import faxu.lost_world.lostworld.stat.Luck;
import faxu.lost_world.lostworld.util.Common;
import faxu.lost_world.lostworld.willpower.Willpower;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ConfirmRaceSelection extends Menu {

    private final LostWorld plugin;
    private final FileConfiguration config;
    private final PlayerDataManager playerDataManager;
    private final RaceDataManager raceDataManager;
    private final Constitution constitution;
    private final Luck luck;
    private final Willpower willpower;

    public ConfirmRaceSelection(LostWorld plugin, PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.plugin = plugin;
        config = plugin.getConfig();
        playerDataManager = plugin.getPlayerDataManager();
        raceDataManager = plugin.getRaceDataManager();

        luck = new Luck(plugin);
        constitution = new Constitution(this.plugin);
        willpower = new Willpower(this.plugin);
    }

    @Override
    public String getMenuName() {
        return Common.colorize(LangConfig.get().getString("name.inv-confirm"));
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

                    willpower.setWillPower(player, 0, true);

                    player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 0.5F, 0.5F);
                    player.sendMessage(Common.colorize(config.getString("messages.choose-new-race")));

                    player.closeInventory();
                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5F, 0.5F);
                    player.sendMessage(Common.colorize(config.getString("messages.already-have-race")));
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
        inventory.setItem(3, CustomItem.getItem(new ItemStack(Material.EMERALD, 1), Common.colorize(LangConfig.get().getString("name.yes")), 10));
        inventory.setItem(5, CustomItem.getItem(new ItemStack(Material.BARRIER, 1), Common.colorize(LangConfig.get().getString("name.no")), 11));
    }
}
