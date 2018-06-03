package com.georlegacy.general.playerwarps;

import com.georlegacy.general.playerwarps.commands.CreatePWarpCommand;
import com.georlegacy.general.playerwarps.commands.DeletePWarpCommand;
import com.georlegacy.general.playerwarps.commands.PWarpCommand;
import com.georlegacy.general.playerwarps.handlers.ConfigHandler;
import com.georlegacy.general.playerwarps.handlers.WarpCreator;
import com.georlegacy.general.playerwarps.handlers.WarpGetter;
import com.georlegacy.general.playerwarps.handlers.WarpRemover;
import com.georlegacy.general.playerwarps.tabcompleters.PWarpTabCompleter;
import com.georlegacy.general.playerwarps.utils.ConfigUtils;
import com.georlegacy.general.playerwarps.utils.FolderUtils;
import com.georlegacy.general.playerwarps.utils.PlayerUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerWarps extends JavaPlugin {

    public static Economy econ = null;

    @Override
    public void onEnable() {
        registerCommands();
        this.getFolderUtils().loadDataFolder();
        this.getConfigUtils().loadConfig();
        this.getFolderUtils().loadWarpsFolder();

        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        else getLogger().info("Successfully found Vault dependency!");
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("setpwarp").setExecutor(new CreatePWarpCommand(this));

        getCommand("delpwarp").setExecutor(new DeletePWarpCommand(this));
        getCommand("delpwarp").setTabCompleter(new PWarpTabCompleter(this));

        getCommand("pwarp").setExecutor(new PWarpCommand(this));
        getCommand("pwarp").setTabCompleter(new PWarpTabCompleter(this));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private ConfigHandler configHandler = new ConfigHandler(this);
    private ConfigUtils configUtils = new ConfigUtils(this);
    private FolderUtils folderUtils = new FolderUtils(this);
    private PlayerUtils playerUtils = new PlayerUtils(this);
    private WarpCreator warpCreator = new WarpCreator(this);
    private WarpGetter warpGetter = new WarpGetter(this);
    private WarpRemover warpRemover = new WarpRemover(this);
    public ConfigHandler getConfigHandler() {
        return configHandler;
    }
    public ConfigUtils getConfigUtils() {
        return configUtils;
    }
    public FolderUtils getFolderUtils() {
        return folderUtils;
    }
    public PlayerUtils getPlayerUtils() {
        return playerUtils;
    }
    public WarpCreator getWarpCreator() {
        return warpCreator;
    }
    public WarpGetter getWarpGetter() {
        return warpGetter;
    }
    public WarpRemover getWarpRemover() {
        return warpRemover;
    }

}
