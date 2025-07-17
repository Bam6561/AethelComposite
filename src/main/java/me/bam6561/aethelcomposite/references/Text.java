package me.bam6561.aethelcomposite.references;

import me.bam6561.aethelcomposite.Plugin;
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
   * @version 1.0.23
   * @since 1.0.18
   */
  public enum Label {
    VALID(ChatColor.GREEN),
    INVALID(ChatColor.RED),
    FUNCTION(ChatColor.YELLOW),
    ACTION(ChatColor.LIGHT_PURPLE),
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
}
