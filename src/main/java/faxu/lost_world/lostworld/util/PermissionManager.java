package faxu.lost_world.lostworld.util;

import faxu.lost_world.lostworld.LostWorld;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class PermissionManager {

    private final LostWorld plugin;

    public PermissionManager(LostWorld plugin) {
        this.plugin = plugin;
    }

    public void addPermission(Player player, String permName) {
        PermissionAttachment attachment = player.addAttachment(plugin);

        attachment.setPermission(permName, true);
        player.recalculatePermissions();
        player.removeAttachment(attachment);
    }

    public void removePermission(Player player, String permName) {
        PermissionAttachment attachment = player.addAttachment(plugin);

        attachment.unsetPermission(permName);
        player.recalculatePermissions();
        player.removeAttachment(attachment);
    }
}
