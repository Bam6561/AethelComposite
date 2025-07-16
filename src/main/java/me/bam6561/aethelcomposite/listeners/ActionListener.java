package me.bam6561.aethelcomposite.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Collection of player action listeners.
 *
 * @author Danny Nguyen
 * @version 1.0.0
 * @since 1.0.0
 */
public class ActionListener implements Listener {
  /**
   * No parameter constructor.
   */
  public ActionListener() {
  }

  /**
   * Routes interactions for player interactions.
   *
   * @param e player interaction event
   */
  @EventHandler
  private void onInteract(PlayerInteractEvent e) {
    Player player = e.getPlayer();
    if (!player.isSneaking()) {
      return;
    }
    if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
      return;
    }
    if (e.getClickedBlock().getType() != Material.CRAFTING_TABLE) {
      return;
    }
    Bukkit.getLogger().warning("Sneak interaction with crafting table.");
  }
}
