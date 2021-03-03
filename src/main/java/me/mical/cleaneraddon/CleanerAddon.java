package me.mical.cleaneraddon;

import me.mical.cleaneraddon.config.ConfigManager;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.listener.InventoryListener;

public final class CleanerAddon extends PPlugin {

    @Override
    protected void preload() {
        pConfig = ConfigManager.getInstance();
    }

    @Override
    protected void afterInit() {
        registerListener(new InventoryListener(this));
    }
}
