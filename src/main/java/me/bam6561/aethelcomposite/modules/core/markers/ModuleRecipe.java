package me.bam6561.aethelcomposite.modules.core.markers;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Recipe managed by the {@link me.bam6561.aethelcomposite.Plugin}.
 * <p>
 * Recipes are lists of ingredients required to craft an item, but a recipe may not exist for every item.
 * <p>
 * Recipes are represented as Lists of ItemStacks.
 *
 * @author Danny Nguyen
 * @version 1.0.77
 * @since 1.0.77
 */
public interface ModuleRecipe {
  /**
   * Gets the recipe, if it exists.
   *
   * @return recipe
   */
  @Nullable
  List<ItemStack> asRecipe();
}
