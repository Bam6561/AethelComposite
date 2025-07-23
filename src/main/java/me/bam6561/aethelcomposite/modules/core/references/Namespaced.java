package me.bam6561.aethelcomposite.modules.core.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.markers.NamespacedKeyValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.StringValue;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * {@link Plugin} namespaces.
 *
 * @author Danny Nguyen
 * @version 1.0.83
 * @since 1.0.38
 */
public class Namespaced {
  /**
   * Enum usage only.
   */
  private Namespaced() {
  }

  /**
   * Reserved namespaced key headers.
   * <p>
   * Key headers are divided into categories by period characters for parsing.
   *
   * @author Danny Nguyen
   * @version 1.0.38
   * @since 1.0.28
   */
  public enum Header implements StringValue {
    /**
     * {@link Plugin}
     */
    AETHEL("aethel."),

    /**
     * Item data.
     */
    ITEM(AETHEL.asString() + "item.");

    /**
     * Key header.
     */
    private final String header;

    /**
     * Associates the entry with the key header.
     *
     * @param header key header
     */
    Header(String header) {
      this.header = header;
    }

    /**
     * Gets the key header.
     *
     * @return key header
     */
    @Override
    @NotNull
    public String asString() {
      return this.header;
    }
  }

  /**
   * Reserved namespaced keys.
   * <p>
   * Spaces are replaced with underscores.
   *
   * @author Danny Nguyen
   * @version 1.0.83
   * @since 1.0.83
   */
  public static class Key {
    /**
     * Enum usage only.
     */
    private Key() {
    }

    /**
     * Top level namespace keys.
     *
     * @author Danny Nguyen
     * @version 1.0.83
     * @since 1.0.83
     */
    public enum Core implements NamespacedKeyValue {
      /**
       * Module.
       */
      MODULE(new NamespacedKey(Plugin.getInstance(), Header.AETHEL.asString() + "module"));

      /**
       * Namespaced key.
       */
      private final NamespacedKey key;

      /**
       * Associates the entry with the namespaced key.
       *
       * @param key namespaced key
       */
      Core(NamespacedKey key) {
        this.key = key;
      }

      /**
       * Gets the namespaced key.
       *
       * @return namespaced key
       */
      @Override
      @NotNull
      public NamespacedKey asKey() {
        return this.key;
      }
    }

    /**
     * Item-related namespaced keys.
     *
     * @author Danny Nguyen
     * @version 1.0.83
     * @since 1.0.29
     */
    public enum Item implements NamespacedKeyValue {
      /**
       * Item ID.
       */
      ID(new NamespacedKey(Plugin.getInstance(), Header.ITEM.asString() + "id"));

      /**
       * Namespaced key.
       */
      private final NamespacedKey key;

      /**
       * Associates the entry with the namespaced key.
       *
       * @param key namespaced key
       */
      Item(NamespacedKey key) {
        this.key = key;
      }

      /**
       * Gets the namespaced key.
       *
       * @return namespaced key
       */
      @Override
      @NotNull
      public NamespacedKey asKey() {
        return this.key;
      }
    }
  }
}
