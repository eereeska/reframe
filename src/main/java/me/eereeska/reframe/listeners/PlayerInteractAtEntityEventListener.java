package me.eereeska.reframe.listeners;

import me.eereeska.reframe.ReFrame;
import me.eereeska.reframe.gui.menu.ItemFrameMenuInventoryHolder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.InventoryHolder;

public class PlayerInteractAtEntityEventListener implements Listener {

    private final ReFrame plugin;

    public PlayerInteractAtEntityEventListener(final ReFrame plugin) {
        this.plugin = plugin;
    }

    @EventHandler (ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onItemFrameBreak(PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof ItemFrame) {
//            if (!e.getHand().equals(EquipmentSlot.HAND)) {
//                return;
//            }

            final Player p = e.getPlayer();
            final ItemFrame itemFrame = (ItemFrame) e.getRightClicked();

            if (p.hasPermission(plugin.getConfig().getString("permission", "reframe.use"))) {
                if (p.isSneaking()) {
                    if (p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                        e.setCancelled(true);
                        p.openInventory(new ItemFrameMenuInventoryHolder(plugin, itemFrame).getInventory());
                    }
                } else {
                    final Block blockBehind = itemFrame.getLocation().getBlock().getRelative(itemFrame.getFacing().getOppositeFace());
                    final BlockState blockBehindState = blockBehind.getState();

                    if (blockBehindState instanceof InventoryHolder && !itemFrame.isVisible()) {
                        e.setCancelled(true);
                        p.openInventory(((InventoryHolder) blockBehindState).getInventory());
                    }
                }

                // Coming soon... THIS IS A SPOILER :(

//                    final List<String> functionsList = plugin.getItemFrameConfig().getFunctionsList(itemFrame);
//
//                    if (functionsList.size() > 0) {
//                        if (functionsList.contains("button-simulation")) {
//                            e.setCancelled(true);
//
//                            final Block holdingBlock = itemFrame.getLocation().getBlock().getRelative(itemFrame.getFacing().getOppositeFace());
//                            final BlockData holdingBlockData = holdingBlock.getBlockData();
//
//                            System.out.println(holdingBlock);
//
//                            if (holdingBlockData instanceof AnaloguePowerable) {
//                                final AnaloguePowerable analoguePowerableBlockData = (AnaloguePowerable) holdingBlockData;
//
//                                analoguePowerableBlockData.setPower(analoguePowerableBlockData.getPower() > 0 ? 0 : analoguePowerableBlockData.getMaximumPower());
//                                holdingBlock.setBlockData(holdingBlockData);
//                            }
//
//                            if (holdingBlockData instanceof Powerable) {
//                                final Powerable powerableBlockData = (Powerable) holdingBlockData;
//
//                                powerableBlockData.setPowered(!powerableBlockData.isPowered());
//                                holdingBlock.setBlockData(powerableBlockData);
//                            }
//
//                            if (holdingBlockData instanceof Openable) {
//                                final Openable openableBlockData = (Openable) holdingBlockData;
//
//                                openableBlockData.setOpen(!openableBlockData.isOpen());
//                                holdingBlock.setBlockData(openableBlockData);
//                            }
//
//                            if (holdingBlockData instanceof Lightable) {
//                                final Lightable lightableBlockData = (Lightable) holdingBlockData;
//
//                                lightableBlockData.setLit(!lightableBlockData.isLit());
//                                holdingBlock.setBlockData(lightableBlockData);
//                            }
//                        }
//                    }
            }
        }
    }
}
