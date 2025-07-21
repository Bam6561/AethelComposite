package me.bam6561.aethelcomposite.utils;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

/**
 * Utilities for Entities.
 *
 * @author Danny Nguyen
 * @version 1.0.57
 * @since 1.0.57
 */
public class EntityUtils {
  /**
   * Utility methods only.
   */
  private EntityUtils() {
  }

  /**
   * Spawns Entities.
   *
   * @author Danny Nguyen
   * @version 1.0.57
   * @since 1.0.57
   */
  public static class Spawn {
    /**
     * Utility methods only.
     */
    private Spawn() {
    }

    /**
     * Spawns one or more entities at a location, with optional natural randomization.
     *
     * @param location     location
     * @param entityType   entity type
     * @param spawns       number to spawn
     * @param isRandomized natural randomization
     */
    public void spawnEntity(Location location, EntityType entityType, int spawns, boolean isRandomized) {
      for (int i = 0; i < spawns; i++) {
        location.getWorld().spawnEntity(location, entityType, isRandomized);
      }
    }
  }
}
