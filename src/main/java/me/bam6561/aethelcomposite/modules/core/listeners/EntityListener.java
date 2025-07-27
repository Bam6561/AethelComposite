package me.bam6561.aethelcomposite.modules.core.listeners;

import me.bam6561.aethelcomposite.modules.core.objects.entity.ModuleEntity;
import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.utils.EntityUtils;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import me.bam6561.aethelcomposite.modules.hook.objects.HookShotEntity;
import me.bam6561.aethelcomposite.modules.hook.objects.HookShotItem;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Collection of entity interaction listeners.
 *
 * @author Danny Nguyen
 * @version 1.1.19
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
      case HOOK -> new HookShotItem(moduleItem.getItem()).modifyProjectile(event);
    }
  }

  /**
   * Routes projectile hit interactions.
   *
   * @param event projectile hit event
   */
  @EventHandler
  private void onProjectileHitEvent(ProjectileHitEvent event) {
    Entity projectile = event.getEntity();
    String entityID = EntityUtils.Read.getEntityID(projectile);
    if (entityID == null) {
      return;
    }

    ModuleEntity moduleEntity = new ModuleEntity(projectile);
    switch (moduleEntity.getModuleName()) {
      case HOOK -> new HookShotEntity(projectile).pullShooter(event);
    }
  }
}
