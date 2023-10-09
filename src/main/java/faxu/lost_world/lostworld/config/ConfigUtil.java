package faxu.lost_world.lostworld.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {

    private static File file;
    private static FileConfiguration fileConfiguration;

    public static void setup(String name) {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("LostWorld").getDataFolder(), name + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Fail to create custom config.");
                ex.printStackTrace();
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return fileConfiguration;
    }

    public static void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException ex) {
            System.out.println("Couldn't save custom config.");
            ex.printStackTrace();
        }
    }

    public static void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static void set(String[] options) {
        for (String option : options) {
            String[] parts = option.split(":");

            String key = "name." + parts[0];
            String value = parts[1];
            get().addDefault(key, value);

            if (parts.length >= 3) {
                key = "description." + parts[0];
                value = parts[2];
                get().addDefault(key, value);
            }
            if (parts.length >= 4) {
                key = "stat." + parts[0];
                value = parts[3];
                get().addDefault(key, value);
            }
        }
    }

    public static void setRaceAbility(String[] options) {
        for (String option : options) {
            String[] parts = option.split(":");

            String name = parts[0].toLowerCase() + ".";
            String key;
            String value;

            if (parts.length >= 3) {
                key = name + "constitution";
                value = parts[2];
                get().addDefault(key, value);
            }
            if (parts.length >= 4) {
                key = name + "defense";
                value = parts[3];
                get().addDefault(key, value);
            }
            if (parts.length >= 5) {
                key = name + "strength";
                value = parts[4];
                get().addDefault(key, value);
            }
            if (parts.length >= 6) {
                key = name + "dexterity";
                value = parts[5];
                get().addDefault(key, value);
            }
            if (parts.length >= 7) {
                key = name + "intelligence";
                value = parts[6];
                get().addDefault(key, value);
            }
            if (parts.length >= 8) {
                key = name + "wisdom";
                value = parts[7];
                get().addDefault(key, value);
            }
            if (parts.length >= 9) {
                key = name + "luck";
                value = parts[8];
                get().addDefault(key, value);
            }
            if (parts.length >= 10) {
                key = name + "charisma";
                value = parts[9];
                get().addDefault(key, value);
            }
        }
    }
}
