package xyz.fluxinc.notificationsound.commands;

import xyz.fluxinc.notificationsound.NotificationSound;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    private NotificationSound instance;

    public PingCommand(NotificationSound inst) { this.instance = inst; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) { sender.sendMessage(instance.generateMessage("invalidUsage")); return true; }
        Player target = instance.getServer().getPlayer(args[0]);
        if (target != null) {
            target.playSound(target.getLocation(), Sound.BLOCK_BELL_USE, 50, 1);
            sender.sendMessage(replaceVar(instance.generateMessage("dingSender"), sender.getName(), target.getName()));
            target.sendMessage(replaceVar(instance.generateMessage("dingTarget"), sender.getName(), target.getName()));
            return true;
        } else {
            sender.sendMessage(instance.generateMessage("userNotFound"));
            return true;
        }
    }

    private String replaceVar(String message, String sender, String target) {
        message = message.replaceAll("%sender%", sender);
        message = message.replaceAll("%target%", target);
        return message;
    }
}
