package me.bam6561.aethelcomposite.modules.core.events;

import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called before a {@link ModuleItemStack} with unique
 * durability behavior takes durability damage.
 * <p>
 * Cancellation prevents modifying the durability damage.
 * <p>
 * May be cancelled without cancelling its source PlayerItemDamageEvent.
 *
 * @author Danny Nguyen
 * @version 1.1.36
 * @since 1.1.35
 */
public class UniqueModuleItemDamageEvent extends Event implements Cancellable {
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
  private final PlayerItemDamageEvent source;

  /**
   * Associates the event with its source.
   *
   * @param source source of the event
   */
  public UniqueModuleItemDamageEvent(@NotNull PlayerItemDamageEvent source) {
    Objects.requireNonNull(source, "Null source");
    this.source = source;
  }

  /**
   * Gets the source of the event.
   *
   * @return source of the event
   */
  public PlayerItemDamageEvent getSource() {
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
