package me.bam6561.aethelcomposite.modules.core.managers;

import me.bam6561.aethelcomposite.modules.core.events.UniqueModuleItemDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Manages {@link UniqueModuleItemDamageEvent} durability changes.
 *
 * @author Danny Nguyen
 * @version 1.1.39
 * @since 1.1.37
 */
public class UniqueModuleItemDamageManager {
  /**
   * No parameter constructor.
   */
  public UniqueModuleItemDamageManager() {
  }

  /**
   * On durability damage:
   * <ul>
   *   <li>
   * </ul>
   *
   * @param event player item damage event
   */
  public void interpretDamage(@NotNull UniqueModuleItemDamageEvent event) {
    Objects.requireNonNull(event, "Null event");
  }
}
