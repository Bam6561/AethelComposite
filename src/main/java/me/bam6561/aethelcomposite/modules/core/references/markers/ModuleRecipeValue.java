package me.bam6561.aethelcomposite.modules.core.references.markers;

import me.bam6561.aethelcomposite.modules.core.objects.ModuleRecipe;
import org.jetbrains.annotations.NotNull;

/**
 * References usable as {@link ModuleRecipe ModuleRecipes}.
 *
 * @author Danny Nguyen
 * @version 1.0.90
 * @since 1.0.90
 */
public interface ModuleRecipeValue {
  /**
   * Gets the {@link ModuleRecipe}.
   *
   * @return {@link ModuleRecipe}
   */
  @NotNull
  ModuleRecipe asModuleRecipe();
}
