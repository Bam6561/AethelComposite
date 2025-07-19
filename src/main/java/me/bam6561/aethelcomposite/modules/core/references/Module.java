package me.bam6561.aethelcomposite.modules.core.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.markers.ItemStackValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.StringValue;
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
   * @version 1.0.35
   * @since 1.0.35
   */
  public static class Lasso {
    /**
     * Enum usage only.
     */
    private Lasso() {
    }

    /**
     * {@link Lasso} items.
     *
     * @author Danny Nguyen
     * @version 1.0.36
     * @since 1.0.21
     */
    public enum Item implements ItemStackValue {
      /**
       * Iron lasso. Captures farms animals.
       */
      IRON(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Iron Lasso", List.of(
              Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
              Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
              Text.Label.DETAILS.asColor() + "{Chicken, Cow, Pig, Sheep}",
              Text.Label.DETAILS.asColor() + "ID: Iron Lasso"),
          Text.Key.ITEM_ID.asKey(), "iron_lasso")),

      /**
       * Golden lasso. Captures iron tier lasso-able and all animals.
       */
      GOLD(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Golden Lasso", List.of(
              Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
              Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
              Text.Label.DETAILS.asColor() + "{Iron Lasso + Animals}",
              Text.Label.DETAILS.asColor() + "ID: Golden Lasso"),
          Text.Key.ITEM_ID.asKey(), "golden_lasso")),


      /**
       * Diamond lasso. Captures golden tier lasso-able and non-boss hostile mobs.
       */
      DIAMOND(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Diamond Lasso", List.of(
              Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
              Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
              Text.Label.DETAILS.asColor() + "{Golden Lasso + Non-Boss Hostile Mobs}",
              Text.Label.DETAILS.asColor() + "ID: Diamond Lasso"),
          Text.Key.ITEM_ID.asKey(), "diamond_lasso")),

      /**
       * Emerald lasso. Captures diamond tier lasso-able and villagers.
       */
      EMERALD(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Emerald Lasso", List.of(
              Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
              Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
              Text.Label.DETAILS.asColor() + "{Diamond Lasso + Villagers}",
              Text.Label.DETAILS.asColor() + "ID: Emerald Lasso"),
          Text.Key.ITEM_ID.asKey(), "emerald_lasso"));

      /**
       * Item.
       */
      private final ItemStack item;

      /**
       * Associates the entry with an item.
       *
       * @param item item
       */
      Item(ItemStack item) {
        this.item = item;
      }

      /**
       * Gets a copy of the item.
       *
       * @return item
       */
      @Override
      @NotNull
      public ItemStack asItem() {
        return this.item.clone();
      }
    }
  }
}