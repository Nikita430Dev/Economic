package me.peopleXrCb.economic.utils.data;

import me.peopleXrCb.economic.main.Economic;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Scheduler {
    private static Plugin plugin;

    static {
        plugin = Economic.getPlugin(Economic.class);
    }

    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable::run);
    }
}