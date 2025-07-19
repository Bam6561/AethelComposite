package me.bam6561.aethelcomposite.modules.core.references.markers;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * References usable as Lists of ItemStacks.
 *
 * @author Danny Nguyen
 * @version 1.0.40
 * @since 1.0.40
 */
public interface ListItemStackValue {
  /**
   * Gets the enum's List of ItemStacks value.
   *
   * @return enum's List of ItemStacks value
   */
  @NotNull
  List<ItemStack> asList();
}
