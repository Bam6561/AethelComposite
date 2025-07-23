package me.bam6561.aethelcomposite.modules.core.markers;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced.Key;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a stack of items managed by the {@link Plugin}.
 * <p>
 * Unlike ItemStacks, ModuleItemStacks have a {@link Key#ITEM_ID}.
 *
 * @author Danny Nguyen
 * @version 1.0.78
 * @since 1.0.78
 */
public class ModuleItemStack {
  /**
   * ItemStack.
   */
  private final ItemStack item;

  /**
   * Associates the ModuleItemStack with its item.
   *
   * @param item interacting item
   */
  public ModuleItemStack(@NotNull ItemStack item) {
    this.item = Objects.requireNonNull(item, "Null item");
  }

  /**
   * Gets the item.
   *
   * @return item
   */
  public ItemStack getItem() {
    return this.item;
  }

  /**
   * Gets the {@link Key#ITEM_ID}.
   *
   * @return {@link Key#ITEM_ID}
   */
  public String getItemID() {
    return this.item.getItemMeta().getPersistentDataContainer().get(Key.ITEM_ID.asKey(), PersistentDataType.STRING);
  }
}
