package me.bam6561.aethelcomposite.modules.core.managers;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.events.gui.GUIOpenEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.modules.core.guis.blocks.WorkbenchGUI;
import me.bam6561.aethelcomposite.modules.core.guis.blocks.markers.Workstation;
import me.bam6561.aethelcomposite.modules.core.objects.item.ModuleItemStack;
import me.bam6561.aethelcomposite.modules.core.objects.item.markers.ActiveAbilityItem;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import me.bam6561.aethelcomposite.modules.hook.objects.items.HookHarnessItem;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import me.bam6561.aethelcomposite.modules.lasso.objects.items.LassoItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.1.23
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
   *   <li>{@link #activateLeggingsItemAbility(PlayerInteractEvent, ItemStack)}
   *   <li>{@link #activateMainHandItemAbility(PlayerInteractEvent, ItemStack)}
   *   <li>{@link #openWorkstation(PlayerInteractEvent)}
   * </ul>
   *
   * @param event player interact event
   */
  public void interpretAction(@NotNull PlayerInteractEvent event) {
    Objects.requireNonNull(event, "Null event");
    PlayerInventory pInv = event.getPlayer().getInventory();
    ItemStack leggingsItem = pInv.getLeggings();
    ItemStack mainHandItem = pInv.getItemInMainHand();

    switch (event.getAction()) {
      case RIGHT_CLICK_AIR -> {
        if (ItemUtils.Read.isNotNullOrAir(leggingsItem)) {
          activateLeggingsItemAbility(event, leggingsItem);
        }
        if (ItemUtils.Read.isNotNullOrAir(mainHandItem)) {
          activateMainHandItemAbility(event, mainHandItem);
        }
      }
      case RIGHT_CLICK_BLOCK -> {
        if (ItemUtils.Read.isNullOrAir(mainHandItem)) {
          openWorkstation(event);
        } else {
          if (ItemUtils.Read.isNotNullOrAir(leggingsItem)) {
            activateLeggingsItemAbility(event, leggingsItem);
          }
          if (ItemUtils.Read.isNotNullOrAir(mainHandItem)) {
            activateMainHandItemAbility(event, mainHandItem);
          }
        }
      }
    }
  }

  /**
   * Activates the ability associated with the {@link ActiveAbilityItem} if it exists.
   *
   * @param event player interact event
   * @param item  interacting item
   */
  private void activateLeggingsItemAbility(PlayerInteractEvent event, ItemStack item) {
    String itemID = ItemUtils.Read.getItemID(item);
    if (itemID == null) {
      return;
    }

    ModuleItemStack moduleItem = new ModuleItemStack(item);
    switch (moduleItem.getModuleName()) {
      case HOOK -> {
        HookHarnessItem hookHarnessItem = new HookHarnessItem(item);
        ItemStack offHandItem = event.getPlayer().getInventory().getItemInOffHand();
        if (ItemUtils.Read.isNullOrAir(offHandItem)) {
          hookHarnessItem.fireHookShot(event);
        } else if (ItemUtils.Read.getItemID(offHandItem).equals(ItemUtils.Read.getItemID(Hook.Item.HOOK_SHOT.asItem()))) {
          hookHarnessItem.reload(event);
        }
      }
    }
  }

  /**
   * Activates the ability associated with the {@link ActiveAbilityItem} if it exists.
   *
   * @param event player interact event
   * @param item  interacting item
   */
  private void activateMainHandItemAbility(PlayerInteractEvent event, ItemStack item) {
    String itemID = ItemUtils.Read.getItemID(item);
    if (itemID == null) {
      return;
    }

    ModuleItemStack moduleItem = new ModuleItemStack(item);
    switch (moduleItem.getModuleName()) {
      case LASSO -> {
        LassoItem lassoItem = new LassoItem(item);
        if (lassoItem.hasEntityData()) {
          lassoItem.releaseEntity(event);
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
        GUIOpenEvent guiOpenEvent = new GUIOpenEvent(player, GUIOpenEvent.Type.WORKBENCH, GUIOpenEvent.Cause.INTERACTION);
        Bukkit.getPluginManager().callEvent(guiOpenEvent);
        if (guiOpenEvent.isCancelled()) {
          return;
        }
        event.setCancelled(true);
        Plugin.getGUIManager().openGUI(player, new WorkbenchGUI());
      }
    }
  }
}
