package me.eereeska.reframe.listeners;

import me.eereeska.reframe.ReFrame;
import me.eereeska.reframe.gui.functions.ItemFrameFunctionsMenuInventoryHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemFrameFunctionsMenuInventoryClickListener implements Listener {

    private final ReFrame plugin;

    public ItemFrameFunctionsMenuInventoryClickListener(final ReFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onIconClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            final Player p = (Player) e.getWhoClicked();

            if (e.getView().getTopInventory().getHolder() instanceof ItemFrameFunctionsMenuInventoryHolder) {
                e.setCancelled(true);

                final ItemStack clicked = e.getCurrentItem();

                if (clicked == null) {
                    return;
                }

                final ItemFrameFunctionsMenuInventoryHolder inv = (ItemFrameFunctionsMenuInventoryHolder) e.getView().getTopInventory().getHolder();

                if (clicked.equals(inv.buttonSimulationIcon())) {
                    if (plugin.getItemFrameConfig().toggleFunction(inv.getItemFrame(), "button-simulation")) {
                        p.sendMessage("Enabled");
                    } else {
                        p.sendMessage("Disabled");
                    }
                }
            }
        }
    }
}
