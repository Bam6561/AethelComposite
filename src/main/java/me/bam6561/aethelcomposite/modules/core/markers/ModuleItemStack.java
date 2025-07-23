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
 * Unlike ItemStacks, ModuleItemStacks have a {@link Key.Core#MODULE} and {@link Key.Item#ID}.
 *
 * @author Danny Nguyen
 * @version 1.0.84
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
   * Gets the {@link Key.Core#MODULE}.
   *
   * @return {@link Key.Core#MODULE}.
   */
  public String getModule() {
    return this.item.getItemMeta().getPersistentDataContainer().get(Key.Core.MODULE.asKey(), PersistentDataType.STRING);
  }

  /**
   * Gets the {@link Key.Item#ID}.
   *
   * @return {@link Key.Item#ID}
   */
  public String getItemID() {
    return this.item.getItemMeta().getPersistentDataContainer().get(Key.Item.ID.asKey(), PersistentDataType.STRING);
  }
}
