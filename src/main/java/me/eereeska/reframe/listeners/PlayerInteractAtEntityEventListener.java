package me.eereeska.reframe.listeners;

import me.eereeska.reframe.ReFrame;
import me.eereeska.reframe.gui.menu.ItemFrameMenuInventoryHolder;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractAtEntityEventListener implements Listener {

    private final ReFrame plugin;

    public PlayerInteractAtEntityEventListener(final ReFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler (ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onItemFrameBreak(PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof ItemFrame) {
            if (e.getPlayer().hasPermission(plugin.getConfig().getString("permission", "reframe.use"))) {
                if (e.getPlayer().isSneaking()) {
                    if (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                        e.setCancelled(true);
                        e.getPlayer().openInventory(new ItemFrameMenuInventoryHolder(plugin, (ItemFrame) e.getRightClicked()).getInventory());
                    }
                }
            }
        }
    }
}
