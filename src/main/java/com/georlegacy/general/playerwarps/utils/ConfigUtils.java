package com.georlegacy.general.playerwarps.utils;

import com.georlegacy.general.playerwarps.PlayerWarps;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ConfigUtils {
    private final PlayerWarps playerWarps;

    public ConfigUtils(PlayerWarps playerWarps) {
        this.playerWarps = playerWarps;
    }

    public void loadConfig() {
        File configFile = new File(playerWarps.getDataFolder() + File.separator + "config.yml");
        if (!(configFile.exists()))
            playerWarps.saveResource("config.yml", true);
        else return;
    }
}
