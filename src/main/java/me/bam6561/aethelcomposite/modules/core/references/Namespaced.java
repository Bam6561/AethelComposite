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
 * @version 1.0.38
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
     * Tags describing an item's meta.
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
   *
   * @author Danny Nguyen
   * @version 1.0.34
   * @since 1.0.29
   */
  public enum Key implements NamespacedKeyValue, StringValue {
    /**
     * Item ID.
     * <p>
     * Spaces are replaced with underscores.
     */
    ITEM_ID(new NamespacedKey(Plugin.getInstance(), Header.ITEM.asString() + "id"), Header.ITEM.asString() + "id");

    /**
     * Namespaced key.
     */
    private final NamespacedKey key;

    /**
     * Namespaced key string.
     */
    private final String keyString;

    /**
     * Associates the entry with the namespaced key and namespaced key string.
     *
     * @param key       namespaced key
     * @param keyString namespaced key string
     */
    Key(NamespacedKey key, String keyString) {
      this.key = key;
      this.keyString = keyString;
    }

    /**
     * Gets the namespaced key.
     *
     * @return namespaced key
     */
    @NotNull
    public NamespacedKey asKey() {
      return this.key;
    }

    /**
     * Gets the namespaced key string.
     *
     * @return namespaced key string
     */
    @Override
    @NotNull
    public String asString() {
      return this.keyString;
    }
  }
}
