package me.bam6561.aethelcomposite.modules.core.listeners;

import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;

/**
 * Collection of living entity interaction listeners.
 *
 * @author Danny Nguyen
 * @version 1.1.10
 * @since 1.1.10
 */
public class LivingEntityListener implements Listener {
  /**
   * No parameter listener.
   */
  public LivingEntityListener() {
  }

  /**
   * Routes entity shooting bow interactions.
   *
   * @param event entity shoot bow event
   */
  @EventHandler
  private void onEntityShootBowEvent(EntityShootBowEvent event) {
    ItemStack bow = event.getBow();
    if (bow.getType() != Material.CROSSBOW) {
      return;
    }
  }
}
