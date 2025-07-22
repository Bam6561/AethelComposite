package me.bam6561.aethelcomposite.modules.core.managers;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import me.bam6561.aethelcomposite.utils.TextUtils;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEntityEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.0.71
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
   *   <li> {@link #activateItemAbility(PlayerInteractEntityEvent, String)}
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

    activateItemAbility(event, itemID);
  }

  /**
   * Activates an item's ability.
   *
   * @param event  player interact entity event
   * @param itemID {@link Namespaced.Key#ITEM_ID}
   */
  private void activateItemAbility(PlayerInteractEntityEvent event, String itemID) {
    switch (itemID) {
      case "iron_lasso", "golden_lasso", "diamond_lasso", "emerald_lasso" ->
          Plugin.getLassoManager().captureEntity(event, Lasso.Item.valueOf(TextUtils.Format.asEnum(itemID)));
    }
  }
}
