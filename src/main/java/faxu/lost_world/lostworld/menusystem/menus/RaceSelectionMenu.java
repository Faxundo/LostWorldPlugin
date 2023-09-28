package faxu.lost_world.lostworld.menusystem.menus;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.menusystem.Menu;
import faxu.lost_world.lostworld.menusystem.PlayerMenuUtility;
import faxu.lost_world.lostworld.races.Races;
import org.bukkit.ChatColor;
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
        return ChatColor.translateAlternateColorCodes('&', config.getString("names.inv-race-selection"));
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
        inventory.setItem(10, getItem(humanItem, config.getString("names.human"),
                config.getString("descriptions.human-description"),
                " ",
                config.getString("descriptions.human-ability"),
                " ",
                config.getString("descriptions.human-stats")));

        ItemStack orcItem = new ItemStack(Material.DIAMOND);
        ItemMeta orcMeta = orcItem.getItemMeta();
        orcMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "race"), PersistentDataType.STRING, Races.ORC.getName());
        orcItem.setItemMeta(orcMeta);
        inventory.setItem(12, getItem(orcItem, config.getString("names.orc"),
                config.getString("descriptions.orc-description"),
                " ",
                config.getString("descriptions.orc-ability"),
                " ",
                config.getString("descriptions.orc-stats")));

        ItemStack elfItem = new ItemStack(Material.DIAMOND);
        ItemMeta elfMeta = elfItem.getItemMeta();
        elfMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "race"), PersistentDataType.STRING, Races.ELF.getName());
        elfItem.setItemMeta(elfMeta);
        inventory.setItem(14, getItem(elfItem, config.getString("names.elf"),
                config.getString("descriptions.elf-description"),
                " ",
                config.getString("descriptions.elf-ability"),
                " ",
                config.getString("descriptions.elf-stats")));

        ItemStack dwarfItem = new ItemStack(Material.DIAMOND);
        ItemMeta dwarfMeta = dwarfItem.getItemMeta();
        dwarfMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "race"), PersistentDataType.STRING, Races.DWARF.getName());
        dwarfItem.setItemMeta(dwarfMeta);
        inventory.setItem(16, getItem(dwarfItem, config.getString("names.dwarf"),
                config.getString("descriptions.dwarf-description"),
                " ",
                config.getString("descriptions.dwarf-ability"),
                " ",
                config.getString("descriptions.dwarf-stats")));
    }
}
