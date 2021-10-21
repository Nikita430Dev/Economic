package me.peopleXrCb.economic.utils.data;

import me.peopleXrCb.economic.main.Economic;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigManager {
    private static Plugin plugin;

    static {
        plugin = Economic.getPlugin(Economic.class);
    }

    public static void create() {
        if (fileExists()) return;
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
    }

    public static String getString(String path) {
        return plugin.getConfig().getString(path);
    }

    private static boolean fileExists() {
        return new File(plugin.getDataFolder() + File.separator + "config.yml").exists();
    }
}