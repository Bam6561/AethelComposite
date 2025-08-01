package me.bam6561.aethelcomposite.modules.core.events.gui;

import me.bam6561.aethelcomposite.modules.core.guis.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Called before opening a {@link GUI} from a neutral state.
 * <p>
 * Will not be called when a player:
 * <ul>
 *   <li>navigates between {@link GUI GUIs} through buttons
 *   <li>reopens a {@link GUI} by responding to a message input request
 * </ul>
 * <p>
 * Cancellation prevents the {@link GUI} from opening.
 *
 * @author Danny Nguyen
 * @version 1.0.95
 * @since 1.0.4
 */
public class GUIOpenEvent extends Event implements Cancellable {
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
   * {@link Type}
   */
  private final Type type;

  /**
   * {@link Cause}
   */
  private final Cause cause;

  /**
   * Associates the event with its player, {@link Type}, and {@link Cause}.
   *
   * @param player interacting player
   * @param type   {@link Type}
   * @param cause  {@link Cause}
   */
  public GUIOpenEvent(@NotNull Player player, @NotNull Type type, @NotNull Cause cause) {
    this.player = Objects.requireNonNull(player, "Null player");
    this.type = Objects.requireNonNull(type, "Null type");
    this.cause = Objects.requireNonNull(cause, "Null cause");
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
   * Gets the {@link Type}.
   *
   * @return {@link Type}
   */
  @NotNull
  public Type getType() {
    return this.type;
  }

  /**
   * Gets the {@link Cause}.
   *
   * @return {@link Cause}
   */
  @NotNull
  public Cause getCause() {
    return this.cause;
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

  /**
   * Type of {@link GUI} being opened.
   *
   * @author Danny Nguyen
   * @version 1.0.74
   * @since 1.0.74
   */
  public enum Type {
    /**
     * {@link me.bam6561.aethelcomposite.modules.core.guis.blocks.WorkbenchGUI}
     */
    WORKBENCH
  }

  /**
   * Cause of the {@link GUIOpenEvent}.
   *
   * @author Danny Nguyen
   * @version 1.0.4
   * @since 1.0.4
   */
  public enum Cause {
    /**
     * By command.
     */
    COMMAND,

    /**
     * By world interaction.
     */
    INTERACTION
  }
}