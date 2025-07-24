package me.bam6561.aethelcomposite.modules.core.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.markers.ChatColorValue;
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
   * @version 1.0.99
   * @since 1.0.18
   */
  public enum Label implements ChatColorValue {
    /**
     * Approval or confirmation.
     */
    VALID(ChatColor.GREEN),

    /**
     * Rejection or denial.
     */
    INVALID(ChatColor.RED),

    /**
     * Advice or instruction.
     */
    TIP(ChatColor.YELLOW),

    /**
     * Ability or skill.
     */
    ACTION(ChatColor.AQUA),

    /**
     * Data or notes.
     */
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
     * @return color
     */
    @Override
    @NotNull
    public ChatColor asColor() {
      return this.color;
    }
  }
}
