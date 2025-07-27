package me.bam6561.aethelcomposite.modules.hook.objects.entities;

import me.bam6561.aethelcomposite.modules.core.objects.entity.ModuleEntity;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a {@link Hook.SpawnableEntity#HOOK_SHOT}.
 * <p>
 * Hook shots are projectiles that pull the shooter towards their point of impact.
 *
 * @author Danny Nguyen
 * @version 1.1.19
 * @since 1.1.19
 */
public class HookShotEntity extends ModuleEntity {
  /**
   * Associates the HookShotEntity with its entity.
   *
   * @param entity interacting entity
   */
  public HookShotEntity(@NotNull Entity entity) {
    super(Objects.requireNonNull(entity, "Null entity"));
    if (Hook.SpawnableEntity.HOOK_SHOT != Hook.SpawnableEntity.valueOf(TextUtils.Format.asEnum(getEntityID()))) {
      throw new IllegalArgumentException();
    }
    if (!(entity instanceof Arrow)) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Pulls the {@link Hook.SpawnableEntity#HOOK_SHOT} shooter towards its point of impact.
   *
   * @param event projectile hit event
   */
  public void pullShooter(@NotNull ProjectileHitEvent event) {
    Objects.requireNonNull(event, "Null event");
    Arrow hookShot = (Arrow) event.getEntity();
    hookShot.getPersistentDataContainer().remove(Namespaced.Key.Core.MODULE.asKey());
    hookShot.getPersistentDataContainer().remove(Namespaced.Key.Entity.ID.asKey());

    if (!(hookShot.getShooter() instanceof LivingEntity shooter)) {
      return;
    }
    if (shooter instanceof Player player) {
      PlayerInventory pInv = player.getInventory();
      if (pInv.getItemInMainHand().getType() != Material.CROSSBOW && pInv.getItemInOffHand().getType() != Material.CROSSBOW) {
        return;
      }
    }

    Location impactLoc = event.getEntity().getLocation();
    Location shooterLoc = shooter.getLocation();
    if (impactLoc.getWorld() != shooterLoc.getWorld()) {
      return;
    }

    Vector impactVector = impactLoc.toVector();
    Vector shooterVector = shooterLoc.toVector();
    Vector launchVector = (impactVector.subtract(shooterVector)).normalize();

    double distance = impactLoc.distance(shooterLoc);
    double amplitude = Math.max(2.5, distance / 4);

    launchVector = new Vector(launchVector.getX() * amplitude, launchVector.getY() * amplitude / 2, launchVector.getZ() * amplitude);
    shooter.setVelocity(launchVector);
  }
}
