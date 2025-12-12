package io.github.qenas.homeSystem.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HomeManager {
    private JavaPlugin plugin;
    private File file;
    private FileConfiguration homeFile;


    public HomeManager(JavaPlugin plugin){
        this.plugin = plugin;
        setupFile();
    }


    private void setupFile() {
        file = new File(plugin.getDataFolder(),"home_file.yml");
        boolean fileExists = true;
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            fileExists = false;
            System.out.println("Creating home_file.yml...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("home_file.yml was found");
        }

        homeFile = YamlConfiguration.loadConfiguration(file);

        if(!fileExists) {
            homeFile.set("home.players", new ArrayList<String>());
            saveFile();
        }
    }

    private void saveFile() {
        try {
            homeFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reloadFile() {
        homeFile = YamlConfiguration.loadConfiguration(file);
    }




}
