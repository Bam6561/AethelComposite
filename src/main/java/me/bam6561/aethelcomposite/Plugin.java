package me.bam6561.aethelcomposite;

import me.bam6561.aethelcomposite.listeners.GUIListener;
import me.bam6561.aethelcomposite.managers.GUIManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the plugin as an object.
 * <p>
 * Through event listeners and command executors, the plugin can
 * handle various requests given to it by its users and the server.
 * </p>
 *
 * @author Danny Nguyen
 * @version 1.0.13
 * @since 1.0.0
 */

public class Plugin extends JavaPlugin {
  /**
   * {@link GUIManager}
   */
  public static final GUIManager GUI_MANAGER = new GUIManager();


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
   */
  private void registerEventListeners() {
    PluginManager manager = getServer().getPluginManager();
    manager.registerEvents(new GUIListener(), this);
  }

  /**
   * Gets the plugin.
   *
   * @return plugin instance
   */
  @NotNull
  public static Plugin getInstance() {
    return getPlugin(Plugin.class);
  }

  /**
   * Gets the {@link GUIManager}.
   *
   * @return {@link GUIManager}
   */
  @NotNull
  public static GUIManager getGUIManager() {
    return GUI_MANAGER;
  }
}
