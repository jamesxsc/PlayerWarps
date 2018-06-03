package com.georlegacy.general.playerwarps.tabcompleters;

import com.georlegacy.general.playerwarps.PlayerWarps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PWarpTabCompleter implements TabCompleter {
    private final PlayerWarps playerWarps;

    public PWarpTabCompleter(PlayerWarps playerWarps) {
        this.playerWarps = playerWarps;
    }

    protected List<String> names = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        File file = new File(playerWarps.getDataFolder() + File.separator + "warps");
        names.clear();
        if (file.listFiles()==null) return null;
        File[] warps = file.listFiles();
        for (File warp : warps) {
            String s = null;
            int index = warp.getName().lastIndexOf(".yml");
            if (index>0) s = warp.getName().substring(0, index);
            names.add(s);
        }
        return names;
    }

}
