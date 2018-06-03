package com.georlegacy.general.playerwarps.handlers;

import com.georlegacy.general.playerwarps.PlayerWarps;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigHandler {
    private final PlayerWarps playerWarps;
    private YamlConfiguration config;
    public ConfigHandler(PlayerWarps playerWarps) {
        if (!playerWarps.getDataFolder().exists())
            playerWarps.getDataFolder().mkdirs();
        this.playerWarps = playerWarps;
        File configFile = new File(playerWarps.getDataFolder() + File.separator + "config.yml");
        if(!configFile.exists())
            playerWarps.saveResource("config.yml", true);
        config = YamlConfiguration.loadConfiguration(configFile);
        loadValues();
    }

    public static final String msgPrefix = "&1[&9Player&fWarps&1] ";

    public int createWarpCost;

    public String createWarpSuccessMsg;
    public String createWarpFailPermMsg;
    public String createWarpFailMaxMsg;
    public String createWarpFailBankruptMsg;
    public String createWarpFailExistMsg;
    public String deleteWarpSuccessMsg;
    public String deleteWarpFailPermMsg;
    public String deleteWarpFailOwnMsg;
    public String deleteWarpFailExistMsg;
    public String tpWarpSuccessMsg;
    public String tpWarpFailExistMsg;
    public String tpWarpFailPermMsg;
    public String listWarpsHeader;
    public String listWarpsFailPermMsg;
    public String listWarpsFailEmptyMsg;
    public String incorrectArgsMsg;
    File warpsFolder;

    private void loadValues() {
        createWarpCost = config.getInt("createWarpCost");
        createWarpSuccessMsg = config.getString("createWarpSuccessMsg");
        createWarpFailPermMsg = config.getString("createWarpFailPermMsg");
        createWarpFailMaxMsg = config.getString("createWarpFailMaxMsg");
        createWarpFailBankruptMsg = config.getString("createWarpFailBankruptMsg");
        createWarpFailExistMsg = config.getString("createWarpFailExistMsg");
        deleteWarpSuccessMsg = config.getString("deleteWarpSuccessMsg");
        deleteWarpFailPermMsg = config.getString("deleteWarpFailPermMsg");
        deleteWarpFailOwnMsg = config.getString("deleteWarpFailOwnMsg");
        deleteWarpFailExistMsg = config.getString("deleteWarpFailExistMsg");
        tpWarpSuccessMsg = config.getString("tpWarpSuccessMsg");
        tpWarpFailExistMsg = config.getString("tpWarpFailExistMsg");
        tpWarpFailPermMsg = config.getString("tpWarpFailPermMsg");
        listWarpsHeader = config.getString("listWarpsHeader");
        listWarpsFailPermMsg = config.getString("listWarpsFailPermMsg");
        listWarpsFailEmptyMsg = config.getString("listWarpsFailEmptyMsg");
        incorrectArgsMsg = config.getString("incorrectArgsMsg");
        warpsFolder = new File(playerWarps.getDataFolder() + File.separator + "warps");
    }

}
