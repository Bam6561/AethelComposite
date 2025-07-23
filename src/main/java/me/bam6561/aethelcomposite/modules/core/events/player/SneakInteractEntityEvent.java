package me.bam6561.aethelcomposite.modules.core.events.player;

import me.bam6561.aethelcomposite.Plugin;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called when a player interacts with an entity while sneaking.
 * <p>
 * Cancellation prevents additional {@link Plugin} functionality.
 * <p>
 * May be cancelled without cancelling its source PlayerInteractEntityEvent.
 *
 * @author Danny Nguyen
 * @version 1.0.88
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
   * Source of the event.
   */
  private final PlayerInteractEntityEvent source;

  /**
   * Associates the event with its source, player, and entity.
   *
   * @param source source of the event
   */
  public SneakInteractEntityEvent(@NotNull PlayerInteractEntityEvent source) {
    this.source = Objects.requireNonNull(source, "Null source");
  }

  /**
   * Gets the source of the event.
   *
   * @return source of the event
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