package me.bam6561.aethelcomposite.modules.hook.events;

import me.bam6561.aethelcomposite.modules.hook.objects.entities.HookShotEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called before the {@link HookShotEntity HookShotEntity's} shooter is
 * pulled towards the {@link HookShotEntity HookShotEntity's} point of impact.
 * <p>
 * Cancellation prevents the {@link HookShotEntity HookShotEntity's} shooter from being pulled.
 * <p>
 * May be cancelled without cancelling its source ProjectileHitEvent.
 *
 * @author Danny Nguyen
 * @version 1.1.35
 * @since 1.1.34
 */
public class HookShotHitEvent extends Event implements Cancellable {
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
  private final ProjectileHitEvent source;

  /**
   * Associates the event with its source.
   *
   * @param source source of the event
   */
  public HookShotHitEvent(@NotNull ProjectileHitEvent source) {
    this.source = Objects.requireNonNull(source, "Null source");
  }

  /**
   * Gets the source of the event.
   *
   * @return source of the event
   */
  @NotNull
  public ProjectileHitEvent getSource() {
    return this.source;
  }

  /**
   * Checks whether the event is cancelled.
   *
   * @return event cancellation state
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
