package com.georlegacy.general.playerwarps.commands;

import com.georlegacy.general.playerwarps.PlayerWarps;
import com.georlegacy.general.playerwarps.handlers.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

public class DeletePWarpCommand implements CommandExecutor {
    private final PlayerWarps playerWarps;
    public DeletePWarpCommand(PlayerWarps playerWarps) {
        this.playerWarps = playerWarps;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("playerwarps.delete")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().deleteWarpFailPermMsg));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().incorrectArgsMsg));
            return true;
        }
        YamlConfiguration warp = playerWarps.getWarpGetter().getWarpByName(args[0]);
        if (warp==null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().deleteWarpFailExistMsg));
            return true;
        }
        if (warp.getString("creator.uuid").equals(((Player) sender).getUniqueId())) {
            playerWarps.getWarpRemover().deleteWarp(args[0]);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().deleteWarpSuccessMsg));
        } else {
            if (!sender.hasPermission("playerwarps.delete.others")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().deleteWarpFailPermMsg));
                return true;
            }
            playerWarps.getWarpRemover().deleteWarp(args[0]);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().deleteWarpSuccessMsg));
            return true;
        }
        return true;
    }

}
