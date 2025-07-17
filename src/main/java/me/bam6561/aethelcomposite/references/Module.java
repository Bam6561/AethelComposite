package me.bam6561.aethelcomposite.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.references.markers.ItemStackValue;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

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
   * @version 1.0.21
   * @since 1.0.21
   */
  public enum Lasso implements ItemStackValue {
    IRON(),
    GOLD(),
    DIAMOND(),
    EMERALD();

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
