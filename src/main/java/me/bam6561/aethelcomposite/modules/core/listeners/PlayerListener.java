package me.bam6561.aethelcomposite.modules.core.listeners;

import me.bam6561.aethelcomposite.modules.core.events.FormulaicModuleItemDamageEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Set;

/**
 * Collection of player interaction listeners.
 *
 * @author Danny Nguyen
 * @version 1.1.40
 * @since 1.0.7
 */
public class PlayerListener implements Listener {
  /**
   * Material types linked to a {@link ModuleItemStack} whose default
   * interactions should be disabled to prevent incorrect usage.
   * <p>
   * See {@link #hasDefaultInteractionsDisabled(Player)}
   */
  private static final Set<Material> DISABLED_MATERIALS = Set.of(Material.LEAD);

  /**
   * {@link Namespaced.Key.Item#ID Item IDs} for
   * {@link ModuleItemStack ModuleItemStacks} with unique durability behaviors.
   */
  private static final Set<String> UNIQUE_DURABILITY_ITEM_IDS = Set.of(ItemUtils.Read.getItemID(Hook.Item.HOOK_HARNESS.asItem()));

  /**
   * {@link Namespaced.Key.Item#ID Item IDs} for
   * {@link ModuleItemStack ModuleItemStacks} with formulaic durability behaviors.
   */
  private static final Set<String> FORMULAIC_DURABILITY_ITEM_IDS = Set.of();

  /**
   * No parameter constructor.
   */
  public PlayerListener() {
  }

  /**
   * Routes player interactions.
   *
   * @param event player interact event
   */
  @EventHandler
  private void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if (hasDefaultInteractionsDisabled(player)) {
      event.setCancelled(true);
    }
    if (player.isSneaking()) {
      SneakInteractEvent sneakInteract = new SneakInteractEvent(event);
      Bukkit.getPluginManager().callEvent(sneakInteract);
    }
  }

  /**
   * Routes player interactions with entities.
   *
   * @param event player interact entity event
   */
  @EventHandler
  private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    Player player = event.getPlayer();
    if (hasDefaultInteractionsDisabled(player)) {
      event.setCancelled(true);
    }
    if (player.isSneaking()) {
      SneakInteractEntityEvent sneakInteractEntity = new SneakInteractEntityEvent(event);
      Bukkit.getPluginManager().callEvent(sneakInteractEntity);
    }
  }

  /**
   * Routes player item damages.
   *
   * @param event player item damage event
   */
  @EventHandler
  private void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {
    ItemStack item = event.getItem();
    String itemID = ItemUtils.Read.getItemID(item);
    if (itemID == null) {
      return;
    }

    if (UNIQUE_DURABILITY_ITEM_IDS.contains(itemID)) {
      event.setCancelled(true);
      return;
    }

    if (FORMULAIC_DURABILITY_ITEM_IDS.contains(itemID)) {
      FormulaicModuleItemDamageEvent formulaicModuleItemDamage = new FormulaicModuleItemDamageEvent(event, new ModuleItemStack(item));
      Bukkit.getPluginManager().callEvent(formulaicModuleItemDamage);
    }
  }

  /**
   * Whether to disable default interactions when an item whose material is
   * linked to a {@link ModuleItemStack} is held in either the main or off-hand.
   *
   * @param player interacting player
   */
  private boolean hasDefaultInteractionsDisabled(Player player) {
    PlayerInventory pInv = player.getInventory();
    ItemStack mainHandItem = pInv.getItemInMainHand();
    ItemStack offHandItem = pInv.getItemInOffHand();
    return (DISABLED_MATERIALS.contains(mainHandItem.getType()) && ItemUtils.Read.getItemID(mainHandItem) != null) ||
        (DISABLED_MATERIALS.contains(offHandItem.getType()) && ItemUtils.Read.getItemID(offHandItem) != null);
  }
}