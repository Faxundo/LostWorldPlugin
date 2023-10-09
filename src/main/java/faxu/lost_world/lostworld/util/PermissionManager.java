package faxu.lost_world.lostworld.util;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class PermissionManager {

    public static void addPermission(LostWorld plugin, Player player, String permName) {
        PermissionAttachment attachment = player.addAttachment(plugin);

        attachment.setPermission(permName, true);
        player.recalculatePermissions();
        player.removeAttachment(attachment);
    }

    public static void removePermission(LostWorld plugin, Player player, String permName) {
        PermissionAttachment attachment = player.addAttachment(plugin);

        attachment.unsetPermission(permName);
        player.recalculatePermissions();
        player.removeAttachment(attachment);
    }
}
