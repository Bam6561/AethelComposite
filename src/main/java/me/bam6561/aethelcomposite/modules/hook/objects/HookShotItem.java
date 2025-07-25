package me.bam6561.aethelcomposite.modules.hook.objects;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Represents a {@link Hook.Item#HOOK_SHOT}
 * <p>
 * HookShotItems are loadable crossbow ammunition that launch the user forward on impact.
 *
 * @author Danny Nguyen
 * @version 1.1.9
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
   * @param event    player interact event
   * @param crossbow crossbow to load
   */
  public void loadCrossbow(@NotNull PlayerInteractEvent event, @NotNull ItemStack crossbow) {
    Objects.requireNonNull(event, "Null event");
    Objects.requireNonNull(crossbow, "Null crossbow");
    PlayerInventory pInv = event.getPlayer().getInventory();
    int loadTime = 25;

    Integer quickCharge = crossbow.getEnchantments().get(Enchantment.QUICK_CHARGE);
    if (quickCharge != null) {
      loadTime = Math.max(0, loadTime - (quickCharge * 5));
    }

    Bukkit.getScheduler().runTaskLater(Plugin.getInstance(), () -> {
      if (!pInv.getItemInMainHand().equals(crossbow)) {
        return;
      }

      CrossbowMeta crossbowMeta = (CrossbowMeta) crossbow.getItemMeta();
      crossbowMeta.setLore(List.of(Text.Label.DETAILS.asColor() + "Ammunition: Hook Shot"));
      crossbowMeta.getPersistentDataContainer().set(Hook.Key.PROJECTILE.asKey(), PersistentDataType.STRING, "Hook Shot");
      crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW));
      crossbow.setItemMeta(crossbowMeta);

      ItemStack hookShotItem = pInv.getItemInOffHand();
      hookShotItem.setAmount(hookShotItem.getAmount() - 1);
    }, loadTime);
  }
}
