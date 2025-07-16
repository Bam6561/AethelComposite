package me.bam6561.aethelcomposite.listeners.interactions;

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
 * {@link SneakInteractEvent} interaction.
 *
 * @author Danny Nguyen
 * @version 1.0.8
 * @since 1.0.8
 */
public class SneakInteraction {
  /**
   * Player interact event.
   */
  private final PlayerInteractEvent event;

  /**
   * Associates the interaction with its source.
   *
   * @param event player interact event
   */
  public SneakInteraction(@NotNull PlayerInteractEvent event) {
    this.event = Objects.requireNonNull(event, "Null event");
  }

  /**
   * Opens a {@link Workstation} when the player's hand is empty while interacting with a block.
   */
  public void interpretAction() {
    switch (event.getAction()) {
      case RIGHT_CLICK_BLOCK -> {
        if (event.isBlockInHand()) {
          return;
        }
        openWorkstation(event.getClickedBlock());
      }
    }
  }

  /**
   * Opens the {@link Workstation} associated with the block type.
   *
   * @param block interacting block
   */
  private void openWorkstation(Block block) {
    switch (block.getType()) {
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
