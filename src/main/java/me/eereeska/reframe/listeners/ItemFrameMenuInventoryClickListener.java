package me.eereeska.reframe.listeners;

import me.eereeska.reframe.ReFrame;
import me.eereeska.reframe.gui.functions.ItemFrameFunctionsMenuInventoryHolder;
import me.eereeska.reframe.gui.menu.ItemFrameMenuInventoryHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemFrameMenuInventoryClickListener implements Listener {

    private final ReFrame plugin;

    public ItemFrameMenuInventoryClickListener(final ReFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onIconClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            final Player p = (Player) e.getWhoClicked();

            if (e.getView().getTopInventory().getHolder() instanceof ItemFrameMenuInventoryHolder) {
                e.setCancelled(true);

                final ItemStack clicked = e.getCurrentItem();

                if (clicked == null) {
                    return;
                }

                final ItemFrameMenuInventoryHolder inventory = (ItemFrameMenuInventoryHolder) e.getView().getTopInventory().getHolder();

                if (clicked.equals(inventory.toggleVisibilityIcon())) {
                    inventory.getItemFrame().setVisible(!inventory.getItemFrame().isVisible());
                    inventory.updateIcons();
                } else if (clicked.equals(inventory.toggleFixationIcon())) {
                    inventory.getItemFrame().setFixed(!inventory.getItemFrame().isFixed());
                    inventory.updateIcons();
                } else if (clicked.equals(inventory.functionsIcon())) {
                    p.openInventory(new ItemFrameFunctionsMenuInventoryHolder(plugin, inventory.getItemFrame()).getInventory());
                }
            }
        }
    }
}
