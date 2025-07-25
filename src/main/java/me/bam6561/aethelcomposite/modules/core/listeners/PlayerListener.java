package me.bam6561.aethelcomposite.modules.core.listeners;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.modules.core.managers.SneakInteractEntityManager;
import me.bam6561.aethelcomposite.modules.core.managers.SneakInteractManager;
import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Set;

/**
 * Collection of player interaction listeners.
 * <p>
 * Plugin-monitored player behavior is managed through:
 * <ul>
 *   <li>{@link SneakInteractManager}
 *   <li>{@link SneakInteractEntityManager}
 * </ul>
 *
 * @author Danny Nguyen
 * @version 1.1.9
 * @since 1.0.7
 */
public class PlayerListener implements Listener {
  /**
   * Material types linked to a {@link ModuleItemStack} whose default
   * interactions should be disabled to prevent incorrect usage.
   * <p>
   * See {@link #hasDefaultInteractionsDisabled(Player)}
   */
  private static final Set<Material> disabledMaterials = Set.of(Material.LEAD);

  /**
   * {@link SneakInteractManager}
   */
  private final SneakInteractManager sneakInteractManager = Plugin.getSneakInteractManager();

  /**
   * {@link SneakInteractEntityManager}
   */
  private final SneakInteractEntityManager sneakInteractEntityManager = Plugin.getSneakInteractEntityManager();

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
      SneakInteractEvent sneakInteractEvent = new SneakInteractEvent(event);
      Bukkit.getPluginManager().callEvent(sneakInteractEvent);
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
      SneakInteractEntityEvent sneakInteractEntityEvent = new SneakInteractEntityEvent(event);
      Bukkit.getPluginManager().callEvent(sneakInteractEntityEvent);
    }
  }

  /**
   * Routes {@link SneakInteractEvent SneakInteractEvents}.
   *
   * @param event {@link SneakInteractEvent}
   */
  @EventHandler
  private void onPlayerSneakInteract(SneakInteractEvent event) {
    if (event.isCancelled()) {
      return;
    }
    sneakInteractManager.interpretAction(event.getSource());
  }

  /**
   * Routes {@link SneakInteractEntityEvent SneakInteractEntityEvents}.
   *
   * @param event {@link SneakInteractEntityEvent}
   */
  @EventHandler
  private void onPlayerSneakInteractEntity(SneakInteractEntityEvent event) {
    if (event.isCancelled()) {
      return;
    }
    sneakInteractEntityManager.interpretAction(event.getSource());
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
    return (disabledMaterials.contains(mainHandItem.getType()) && ItemUtils.Read.getItemID(mainHandItem) != null) ||
        (disabledMaterials.contains(offHandItem.getType()) && ItemUtils.Read.getItemID(offHandItem) != null);
  }
}