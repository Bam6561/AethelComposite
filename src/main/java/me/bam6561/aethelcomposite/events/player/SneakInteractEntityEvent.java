package me.bam6561.aethelcomposite.events.player;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called when a player interacts with an entity while sneaking.
 * <p>
 * May be cancelled without cancelling its source PlayerInteractEntityEvent.
 *
 * @author Danny Nguyen
 * @version 1.0.5
 * @since 1.0.5
 */
public class SneakInteractEntityEvent extends Event implements Cancellable {
  /**
   * Event handlers.
   */
  private static final HandlerList HANDLERS = new HandlerList();

  /**
   * Cancellation state.
   */
  private boolean isCancelled = false;

  /**
   * Event source.
   */
  private final PlayerInteractEntityEvent source;

  /**
   * Associates the event with its source.
   *
   * @param source player interact entity event
   */
  public SneakInteractEntityEvent(@NotNull PlayerInteractEntityEvent source) {
    this.source = Objects.requireNonNull(source, "Null source");
  }

  /**
   * Gets the source event.
   *
   * @return player interact entity event
   */
  @NotNull
  public PlayerInteractEntityEvent getSource() {
    return this.source;
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
   * @param b cancellation state
   */
  @Override
  public void setCancelled(boolean b) {
    this.isCancelled = b;
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