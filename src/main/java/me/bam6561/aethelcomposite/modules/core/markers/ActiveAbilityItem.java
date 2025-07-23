package me.bam6561.aethelcomposite.modules.core.markers;

/**
 * {@link ModuleItemStack ModuleItemStacks} that have an activated ability.
 *
 * @author Danny Nguyen
 * @version 1.0.81
 * @since 1.0.81
 */
public interface ActiveAbilityItem {
  /**
   * Triggers the active ability.
   */
  void triggerActiveAbility();
}
