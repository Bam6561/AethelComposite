package me.bam6561.aethelcomposite.modules.hook.objects.items;

import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.references.ModuleName;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.utils.EntityUtils;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a {@link Hook.Item#HOOK_SHOT}.
 * <p>
 * Hook shots are loadable crossbow ammunition that
 * launch the user to the projectile's point of impact.
 *
 * @author Danny Nguyen
 * @version 1.1.17
 * @since 1.1.6
 */
public class HookShotItem extends ModuleItemStack {
  /**
   * Associates the HookShotItem with its item.
   *
   * @param item interacting item
   */
  public HookShotItem(@NotNull ItemStack item) {
    super(Objects.requireNonNull(item, "Null item"));
    if (Hook.Item.HOOK_SHOT != Hook.Item.valueOf(TextUtils.Format.asEnum(getItemID()))) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Modifies the shot projectile into a {@link Hook.SpawnableEntity#HOOK_SHOT}.
   *
   * @param event entity shoot bow event
   */
  public void modifyProjectile(@NotNull EntityShootBowEvent event) {
    Objects.requireNonNull(event, "Null event");
    EntityUtils.Modify.setEntityData(event.getProjectile(), Namespaced.Key.Core.MODULE.asKey(), ModuleName.HOOK.asString(), Namespaced.Key.Entity.ID.asKey(), Hook.SpawnableEntity.HOOK_SHOT.asString());
  }
}
