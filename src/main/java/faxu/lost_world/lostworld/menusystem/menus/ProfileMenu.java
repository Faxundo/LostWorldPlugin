package faxu.lost_world.lostworld.menusystem.menus;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.item.CustomItem;
import faxu.lost_world.lostworld.leveling.RaceLeveling;
import faxu.lost_world.lostworld.menusystem.Menu;
import faxu.lost_world.lostworld.menusystem.PlayerMenuUtility;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.stat.Stats;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.Material;
import org.bukkit.Sound;
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
        return Common.colorize(LangConfig.get().getString("name.inv-profile"));
    }

    @Override
    public int getSlots() {
        return 9 * 6;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PlayerData playerData = playerDataManager.getPlayerData(player);
        int initialPoints = playerData.getStatPoints();

        int slot = event.getSlot();

        switch (slot) {
            case 10, 11, 19, 20, 28, 29, 37, 38 -> {
                if (initialPoints > 0) {
                    String statName = getNameSlot(slot);
                    if (statName != null) {
                        player.playSound(player.getLocation(), Sound.BLOCK_CHISELED_BOOKSHELF_INSERT_ENCHANTED, 1F, 1F);
                        playerDataManager.updateStat(player, statName, "add", 1);
                        playerDataManager.setStatPoint(player, playerData.getStatPoints() - 1);
                        player.closeInventory();
                        playerDataManager.refreshStats(player);
                        new ProfileMenu(plugin, playerMenuUtility).openMenu();
                    }
                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5F, 0.5F);
                    player.sendMessage(Common.colorize(config.getString("messages.no-have-points")));
                }
            }
        }
        if (slot == 25) {
            if (playerData.getRaceLevel() == 5) {

            }
            if (!playerData.getRace().getName().equals(Races.SOUL.getName())) {
                raceLeveling.updateLevel(player);
                player.closeInventory();
                new ProfileMenu(plugin, playerMenuUtility).openMenu();
            } else {
                player.sendMessage(Common.colorize(config.getString("messages.need-race")));
            }
        }
        if (slot == 31) {
            if (!playerData.getRace().getName().equals(Races.SOUL.getName())) {
                new RaceAbilityView(plugin, playerMenuUtility).openMenu();
            } else {
                player.sendMessage(Common.colorize(config.getString("messages.need-race")));
            }
        }
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(22, getHead(playerMenuUtility.getOwner()));
        inventory.setItem(31, getRaceIcon(playerMenuUtility.getOwner()));
        inventory.setItem(25, CustomItem.getItem(new ItemStack(Material.REDSTONE), LangConfig.get().getString("name.level-up"), 12));

        inventory.setItem(10, CustomItem.getItem(new ItemStack(Material.PINK_STAINED_GLASS_PANE),
                LangConfig.get().getString("name.constitution"), 31, LangConfig.get().getString("description.constitution")));
        inventory.setItem(11, CustomItem.getItem(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE),
                LangConfig.get().getString("name.defense"), 32, LangConfig.get().getString("description.defense")));
        inventory.setItem(19, CustomItem.getItem(new ItemStack(Material.RED_STAINED_GLASS_PANE),
                LangConfig.get().getString("name.strength"), 33, LangConfig.get().getString("description.strength")));
        inventory.setItem(20, CustomItem.getItem(new ItemStack(Material.GREEN_STAINED_GLASS_PANE),
                LangConfig.get().getString("name.dexterity"), 34, LangConfig.get().getString("description.dexterity")));
        inventory.setItem(28, CustomItem.getItem(new ItemStack(Material.BLUE_STAINED_GLASS_PANE),
                LangConfig.get().getString("name.intelligence"), 35, LangConfig.get().getString("description.intelligence")));
        inventory.setItem(29, CustomItem.getItem(new ItemStack(Material.PURPLE_STAINED_GLASS_PANE),
                LangConfig.get().getString("name.wisdom"), 36, LangConfig.get().getString("description.wisdom")));
        inventory.setItem(37, CustomItem.getItem(new ItemStack(Material.LIME_STAINED_GLASS_PANE),
                LangConfig.get().getString("name.luck"), 37, LangConfig.get().getString("description.luck")));
        inventory.setItem(38, CustomItem.getItem(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE),
                LangConfig.get().getString("name.charisma"), 38, LangConfig.get().getString("description.charisma")));
    }

    public ItemStack getHead(Player player) {
        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
        Material skull = Material.PLAYER_HEAD;
        ItemStack item = new ItemStack(skull, 1);
        CustomItem.getItem(item, "&6&l" + player.getName(),
                " ",
                LangConfig.get().getString("name.stats-points") + ": " + playerData.getStatPoints(),
                " ",
                LangConfig.get().getString("name.constitution") + ": " + playerData.getConstitution(),
                LangConfig.get().getString("name.defense") + ": " + playerData.getDefense(),
                LangConfig.get().getString("name.strength") + ": " + playerData.getStrength(),
                LangConfig.get().getString("name.dexterity") + ": " + playerData.getDexterity(),
                LangConfig.get().getString("name.intelligence") + ": " + playerData.getIntelligence(),
                LangConfig.get().getString("name.wisdom") + ": " + playerData.getWisdom(),
                LangConfig.get().getString("name.luck") + ": " + playerData.getLuck(),
                LangConfig.get().getString("name.charisma") + ": " + playerData.getCharisma()
        );
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.setOwningPlayer(player);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getRaceIcon(Player player) {
        PlayerData playerData = plugin.getPlayerDataManager().getPlayerData(player);
        return CustomItem.getItem(new ItemStack(Material.DIAMOND), LangConfig.get().getString("name.race-information"), playerData.getRace().getId(),
                " ",
                LangConfig.get().getString("name.race") + ": " + LangConfig.get().getString("name." + playerData.getRace().getName().toLowerCase()),
                LangConfig.get().getString("name.race-level") + ": " + playerData.getRaceLevel());
    }

    public String getNameSlot(int slot) {
        return switch (slot) {
            case 10 -> Stats.CONSTITUTION.getName();
            case 11 -> Stats.DEFENSE.getName();
            case 19 -> Stats.STRENGTH.getName();
            case 20 -> Stats.DEXTERITY.getName();
            case 28 -> Stats.INTELLIGENCE.getName();
            case 29 -> Stats.WISDOM.getName();
            case 37 -> Stats.LUCK.getName();
            case 38 -> Stats.CHARISMA.getName();
            default -> null;
        };
    }
}
