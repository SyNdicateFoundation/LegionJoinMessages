//TODO: add .yml config manager instead of this shit
package ir.realsuperuser;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;

public class Main extends JavaPlugin {
    @Getter private static Main instance;

    private File configFolder;
    private File configFile;
    public String joinmessage;
    public String leavemessage;

    @Override
    @SneakyThrows
    public void onEnable() {
        instance = this;
        configFolder = getDataFolder();
        configFile = new File(configFolder, "conf.txt");

        handleConfig();

        getServer().getPluginManager().registerEvents(new JoinHandler(), this);
        getServer().getPluginManager().registerEvents(new LeaveHandler(), this);
    }

    private void handleConfig() throws IOException {
        if (!configFolder.exists()) {
            configFolder.mkdirs();
            try (final BufferedWriter bw = new BufferedWriter(new FileWriter(configFile))) {
                bw.write("JOINMESSAGE:Welcome PLAYERNAME to EXAMPLESERVER!");
                bw.newLine();
                bw.write("LEAVEMESSAGE:PLAYERNAME left from the server!");
            }
        }

        try (final BufferedReader bf = new BufferedReader(new FileReader(configFile))) {
            String line;
            while ((line = bf.readLine()) != null) {
                if (line.startsWith("JOINMESSAGE:"))
                    joinmessage = ChatColor.translateAlternateColorCodes('&', line.split(":", 2)[1]);
                else
                    leavemessage = ChatColor.translateAlternateColorCodes('&', line.split(":", 2)[1]);
            }
        }
        if (String.format("%s%s", joinmessage, leavemessage).isEmpty()) {
            joinmessage = "Welcome PLAYERNAME to the server!";
            leavemessage = "PLAYERNAME left from the server";
        }
    }
}