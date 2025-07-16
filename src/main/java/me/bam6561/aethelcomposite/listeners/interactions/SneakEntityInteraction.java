package me.bam6561.aethelcomposite.listeners.interactions;

import me.bam6561.aethelcomposite.events.player.SneakInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * {@link SneakInteractEntityEvent} interaction.
 *
 * @author Danny Nguyen
 * @version 1.0.8
 * @since 1.0.8
 */
public class SneakEntityInteraction {
  /**
   * Player interact entity event.
   */
  private final PlayerInteractEntityEvent event;

  /**
   * Associates the interaction with its source.
   *
   * @param event player interact entity event
   */
  public SneakEntityInteraction(PlayerInteractEntityEvent event) {
    this.event = event;
  }

  /**
   * Currently does nothing.
   */
  public void interpretAction() {
  }
}
