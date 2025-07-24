package me.bam6561.aethelcomposite.modules.core.managers;

import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.modules.core.objects.item.markers.ActiveAbilityItem;
import me.bam6561.aethelcomposite.modules.lasso.objects.LassoItem;
import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEntityEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.0.96
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
   *   <li>{@link #activateItemAbility(PlayerInteractEntityEvent, ItemStack)}
   * </ul>
   *
   * @param event player interact entity event
   */
  public void interpretAction(@NotNull PlayerInteractEntityEvent event) {
    Objects.requireNonNull(event, "Null event");
    ItemStack mainHandItem = event.getPlayer().getInventory().getItemInMainHand();

    if (ItemUtils.Read.isNullOrAir(mainHandItem)) {
      return;
    }

    String itemID = ItemUtils.Read.getItemID(mainHandItem);
    if (itemID == null) {
      return;
    }

    activateItemAbility(event, mainHandItem);
  }

  /**
   * Activates the ability associated with the {@link ActiveAbilityItem} if it exists.
   *
   * @param event player interact entity event
   * @param item  interacting item
   */
  private void activateItemAbility(PlayerInteractEntityEvent event, ItemStack item) {
    ModuleItemStack moduleItem = new ModuleItemStack(item);
    switch (moduleItem.getModuleName()) {
      case LASSO -> new LassoItem(moduleItem.getItem()).captureEntity(event);
    }
  }
}
