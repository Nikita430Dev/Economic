package me.peopleXrCb.economic.utils.data;

import me.peopleXrCb.economic.main.Economic;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class PlayerData {
    private static HashMap<OfflinePlayer, PlayerData> data = new HashMap<>();
    private DatabaseValue old;
    private OfflinePlayer player;
    private int money;

    public PlayerData(OfflinePlayer player, int money) {
        this.player = player;
        this.money = money;
        this.old = this.toValue();
    }

    public DatabaseValue toValue() {
        return new DatabaseValue(player.getPlayer(), money);
    }

    public void register() {
        data.put(player, this);
    }

    public void unregister() {
        data.remove(player);
        Economic.getDatabase().replace(old, this.toValue());
    }

    public static PlayerData getData(OfflinePlayer player) {
        return data.get(player);
    }

    public static void forEach(BiConsumer<OfflinePlayer, PlayerData> consumer) {
        data.forEach(consumer);
    }
}