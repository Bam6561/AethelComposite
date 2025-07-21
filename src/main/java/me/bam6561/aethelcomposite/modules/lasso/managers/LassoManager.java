package me.bam6561.aethelcomposite.modules.lasso.managers;

import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Manages {@link Lasso.Item} interactions.
 *
 * @author Danny nguyen
 * @version 1.0.55
 * @since 1.0.55
 */
public class LassoManager {
  /**
   * No parameter constructor.
   */
  public LassoManager() {
  }

  /**
   * Captures the entity, if the {@link Lasso.Item} tier allows.
   *
   * @param event player interact entity event
   * @param tier  {@link Lasso.Item} tier
   */
  public void captureEntity(@NotNull PlayerInteractEntityEvent event, @NotNull Lasso.Item tier) {

  }

  /**
   * Releases the entity from the {@link Lasso.Item}.
   *
   * @param event player interact event
   */
  public void releaseEntity(@NotNull PlayerInteractEvent event) {

  }
}
