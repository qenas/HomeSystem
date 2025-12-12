package io.github.qenas.homeSystem;

import io.github.qenas.homeSystem.manager.HomeManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HomeSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        HomeManager homeManager = new HomeManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
