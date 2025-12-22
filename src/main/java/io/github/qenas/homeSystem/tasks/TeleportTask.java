package io.github.qenas.homeSystem.tasks;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportTask extends BukkitRunnable {

    JavaPlugin plugin;

    public TeleportTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

    }
}
