package me.bam6561.aethelcomposite.modules.core.references.markers;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * References used as NamespacedKeys.
 *
 * @author Danny Nguyen
 * @version 1.0.34
 * @since 1.0.34
 */
public interface NamespacedKeyValue {
  /**
   * Gets the enum's NamespacedKey value.
   *
   * @return enum's NamespacedKey value
   */
  @NotNull
  NamespacedKey asKey();
}
