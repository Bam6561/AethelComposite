package me.bam6561.aethelcomposite.listeners;

import me.bam6561.aethelcomposite.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.listeners.interactions.SneakEntityInteraction;
import me.bam6561.aethelcomposite.listeners.interactions.SneakInteraction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Collection of player interaction listeners.
 *
 * @author Danny Nguyen
 * @version 1.0.7
 * @since 1.0.7
 */
public class PlayerListener implements Listener {
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
    if (player.isSneaking()) {
      SneakInteractEvent sneakInteractEvent = new SneakInteractEvent(event);
      Bukkit.getPluginManager().callEvent(sneakInteractEvent);
      if (sneakInteractEvent.isCancelled()) {
        return;
      }
      new SneakInteraction(event).interpretAction();
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
    if (player.isSneaking()) {
      SneakInteractEntityEvent sneakEntityInteract = new SneakInteractEntityEvent(event);
      Bukkit.getPluginManager().callEvent(sneakEntityInteract);
      if (sneakEntityInteract.isCancelled()) {
        return;
      }
      new SneakEntityInteraction(event).interpretAction();
    }
  }
}