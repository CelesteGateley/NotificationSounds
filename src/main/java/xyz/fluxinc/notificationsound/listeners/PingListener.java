package xyz.fluxinc.notificationsound.listeners;

import xyz.fluxinc.notificationsound.NotificationSound;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PingListener implements Listener {

    private NotificationSound instance;

    public PingListener(NotificationSound inst) { this.instance = inst; }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        if (!e.getMessage().contains("@")) { return; }
        Player p = null;
        for (String name : this.instance.getPlayers()) {
            if (e.getMessage().contains("@" + name) || e.getMessage().contains(name + "@")) {
                p = instance.getServer().getPlayer(name);
                break;
            }
        }

        if (p != null) { p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 10, 1); }
    }
}
