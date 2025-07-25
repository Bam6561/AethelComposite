package me.bam6561.aethelcomposite.modules.core.events.player;

import me.bam6561.aethelcomposite.Plugin;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called when a player interacts while sneaking.
 * <p>
 * Cancellation prevents additional {@link Plugin} functionality.
 * <p>
 * May be cancelled without cancelling its source PlayerInteractEvent.
 *
 * @author Danny Nguyen
 * @version 1.0.88
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
   * Source of the event.
   */
  private final PlayerInteractEvent source;

  /**
   * Associates the event with its source.
   *
   * @param source source of the event
   */
  public SneakInteractEvent(@NotNull PlayerInteractEvent source) {
    this.source = Objects.requireNonNull(source, "Null source");
  }

  /**
   * Gets the source of the event.
   *
   * @return source of the event
   */
  @NotNull
  public PlayerInteractEvent getSource() {
    return this.source;
  }

  /**
   * Checks whether the event is cancelled.
   *
   * @return cancellation state
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