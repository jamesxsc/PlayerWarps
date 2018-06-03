package com.georlegacy.general.playerwarps.handlers;

import com.georlegacy.general.playerwarps.PlayerWarps;

import java.io.File;

public class WarpRemover {
    private final PlayerWarps playerWarps;
    public WarpRemover(PlayerWarps playerWarps) {
        this.playerWarps = playerWarps;
    }

    public void deleteWarp(String warpName) {
        File warp = new File(playerWarps.getConfigHandler().warpsFolder + File.separator + warpName);
        warp.delete();
    }

}
