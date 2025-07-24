package me.bam6561.aethelcomposite.modules.core.objects.recipe;

import me.bam6561.aethelcomposite.Plugin;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a recipe managed by the {@link Plugin}.
 * <p>
 * Recipes are lists of ingredients required to craft lists of results.
 * <p>
 * A recipe may not exist for every item.
 *
 * @author Danny Nguyen
 * @version 1.0.110
 * @since 1.0.79
 */
public class ModuleRecipe {
  /**
   * Recipe ingredients.
   */
  private final List<ItemStack> ingredients;

  /**
   * Recipe results.
   */
  private final List<ItemStack> results;

  /**
   * Associates the ModuleRecipe with its ingredients and results.
   *
   * @param ingredients recipe ingredients
   * @param results     recipe results
   */
  public ModuleRecipe(@NotNull List<ItemStack> ingredients, @NotNull List<ItemStack> results) {
    this.ingredients = Objects.requireNonNull(ingredients, "Null ingredients");
    this.results = Objects.requireNonNull(results, "Null results");
  }

  /**
   * Provides a deep copy of an existing ModuleRecipe.
   *
   * @param original existing ModuleRecipe
   */
  public ModuleRecipe(@NotNull ModuleRecipe original) {
    List<ItemStack> copyIngredients = new ArrayList<>();
    for (ItemStack item : original.getIngredients()) {
      copyIngredients.add(item.clone());
    }
    this.ingredients = copyIngredients;

    List<ItemStack> copyResults = new ArrayList<>();
    for (ItemStack item : original.getResults()) {
      copyResults.add(item.clone());
    }
    this.results = copyResults;
  }

  /**
   * Gets the recipe's ingredients.
   *
   * @return recipe's ingredients
   */
  public List<ItemStack> getIngredients() {
    return this.ingredients;
  }

  /**
   * Gets the recipe's results.
   *
   * @return recipe's results
   */
  public List<ItemStack> getResults() {
    return this.results;
  }
}
