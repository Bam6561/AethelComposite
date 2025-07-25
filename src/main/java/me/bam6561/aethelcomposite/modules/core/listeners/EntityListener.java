package me.bam6561.aethelcomposite.modules.core.listeners;

import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import me.bam6561.aethelcomposite.modules.hook.objects.HookShotItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Collection of entity interaction listeners.
 *
 * @author Danny Nguyen
 * @version 1.1.13
 * @since 1.1.10
 */
public class EntityListener implements Listener {
  /**
   * No parameter listener.
   */
  public EntityListener() {
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

    ItemStack consumable = event.getConsumable();
    String itemID = ItemUtils.Read.getItemID(consumable);
    if (itemID == null) {
      return;
    }

    ModuleItemStack moduleItem = new ModuleItemStack(consumable);
    switch (moduleItem.getModuleName()) {
      case HOOK -> new HookShotItem(moduleItem.getItem()).fireProjectile(event);
    }
  }
}
