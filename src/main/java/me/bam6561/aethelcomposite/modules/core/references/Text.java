package me.bam6561.aethelcomposite.modules.core.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.markers.ChatColorValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.StringValue;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

/**
 * {@link Plugin} text.
 *
 * @author Danny Nguyen
 * @version 1.0.38
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
   * @version 1.0.34
   * @since 1.0.18
   */
  public enum Label implements ChatColorValue, StringValue {
    VALID(ChatColor.GREEN, "green"),
    INVALID(ChatColor.RED, "red"),
    TIP(ChatColor.YELLOW, "yellow"),
    ACTION(ChatColor.AQUA, "aqua"),
    DETAILS(ChatColor.GRAY, "gray");

    /**
     * Color.
     */
    private final ChatColor color;

    /**
     * Color name.
     */
    private final String colorName;

    /**
     * Associates a label with its color and color name.
     *
     * @param color     color
     * @param colorName color name
     */
    Label(ChatColor color, String colorName) {
      this.color = color;
      this.colorName = colorName;
    }

    /**
     * Gets the color.
     *
     * @return color
     */
    @Override
    @NotNull
    public ChatColor asColor() {
      return this.color;
    }


    /**
     * Gets the color name.
     *
     * @return color name
     */
    @Override
    @NotNull
    public String asString() {
      return this.colorName;
    }
  }
}
