package faxu.lost_world.lostworld.util;

import org.bukkit.ChatColor;

public final class Common {

    private Common() {

    }

    public static String colorize (String text) {
        if (text == null) {
            return "NULL";
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
