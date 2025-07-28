package me.bam6561.aethelcomposite.modules.core.managers;

import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.objects.item.markers.ActiveAbilityItem;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import me.bam6561.aethelcomposite.modules.hook.objects.items.HookHarnessItem;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import me.bam6561.aethelcomposite.modules.lasso.objects.items.LassoItem;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEntityEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.1.24
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
   *   <li>{@link #activateLeggingsItemAbility(PlayerInteractEntityEvent, ItemStack}
   *   <li>{@link #activateMainHandItemAbility(PlayerInteractEntityEvent, ItemStack)}
   * </ul>
   *
   * @param event player interact entity event
   */
  public void interpretAction(@NotNull PlayerInteractEntityEvent event) {
    Objects.requireNonNull(event, "Null event");
    PlayerInventory pInv = event.getPlayer().getInventory();
    ItemStack leggingsItem = pInv.getLeggings();
    ItemStack mainHandItem = pInv.getItemInMainHand();

    if (ItemUtils.Read.isNotNullOrAir(leggingsItem)) {
      activateLeggingsItemAbility(event, leggingsItem);
    }
    if (ItemUtils.Read.isNotNullOrAir(mainHandItem)) {
      activateMainHandItemAbility(event, mainHandItem);
    }
  }

  /**
   * Activates the ability associated with the {@link ActiveAbilityItem} if it exists.
   *
   * @param event player interact event
   * @param item  interacting item
   */
  private void activateLeggingsItemAbility(PlayerInteractEntityEvent event, ItemStack item) {
    String itemID = ItemUtils.Read.getItemID(item);
    if (itemID == null) {
      return;
    }

    ModuleItemStack moduleItem = new ModuleItemStack(item);
    switch (moduleItem.getModuleName()) {
      case HOOK -> {
        HookHarnessItem hookHarnessItem = new HookHarnessItem(item);
        ItemStack offHandItem = event.getPlayer().getInventory().getItemInOffHand();
        if (ItemUtils.Read.isNullOrAir(offHandItem)) {
          hookHarnessItem.fireHookShot(event);
        } else if (ItemUtils.Read.getItemID(offHandItem).equals(ItemUtils.Read.getItemID(Hook.Item.HOOK_SHOT.asItem()))) {
          hookHarnessItem.reload(event);
        }
      }
    }
  }

  /**
   * Activates the ability associated with the {@link ActiveAbilityItem} if it exists.
   *
   * @param event player interact entity event
   * @param item  interacting item
   */
  private void activateMainHandItemAbility(PlayerInteractEntityEvent event, ItemStack item) {
    String itemID = ItemUtils.Read.getItemID(item);
    if (itemID == null) {
      return;
    }

    ModuleItemStack moduleItem = new ModuleItemStack(item);
    switch (moduleItem.getModuleName()) {
      case LASSO -> {
        LassoItem lassoItem = new LassoItem(item);
        if (!lassoItem.hasEntityData()) {
          lassoItem.captureEntity(event);
        } else {
          lassoItem.releaseEntity(event);
        }
      }
    }
  }
}
