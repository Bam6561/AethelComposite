package me.bam6561.aethelcomposite;

import me.bam6561.aethelcomposite.listeners.ActionListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents the plugin as an object.
 * <p>
 * Through event listeners and command executors, the plugin can
 * handle various requests given to it by its users and the server.
 * </p>
 *
 * @author Danny Nguyen
 * @version 0
 * @since 0
 */

public class Plugin extends JavaPlugin {
  /**
   * No parameter constructor.
   */
  public Plugin() {
  }

  /**
   * On enable:
   * <ul>
   *   <li>{@link #registerEventListeners() Registers} event listeners.
   * </ul>
   */
  @Override
  public void onEnable() {
    registerEventListeners();
  }

  /**
   * On disable:
   * <ul>
   *   <li>
   * </ul>
   */
  @Override
  public void onDisable() {
  }

  /**
   * Registers the plugin's event listeners.
   * <ul>
   *   <li>{@link ActionListener}
   * </ul>
   */
  private void registerEventListeners() {
    PluginManager manager = getServer().getPluginManager();
    manager.registerEvents(new ActionListener(), this);
  }
}
