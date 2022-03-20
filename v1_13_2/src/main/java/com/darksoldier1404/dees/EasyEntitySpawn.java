package com.darksoldier1404.dees;

import com.darksoldier1404.dees.commands.DEESCommand;
import com.darksoldier1404.dees.events.DEESEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EasyEntitySpawn extends JavaPlugin {
    private static EasyEntitySpawn plugin;
    public static final String prefix = "§f[ §aDEES §f] ";
    public static EasyEntitySpawn getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        plugin.getServer().getPluginManager().registerEvents(new DEESEvent(), plugin);
        getCommand("dees").setExecutor(new DEESCommand());
    }
}
