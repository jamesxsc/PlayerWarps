package com.georlegacy.general.playerwarps.commands;

import com.georlegacy.general.playerwarps.PlayerWarps;
import com.georlegacy.general.playerwarps.handlers.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreatePWarpCommand implements CommandExecutor {
    private final PlayerWarps playerWarps;

    public CreatePWarpCommand(PlayerWarps playerWarps) {
        this.playerWarps = playerWarps;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + "&7Only players can set warps since the console does not have a location."));
            return true;
        }
        Player p = (Player) sender;
        if (args.length!=1) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().incorrectArgsMsg));
            return true;
        }
        if (!(p.hasPermission("playerwarps.create"))) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().createWarpFailPermMsg));
            return true;
        }
        if (playerWarps.getPlayerUtils().getPlayerWarps(p)>=playerWarps.getPlayerUtils().getMaxWarps(p)) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().createWarpFailMaxMsg));
            return true;
        }
        else {
            if (playerWarps.getWarpCreator().createWarp(p, args[0])) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().createWarpSuccessMsg));
                return true;
            } else {
                return true;
            }
        }
    }
}
