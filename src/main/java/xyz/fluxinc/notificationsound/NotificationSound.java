package xyz.fluxinc.notificationsound;

import xyz.fluxinc.notificationsound.commands.PingCommand;
import xyz.fluxinc.notificationsound.listeners.JoinLeaveListener;
import xyz.fluxinc.notificationsound.listeners.PingListener;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class NotificationSound extends JavaPlugin {

    private ArrayList<String> players;
    private YamlConfiguration lang;

    @Override
    public void onEnable() {

        lang = new YamlConfiguration();
        saveResource("lang.yml", false);
        try {
            lang.load(new File(getDataFolder(), "lang.yml"));
        } catch (IOException | InvalidConfigurationException e) {
            getLogger().severe("[PingNotification] Invalid Lang File, Disabling!!");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        players = new ArrayList<>();
        for (Player p : getServer().getOnlinePlayers()) { players.add(p.getName()); }

        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new PingListener(this), this);
        getCommand("notify").setExecutor(new PingCommand(this));
    }

    @Override
    public void onDisable() {}

    public ArrayList<String> getPlayers() { return this.players; }

    public void addPlayer(String playername) { if (!players.contains(playername)) { players.add(playername); }}

    public void removePlayer(String playername) { players.remove(playername); }

    public String generateMessage(String langKey) {
        String prefix = lang.getString("prefix");
        String msg = lang.getString(langKey);
        return ChatColor.translateAlternateColorCodes('&', prefix + " " + msg);
    }
}
