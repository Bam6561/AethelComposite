package me.bam6561.aethelcomposite;

import me.bam6561.aethelcomposite.listeners.GUIListener;
import me.bam6561.aethelcomposite.listeners.PlayerListener;
import me.bam6561.aethelcomposite.managers.GUIManager;
import me.bam6561.aethelcomposite.managers.SneakInteractEntityManager;
import me.bam6561.aethelcomposite.managers.SneakInteractManager;
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
 * @version 1.0.24
 * @since 1.0.0
 */

public class Plugin extends JavaPlugin {
  /**
   * {@link GUIManager}
   */
  public static final GUIManager GUI_MANAGER = new GUIManager();

  /**
   * {@link SneakInteractManager}
   */
  public static final SneakInteractManager SNEAK_INTERACT_MANAGER = new SneakInteractManager();

  /**
   * {@link SneakInteractEntityManager}
   */
  public static final SneakInteractEntityManager SNEAK_INTERACT_ENTITY_MANAGER = new SneakInteractEntityManager();

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
    manager.registerEvents(new PlayerListener(), this);
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

  /**
   * Gets the {@link SneakInteractManager}.
   *
   * @return {@link SneakInteractManager}
   */
  @NotNull
  public static SneakInteractManager getSneakInteractManager() {
    return SNEAK_INTERACT_MANAGER;
  }

  /**
   * Gets the {@link SneakInteractEntityManager}.
   *
   * @return {@link SneakInteractEntityManager}
   */
  @NotNull
  public static SneakInteractEntityManager getSneakInteractEntityManager() {
    return SNEAK_INTERACT_ENTITY_MANAGER;
  }
}
