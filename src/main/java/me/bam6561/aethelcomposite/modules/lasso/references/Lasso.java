package me.bam6561.aethelcomposite.modules.lasso.references;

import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.references.markers.ItemStackValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.ListItemStackValue;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Lasso module-related references.
 *
 * @author Danny Nguyen
 * @version 1.0.39
 * @since 1.0.39
 */
public class Lasso {
  /**
   * Enum usage only.
   */
  private Lasso() {
  }

  /**
   * Lasso items.
   *
   * @author Danny Nguyen
   * @version 1.0.42
   * @since 1.0.21
   */
  public enum Item implements ItemStackValue {
    /**
     * Captures farms animals.
     */
    IRON_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Iron Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Chicken, Cow, Pig, Sheep}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Iron Lasso"),
        Namespaced.Key.ITEM_ID.asKey(), "iron_lasso")),

    /**
     * Captures iron tier lasso-able and all animals.
     */
    GOLDEN_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Golden Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Iron Lasso + Animals}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Golden Lasso"),
        Namespaced.Key.ITEM_ID.asKey(), "golden_lasso")),

    /**
     * Captures golden tier lasso-able and non-boss hostile mobs.
     */
    DIAMOND_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Diamond Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Golden Lasso + Non-Boss Hostile Mobs}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Diamond Lasso"),
        Namespaced.Key.ITEM_ID.asKey(), "diamond_lasso")),

    /**
     * Captures diamond tier lasso-able and villagers.
     */
    EMERALD_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Emerald Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Diamond Lasso + Villagers}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Emerald Lasso"),
        Namespaced.Key.ITEM_ID.asKey(), "emerald_lasso"));

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

  /**
   * Lasso {@link Item} recipes.
   *
   * @author Danny Nguyen
   * @version 1.0.40
   * @since 1.0.40
   */
  public enum Recipe implements ListItemStackValue {
    /**
     * {@link Item#IRON_LASSO}
     */
    IRON_LASSO(List.of(new ItemStack(Material.LEAD, 1), new ItemStack(Material.IRON_INGOT, 2))),

    /**
     * {@link Item#GOLDEN_LASSO}
     */
    GOLDEN_LASSO(List.of(Item.IRON_LASSO.asItem(), new ItemStack(Material.GOLD_INGOT, 2))),

    /**
     * {@link Item#DIAMOND_LASSO}
     */
    DIAMOND_LASSO(List.of(Item.GOLDEN_LASSO.asItem(), new ItemStack(Material.DIAMOND, 2))),

    /**
     * {@link Item#EMERALD_LASSO}
     */
    EMERALD_LASSO(List.of(Item.DIAMOND_LASSO.asItem(), new ItemStack(Material.EMERALD, 8)));

    /**
     * Item recipe.
     */
    private final List<ItemStack> recipe;

    /**
     * Associates the item with a recipe.
     *
     * @param recipe item recipe
     */
    Recipe(List<ItemStack> recipe) {
      this.recipe = recipe;
    }

    /**
     * Gets the item recipe.
     *
     * @return item recipe
     */
    @Override
    @NotNull
    public List<ItemStack> asList() {
      return this.recipe;
    }
  }
}
