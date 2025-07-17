package me.bam6561.aethelcomposite.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.references.markers.ItemStackValue;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link Plugin} modules.
 * <p>
 * Modules are defined as add-ons that function independently and can be toggled on or off separately.
 *
 * @author Danny Nguyen
 * @version 1.0.21
 * @since 1.0.21
 */
public class Module {
  /**
   * Enum usage only.
   */
  private Module() {
  }

  /**
   * Lasso module.
   *
   * @author Danny Nguyen
   * @version 1.0.23
   * @since 1.0.21
   */
  public enum Lasso implements ItemStackValue {
    IRON(ItemUtils.Create.createItem(Material.LEAD, "Iron Lasso", List.of(
        Text.Label.DETAILS + "Stores a creature.",
        Text.Label.FUNCTION + "[Main Hand][Sneak-Interact] " + Text.Label.ACTION + "Capture",
        Text.Label.DETAILS + "{Chicken, Cow, Pig, Sheep}"))),
    GOLD(ItemUtils.Create.createItem(Material.LEAD, "Golden Lasso", List.of(
            Text.Label.DETAILS + "Stores a creature.",
            Text.Label.FUNCTION + "[Main Hand][Sneak-Interact] " + Text.Label.ACTION + "Capture",
            Text.Label.DETAILS + "{Iron Lasso + Animals}"))),
    DIAMOND(ItemUtils.Create.createItem(Material.LEAD, "Diamond Lasso", List.of(
            Text.Label.DETAILS + "Stores a creature.",
            Text.Label.FUNCTION + "[Main Hand][Sneak-Interact] " + Text.Label.ACTION + "Capture",
            Text.Label.DETAILS + "{Golden Lasso + Non-Boss Hostile Mobs}"))),
    EMERALD(ItemUtils.Create.createItem(Material.LEAD, "Emerald Lasso", List.of(
        Text.Label.DETAILS + "Stores a creature.",
        Text.Label.FUNCTION + "[Main Hand][Sneak-Interact] " + Text.Label.ACTION + "Capture",
        Text.Label.DETAILS + "{Diamond Lasso + Villagers}")));

    /**
     * Item.
     */
    private final ItemStack item;

    /**
     * Associates the entry with an item.
     *
     * @param item item
     */
    Lasso(ItemStack item) {
      this.item = item;
    }

    /**
     * Gets the item.
     *
     * @return item
     */
    @Override
    @NotNull
    public ItemStack asItem() {
      return this.item;
    }
  }
}
