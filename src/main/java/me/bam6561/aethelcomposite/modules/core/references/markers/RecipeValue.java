package me.bam6561.aethelcomposite.modules.core.references.markers;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * References usable as Recipes.
 * <p>
 * Recipes are lists of materials required to craft an item, but a recipe may not exist for every item.
 * <p>
 * Recipes are represented as Lists of ItemStacks.
 *
 * @author Danny Nguyen
 * @version 1.0.44
 * @since 1.0.40
 */
public interface RecipeValue {
  /**
   * Gets the enum's recipe value.
   *
   * @return enum's recipe value
   */
  @Nullable
  List<ItemStack> asRecipe();
}
