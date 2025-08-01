package me.bam6561.aethelcomposite.modules.core.references.markers;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * References usable as NamespacedKeys.
 *
 * @author Danny Nguyen
 * @version 1.0.34
 * @since 1.0.34
 */
public interface NamespacedKeyValue {
  /**
   * Gets the NamespacedKey.
   *
   * @return NamespacedKey
   */
  @NotNull
  NamespacedKey asKey();
}
