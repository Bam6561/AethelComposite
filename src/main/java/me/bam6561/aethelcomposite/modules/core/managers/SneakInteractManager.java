package me.bam6561.aethelcomposite.modules.core.managers;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.events.gui.GUIOpenEvent;
import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.modules.core.guis.blocks.CraftingTableGUI;
import me.bam6561.aethelcomposite.modules.core.guis.blocks.markers.Workstation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.0.24
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
      case RIGHT_CLICK_BLOCK -> {
        if (event.isBlockInHand()) {
          return;
        }
        openWorkstation(event);
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
        GUIOpenEvent guiOpen = new GUIOpenEvent(player, GUIOpenEvent.Cause.INTERACTION);
        Bukkit.getPluginManager().callEvent(guiOpen);
        if (guiOpen.isCancelled()) {
          return;
        }
        event.setCancelled(true);
        Plugin.getGUIManager().openGUI(player, new CraftingTableGUI());
      }
    }
  }
}
