package com.georlegacy.general.playerwarps.commands;

import com.georlegacy.general.playerwarps.PlayerWarps;
import com.georlegacy.general.playerwarps.handlers.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PWarpCommand implements CommandExecutor {
    private final PlayerWarps playerWarps;
    public PWarpCommand(PlayerWarps playerWarps) {
        this.playerWarps = playerWarps;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("playerwarps.use")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().tpWarpFailPermMsg));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().incorrectArgsMsg));
            return true;
        }
        Location warp = playerWarps.getWarpGetter().getByName(args[0]);
        if (warp==null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().tpWarpFailExistMsg));
            return true;
        }
        ((Player) sender).teleport(warp);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.msgPrefix + playerWarps.getConfigHandler().tpWarpSuccessMsg));
        return true;
    }

}
