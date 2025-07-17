package me.bam6561.aethelcomposite.listeners;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.events.player.SneakInteractEvent;
import me.bam6561.aethelcomposite.managers.SneakInteractEntityManager;
import me.bam6561.aethelcomposite.managers.SneakInteractManager;
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
 * @version 1.0.24
 * @since 1.0.7
 */
public class PlayerListener implements Listener {
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
    if (player.isSneaking()) {
      SneakInteractEvent sneakInteractEvent = new SneakInteractEvent(event.getPlayer(), event.getClickedBlock());
      Bukkit.getPluginManager().callEvent(sneakInteractEvent);
      if (sneakInteractEvent.isCancelled()) {
        return;
      }
      sneakInteractManager.interpretAction(event);
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
      SneakInteractEntityEvent sneakInteractEntity = new SneakInteractEntityEvent(event.getPlayer(), event.getRightClicked());
      Bukkit.getPluginManager().callEvent(sneakInteractEntity);
      if (sneakInteractEntity.isCancelled()) {
        return;
      }
      sneakInteractEntityManager.interpretAction(event);
    }
  }
}