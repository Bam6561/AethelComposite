package me.bam6561.aethelcomposite.modules.core.references.markers;

import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

/**
 * References usable as ChatColors.
 *
 * @author Danny Nguyen
 * @version 1.0.34
 * @since 1.0.34
 */
public interface ChatColorValue {
  /**
   * Gets the enum's ChatColor value.
   *
   * @return enum's ChatColor value
   */
  @NotNull
  ChatColor asColor();
}
