package me.bam6561.aethelcomposite.modules.lasso.objects;

import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.utils.EntityUtils;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import me.bam6561.aethelcomposite.modules.lasso.events.LassoCaptureEvent;
import me.bam6561.aethelcomposite.modules.lasso.events.LassoReleaseEvent;
import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a {@link Lasso.Item}.
 * <p>
 * LassoItems are reusable tools that can {@link #captureEntity(PlayerInteractEntityEvent)}
 * and {@link #releaseEntity(PlayerInteractEvent)}, depending on their tier.
 *
 * @author Danny Nguyen
 * @version 1.1.8
 * @since 1.0.86
 */
public class LassoItem extends ModuleItemStack {
  /**
   * Entity types an {@link Lasso.Item#IRON_LASSO} can capture.
   */
  private static final Set<EntityType> ironTier = Set.of(EntityType.CHICKEN, EntityType.COW, EntityType.SHEEP, EntityType.PIG);

  /**
   * New entity types an {@link Lasso.Item#GOLDEN_LASSO} can capture.
   * <p>
   * Chain previous tiers together to get a full set of captureable entity types.
   */
  private static final Set<EntityType> goldenTier = Set.of(EntityType.ALLAY, EntityType.ARMADILLO, EntityType.AXOLOTL, EntityType.BAT, EntityType.BEE, EntityType.CAMEL, EntityType.CAT, EntityType.COD, EntityType.DOLPHIN, EntityType.DONKEY, EntityType.FOX, EntityType.FROG, EntityType.GLOW_SQUID, EntityType.GOAT, EntityType.HAPPY_GHAST, EntityType.HORSE, EntityType.LLAMA, EntityType.MOOSHROOM, EntityType.MULE, EntityType.OCELOT, EntityType.PANDA, EntityType.PARROT, EntityType.POLAR_BEAR, EntityType.PUFFERFISH, EntityType.RABBIT, EntityType.SALMON, EntityType.SHEEP, EntityType.SKELETON_HORSE, EntityType.SNIFFER, EntityType.SNOW_GOLEM, EntityType.SQUID, EntityType.STRIDER, EntityType.TADPOLE, EntityType.TRADER_LLAMA, EntityType.TROPICAL_FISH, EntityType.TURTLE, EntityType.WOLF, EntityType.ZOMBIE_HORSE);

  /**
   * New entity types an {@link Lasso.Item#DIAMOND_LASSO} can capture.
   * <p>
   * Chain previous tiers together to get a full set of captureable entity types.
   */
  private static final Set<EntityType> diamondTier = Set.of(EntityType.BLAZE, EntityType.BOGGED, EntityType.BREEZE, EntityType.CAVE_SPIDER, EntityType.CREAKING, EntityType.CREEPER, EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HOGLIN, EntityType.HUSK, EntityType.IRON_GOLEM, EntityType.MAGMA_CUBE, EntityType.PHANTOM, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.PILLAGER, EntityType.RAVAGER, EntityType.SHULKER, EntityType.SILVERFISH, EntityType.SKELETON, EntityType.SLIME, EntityType.SPIDER, EntityType.STRAY, EntityType.VEX, EntityType.VINDICATOR, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOGLIN, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN);

  /**
   * New entity types an {@link Lasso.Item#EMERALD_LASSO} can capture.
   * <p>
   * Chain previous tiers together to get a full set of captureable entity types.
   */
  private static final Set<EntityType> emeraldTier = Set.of(EntityType.VILLAGER, EntityType.WANDERING_TRADER);

  /**
   * Associates the LassoItem with its item.
   *
   * @param item interacting item
   */
  public LassoItem(@NotNull ItemStack item) {
    super(Objects.requireNonNull(item, "Null item"));
    String itemID = getItemID();
    if (itemID.endsWith("'d")) {
      Lasso.Item.valueOf(TextUtils.Format.asEnum(itemID.substring(0, itemID.length() - 2)));
    } else {
      Lasso.Item.valueOf(TextUtils.Format.asEnum(itemID));
    }
  }

  /**
   * Captures the entity, if the {@link Lasso.Item} tier allows.
   *
   * @param event player interact entity event
   */
  public void captureEntity(@NotNull PlayerInteractEntityEvent event) {
    Objects.requireNonNull(event, "Null event");
    Entity entity = event.getRightClicked();
    if (!(entity instanceof LivingEntity)) {
      return;
    }

    Player player = event.getPlayer();
    PlayerInventory inv = player.getInventory();

    Lasso.Item tier = Lasso.Item.valueOf(TextUtils.Format.asEnum(getItemID()));
    EntityType entityType = entity.getType();

    if (isCaptureable(tier, entityType)) {
      LassoCaptureEvent lassoCapture = new LassoCaptureEvent(player, entity);
      Bukkit.getPluginManager().callEvent(lassoCapture);
      if (lassoCapture.isCancelled()) {
        return;
      }
      event.setCancelled(true);
      storeEntityToData(player, inv, entity);
    } else {
      event.setCancelled(true);
      player.sendMessage(Text.Label.INVALID.asColor() + "Unable to capture " + TextUtils.Format.asTitle(entityType.name()) + ".");
    }
  }

  /**
   * Releases the entity from the {@link Lasso.Item}.
   *
   * @param event player interact event
   */
  public void releaseEntity(@NotNull PlayerInteractEvent event) {
    Objects.requireNonNull(event, "Null event");
    Block block = event.getClickedBlock();
    if (block == null) {
      return;
    }

    Player player = event.getPlayer();
    PlayerInventory inv = player.getInventory();

    String entityData = inv.getItemInMainHand().getItemMeta().getPersistentDataContainer().get(Lasso.Key.ENTITY_DATA.asKey(), PersistentDataType.STRING);
    LassoReleaseEvent lassoRelease = new LassoReleaseEvent(player, entityData);
    Bukkit.getPluginManager().callEvent(lassoRelease);
    if (lassoRelease.isCancelled()) {
      return;
    }
    event.setCancelled(true);

    retrieveEntityFromData(player, inv, block.getLocation().add(event.getClickedPosition()));
  }

  /**
   * Releases the entity from the {@link Lasso.Item}.
   *
   * @param event player interact event
   */
  public void releaseEntity(@NotNull PlayerInteractEntityEvent event) {
    Objects.requireNonNull(event, "Null event");
    Player player = event.getPlayer();
    PlayerInventory inv = player.getInventory();

    String entityData = inv.getItemInMainHand().getItemMeta().getPersistentDataContainer().get(Lasso.Key.ENTITY_DATA.asKey(), PersistentDataType.STRING);
    LassoReleaseEvent lassoRelease = new LassoReleaseEvent(player, entityData);
    Bukkit.getPluginManager().callEvent(lassoRelease);
    if (lassoRelease.isCancelled()) {
      return;
    }
    event.setCancelled(true);

    retrieveEntityFromData(player, inv, event.getRightClicked().getLocation());
  }

  /**
   * If the {@link Lasso.Item} has {@link Lasso.Key#ENTITY_DATA}.
   *
   * @return if the {@link Lasso.Item} has {@link Lasso.Key#ENTITY_DATA}
   */
  public boolean hasEntityData() {
    return getItemData().has(Lasso.Key.ENTITY_DATA.asKey(), PersistentDataType.STRING);
  }

  /**
   * If the entity type is captureable by the {@link Lasso.Item} tier.
   *
   * @param tier       {@link Lasso.Item} tier
   * @param entityType entity type
   * @return if the entity type is captureable
   */
  private boolean isCaptureable(Lasso.Item tier, EntityType entityType) {
    return switch (tier) {
      case IRON_LASSO -> ironTier.contains(entityType);
      case GOLDEN_LASSO -> (ironTier.contains(entityType) || goldenTier.contains(entityType));
      case DIAMOND_LASSO ->
          (ironTier.contains(entityType) || goldenTier.contains(entityType) || diamondTier.contains(entityType));
      case EMERALD_LASSO ->
          (ironTier.contains(entityType) || goldenTier.contains(entityType) || diamondTier.contains(entityType) || emeraldTier.contains(entityType));
    };
  }

  /**
   * Stores the entity to {@link Lasso.Key#ENTITY_DATA} into the {@link Lasso.Item} and removes it from the world.
   *
   * @param player interacting player
   * @param inv    player inventory
   * @param entity interacting entity
   */
  private void storeEntityToData(Player player, PlayerInventory inv, Entity entity) {
    ItemStack mainHandItem = inv.getItemInMainHand();
    ItemStack lasso = mainHandItem.clone();
    lasso.setAmount(1);

    ItemMeta meta = lasso.getItemMeta();
    PersistentDataContainer lassoData = meta.getPersistentDataContainer();
    String newItemID = ItemUtils.Read.getItemID(lasso) + "'d";

    lassoData.set(Namespaced.Key.Item.ID.asKey(), PersistentDataType.STRING, newItemID);
    lassoData.set(Lasso.Key.ENTITY_DATA.asKey(), PersistentDataType.STRING, EntityUtils.Data.encodeEntityString(entity));

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

    World world = player.getWorld();
    world.playSound(player.getEyeLocation(), Sound.ITEM_LEAD_TIED, 1, 1);
    world.spawnParticle(Particle.POOF, entity.getLocation(), 10, 0.1, 0.1, 0.1, 0.075);
    entity.remove();
  }

  /**
   * Retrieves the entity {@link Lasso.Key#ENTITY_DATA} from the
   * {@link Lasso.Item} and spawns it into the world at the provided location.
   *
   * @param player interacting player
   * @param inv    player inventory
   * @param loc    location
   */
  private void retrieveEntityFromData(Player player, PlayerInventory inv, Location loc) {
    ItemStack mainHandItem = inv.getItemInMainHand();
    ItemStack lasso = mainHandItem.clone();
    lasso.setAmount(1);

    ItemMeta meta = lasso.getItemMeta();
    PersistentDataContainer lassoData = meta.getPersistentDataContainer();
    String itemID = ItemUtils.Read.getItemID(lasso);
    String newItemID = itemID.substring(0, itemID.length() - 2);
    String entityData = lassoData.get(Lasso.Key.ENTITY_DATA.asKey(), PersistentDataType.STRING);

    lassoData.set(Namespaced.Key.Item.ID.asKey(), PersistentDataType.STRING, newItemID);
    lassoData.remove(Lasso.Key.ENTITY_DATA.asKey());

    List<String> newLore = new ArrayList<>(List.of(
        Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
        Text.Label.DETAILS.asColor() + "Stores a creature to be released later."));
    switch (newItemID) {
      case "iron_lasso" -> newLore.add(Text.Label.DETAILS.asColor() + "{Chicken, Cow, Pig, Sheep}");
      case "golden_lasso" -> newLore.add(Text.Label.DETAILS.asColor() + "{Iron Lasso + Animals}");
      case "diamond_lasso" -> newLore.add(Text.Label.DETAILS.asColor() + "{Golden Lasso + Non-Boss Hostile Mobs}");
      case "emerald_lasso" -> newLore.add(Text.Label.DETAILS.asColor() + "{Diamond Lasso + Villagers}");
    }
    newLore.add(Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + TextUtils.Format.asTitle(newItemID));

    meta.setLore(newLore);
    meta.setEnchantmentGlintOverride(false);
    lasso.setItemMeta(meta);

    mainHandItem.setAmount(mainHandItem.getAmount() - 1);
    inv.setItemInMainHand(mainHandItem);

    if (inv.firstEmpty() != -1) {
      inv.addItem(lasso);
    } else {
      player.getWorld().dropItem(player.getLocation(), lasso);
    }

    World world = player.getWorld();
    world.playSound(player.getEyeLocation(), Sound.ITEM_LEAD_UNTIED, 1, 1);
    world.spawnParticle(Particle.POOF, loc, 10, 0.1, 0.1, 0.1, 0.075);
    EntityUtils.Data.decodeEntityString(entityData, loc);
  }
}