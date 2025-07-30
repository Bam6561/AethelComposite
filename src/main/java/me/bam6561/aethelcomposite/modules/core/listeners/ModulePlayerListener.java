package me.bam6561.aethelcomposite.modules.core.listeners;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.events.UniqueModuleItemDamageEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.modules.core.managers.SneakInteractEntityManager;
import me.bam6561.aethelcomposite.modules.core.managers.SneakInteractManager;
import me.bam6561.aethelcomposite.modules.core.managers.UniqueModuleItemDamageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Collection of {@link Plugin}-related player interaction listeners.
 * <p>
 * Plugin-monitored player behavior is managed through:
 * <ul>
 *   <li>{@link SneakInteractManager}
 *   <li>{@link SneakInteractEntityManager}
 * </ul>
 *
 * @author Danny Nguyen
 * @version 1.1.39
 * @since 1.1.29
 */
public class ModulePlayerListener implements Listener {
  /**
   * {@link SneakInteractManager}
   */
  private final SneakInteractManager sneakInteractManager = Plugin.getSneakInteractManager();

  /**
   * {@link SneakInteractEntityManager}
   */
  private final SneakInteractEntityManager sneakInteractEntityManager = Plugin.getSneakInteractEntityManager();

  /**
   * {@link UniqueModuleItemDamageManager}
   */
  private final UniqueModuleItemDamageManager uniqueModuleItemDamageManager = Plugin.getUniqueModuleItemDamageManager();

  /**
   * Routes {@link SneakInteractEvent SneakInteractEvents}.
   *
   * @param event {@link SneakInteractEvent}
   */
  @EventHandler
  private void onSneakInteract(SneakInteractEvent event) {
    if (event.isCancelled()) {
      return;
    }
    sneakInteractManager.interpretAction(event.getSource());
  }

  /**
   * Routes {@link SneakInteractEntityEvent SneakInteractEntityEvents}.
   *
   * @param event {@link SneakInteractEntityEvent}
   */
  @EventHandler
  private void onSneakInteractEntity(SneakInteractEntityEvent event) {
    if (event.isCancelled()) {
      return;
    }
    sneakInteractEntityManager.interpretAction(event.getSource());
  }

  /**
   * Routes {@link UniqueModuleItemDamageEvent UniqueModuleItemDamageEvents}.
   *
   * @param event {@link UniqueModuleItemDamageEvent}
   */
  @EventHandler
  private void onUniqueModuleItemDamage(UniqueModuleItemDamageEvent event) {
    if (event.isCancelled()) {
      return;
    }
    uniqueModuleItemDamageManager.interpretDamage(event);
  }
}
