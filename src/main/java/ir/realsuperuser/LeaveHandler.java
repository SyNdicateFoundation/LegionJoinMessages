package ir.realsuperuser;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class LeaveHandler implements Listener {
    @EventHandler
    public void onPlayerLeave(final PlayerQuitEvent e) {
        e.setQuitMessage("");
        new BukkitRunnable() {
            @Override public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(Main.getInstance().leavemessage.replace("PLAYERNAME", e.getPlayer().getName())));
            }
        }.runTaskAsynchronously(Main.getInstance());
    }   
}
