package me.eereeska.reframe.gui.functions;

import me.eereeska.reframe.ReFrame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemFrameFunctionsMenuInventoryHolder implements InventoryHolder {

    private final Inventory inv;
    private final ItemFrame itemFrame;

    public ItemFrameFunctionsMenuInventoryHolder(final ReFrame plugin, final ItemFrame itemFrame) {
        this.inv = Bukkit.createInventory(this, 27, plugin.getConfig().getString("phrases.functions", "Functions"));
        this.itemFrame = itemFrame;

        this.inv.setItem(11, this.buttonSimulationIcon());
    }

    public final ItemStack buttonSimulationIcon() {
        final ItemStack item = new ItemStack(Material.STONE_BUTTON);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Button Simulation");

        item.setItemMeta(meta);

        return item;
    }

    public final ItemFrame getItemFrame() {
        return this.itemFrame;
    }

    @Override
    public final Inventory getInventory() {
        return this.inv;
    }
}
