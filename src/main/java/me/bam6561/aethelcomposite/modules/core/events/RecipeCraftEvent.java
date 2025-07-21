package me.bam6561.aethelcomposite.modules.core.events;

import me.bam6561.aethelcomposite.modules.core.references.Module;
import me.bam6561.aethelcomposite.modules.core.utils.RecipeCraftOperation;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called before crafting any {@link Module Module's} recipe.
 * <p>
 * Cancellation prevents the {@link RecipeCraftOperation} from occurring.
 *
 * @author Danny Nguyen
 * @version 1.0.50
 * @since 1.0.50
 */
public class RecipeCraftEvent extends Event implements Cancellable {
  /**
   * Event handlers.
   */
  private static final HandlerList HANDLERS = new HandlerList();

  /**
   * Cancellation state.
   */
  private boolean isCancelled = false;

  /**
   * {@link InventorySource}
   */
  private final InventorySource inventorySource;

  /**
   * If applicable, interacting player.
   */
  public Player player = null;

  /**
   * Associates the event with its {@link InventorySource} without player interaction.
   *
   * @param inventorySource {@link InventorySource}
   */
  public RecipeCraftEvent(@NotNull InventorySource inventorySource) {
    this.inventorySource = inventorySource;
  }

  /**
   * Associates the event with its {@link InventorySource} and interacting player.
   *
   * @param inventorySource {@link InventorySource}
   * @param player          interacting player
   */
  public RecipeCraftEvent(@NotNull InventorySource inventorySource, @NotNull Player player) {
    this.inventorySource = inventorySource;
    this.player = player;
  }

  /**
   * Gets the {@link InventorySource}.
   *
   * @return {@link InventorySource}
   */
  @NotNull
  public InventorySource getInventorySource() {
    return this.getInventorySource();
  }

  /**
   * Gets the interacting player.
   * <p>
   * Not all {@link RecipeCraftOperation RecipeCraftOperations}
   * may be triggered by a player, so this may return null.
   *
   * @return interacting player
   */
  @Nullable
  public Player getPlayer() {
    return this.player;
  }


  /**
   * If the event is cancelled.
   *
   * @return if the event is cancelled
   */
  @Override
  public boolean isCancelled() {
    return this.isCancelled;
  }

  /**
   * Sets the event's cancellation state.
   *
   * @param cancelled cancellation state
   */
  @Override
  public void setCancelled(boolean cancelled) {
    this.isCancelled = cancelled;
  }

  /**
   * Gets the event's handlers.
   *
   * @return event handlers
   */
  @NotNull
  @Override
  public HandlerList getHandlers() {
    return HANDLERS;
  }

  /**
   * Gets the event's handlers.
   *
   * @return event handlers
   */
  @NotNull
  public static HandlerList getHandlerList() {
    return HANDLERS;
  }

  /**
   * {@link RecipeCraftOperation RecipeCraftOperation's} inventory source.
   *
   * @author Danny Nguyen
   * @version 1.0.50
   * @since 1.0.50
   */
  public enum InventorySource {
    /**
     * Block.
     */
    BLOCK,

    /**
     * Non-player entity.
     */
    NONPLAYER,

    /**
     * Player.
     */
    PLAYER,

    /**
     * Indeterminate.
     */
    NULL
  }
}