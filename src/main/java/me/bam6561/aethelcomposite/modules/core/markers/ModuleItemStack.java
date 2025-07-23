package me.bam6561.aethelcomposite.modules.core.markers;

import me.bam6561.aethelcomposite.Plugin;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Stack of items managed by the {@link Plugin}.
 *
 * @author Danny Nguyen
 * @version 1.0.77
 * @since 1.0.77
 */
public interface ModuleItemStack {
  /**
   * Gets the item.
   *
   * @return item
   */
  @NotNull
  ItemStack asItem();
}
