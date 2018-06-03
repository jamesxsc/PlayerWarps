package com.georlegacy.general.playerwarps.handlers;

import com.georlegacy.general.playerwarps.PlayerWarps;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class WarpGetter {
    private final PlayerWarps playerWarps;
    public WarpGetter(PlayerWarps playerWarps){
        this.playerWarps = playerWarps;
    }

    public Location getByName(String name) {
        File warp = new File(playerWarps.getConfigHandler().warpsFolder + File.separator + name + ".yml");
        if (!warp.exists()) {
            return null;
        }
        YamlConfiguration wc = YamlConfiguration.loadConfiguration(warp);
        return new Location(
                playerWarps.getServer().getWorld(wc.getString("location.world")),
                wc.getDouble("location.x"),
                wc.getDouble("location.y"),
                wc.getDouble("location.z"),
                (float) wc.getDouble("location.direction.yaw"),
                (float) wc.getDouble("location.direction.pitch")
        );
    }

    public YamlConfiguration getWarpByName(String name) {
        File warp = new File(playerWarps.getConfigHandler().warpsFolder + File.separator + name + ".yml");
        if (!warp.exists()) {
            return null;
        }
        return YamlConfiguration.loadConfiguration(warp);
    }

}
