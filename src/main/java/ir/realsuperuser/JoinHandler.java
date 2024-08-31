package ir.realsuperuser;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinHandler implements Listener {
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        e.setJoinMessage("");
        new BukkitRunnable() {
            @Override public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(Main.getInstance().joinmessage.replace("PLAYERNAME", e.getPlayer().getName())));
            }
        }.runTaskAsynchronously(Main.getInstance());
    }
}
