package me.bam6561.aethelcomposite.modules.lasso.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.references.markers.ItemStackValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.NamespacedKeyValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.RecipeValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.StringValue;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Lasso module-related references.
 *
 * @author Danny Nguyen
 * @version 1.0.44
 * @since 1.0.39
 */
public class Lasso {
  /**
   * Enum usage only.
   */
  private Lasso() {
  }

  /**
   * Lasso items and their recipes.
   *
   * @author Danny Nguyen
   * @version 1.0.44
   * @since 1.0.21
   */
  public enum Item implements ItemStackValue, RecipeValue {
    /**
     * Captures farms animals.
     */
    IRON_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Iron Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Chicken, Cow, Pig, Sheep}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Iron Lasso"),
        Namespaced.Key.ITEM_ID.asKey(), "iron_lasso"),
        List.of(new ItemStack(Material.LEAD, 1), new ItemStack(Material.IRON_INGOT, 2))),

    /**
     * Captures iron tier lasso-able and all animals.
     */
    GOLDEN_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Golden Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Iron Lasso + Animals}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Golden Lasso"),
        Namespaced.Key.ITEM_ID.asKey(), "golden_lasso"),
        List.of(Item.IRON_LASSO.asItem(), new ItemStack(Material.GOLD_INGOT, 2))),

    /**
     * Captures golden tier lasso-able and non-boss hostile mobs.
     */
    DIAMOND_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Diamond Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Golden Lasso + Non-Boss Hostile Mobs}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Diamond Lasso"),
        Namespaced.Key.ITEM_ID.asKey(), "diamond_lasso"),
        List.of(Item.GOLDEN_LASSO.asItem(), new ItemStack(Material.DIAMOND, 2))),

    /**
     * Captures diamond tier lasso-able and villagers.
     */
    EMERALD_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Emerald Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Diamond Lasso + Villagers}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Emerald Lasso"),
        Namespaced.Key.ITEM_ID.asKey(), "emerald_lasso"),
        List.of(Item.DIAMOND_LASSO.asItem(), new ItemStack(Material.EMERALD, 8)));

    /**
     * Item.
     */
    private final ItemStack item;

    /**
     * Item recipe.
     */
    private final List<ItemStack> recipe;

    /**
     * Associates an item with its recipe.
     *
     * @param item   item
     * @param recipe item recipe
     */
    Item(ItemStack item, List<ItemStack> recipe) {
      this.item = item;
      this.recipe = recipe;
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

    /**
     * Gets a copy of item recipe.
     *
     * @return item recipe
     */
    @Override
    @Nullable
    public List<ItemStack> asRecipe() {
      return new ArrayList<>(this.recipe);
    }

    /**
     * Reserved namespaced keys.
     *
     * @author Danny Nguyen
     * @version 1.0.61
     * @since 1.0.61
     */
    public enum Key implements NamespacedKeyValue, StringValue {
      /**
       * Captured entity data.
       */
      LASSO_ENTITY_DATA(new NamespacedKey(Plugin.getInstance(), Namespaced.Header.ITEM.asString() + "lasso_entity_data"), Namespaced.Header.ITEM.asString() + "lasso_entity_data");

      /**
       * Namespaced key.
       */
      private final NamespacedKey key;

      /**
       * Namespaced key string.
       */
      private final String keyString;

      /**
       * Associates the entry with the namespaced key and namespaced key string.
       *
       * @param key       namespaced key
       * @param keyString namespaced key string
       */
      Key(NamespacedKey key, String keyString) {
        this.key = key;
        this.keyString = keyString;
      }

      /**
       * Gets the namespaced key.
       *
       * @return namespaced key
       */
      @Override
      @NotNull
      public NamespacedKey asKey() {
        return this.key;
      }

      /**
       * Gets the namespaced key string.
       *
       * @return namespaced key string
       */
      @Override
      @NotNull
      public String asString() {
        return this.keyString;
      }
    }
  }
}
