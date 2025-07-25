package me.bam6561.aethelcomposite.modules.hook.objects;

import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Represents a {@link Hook.Item#HOOK_SHOT}
 * <p>
 * HookShotItems are loadable crossbow ammunition that launch the user forward on impact.
 *
 * @author Danny Nguyen
 * @version 1.1.6
 * @since 1.1.6
 */
public class HookShotItem extends ModuleItemStack {
  /**
   * Associates the HookShotItem with its item.
   *
   * @param item interacting item
   */
  public HookShotItem(@NotNull ItemStack item) {
    super(item);
    if (Hook.Item.HOOK_SHOT != Hook.Item.valueOf(TextUtils.Format.asEnum(getItemID()))) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Loads the crossbow with a {@link Hook.Item#HOOK_SHOT}.
   *
   * @param event player interact event
   */
  public void loadCrossbow(@NotNull PlayerInteractEvent event) {
    Player player = event.getPlayer();
    PlayerInventory pInv = player.getInventory();

    ItemStack mainHandItem = pInv.getItemInMainHand();
    if (mainHandItem.getType() != Material.CROSSBOW) {
      return;
    }

    CrossbowMeta crossbowMeta = (CrossbowMeta) mainHandItem.getItemMeta();
    if (crossbowMeta.hasChargedProjectiles()) {
      return;
    }

    crossbowMeta.setLore(List.of("Ammunition: Hook Shot"));
    crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW));
    mainHandItem.setItemMeta(crossbowMeta);

    ItemStack hookShotItem = pInv.getItemInOffHand();
    hookShotItem.setAmount(hookShotItem.getAmount() - 1);
  }
}
