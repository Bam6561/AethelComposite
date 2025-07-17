package me.bam6561.aethelcomposite.managers;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.events.gui.GUIOpenEvent;
import me.bam6561.aethelcomposite.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.guis.blocks.CraftingTableGUI;
import me.bam6561.aethelcomposite.guis.blocks.markers.Workstation;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.0.22
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
   */
  public void interpretAction(@NotNull PlayerInteractEvent event) {
    Objects.requireNonNull(event, "Null event");
    switch (event.getAction()) {
      case RIGHT_CLICK_BLOCK -> {
        if (event.isBlockInHand()) {
          return;
        }
        openWorkstation(event.getPlayer(), event.getClickedBlock());
      }
    }
  }

  /**
   * Opens the {@link Workstation} associated with the block type if it exists.
   *
   * @param player interacting player
   * @param block  interacting block
   */
  private void openWorkstation(Player player, Block block) {
    switch (block.getType()) {
      case CRAFTING_TABLE -> {
        GUIOpenEvent guiOpen = new GUIOpenEvent(player, GUIOpenEvent.Cause.INTERACTION);
        Bukkit.getPluginManager().callEvent(guiOpen);
        if (guiOpen.isCancelled()) {
          return;
        }
        Plugin.getGUIManager().openGUI(player, new CraftingTableGUI());
      }
    }
  }
}
