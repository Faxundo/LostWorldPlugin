package faxu.lost_world.lostworld.item.racialitem;

import faxu.lost_world.lostworld.config.files.LangConfig;
import faxu.lost_world.lostworld.item.CustomItem;
import faxu.lost_world.lostworld.util.Common;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class BranchOfLife extends CustomItem {

    @Override
    public String getName() {
        return LangConfig.get().getString("name.branch-life");
    }

    @Override
    public Material getMaterial() {
        return Material.STICK;
    }

    @Override
    public List<String> getLore() {
        return Arrays.asList(Common.colorize(LangConfig.get().getString("description.branch-life")));
    }

    @Override
    public void handleLeftClick(Player player, ItemStack itemStack, PlayerInteractEvent event) {

    }

    @Override
    public void handleRightClick(Player player, ItemStack itemStack, PlayerInteractEvent event) {

    }

    @Override
    public void handleDrop(Player player, ItemStack itemStack, PlayerDropItemEvent event) {

    }
}
