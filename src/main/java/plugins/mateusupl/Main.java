package plugins.mateusupl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        MySQL.criarTabela();
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"MYSQL CONNECTION");
        Bukkit.getPluginManager().registerEvents(new Events(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
