package me.bam6561.aethelcomposite.modules.core.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.markers.StringValue;
import org.jetbrains.annotations.NotNull;

/**
 * {@link Plugin} modules.
 * <p>
 * Modules are defined as add-ons that function independently.
 *
 * @author Danny Nguyen
 * @version 1.0.37
 * @since 1.0.21
 */
public class Module {
  /**
   * Enum usage only.
   */
  private Module() {
  }

  /**
   * Module names.
   *
   * @author Danny Nguyen
   * @version 1.0.31
   * @since 1.0.26
   */
  public enum Name implements StringValue {
    /**
     * Lassos.
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
    Name(String name) {
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
}