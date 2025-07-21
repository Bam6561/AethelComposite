package me.bam6561.aethelcomposite.modules.lasso.managers;

import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Manages {@link Lasso.Item} interactions.
 *
 * @author Danny nguyen
 * @version 1.0.56
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
  private static Set<EntityType> goldenLassoCaptureable = Set.of(EntityType.ALLAY, EntityType.ARMADILLO, EntityType.AXOLOTL, EntityType.BAT,
      EntityType.BEE, EntityType.CAMEL, EntityType.CAT, EntityType.COD, EntityType.DOLPHIN, EntityType.DONKEY, EntityType.FOX, EntityType.FROG,
      EntityType.GLOW_SQUID, EntityType.GOAT, EntityType.HAPPY_GHAST, EntityType.HORSE, EntityType.LLAMA, EntityType.MOOSHROOM, EntityType.MULE,
      EntityType.OCELOT, EntityType.PANDA, EntityType.PARROT, EntityType.POLAR_BEAR, EntityType.PUFFERFISH, EntityType.RABBIT, EntityType.SALMON,
      EntityType.SHEEP, EntityType.SKELETON_HORSE, EntityType.SNIFFER, EntityType.SNOW_GOLEM, EntityType.SQUID, EntityType.STRIDER,
      EntityType.TADPOLE, EntityType.TRADER_LLAMA, EntityType.TROPICAL_FISH, EntityType.TURTLE, EntityType.WOLF, EntityType.ZOMBIE_HORSE);

  /**
   * New entity types an {@link Lasso.Item#DIAMOND_LASSO} can capture.
   * <p>
   * Chain previous tiers together to get a full set of captureable entity types.
   */
  private static Set<EntityType> diamondLassoCaptureable = Set.of(EntityType.BLAZE, EntityType.BOGGED, EntityType.BREEZE, EntityType.CAVE_SPIDER,
      EntityType.CREAKING, EntityType.CREEPER, EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.ENDERMAN, EntityType.ENDERMITE,
      EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HOGLIN, EntityType.HUSK, EntityType.IRON_GOLEM, EntityType.MAGMA_CUBE,
      EntityType.PHANTOM, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.PILLAGER, EntityType.RAVAGER, EntityType.SHULKER,
      EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.STRAY, EntityType.VEX, EntityType.VINDICATOR,
      EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOGLIN, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN);

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
    EntityType entityType = entity.getType();

    switch (tier) {
      case IRON_LASSO -> {
        if (ironLassoCaptureable.contains(entityType)) {
          
        }
      }
      case GOLDEN_LASSO -> {
        if (ironLassoCaptureable.contains(entityType) || goldenLassoCaptureable.contains(entityType)) {

        }
      }
      case DIAMOND_LASSO -> {
        if (ironLassoCaptureable.contains(entityType) || goldenLassoCaptureable.contains(entityType) || diamondLassoCaptureable.contains(entityType)) {

        }
      }
      case EMERALD_LASSO -> {
        if (ironLassoCaptureable.contains(entityType) || goldenLassoCaptureable.contains(entityType) || diamondLassoCaptureable.contains(entityType) || emeraldLassoCaptureable.contains(entityType)) {

        }
      }
    }
  }

  /**
   * Releases the entity from the {@link Lasso.Item}.
   *
   * @param event player interact event
   */
  public void releaseEntity(@NotNull PlayerInteractEvent event) {

  }
}
