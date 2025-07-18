package me.bam6561.aethelcomposite.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.references.markers.StringValue;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

/**
 * {@link Plugin} text.
 *
 * @author Danny Nguyen
 * @version 1.0.18
 * @since 1.0.18
 */
public class Text {
  /**
   * Enum usage only.
   */
  private Text() {
  }

  /**
   * Information type colors.
   *
   * @author Danny Nguyen
   * @version 1.0.25
   * @since 1.0.18
   */
  public enum Label {
    VALID(ChatColor.GREEN),
    INVALID(ChatColor.RED),
    TIP(ChatColor.YELLOW),
    ACTION(ChatColor.AQUA),
    DETAILS(ChatColor.GRAY);

    /**
     * Color.
     */
    private final ChatColor color;

    /**
     * Associates a label with its color.
     *
     * @param color color
     */
    Label(ChatColor color) {
      this.color = color;
    }

    /**
     * Gets the color.
     *
     * @return color.
     */
    @NotNull
    public ChatColor asColor() {
      return this.color;
    }
  }

  /**
   * Reserved namespaced key headers.
   *
   * @author Danny Nguyen
   * @version 1.0.28
   * @since 1.0.28
   */
  public enum KeyHeader implements StringValue {
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
    KeyHeader(String header) {
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
}
