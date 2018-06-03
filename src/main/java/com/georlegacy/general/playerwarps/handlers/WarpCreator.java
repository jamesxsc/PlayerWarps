package com.georlegacy.general.playerwarps.handlers;

import com.georlegacy.general.playerwarps.PlayerWarps;
import com.georlegacy.general.playerwarps.utils.TimeUtils;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WarpCreator {
    private final PlayerWarps playerWarps;

    public WarpCreator(PlayerWarps playerWarps) {
        this.playerWarps = playerWarps;
    }

    public boolean createWarp(Player p, String warpName) {
        File warpFile = new File(playerWarps.getConfigHandler().warpsFolder + File.separator + warpName + ".yml");
        if (warpFile.exists()) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().createWarpFailExistMsg));
            return false;
        } else {
            EconomyResponse r = playerWarps.econ.withdrawPlayer(p, playerWarps.getConfigHandler().createWarpCost);
            if (!(r.transactionSuccess())) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().createWarpFailBankruptMsg));
                return false;
            } else {
                try {
                    Files.copy(playerWarps.getClass().getClassLoader().getResourceAsStream("warpTemplate.yml"), warpFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                YamlConfiguration warpConfig = YamlConfiguration.loadConfiguration(warpFile);
                warpConfig.set("location.world", p.getWorld().getName());
                warpConfig.set("location.x", p.getLocation().getBlockX());
                warpConfig.set("location.y", p.getLocation().getBlockY());
                warpConfig.set("location.z", p.getLocation().getBlockZ());
                warpConfig.set("location.direction.pitch", p.getLocation().getPitch());
                warpConfig.set("location.direction.yaw", p.getLocation().getYaw());
                warpConfig.set("creator.username", p.getName());
                warpConfig.set("creator.uuid", p.getUniqueId().toString());
                warpConfig.set("name", warpName);
                warpConfig.set("timeCreated", TimeUtils.getCurrentTimeStamp());
                warpConfig.set("visits", 0);

                playerWarps.getPlayerUtils().addPlayerWarp(p);
                try {
                    warpConfig.save(warpFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
    }

}
