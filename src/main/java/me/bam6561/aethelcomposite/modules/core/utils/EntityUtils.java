package me.bam6561.aethelcomposite.modules.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Utilities for Entities.
 *
 * @author Danny Nguyen
 * @version 1.0.59
 * @since 1.0.57
 */
public class EntityUtils {
  /**
   * Utility methods only.
   */
  private EntityUtils() {
  }

  /**
   * Spawns entities.
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
     * @param spawns       number of spawns
     * @param isRandomized natural randomization
     */
    public void spawnEntity(Location location, EntityType entityType, int spawns, boolean isRandomized) {
      for (int i = 0; i < spawns; i++) {
        location.getWorld().spawnEntity(location, entityType, isRandomized);
      }
    }
  }

  /**
   * Serializes and deserializes Entities.
   *
   * @author Danny Nguyen
   * @version 1.0.66
   * @since 1.0.59
   */
  public static class Data {
    /**
     * Utility methods only.
     */
    private Data() {
    }

    /**
     * Serializes an entity into an NBT string.
     *
     * @param entity entity to encode
     * @return serialized entity string
     */
    @Nullable
    public static String encodeEntityString(@NotNull Entity entity) {
      Objects.requireNonNull(entity, "Null entity");
      return entity.getAsString();
    }

    /**
     * Deserializes an entity NBT string and spawns it at a location.
     *
     * @param data     serialized entity string
     * @param location spawn location
     * @return decoded entity
     */
    @NotNull
    public static Entity decodeEntityString(@NotNull String data, @NotNull Location location) {
      Objects.requireNonNull(data, "Null data");
      return Bukkit.getEntityFactory().createEntitySnapshot(data).createEntity(location);
    }
  }
}
