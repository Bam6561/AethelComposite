package me.bam6561.aethelcomposite.modules.lasso.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.markers.ModuleRecipe;
import me.bam6561.aethelcomposite.modules.core.references.Module;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.references.markers.ItemStackValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.ModuleRecipeValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.NamespacedKeyValue;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Lasso {@link Module} references.
 *
 * @author Danny Nguyen
 * @version 1.0.89
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
   * @version 1.0.91
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
        Namespaced.Key.Core.MODULE.asKey(), Module.Name.LASSO.asString(), Namespaced.Key.Item.ID.asKey(), "iron_lasso")),

    /**
     * Captures iron tier lasso-able and all animals.
     */
    GOLDEN_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Golden Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Iron Lasso + Animals}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Golden Lasso"),
        Namespaced.Key.Core.MODULE.asKey(), Module.Name.LASSO.asString(), Namespaced.Key.Item.ID.asKey(), "golden_lasso")),

    /**
     * Captures golden tier lasso-able and non-boss hostile mobs.
     */
    DIAMOND_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Diamond Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Golden Lasso + Non-Boss Hostile Mobs}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Diamond Lasso"),
        Namespaced.Key.Core.MODULE.asKey(), Module.Name.LASSO.asString(), Namespaced.Key.Item.ID.asKey(), "diamond_lasso")),

    /**
     * Captures diamond tier lasso-able and villagers.
     */
    EMERALD_LASSO(ItemUtils.Create.createItem(Material.LEAD, ChatColor.WHITE + "Emerald Lasso", List.of(
            Text.Label.ACTION.asColor() + "Capture " + Text.Label.TIP.asColor() + "[Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Stores a creature to be released later.",
            Text.Label.DETAILS.asColor() + "{Diamond Lasso + Villagers}",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Emerald Lasso"),
        Namespaced.Key.Core.MODULE.asKey(), Module.Name.LASSO.asString(), Namespaced.Key.Item.ID.asKey(), "emerald_lasso"));

    /**
     * Item.
     */
    private final ItemStack item;

    /**
     * Associates an entry with its item.
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
   * Lasso {@link ModuleRecipe ModuleRecipes}.
   *
   * @author Danny Nguyen
   * @version 1.0.89
   * @since 1.0.89
   */
  public enum Recipe implements ModuleRecipeValue {
    /**
     * {@link Item#IRON_LASSO}
     */
    IRON_LASSO(new ModuleRecipe(List.of(new ItemStack(Material.LEAD, 1), new ItemStack(Material.IRON_INGOT, 2)), List.of(Item.IRON_LASSO.asItem()))),

    /**
     * {@link Item#GOLDEN_LASSO}
     */
    GOLDEN_LASSO(new ModuleRecipe(List.of(Item.IRON_LASSO.asItem(), new ItemStack(Material.GOLD_INGOT, 2)), List.of(Item.GOLDEN_LASSO.asItem()))),

    /**
     * {@link Item#DIAMOND_LASSO}
     */
    DIAMOND_LASSO(new ModuleRecipe(List.of(Item.GOLDEN_LASSO.asItem(), new ItemStack(Material.DIAMOND, 2)), List.of(Item.DIAMOND_LASSO.asItem()))),

    /**
     * {@link Item#EMERALD_LASSO}
     */
    EMERALD_LASSO(new ModuleRecipe(List.of(Item.DIAMOND_LASSO.asItem(), new ItemStack(Material.EMERALD, 8)), List.of(Item.EMERALD_LASSO.asItem())));

    /**
     * {@link ModuleRecipe}
     */
    private final ModuleRecipe moduleRecipe;

    /**
     * Associates an entry with its {@link ModuleRecipe}.
     *
     * @param moduleRecipe {@link ModuleRecipe}
     */
    Recipe(ModuleRecipe moduleRecipe) {
      this.moduleRecipe = moduleRecipe;
    }

    /**
     * Gets the {@link ModuleRecipe}.
     *
     * @return {@link ModuleRecipe}
     */
    @NotNull
    public ModuleRecipe asModuleRecipe() {
      return this.moduleRecipe;
    }
  }

  /**
   * Reserved namespaced keys.
   *
   * @author Danny Nguyen
   * @version 1.0.89
   * @since 1.0.61
   */
  public enum Key implements NamespacedKeyValue {
    /**
     * Stored entity data.
     */
    ENTITY_DATA(new NamespacedKey(Plugin.getInstance(), Namespaced.Header.ITEM.asString() + "lasso_entity_data"));

    /**
     * Namespaced key.
     */
    private final NamespacedKey key;

    /**
     * Associates the entry with its namespaced key.
     *
     * @param key namespaced key
     */
    Key(NamespacedKey key) {
      this.key = key;
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
  }
}
