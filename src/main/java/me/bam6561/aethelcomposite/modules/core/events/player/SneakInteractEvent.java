package me.bam6561.aethelcomposite.modules.core.events.player;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Called when a player interacts while sneaking.
 * <p>
 * May be cancelled without cancelling its source PlayerInteractEvent.
 *
 * @author Danny Nguyen
 * @version 1.0.75
 * @since 1.0.5
 */
public class SneakInteractEvent extends Event implements Cancellable {
  /**
   * Event handlers.
   */
  private static final HandlerList HANDLERS = new HandlerList();

  /**
   * Cancellation state.
   */
  private boolean isCancelled = false;

  /**
   * Interacting player.
   */
  private final Player player;

  /**
   * Interacting item.
   */
  private final ItemStack item;

  /**
   * Type of player action.
   */
  private final Action action;

  /**
   * Interacting block.
   */
  private final Block block;

  /**
   * Interacting block face.
   */
  private final BlockFace blockFace;

  /**
   * Clicked position on the block.
   */
  private final Vector clickedPosition;

  /**
   * Associates the event with its player, item in hand, action, block, block face, and position interacted with.
   *
   * @param player          interacting player
   * @param item            interacting item
   * @param action          type of player action
   * @param block           interacting block
   * @param blockFace       interacting block face
   * @param clickedPosition clicked position on the block
   */
  public SneakInteractEvent(@NotNull Player player, @Nullable ItemStack item, @NotNull Action action, @Nullable Block block, @NotNull BlockFace blockFace, @Nullable Vector clickedPosition) {
    this.player = Objects.requireNonNull(player, "Null player");
    this.item = item;
    this.action = Objects.requireNonNull(action, "Null action");
    this.block = block;
    this.blockFace = Objects.requireNonNull(blockFace, "Null block face");
    this.clickedPosition = clickedPosition;
  }

  /**
   * Gets the interacting player.
   *
   * @return interacting player
   */
  @NotNull
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Gets the interacting item
   *
   * @return interacting item
   */
  @Nullable
  public ItemStack getItem() {
    return this.item;
  }

  /**
   * Gets the type of player action.
   *
   * @return type of player action
   */
  @NotNull
  public Action getAction() {
    return this.action;
  }

  /**
   * Gets the interacting block.
   *
   * @return interacting block
   */
  @Nullable
  public Block getBlock() {
    return this.block;
  }

  /**
   * Gets the interacting block face.
   *
   * @return interacting block face
   */
  @NotNull
  public BlockFace getBlockFace() {
    return this.blockFace;
  }

  /**
   * Gets the clicked position on the block.
   *
   * @return clicked position on the block
   */
  @Nullable
  public Vector getClickedPosition() {
    return this.clickedPosition;
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
}