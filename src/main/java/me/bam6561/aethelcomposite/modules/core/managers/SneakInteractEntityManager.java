package me.bam6561.aethelcomposite.modules.core.managers;

import me.bam6561.aethelcomposite.modules.core.events.player.SneakInteractEntityEvent;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link SneakInteractEntityEvent} interactions.
 *
 * @author Danny Nguyen
 * @version 1.0.54
 * @since 1.0.8
 */
public class SneakInteractEntityManager {
  /**
   * No parameter constructor.
   */
  public SneakInteractEntityManager() {
  }

  /**
   * On interaction:
   * <ul>
   *   <li>
   * </ul>
   *
   * @param event player interact entity event
   */
  public void interpretAction(@NotNull PlayerInteractEntityEvent event) {
    Objects.requireNonNull(event, "Null event");
    Player player = event.getPlayer();
    PlayerInventory inv = player.getInventory();

    ItemStack mainHandItem = inv.getItemInMainHand();
    if (ItemUtils.Read.isNotNullOrAir(mainHandItem)) {
      String itemID = ItemUtils.Read.getItemID(mainHandItem);
      switch (itemID) {
        case "iron_lasso" -> {

        }
        case "golden_lasso" -> {

        }
        case "diamond_lasso" -> {

        }
        case "emerald_lasso" -> {
        }
      }
    }
  }
}
