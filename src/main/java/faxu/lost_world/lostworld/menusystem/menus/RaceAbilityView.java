package faxu.lost_world.lostworld.menusystem.menus;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.ability.fantasyclassic.initialability.InitialAbilities;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.data.PlayerData;
import faxu.lost_world.lostworld.data.PlayerDataManager;
import faxu.lost_world.lostworld.data.ability.AbilityDataManager;
import faxu.lost_world.lostworld.item.CustomItem;
import faxu.lost_world.lostworld.menusystem.Menu;
import faxu.lost_world.lostworld.menusystem.PlayerMenuUtility;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RaceAbilityView extends Menu {

    private final LostWorld plugin;
    private final FileConfiguration config;
    private final PlayerDataManager playerDataManager;
    private final AbilityDataManager abilityDataManager;

    public RaceAbilityView(LostWorld plugin, PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.plugin = plugin;
        config = plugin.getConfig();
        playerDataManager = plugin.getPlayerDataManager();
        abilityDataManager = plugin.getAbilityDataManager();
    }

    @Override
    public String getMenuName() {
        return Common.colorize(LangConfig.get().getString("name.inv-race-ability"));
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if (slot == 0) {
            player.closeInventory();
            new ProfileMenu(plugin, playerMenuUtility).openMenu();
        }
    }

    @Override
    public void setMenuItems() {
        PlayerData playerData = playerDataManager.getPlayerData(playerMenuUtility.getOwner());

        Map<String, Integer> racialAbilities = playerData.getRacialAbilities();

        List<ItemStack> listItem = listAbilities(playerData);

        inventory.setItem(0, CustomItem.getItem(new ItemStack(Material.COAL), LangConfig.get().getString("name.back"), 13));

        for (int i = 0; i < listItem.size(); i++) {
            inventory.setItem(2 + i, listItem.get(i));
        }
        inventory.setItem(8, CustomItem.getItem(new ItemStack(Material.COAL), LangConfig.get().getString("name.next"), 14));
    }

    public List<ItemStack> listAbilities(PlayerData playerData) {
        List<ItemStack> listItem = new ArrayList<>();

        for (InitialAbilities initialAbilities : InitialAbilities.values()) {
            if (plugin.getAbilityDataManager().getAbilityByName(initialAbilities.getName()).getOrigin().getId() == playerData.getRace().getId()) {
                ItemStack item = new ItemStack(Material.DIAMOND, 1);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.GREEN + initialAbilities.getName());
                List<String> lore = new ArrayList<>();
                lore.add(Common.colorize(LangConfig.get().getString("description." + initialAbilities.getName().toLowerCase().replace(' ', '-'))));
                meta.setLore(lore);
                item.setItemMeta(meta);

                listItem.add(item);
            }
        }
        return listItem;
    }
}
