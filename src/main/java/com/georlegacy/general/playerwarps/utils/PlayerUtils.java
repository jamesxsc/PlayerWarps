package com.georlegacy.general.playerwarps.utils;

import com.georlegacy.general.playerwarps.PlayerWarps;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerUtils {
    private final PlayerWarps playerWarps;

    public PlayerUtils(PlayerWarps playerWarps) {
        this.playerWarps = playerWarps;
    }

    public int getPlayerWarps(Player p) {
        File file = new File(playerWarps.getDataFolder() + File.separator + "players.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (!config.contains(p.getUniqueId().toString() + ".createdWarps")) return 0;
        else return config.getInt(p.getUniqueId().toString() + ".createdWarps");
    }

    public void addPlayerWarp(Player p) {
        File file = new File(playerWarps.getDataFolder() + File.separator + "players.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.contains(p.getUniqueId().toString() + ".createdWarps")) {
            int warps = config.getInt(p.getUniqueId().toString() + ".createdWarps");
            config.set(p.getUniqueId().toString() + ".createdWarps", warps+1);
        }
        else
            config.set(p.getUniqueId().toString() + ".createdWarps", 1);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMaxWarps(Player p) {
        File file = new File(playerWarps.getDataFolder() + File.separator + "overrides.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.contains(p.getUniqueId().toString())) {
            return config.getInt(p.getUniqueId().toString());
        } else {
            File configFile = new File(playerWarps.getDataFolder() + File.separator + "config.yml");
            YamlConfiguration config1 = YamlConfiguration.loadConfiguration(configFile);
            return config1.getInt("defaultMaxPlayerWarps");
        }
    }
}
