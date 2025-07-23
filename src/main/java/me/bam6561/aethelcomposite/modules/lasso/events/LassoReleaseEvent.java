package me.bam6561.aethelcomposite.modules.lasso.events;

import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called before a player releases an entity with a {@link Lasso.Item}.
 * <p>
 * Cancellation prevents the entity from being released.
 *
 * @author Danny Nguyen
 * @version 1.0.87
 * @since 1.0.87
 */
public class LassoReleaseEvent extends Event implements Cancellable {
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
   * Entity data.
   */
  private final String entityData;

  /**
   * Associates the event with its player and entity data.
   *
   * @param player     interacting player
   * @param entityData entity data
   */
  public LassoReleaseEvent(@NotNull Player player, @NotNull String entityData) {
    this.player = Objects.requireNonNull(player, "Null player");
    this.entityData = Objects.requireNonNull(entityData, "Null entity data");
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
   * Gets the entity data.
   *
   * @return entity data
   */
  @NotNull
  public String getEntityData() {
    return this.entityData;
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
