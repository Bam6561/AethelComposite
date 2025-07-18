package me.bam6561.aethelcomposite.modules.core.managers;

import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEntityEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.0.22
 * @since 1.0.8
 */
public class SneakInteractEntityManager {
  /**
   * No parameter constructor.
   */
  public SneakInteractEntityManager() {
  }

  /**
   * On interaction:
   * <ul>
   *   <li>
   * </ul>
   *
   * @param event player interact entity event
   */
  public void interpretAction(@NotNull PlayerInteractEntityEvent event) {
    Objects.requireNonNull(event, "Null event");
  }
}
