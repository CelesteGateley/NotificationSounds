package xyz.fluxinc.notificationsound.listeners;

import xyz.fluxinc.notificationsound.NotificationSound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    private NotificationSound instance;

    public JoinLeaveListener(NotificationSound inst) { this.instance = inst; }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) { this.instance.addPlayer(e.getPlayer().getName()); }

    @EventHandler
    public void playerLeaveEvent(PlayerQuitEvent e) { this.instance.removePlayer(e.getPlayer().getName()); }
}
