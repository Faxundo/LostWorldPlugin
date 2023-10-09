package faxu.lost_world.lostworld.menusystem.menus;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.item.CustomItem;
import faxu.lost_world.lostworld.menusystem.Menu;
import faxu.lost_world.lostworld.menusystem.PlayerMenuUtility;
import faxu.lost_world.lostworld.race.Races;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class RaceSelectionMenu extends Menu {

    private final LostWorld plugin;
    private final FileConfiguration config;

    public RaceSelectionMenu(LostWorld plugin, PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    @Override
    public String getMenuName() {
        return Common.colorize(LangConfig.get().getString("name.inv-race-selection"));
    }

    @Override
    public int getSlots() {
        return 9 * 3;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        PlayerMenuUtility playerMenuUtility = LostWorld.getPlayerMenuUtility(player);

        if (event.getCurrentItem().getType().equals(Material.DIAMOND)) {
            playerMenuUtility.setRaceSelected(event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "race"), PersistentDataType.STRING));
            new ConfirmRaceSelection(plugin, playerMenuUtility).openMenu();
        }
    }

    @Override
    public void setMenuItems() {

        ItemStack humanItem = new ItemStack(Material.DIAMOND);
        ItemMeta humanMeta = humanItem.getItemMeta();
        humanMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "race"), PersistentDataType.STRING, Races.HUMAN.getName());
        humanItem.setItemMeta(humanMeta);
        inventory.setItem(10, CustomItem.getItem(humanItem, LangConfig.get().getString("name.human"), 2,
                LangConfig.get().getString("description.human"),
                " ",
                LangConfig.get().getString("name.initial-stats"),
                LangConfig.get().getString("stat.human")));

        ItemStack orcItem = new ItemStack(Material.DIAMOND);
        ItemMeta orcMeta = orcItem.getItemMeta();
        orcMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "race"), PersistentDataType.STRING, Races.ORC.getName());
        orcItem.setItemMeta(orcMeta);
        inventory.setItem(12, CustomItem.getItem(orcItem, LangConfig.get().getString("name.orc"), 3,
                LangConfig.get().getString("description.orc"),
                " ",
                LangConfig.get().getString("name.initial-stats"),
                LangConfig.get().getString("stat.orc")));

        ItemStack elfItem = new ItemStack(Material.DIAMOND);
        ItemMeta elfMeta = elfItem.getItemMeta();
        elfMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "race"), PersistentDataType.STRING, Races.ELF.getName());
        elfItem.setItemMeta(elfMeta);
        inventory.setItem(14, CustomItem.getItem(elfItem, LangConfig.get().getString("name.elf"), 4,
                LangConfig.get().getString("description.elf"),
                " ",
                LangConfig.get().getString("name.initial-stats"),
                LangConfig.get().getString("stat.elf")));

        ItemStack dwarfItem = new ItemStack(Material.DIAMOND);
        ItemMeta dwarfMeta = dwarfItem.getItemMeta();
        dwarfMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "race"), PersistentDataType.STRING, Races.DWARF.getName());
        dwarfItem.setItemMeta(dwarfMeta);
        inventory.setItem(16, CustomItem.getItem(dwarfItem, LangConfig.get().getString("name.dwarf"), 5,
                LangConfig.get().getString("description.dwarf"),
                " ",
                LangConfig.get().getString("name.initial-stats"),
                LangConfig.get().getString("stat.dwarf")));
    }
}
