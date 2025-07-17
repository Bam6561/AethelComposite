package me.bam6561.aethelcomposite.managers;

import me.bam6561.aethelcomposite.events.player.SneakInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEntityEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.0.19
 * @since 1.0.8
 */
public class SneakEntityInteractManager {
  /**
   * Player interact entity event.
   */
  private final PlayerInteractEntityEvent event;

  /**
   * Associates the interaction with its source.
   *
   * @param event player interact entity event
   */
  public SneakEntityInteractManager(@NotNull PlayerInteractEntityEvent event) {
    this.event = Objects.requireNonNull(event, "Null event");
  }

  /**
   * Currently does nothing.
   */
  public void interpretAction() {
  }
}
