package me.bam6561.aethelcomposite.modules.core.guis;

import me.bam6561.aethelcomposite.Plugin;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * {@link Plugin} managed inventory.
 *
 * @author Danny Nguyen
 * @version 1.0.1
 * @since 1.0.1
 */
public abstract class GUI implements InventoryHandler {
  /**
   * Inventory.
   */
  private final Inventory inventory = createInventory();

  /**
   * No parameter constructor.
   */
  public GUI() {
  }

  /**
   * Creates the inventory.
   *
   * @return inventory
   */
  @NotNull
  protected abstract Inventory createInventory();

  /**
   * Adds inventory buttons.
   */
  protected abstract void addButtons();

  /**
   * What to do when a click occurs outside the inventory
   * view and clicks inside the player's inventory.
   *
   * @param event inventory click event
   * @return finished interaction
   */
  protected abstract boolean isNullOrPlayerInventoryClick(@NotNull InventoryClickEvent event);

  /**
   * Gets the inventory.
   *
   * @return inventory
   */
  @NotNull
  public Inventory getInventory() {
    return this.inventory;
  }
}