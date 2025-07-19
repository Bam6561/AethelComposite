package me.bam6561.aethelcomposite.modules.core.references.markers;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * References usable as ItemStacks.
 *
 * @author Danny Nguyen
 * @version 1.0.15
 * @since 1.0.15
 */
public interface ItemStackValue {
  /**
   * Gets the enum's ItemStack value.
   *
   * @return enum's ItemStack value
   */
  @NotNull
  ItemStack asItem();
}