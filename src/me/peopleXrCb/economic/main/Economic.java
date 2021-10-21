package me.peopleXrCb.economic.main;

import me.peopleXrCb.economic.utils.data.ConfigManager;
import me.peopleXrCb.economic.utils.data.Database;
import me.peopleXrCb.economic.utils.data.PlayerData;
import org.bukkit.plugin.java.JavaPlugin;

public class Economic extends JavaPlugin {
    private static Database database;

    public static Database getDatabase() {
        return database;
    }

    public void onEnable() {
        ConfigManager.create();
        database = new Database(ConfigManager.getString("url"), ConfigManager.getString("login"), ConfigManager.getString("password"));
        database.create();
        database.loadValues();
    }

    public void onDisable() {
        PlayerData.forEach((player, data) -> data.unregister());
    }
}