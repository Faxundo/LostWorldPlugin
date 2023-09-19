package faxu.lost_world.lostworld.events;

import faxu.lost_world.lostworld.LostWorld;
import faxu.lost_world.lostworld.stats.Luck;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class BlockBreakListener implements Listener {

    private final LostWorld plugin;
    private final Luck luck;

    public BlockBreakListener(LostWorld plugin) {
        this.plugin = plugin;
        luck = new Luck();
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event)  {
        Player player = (Player) event.getPlayer();
        Block block = event.getBlock();
        Material material = block.getType();

        if (!event.isDropItems()) {
            return;
        }
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {

            ItemStack itemHand = player.getInventory().getItemInMainHand();
            int max = plugin.getPlayerDataManager().getPlayerData(player).getLuck();

            boolean chance = false;

            for (int i = 0; i <= 1 + (max / 10); i++) {
                if (!chance) {
                    double result = (Math.random() * (6 - 1) + 1) + max;
                    if (result >= 6+i) {
                        chance = true;
                    }
                }
            }

            if (chance) {
                if (itemHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    if (block.isPreferredTool(itemHand)) {
                        //Check if is a Ore or is a block descrafteable, for avoid duplications.
                        if (!isOre(material) && !isDescrafteable(material)) {
                            event.getBlock().setType(Material.AIR);
                            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
                                    new ItemStack(material, 2));
                        }


                    }


                } else {
                    Collection<ItemStack> drops = event.getBlock().getDrops();
                    for (ItemStack drop : drops) {
                        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
                                drop);
                    }

                }
            }
        }
    }

    public boolean isOre(Material material) {
        String name = material.name();
        return name.equals("COAL_ORE") ||
                name.equals("DEEPSLATE_COAL_ORE") ||
                name.equals("IRON_ORE") ||
                name.equals("DEEPSLATE_IRON_ORE") ||
                name.equals("COPPER_ORE") ||
                name.equals("DEEPSLATE_COPPER_ORE") ||
                name.equals("GOLD_ORE") ||
                name.equals("DEEPSLATE_GOLD_ORE") ||
                name.equals("REDSTONE_ORE") ||
                name.equals("DEEPSLATE_REDSTONE_ORE") ||
                name.equals("EMERALD_ORE") ||
                name.equals("DEEPSLATE_EMERALD_ORE") ||
                name.equals("LAPIS_ORE") ||
                name.equals("DEEPSLATE_LAPIS_ORE") ||
                name.equals("DIAMOND_ORE") ||
                name.equals("DEEPSLATE_DIAMON_ORE") ||
                name.equals("NETHER_GOLD_ORE") ||
                name.equals("NETHER_QUARTZ_ORE");
    }

    public boolean isDescrafteable(Material material) {
        String name = material.name();
        return name.equals("COAL_BLOCK") ||
                name.equals("IRON_BLOCK") ||
                name.equals("GOLD_BLOCK") ||
                name.equals("REDSTONE_BLOCK") ||
                name.equals("EMERALD_BLOCK") ||
                name.equals("LAPIS_BLOCK") ||
                name.equals("DIAMOND_BLOCK") ||
                name.equals("NETHERITE_BLOCK") ||
                name.equals("COPPER_BLOCK") ||
                name.equals("WAXED_COPPER_BLOCK") ||
                name.equals("CLAY") ||
                name.equals("RAW_IRON_BLOCK") ||
                name.equals("RAW_COPPER_BLOCK") ||
                name.equals("RAW_GOLD_BLOCK") ||
                name.equals("SLIME_BLOCK");
    }

}
