package me.bam6561.aethelcomposite.modules.hook.objects.items;

import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.objects.item.markers.ActiveAbilityItem;
import me.bam6561.aethelcomposite.modules.core.references.ModuleName;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.utils.EntityUtils;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a {@link Hook.Item#HOOK_HARNESS}.
 * <p>
 * Hook harnesses are leggings equipment that store
 * and fire {@link Hook.Item#HOOK_SHOT} ammunition.
 *
 * @author Danny Nguyen
 * @version 1.1.23
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
   * @param event player interact event
   */
  public void fireHookShot(@NotNull PlayerInteractEvent event) {
    Objects.requireNonNull(event, "Null event");
    Player player = event.getPlayer();

    Location loc = player.getLocation();
    Vector direction = loc.getDirection();

    Entity projectile = player.launchProjectile(Arrow.class, direction);
    EntityUtils.Modify.setEntityData(projectile, Namespaced.Key.Core.MODULE.asKey(), ModuleName.HOOK.asString(), Namespaced.Key.Entity.ID.asKey(), Hook.SpawnableEntity.HOOK_SHOT.asString());
    projectile.setVelocity(projectile.getVelocity().multiply(3));
  }

  /**
   * Reloads the Hook Harness with {@link Hook.Item#HOOK_SHOT} ammunition.
   *
   * @param event player interact event
   */
  public void reload(@NotNull PlayerInteractEvent event) {
  }
}
