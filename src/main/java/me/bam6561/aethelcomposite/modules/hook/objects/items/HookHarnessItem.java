package me.bam6561.aethelcomposite.modules.hook.objects.items;

import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.objects.item.markers.ActiveAbilityItem;
import me.bam6561.aethelcomposite.modules.core.references.ModuleName;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.utils.EntityUtils;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a {@link Hook.Item#HOOK_HARNESS}.
 * <p>
 * Hook Harnesses are leggings equipment that store and fire {@link Hook.Item#HOOK_SHOT} ammunition.
 * <p>
 * Remaining ammunition in the harness is represented by its durability bar.
 *
 * @author Danny Nguyen
 * @version 1.1.41
 * @since 1.1.21
 */
public class HookHarnessItem extends ModuleItemStack implements ActiveAbilityItem {
  /**
   * Associates the HookHarnessItem with its item.
   *
   * @param item interacting item
   */
  public HookHarnessItem(@NotNull ItemStack item) {
    super(Objects.requireNonNull(item, "Null item"));
    if (Hook.Item.HOOK_HARNESS != Hook.Item.valueOf(TextUtils.Format.asEnum(getItemID()))) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Fires loaded {@link Hook.Item#HOOK_SHOT} ammunition.
   *
   * @param player interacting player
   */
  public void fireHookShot(@NotNull Player player) {
    Objects.requireNonNull(player, "Null player");
    Damageable damageableMeta = ((Damageable) getItem().getItemMeta());
    int damage = damageableMeta.getDamage();
    int maxDamage = damageableMeta.hasMaxDamage() ? damageableMeta.getMaxDamage() : getItem().getType().getMaxDurability();
    if (damage == maxDamage) {
      player.sendMessage(Text.Label.DETAILS.asColor() + "H.O.O.K Harness: " + Text.Label.INVALID.asColor() + "Empty ammunition.");
      return;
    }
    damageableMeta.setDamage(damage + 1);
    getItem().setItemMeta(damageableMeta);

    Entity projectile = player.launchProjectile(Arrow.class, player.getLocation().getDirection());
    EntityUtils.Modify.setEntityData(projectile, Namespaced.Key.Core.MODULE.asKey(), ModuleName.HOOK.asString(), Namespaced.Key.Entity.ID.asKey(), Hook.SpawnableEntity.HOOK_SHOT.asString());
    projectile.setVelocity(projectile.getVelocity().multiply(3));
  }

  /**
   * Reloads the Hook Harness with {@link Hook.Item#HOOK_SHOT} ammunition.
   *
   * @param player interacting player
   */
  public void reload(@NotNull Player player) {
    Objects.requireNonNull(player, "Null player");
    Damageable damageableMeta = ((Damageable) getItem().getItemMeta());
    if (!damageableMeta.hasDamage()) {
      player.sendMessage(Text.Label.DETAILS.asColor() + "H.O.O.K Harness: " + Text.Label.INVALID.asColor() + "Full ammunition.");
      return;
    }

    ItemStack ammunition = player.getInventory().getItemInOffHand();
    int amount = ammunition.getAmount();
    if (amount < 2) {
      player.sendMessage(Text.Label.DETAILS.asColor() + "H.O.O.K Harness: " + Text.Label.INVALID.asColor() + "Reloads with 2 Hook Shots.");
      return;
    }
    ammunition.setAmount(amount - 2);

    int damage = damageableMeta.getDamage() - 15;
    Map<Enchantment, Integer> enchantments = getItem().getEnchantments();
    if (enchantments.containsKey(Enchantment.UNBREAKING)) {
      damage -= enchantments.get(Enchantment.UNBREAKING) * 20;
    }
    damageableMeta.setDamage(Math.max(0, damage));
    getItem().setItemMeta(damageableMeta);
  }
}
