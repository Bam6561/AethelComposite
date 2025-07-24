package me.bam6561.aethelcomposite.modules.core.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.markers.StringValue;
import me.bam6561.aethelcomposite.modules.hook.references.Hook;
import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import org.jetbrains.annotations.NotNull;

/**
 * {@link Plugin} modules.
 *
 * @author Danny Nguyen
 * @version 1.1.2
 * @since 1.0.102
 */
public enum ModuleName implements StringValue {
  /**
   * {@link Hook}
   */
  HOOK("hook"),

  /**
   * {@link Lasso}
   */
  LASSO("lasso");

  /**
   * Module name.
   */
  private final String name;

  /**
   * Associates the module with its name.
   *
   * @param name module name
   */
  ModuleName(String name) {
    this.name = name;
  }

  /**
   * Gets the module's name.
   *
   * @return module name
   */
  @Override
  @NotNull
  public String asString() {
    return this.name;
  }
}
