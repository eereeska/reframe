package me.eereeska.reframe;

import me.eereeska.reframe.configs.ItemFrameConfig;
import me.eereeska.reframe.listeners.ItemFrameFunctionsMenuInventoryClickListener;
import me.eereeska.reframe.listeners.ItemFrameMenuInventoryClickListener;
import me.eereeska.reframe.listeners.PlayerInteractAtEntityEventListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ReFrame extends JavaPlugin {

    private ItemFrameConfig itemFrameConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        itemFrameConfig = new ItemFrameConfig(this);

        final PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerInteractAtEntityEventListener(this), this);
        pm.registerEvents(new ItemFrameMenuInventoryClickListener(this), this);
        pm.registerEvents(new ItemFrameFunctionsMenuInventoryClickListener(this), this);

        getLogger().info("§aEnabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cDisabled");
    }

    public final ItemFrameConfig getItemFrameConfig() {
        return this.itemFrameConfig;
    }
}
