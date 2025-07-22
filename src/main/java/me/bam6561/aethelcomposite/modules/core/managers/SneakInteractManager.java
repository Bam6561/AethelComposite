package me.bam6561.aethelcomposite.modules.core.managers;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.events.gui.GUIOpenEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.modules.core.guis.blocks.WorkbenchGUI;
import me.bam6561.aethelcomposite.modules.core.guis.blocks.markers.Workstation;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.0.74
 * @since 1.0.8
 */
public class SneakInteractManager {
  /**
   * No parameter constructor.
   */
  public SneakInteractManager() {
  }

  /**
   * On interaction:
   * <ul>
   *   <li> Opens a {@link Workstation}.
   * </ul>
   *
   * @param event player interact event
   */
  public void interpretAction(@NotNull PlayerInteractEvent event) {
    Objects.requireNonNull(event, "Null event");
    switch (event.getAction()) {
      case RIGHT_CLICK_AIR -> {
        ItemStack mainHandItem = event.getPlayer().getInventory().getItemInMainHand();
        if (ItemUtils.Read.isNullOrAir(mainHandItem)) {
          return;
        }

        String itemID = ItemUtils.Read.getItemID(mainHandItem);
        if (itemID == null) {
          return;
        }

        activateItemAbility(event, itemID);
      }
      case RIGHT_CLICK_BLOCK -> {
        ItemStack mainHandItem = event.getPlayer().getInventory().getItemInMainHand();
        if (ItemUtils.Read.isNullOrAir(mainHandItem)) {
          if (event.isBlockInHand()) {
            return;
          }
          openWorkstation(event);
        } else {
          String itemID = ItemUtils.Read.getItemID(mainHandItem);
          if (itemID == null) {
            return;
          }

          activateItemAbility(event, itemID);
        }
      }
    }
  }

  /**
   * Opens the {@link Workstation} associated with the block type if it exists.
   *
   * @param event player interact event
   */
  private void openWorkstation(PlayerInteractEvent event) {
    switch (event.getClickedBlock().getType()) {
      case CRAFTING_TABLE -> {
        Player player = event.getPlayer();
        GUIOpenEvent guiOpenEvent = new GUIOpenEvent(player, GUIOpenEvent.Cause.INTERACTION, GUIOpenEvent.Type.WORKBENCH);
        Bukkit.getPluginManager().callEvent(guiOpenEvent);
        if (guiOpenEvent.isCancelled()) {
          return;
        }
        event.setCancelled(true);
        Plugin.getGUIManager().openGUI(player, new WorkbenchGUI());
      }
    }
  }

  /**
   * Activates an item's ability.
   *
   * @param event  player interact entity event
   * @param itemID {@link Namespaced.Key#ITEM_ID}
   */
  private void activateItemAbility(PlayerInteractEvent event, String itemID) {
    switch (itemID) {
      case "iron_lasso'd", "golden_lasso'd", "diamond_lasso'd", "emerald_lasso'd" ->
          Plugin.getLassoManager().releaseEntity(event);
    }
  }
}
