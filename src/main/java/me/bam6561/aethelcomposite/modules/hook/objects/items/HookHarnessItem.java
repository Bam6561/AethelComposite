package me.bam6561.aethelcomposite.modules.hook.objects.items;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.objects.item.markers.ActiveAbilityItem;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a {@link Hook.Item#HOOK_HARNESS}.
 * <p>
 * Hook Harnesses are leggings equipment that store and fire {@link Hook.Item#HOOK_SHOT} ammunition.
 * Unlike crossbow-fired {@link Hook.Item#HOOK_SHOT Hook Shots}, the {@link Hook.Item#HOOK_HARNESS}
 * emits two particle trails, with its targeting path centered between them.
 * <p>
 * Remaining ammunition in the {@link Hook.Item#HOOK_HARNESS} is represented by its durability bar.
 *
 * @author Danny Nguyen
 * @version 1.1.44
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

    World world = player.getWorld();
    Location eyeLoc = player.getEyeLocation();
    Location waistLoc = player.getLocation().add(0, 0.65, 0);

    world.playSound(eyeLoc, Sound.ENTITY_BREEZE_WIND_BURST, 0.35f, 0.25f);
    Bukkit.getScheduler().runTaskLater(Plugin.getInstance(), () -> world.playSound(eyeLoc, Sound.ENTITY_BREEZE_DEATH, 0.25f, 0.75f), 1);
    world.spawnParticle(Particle.CLOUD, waistLoc, 5, 0.1, 0.1, 0.1, 0.0125);

    Vector direction = eyeLoc.getDirection();
    Vector sideOffset = direction.clone().crossProduct(new Vector(0, 1, 0)).normalize().multiply(0.35);
    Location targetLoc = traceTargetLocation(world, waistLoc, direction, sideOffset, 32, 0);

    if (targetLoc == null) {
      return;
    }

    Vector targetVector = targetLoc.getBlock().getLocation().add(0.5, 0.5, 0.5).toVector();
    pullPlayerTowardsBlock(targetVector, player);
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

    player.getWorld().playSound(player.getEyeLocation(), Sound.ITEM_CROSSBOW_LOADING_MIDDLE, 1, 0.75f);

    int damage = damageableMeta.getDamage() - 15;
    Map<Enchantment, Integer> enchantments = getItem().getEnchantments();
    if (enchantments.containsKey(Enchantment.UNBREAKING)) {
      damage -= enchantments.get(Enchantment.UNBREAKING) * 20;
    }
    damageableMeta.setDamage(Math.max(0, damage));
    getItem().setItemMeta(damageableMeta);
  }

  /**
   * Traces an uninterrupted travel path from the starting location while emitting side particle trails.
   *
   * @param world      world
   * @param location   starting location
   * @param direction  facing direction
   * @param sideOffset particle trail side offset
   * @param range      range
   * @param tickDelay  delay in ticks
   * @return final location or null if out of range
   */
  private Location traceTargetLocation(World world, Location location, Vector direction, Vector sideOffset, int range, int tickDelay) {
    if (location.getBlock().getType().isSolid()) {
      return location;
    }
    if (range == 0) {
      return null;
    }

    location.add(direction);

    if (range % 2 == 0) {
      Location centerLoc = location.clone();
      Bukkit.getScheduler().runTaskLater(Plugin.getInstance(), () -> {
        world.spawnParticle(Particle.WAX_OFF, centerLoc.clone().add(sideOffset), 1, 0, 0, 0);
        world.spawnParticle(Particle.WAX_OFF, centerLoc.clone().subtract(sideOffset), 1, 0, 0, 0);
      }, tickDelay);
    }
    if (range % 8 == 0) {
      tickDelay += 2;
    }

    return traceTargetLocation(world, location, direction, sideOffset, range - 1, tickDelay);
  }

  /**
   * Continuously pulls the shooter towards the location until halt conditions are met.
   *
   * @param targetVector target vector
   * @param shooter      projectile shooter
   */
  private void pullPlayerTowardsBlock(Vector targetVector, Player shooter) {
    ItemStack leggings = shooter.getInventory().getLeggings();
    if (leggings.getType() != Material.LEATHER_LEGGINGS) {
      return;
    }
    String leggingsID = ItemUtils.Read.getItemID(leggings);
    if (leggingsID == null || !leggingsID.equals(ItemUtils.Read.getItemID(Hook.Item.HOOK_HARNESS.asItem()))) {
      return;
    }

    Vector pullVector = (targetVector.clone().subtract(shooter.getLocation().toVector())).normalize();
    shooter.setVelocity(pullVector.multiply(1.1));

    Bukkit.getScheduler().runTaskLater(Plugin.getInstance(), () -> pullPlayerTowardsBlock(targetVector, shooter), 2);
  }
}