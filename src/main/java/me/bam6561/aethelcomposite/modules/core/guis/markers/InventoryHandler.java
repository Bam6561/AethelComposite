package me.bam6561.aethelcomposite.modules.core.guis.markers;

import me.bam6561.aethelcomposite.Plugin;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Outlines base interactions for a {@link Plugin} managed inventory.
 *
 * @author Danny Nguyen
 * @version 1.0.1
 * @since 1.0.1
 */
public interface InventoryHandler {
  /**
   * What to do when a click occurs.
   *
   * @param event inventory click event
   */
  void onClick(@NotNull InventoryClickEvent event);

  /**
   * What to do when a drag click occurs.
   *
   * @param event inventory drag event
   */
  void onDrag(@NotNull InventoryDragEvent event);

  /**
   * What to do when the inventory is opened.
   *
   * @param event inventory open event
   */
  void onOpen(@NotNull InventoryOpenEvent event);

  /**
   * What to do when the inventory is closed.
   *
   * @param event inventory close event
   */
  void onClose(@NotNull InventoryCloseEvent event);
}