package me.peopleXrCb.economic.events;

import me.peopleXrCb.economic.main.Economic;
import me.peopleXrCb.economic.utils.data.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerHandler implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (PlayerData.getData(e.getPlayer()) != null) return;
        PlayerData data = new PlayerData(e.getPlayer(), 0);
        data.register();
        Economic.getDatabase().insert(data.toValue());
    }
}