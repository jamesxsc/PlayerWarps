package com.georlegacy.general.playerwarps.utils;

import com.georlegacy.general.playerwarps.PlayerWarps;

import java.io.File;

public class FolderUtils {
    private final PlayerWarps playerWarps;

    public FolderUtils(PlayerWarps playerWarps) {
        this.playerWarps = playerWarps;
    }

    public void loadDataFolder() {
        File dataFolder = playerWarps.getDataFolder();
        if (!(dataFolder.exists())) {
            dataFolder.mkdirs();
            playerWarps.getLogger().info("Successfully created plugin data folder");
        }
        else return;
    }

    public void loadWarpsFolder() {
        File warpFolder = new File (playerWarps.getDataFolder() + File.separator + "warps");
        if (!(warpFolder.exists())) {
            warpFolder.mkdirs();
            playerWarps.getLogger().info("Successfully created warps folder");
        }
        else return;
    }
}
