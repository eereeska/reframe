package me.eereeska.reframe.gui.menu;

import me.eereeska.reframe.ReFrame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ItemFrameMenuInventoryHolder implements InventoryHolder {

    private final ReFrame plugin;

    private final Inventory inv;
    private final ItemFrame itemFrame;

    public ItemFrameMenuInventoryHolder(final ReFrame plugin, final ItemFrame itemFrame) {
        this.plugin = plugin;

        this.inv = Bukkit.createInventory(this, 27, plugin.getConfig().getString("phrases.menu", "Menu"));
        this.itemFrame = itemFrame;

        this.updateIcons();
    }

    public final ItemStack toggleVisibilityIcon() {
        final ItemStack item = new ItemStack(this.itemFrame.isVisible() ? Material.ENDER_EYE : Material.ENDER_PEARL);
        final ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setDisplayName(this.plugin.getConfig().getString("phrases.visibility"));

        meta.setLore(Collections.singletonList(this.itemFrame.isVisible() ? this.plugin.getConfig().getString("phrases.visible") : this.plugin.getConfig().getString("phrases.invisible")));

        item.setItemMeta(meta);

        return item;
    }

    public final ItemStack toggleFixationIcon() {
        final ItemStack item = new ItemStack(this.itemFrame.isFixed() ? Material.BEDROCK : Material.GRASS_BLOCK);
        final ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setDisplayName(this.plugin.getConfig().getString("phrases.fixation"));

        meta.setLore(Collections.singletonList(this.itemFrame.isFixed() ? this.plugin.getConfig().getString("phrases.fixed") : this.plugin.getConfig().getString("phrases.unfixed")));

        item.setItemMeta(meta);

        return item;
    }

    public final ItemStack functionsIcon() {
        final ItemStack item = new ItemStack(Material.REDSTONE);
        final ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setDisplayName(this.plugin.getConfig().getString("phrases.functions"));

        item.setItemMeta(meta);

        return item;
    }

    public final void updateIcons() {
        this.inv.setItem(12, this.toggleVisibilityIcon());
        this.inv.setItem(14, this.toggleFixationIcon());
//        this.inv.setItem(15, this.functionsIcon());
    }

    public final ItemFrame getItemFrame() {
        return this.itemFrame;
    }

    @Override
    public final Inventory getInventory() {
        return this.inv;
    }
}
