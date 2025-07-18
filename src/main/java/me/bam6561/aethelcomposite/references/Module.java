package me.bam6561.aethelcomposite.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.references.markers.ItemStackValue;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
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
   * List of modules.
   *
   * @author Danny Nguyen
   * @version 1.0.26
   * @since 1.0.26
   */
  public enum List {
    LASSO;

    /**
     * No parameter constructor.
     */
    List() {
    }
  }


  /**
   * Lasso module.
   *
   * @author Danny Nguyen
   * @version 1.0.25
   * @since 1.0.21
   */
  public enum Lasso implements ItemStackValue {
    IRON(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Iron Lasso", List.of(
        Text.Label.TIP.asColor() + "[Sneak-Interact] " + Text.Label.ACTION.asColor() + "Capture",
        Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
        Text.Label.DETAILS.asColor() + "{Chicken, Cow, Pig, Sheep}"))),
    GOLD(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Golden Lasso", List.of(
        Text.Label.TIP.asColor() + "[Sneak-Interact] " + Text.Label.ACTION.asColor() + "Capture",
        Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
        Text.Label.DETAILS.asColor() + "{Iron Lasso + Animals}"))),
    DIAMOND(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Diamond Lasso", List.of(
        Text.Label.TIP.asColor() + "[Sneak-Interact] " + Text.Label.ACTION.asColor() + "Capture",
        Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
        Text.Label.DETAILS.asColor() + "{Golden Lasso + Non-Boss Hostile Mobs}"))),
    EMERALD(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Emerald Lasso", List.of(
        Text.Label.TIP.asColor() + "[Sneak-Interact] " + Text.Label.ACTION.asColor() + "Capture",
        Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
        Text.Label.DETAILS.asColor() + "{Diamond Lasso + Villagers}")));

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
