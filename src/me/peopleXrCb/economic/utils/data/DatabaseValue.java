package me.peopleXrCb.economic.utils.data;

import org.bukkit.entity.Player;

public class DatabaseValue {
    private Player player;
    private int money;

    public DatabaseValue(Player player, int money) {
        this.player = player;
        this.money = money;
    }

    public Player getPlayer() {
        return player;
    }

    public int getMoney() {
        return money;
    }
}