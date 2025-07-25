package me.bam6561.aethelcomposite.modules.lasso.events;

import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called before a player captures an entity with a {@link Lasso.Item}.
 * <p>
 * Cancellation prevents the entity from being captured.
 *
 * @author Danny Nguyen
 * @version 1.0.68
 * @since 1.0.68
 */
public class LassoCaptureEvent extends Event implements Cancellable {
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
   * Interacting entity.
   */
  private final Entity entity;

  /**
   * Associates the event with its player and entity.
   *
   * @param player interacting player
   * @param entity interacting entity
   */
  public LassoCaptureEvent(@NotNull Player player, @NotNull Entity entity) {
    this.player = Objects.requireNonNull(player, "Null player");
    this.entity = Objects.requireNonNull(entity, "Null entity");
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
   * Gets the interacting entity.
   *
   * @return interacting entity.
   */
  @NotNull
  public Entity getEntity() {
    return this.entity;
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