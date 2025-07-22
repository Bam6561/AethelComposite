package me.bam6561.aethelcomposite.modules.lasso.managers;

import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.lasso.events.LassoCaptureEvent;
import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import me.bam6561.aethelcomposite.utils.EntityUtils;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import me.bam6561.aethelcomposite.utils.TextUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Manages {@link Lasso.Item} interactions.
 *
 * @author Danny Nguyen
 * @version 1.0.69
 * @since 1.0.55
 */
public class LassoManager {
  /**
   * Entity types an {@link Lasso.Item#IRON_LASSO} can capture.
   */
  private static Set<EntityType> ironLassoCaptureable = Set.of(EntityType.CHICKEN, EntityType.COW, EntityType.SHEEP, EntityType.PIG);

  /**
   * New entity types an {@link Lasso.Item#GOLDEN_LASSO} can capture.
   * <p>
   * Chain previous tiers together to get a full set of captureable entity types.
   */
  private static Set<EntityType> goldenLassoCaptureable = Set.of(EntityType.ALLAY, EntityType.ARMADILLO, EntityType.AXOLOTL, EntityType.BAT, EntityType.BEE, EntityType.CAMEL, EntityType.CAT, EntityType.COD, EntityType.DOLPHIN, EntityType.DONKEY, EntityType.FOX, EntityType.FROG, EntityType.GLOW_SQUID, EntityType.GOAT, EntityType.HAPPY_GHAST, EntityType.HORSE, EntityType.LLAMA, EntityType.MOOSHROOM, EntityType.MULE, EntityType.OCELOT, EntityType.PANDA, EntityType.PARROT, EntityType.POLAR_BEAR, EntityType.PUFFERFISH, EntityType.RABBIT, EntityType.SALMON, EntityType.SHEEP, EntityType.SKELETON_HORSE, EntityType.SNIFFER, EntityType.SNOW_GOLEM, EntityType.SQUID, EntityType.STRIDER, EntityType.TADPOLE, EntityType.TRADER_LLAMA, EntityType.TROPICAL_FISH, EntityType.TURTLE, EntityType.WOLF, EntityType.ZOMBIE_HORSE);

  /**
   * New entity types an {@link Lasso.Item#DIAMOND_LASSO} can capture.
   * <p>
   * Chain previous tiers together to get a full set of captureable entity types.
   */
  private static Set<EntityType> diamondLassoCaptureable = Set.of(EntityType.BLAZE, EntityType.BOGGED, EntityType.BREEZE, EntityType.CAVE_SPIDER, EntityType.CREAKING, EntityType.CREEPER, EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HOGLIN, EntityType.HUSK, EntityType.IRON_GOLEM, EntityType.MAGMA_CUBE, EntityType.PHANTOM, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.PILLAGER, EntityType.RAVAGER, EntityType.SHULKER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.STRAY, EntityType.VEX, EntityType.VINDICATOR, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOGLIN, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN);

  /**
   * New entity types an {@link Lasso.Item#EMERALD_LASSO} can capture.
   * <p>
   * Chain previous tiers together to get a full set of captureable entity types.
   */
  private static Set<EntityType> emeraldLassoCaptureable = Set.of(EntityType.VILLAGER, EntityType.WANDERING_TRADER);

  /**
   * No parameter constructor.
   */
  public LassoManager() {
  }

  /**
   * Captures the entity, if the {@link Lasso.Item} tier allows.
   *
   * @param event player interact entity event
   * @param tier  {@link Lasso.Item} tier
   */
  public void captureEntity(@NotNull PlayerInteractEntityEvent event, @NotNull Lasso.Item tier) {
    Player player = event.getPlayer();
    PlayerInventory inv = player.getInventory();
    Entity entity = event.getRightClicked();

    if (isCaptureable(tier, entity.getType())) {
      LassoCaptureEvent lassoCaptureEvent = new LassoCaptureEvent(player, entity);
      Bukkit.getPluginManager().callEvent(lassoCaptureEvent);
      if (lassoCaptureEvent.isCancelled()) {
        return;
      }
      event.setCancelled(true);
      storeEntityAsData(player, inv, entity);
    } else {
      event.setCancelled(true);
      player.sendMessage(Text.Label.INVALID.asColor() + "[!] Failed to capture entity.");
    }
  }

  /**
   * Releases the entity from the {@link Lasso.Item}.
   *
   * @param event player interact event
   */
  public void releaseEntity(@NotNull PlayerInteractEvent event) {

  }

  /**
   * If the entity type is captureable by the {@link Lasso.Item} tier.
   *
   * @param tier       {@link Lasso.Item} tier
   * @param entityType entity type
   * @return the entity type is captureable
   */
  private boolean isCaptureable(Lasso.Item tier, EntityType entityType) {
    switch (tier) {
      case IRON_LASSO -> {
        if (ironLassoCaptureable.contains(entityType)) {
          return true;
        }
      }
      case GOLDEN_LASSO -> {
        if (ironLassoCaptureable.contains(entityType) || goldenLassoCaptureable.contains(entityType)) {
          return true;
        }
      }
      case DIAMOND_LASSO -> {
        if (ironLassoCaptureable.contains(entityType) || goldenLassoCaptureable.contains(entityType) || diamondLassoCaptureable.contains(entityType)) {
          return true;
        }
      }
      case EMERALD_LASSO -> {
        if (ironLassoCaptureable.contains(entityType) || goldenLassoCaptureable.contains(entityType) || diamondLassoCaptureable.contains(entityType) || emeraldLassoCaptureable.contains(entityType)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Stores the entity into the {@link Lasso.Item} and removes it from the world.
   *
   * @param player interacting player
   * @param inv    player inventory
   * @param entity interacting entity
   */
  private void storeEntityAsData(Player player, PlayerInventory inv, Entity entity) {
    ItemStack mainHandItem = inv.getItemInMainHand();
    ItemStack lasso = mainHandItem.clone();
    ItemMeta meta = lasso.getItemMeta();
    PersistentDataContainer lassoData = meta.getPersistentDataContainer();
    String newItemID = ItemUtils.Read.getItemID(lasso) + "'d";

    lasso.setAmount(1);
    lassoData.set(Namespaced.Key.ITEM_ID.asKey(), PersistentDataType.STRING, newItemID);
    lassoData.set(Lasso.Key.LASSO_ENTITY_DATA.asKey(), PersistentDataType.STRING, EntityUtils.Data.encodeEntityString(entity));
    meta.setLore(List.of(
        Text.Label.ACTION.asColor() + "Release " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
        Text.Label.DETAILS.asColor() + "Releases the stored creature.",
        Text.Label.DETAILS.asColor() + entity.getName() + " [" + TextUtils.Format.asTitle(entity.getType().name()) + "]",
        Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + TextUtils.Format.asTitle(newItemID)));
    meta.setEnchantmentGlintOverride(true);
    lasso.setItemMeta(meta);

    mainHandItem.setAmount(mainHandItem.getAmount() - 1);
    inv.setItemInMainHand(mainHandItem);

    if (inv.firstEmpty() != -1) {
      inv.addItem(lasso);
    } else {
      player.getWorld().dropItem(player.getLocation(), lasso);
    }

    entity.remove();
  }
}
