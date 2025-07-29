package me.bam6561.aethelcomposite.modules.hook.objects.entities;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.objects.entity.ModuleEntity;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a {@link Hook.SpawnableEntity#HOOK_SHOT}.
 * <p>
 * Hook shots are projectiles that pull the shooter towards their point of impact.
 *
 * @author Danny Nguyen
 * @version 1.1.28
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
    Arrow hookShotEntity = (Arrow) event.getEntity();
    hookShotEntity.getPersistentDataContainer().remove(Namespaced.Key.Core.MODULE.asKey());
    hookShotEntity.getPersistentDataContainer().remove(Namespaced.Key.Entity.ID.asKey());
    hookShotEntity.setItem(new ItemStack(Material.ARROW));

    if (!(hookShotEntity.getShooter() instanceof LivingEntity shooter)) {
      return;
    }
    if (shooter instanceof Player player && player.getInventory().getItemInMainHand().getType() != Material.CROSSBOW) {
      return;
    }

    Location impactLoc = event.getEntity().getLocation();
    Location shooterLoc = shooter.getLocation();
    if (impactLoc.getWorld() != shooterLoc.getWorld()) {
      return;
    }
    if (impactLoc.distance(shooterLoc) > 32) {
      return;
    }

    if (event.getHitBlock() != null) {
      if (shooter instanceof Player player) {
        pullTowardsBlock(event.getHitBlock(), player);
      } else {
        pullTowardsBlock(event.getHitBlock(), shooter, 100);
      }
      return;
    }

    if (event.getHitEntity() != null) {
      if (shooter instanceof Player player) {
        pullTowardsEntity(event.getHitEntity(), player);
      } else {
        pullTowardsEntity(event.getHitEntity(), shooter, 100);
      }
    }
  }

  /**
   * Continuously pulls the player-defined shooter towards the block until halt conditions are met.
   *
   * @param block   interacting block
   * @param shooter projectile shooter
   */
  private void pullTowardsBlock(Block block, Player shooter) {
    PlayerInventory pInv = shooter.getInventory();
    if (pInv.getItemInMainHand().getType() != Material.CROSSBOW) {
      return;
    }
    if (((CrossbowMeta) pInv.getItemInMainHand().getItemMeta()).hasChargedProjectiles()) {
      return;
    }
    if (block.getType() != block.getLocation().getBlock().getType()) {
      return;
    }

    Vector blockVector = block.getLocation().add(0.5, 0.5, 0.5).toVector();
    Vector pullVector = (blockVector.subtract(shooter.getLocation().toVector())).normalize();
    shooter.setVelocity(pullVector.multiply(0.5));

    Bukkit.getScheduler().runTaskLater(Plugin.getInstance(), () -> pullTowardsBlock(block, shooter), 2);
  }

  /**
   * Continuously pulls the player-defined shooter towards the entity until halt conditions are met.
   *
   * @param entity  interacting entity
   * @param shooter projectile shooter
   */
  private void pullTowardsEntity(Entity entity, Player shooter) {
    PlayerInventory pInv = shooter.getInventory();
    if (pInv.getItemInMainHand().getType() != Material.CROSSBOW) {
      return;
    }
    if (((CrossbowMeta) pInv.getItemInMainHand().getItemMeta()).hasChargedProjectiles()) {
      return;
    }

    Location entityLocation = entity.getLocation();
    Location shooterLocation = shooter.getLocation();
    if (entityLocation.distance(shooterLocation) > 32 || !entity.getWorld().equals(shooter.getWorld()) || entity.isDead()) {
      return;
    }

    Vector pullVector = (entityLocation.toVector().subtract(shooterLocation.toVector())).normalize();
    shooter.setVelocity(pullVector.multiply(0.5));

    Bukkit.getScheduler().runTaskLater(Plugin.getInstance(), () -> pullTowardsEntity(entity, shooter), 2);
  }

  /**
   * Continuously pulls the non-player-defined shooter towards the block until halt conditions are met.
   *
   * @param block   interacting block
   * @param shooter projectile shooter
   * @param ticks   duration
   */
  private void pullTowardsBlock(Block block, LivingEntity shooter, long ticks) {
    if (ticks <= 0) {
      return;
    }

    if (block.getType() != block.getLocation().getBlock().getType()) {
      return;
    }

    Vector blockVector = block.getLocation().add(0.5, 0.5, 0.5).toVector();
    Vector pullVector = (blockVector.subtract(shooter.getLocation().toVector())).normalize();
    shooter.setVelocity(pullVector.multiply(0.5));

    Bukkit.getScheduler().runTaskLater(Plugin.getInstance(), () -> pullTowardsBlock(block, shooter, ticks - 2), 2);
  }

  /**
   * Continuously pulls the non-player-defined shooter towards the block until halt conditions are met.
   *
   * @param entity  interacting block
   * @param shooter projectile shooter
   * @param ticks   duration
   */
  private void pullTowardsEntity(Entity entity, LivingEntity shooter, long ticks) {
    if (ticks <= 0) {
      return;
    }

    Location entityLocation = entity.getLocation();
    Location shooterLocation = shooter.getLocation();
    if (entityLocation.distance(shooterLocation) > 64 || !entity.getWorld().equals(shooter.getWorld()) || entity.isDead()) {
      return;
    }

    Vector pullVector = (entityLocation.toVector().subtract(shooterLocation.toVector())).normalize();
    shooter.setVelocity(pullVector.multiply(0.5));

    Bukkit.getScheduler().runTaskLater(Plugin.getInstance(), () -> pullTowardsEntity(entity, shooter, ticks - 2), 2);
  }
}
