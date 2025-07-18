package me.bam6561.aethelcomposite.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.references.markers.ItemStackValue;
import me.bam6561.aethelcomposite.references.markers.StringValue;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import net.md_5.bungee.api.ChatColor;
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
   * Module names.
   *
   * @author Danny Nguyen
   * @version 1.0.31
   * @since 1.0.26
   */
  public enum Name implements StringValue {
    /**
     * {@link Lasso}
     */
    LASSO("lasso");

    /**
     * Module name.
     */
    private final String name;

    /**
     * Associates the module with its name.
     *
     * @param name module name
     */
    Name(String name) {
      this.name = name;
    }

    /**
     * Gets the module's name.
     *
     * @return module name
     */
    @Override
    @NotNull
    public String asString() {
      return this.name;
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
    /**
     * Iron lasso. Captures farms animals.
     */
    IRON(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Iron Lasso", List.of(
        Text.Label.TIP.asColor() + "[Sneak-Interact] " + Text.Label.ACTION.asColor() + "Capture",
        Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
        Text.Label.DETAILS.asColor() + "{Chicken, Cow, Pig, Sheep}"))),

    /**
     * Golden lasso. Captures iron tier and all animals.
     */
    GOLD(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Golden Lasso", List.of(
        Text.Label.TIP.asColor() + "[Sneak-Interact] " + Text.Label.ACTION.asColor() + "Capture",
        Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
        Text.Label.DETAILS.asColor() + "{Iron Lasso + Animals}"))),

    /**
     * Diamond lasso. Captures golden tier and non-boss hostile mobs.
     */
    DIAMOND(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Diamond Lasso", List.of(
        Text.Label.TIP.asColor() + "[Sneak-Interact] " + Text.Label.ACTION.asColor() + "Capture",
        Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
        Text.Label.DETAILS.asColor() + "{Golden Lasso + Non-Boss Hostile Mobs}"))),

    /**
     * Emerald lasso. Captures diamond tier and villagers.
     */
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
