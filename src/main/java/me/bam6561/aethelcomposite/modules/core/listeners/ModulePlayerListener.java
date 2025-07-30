package me.bam6561.aethelcomposite.modules.core.listeners;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.events.FormulaicModuleItemDamageEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.modules.core.managers.FormulaicModuleItemDamageManager;
import me.bam6561.aethelcomposite.modules.core.managers.SneakInteractEntityManager;
import me.bam6561.aethelcomposite.modules.core.managers.SneakInteractManager;
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
 * @version 1.1.40
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
   * {@link FormulaicModuleItemDamageManager}
   */
  private final FormulaicModuleItemDamageManager formulaicModuleItemDamageManager = Plugin.getFormulaicModuleItemDamageManager();

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
   * Routes {@link FormulaicModuleItemDamageEvent FormulaicModuleItemDamageEvents}.
   *
   * @param event {@link FormulaicModuleItemDamageEvent}
   */
  @EventHandler
  private void onFormulaicModuleItemDamage(FormulaicModuleItemDamageEvent event) {
    if (event.isCancelled()) {
      return;
    }
    event.getSource().setCancelled(true);
    formulaicModuleItemDamageManager.interpretDamage(event);
  }
}
