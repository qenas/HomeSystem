package io.github.qenas.homeSystem.manager;

import io.github.qenas.homeSystem.tasks.TeleportTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportManager {
    private JavaPlugin plugin;
    private Map<UUID, TeleportTask> activeTask = new HashMap<>();

    public TeleportManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

}
