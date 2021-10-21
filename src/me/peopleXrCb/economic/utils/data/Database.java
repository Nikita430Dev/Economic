package me.peopleXrCb.economic.utils.data;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private String url, login, password;

    public Database(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public void insert(DatabaseValue value) {
        Scheduler.runAsync(() -> {
            try (Connection connection = DriverManager.getConnection(url, login, password); Statement statement = connection.createStatement();) {
                String url = "INSERT INTO `players` (`player`, `money`) VALUES (\'"+value.getPlayer().getName()+"\', \'"+value.getMoney()+"\')";
                statement.executeUpdate(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void replace(DatabaseValue oldValue, DatabaseValue newValue) {
        Scheduler.runAsync(() -> {
            try (Connection connection = DriverManager.getConnection(url, login, password); Statement statement = connection.createStatement();) {
                String url = "SELECT `player`, `money`, REPLACE(`money`, \'"+oldValue.getMoney()+"\', \'"+newValue.getMoney()+"\') FROM `players` WHERE `player` = \'"+oldValue.getPlayer().getName()+"\'";
                statement.executeUpdate(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void loadValues() {
        Scheduler.runAsync(() -> {
            try (Connection connection = DriverManager.getConnection(url, login, password); Statement statement = connection.createStatement();) {
                String url = "SELECT * FROM `players`";
                ResultSet resultSet = statement.executeQuery(url);
                while (resultSet.next()) {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(resultSet.getString("player"));
                    int money = resultSet.getInt("money");
                    PlayerData data = new PlayerData(player, money);
                    data.register();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void create() {
        Scheduler.runAsync(() -> {
            try (Connection connection = DriverManager.getConnection(url, login, password); Statement statement = connection.createStatement();) {
                String url = "CREATE TABLE IF NOT EXISTS `players` (`player` VARCHAR(45) NOT NULL, `money` VARCHAR(45) NOT NULL)";
                statement.executeUpdate(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}