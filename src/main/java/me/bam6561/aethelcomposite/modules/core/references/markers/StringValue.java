package me.bam6561.aethelcomposite.modules.core.references.markers;

import org.jetbrains.annotations.NotNull;

/**
 * References usable as Strings.
 *
 * @author Danny Nguyen
 * @version 1.0.18
 * @since 1.0.18
 */
public interface StringValue {
  /**
   * Gets the String.
   *
   * @return String
   */
  @NotNull
  String asString();
}