package me.bam6561.aethelcomposite.modules.core.objects;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.Module;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.utils.TextUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a stack of items managed by the {@link Plugin}.
 * <p>
 * Unlike ItemStacks, ModuleItemStacks belong to a {@link Module.Name} and have a {@link Namespaced.Key.Item#ID}.
 *
 * @author Danny Nguyen
 * @version 1.0.94
 * @since 1.0.78
 */
public class ModuleItemStack {
  /**
   * ItemStack.
   */
  private final ItemStack item;

  /**
   * Item data.
   */
  private final PersistentDataContainer itemData;

  /**
   * Associates the ModuleItemStack with its item.
   *
   * @param item interacting item
   */
  public ModuleItemStack(@NotNull ItemStack item) {
    this.item = Objects.requireNonNull(item, "Null item");
    this.itemData = item.getItemMeta().getPersistentDataContainer();
    if (!itemData.has(Namespaced.Key.Core.MODULE.asKey(), PersistentDataType.STRING)) {
      throw new IllegalArgumentException("Not a ModuleItemStack");
    }
    if (!itemData.has(Namespaced.Key.Item.ID.asKey(), PersistentDataType.STRING)) {
      throw new IllegalArgumentException("Not a ModuleItemStack");
    }
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
   * Gets the item data.
   *
   * @return item data
   */
  public PersistentDataContainer getItemData() {
    return this.itemData;
  }

  /**
   * Gets the {@link Module.Name}.
   *
   * @return {@link Module.Name}
   */
  public Module.Name getModuleName() {
    return Module.Name.valueOf(TextUtils.Format.asEnum(this.itemData.get(Namespaced.Key.Core.MODULE.asKey(), PersistentDataType.STRING)));
  }

  /**
   * Gets the {@link Namespaced.Key.Item#ID}.
   *
   * @return {@link Namespaced.Key.Item#ID}
   */
  public String getItemID() {
    return this.itemData.get(Namespaced.Key.Item.ID.asKey(), PersistentDataType.STRING);
  }
}
