package me.bam6561.aethelcomposite.modules.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Utilities for Entities.
 *
 * @author Danny Nguyen
 * @version 1.1.15
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
   * @version 1.1.16
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
    public static void spawnEntity(@NotNull Location location, @NotNull EntityType entityType, int spawns, boolean isRandomized) {
      Objects.requireNonNull(location, "Null location");
      Objects.requireNonNull(entityType, "Null entity type");
      for (int i = 0; i < spawns; i++) {
        location.getWorld().spawnEntity(location, entityType, isRandomized);
      }
    }

    /**
     * Spawns an entity with 2 key string values.
     *
     * @param location   spawn location
     * @param entityType entity type
     * @param key        key
     * @param value      key value
     * @param key2       second key
     * @param value2     second key value
     * @return entity with 2 key string values
     */
    public static Entity spawnEntity(@NotNull Location location, @NotNull EntityType entityType, @NotNull NamespacedKey key, @NotNull String value, @NotNull NamespacedKey key2, @NotNull String value2) {
      Objects.requireNonNull(location, "Null location");
      Objects.requireNonNull(entityType, "Null entity type");
      Objects.requireNonNull(key, "Null key");
      Objects.requireNonNull(value, "Null value");
      Objects.requireNonNull(key, "Null key2");
      Objects.requireNonNull(value2, "Null value2");
      Entity entity = location.getWorld().spawnEntity(location, entityType);
      entity.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
      entity.getPersistentDataContainer().set(key2, PersistentDataType.STRING, value2);
      return entity;
    }
  }

  /**
   * Modifies entities.
   *
   * @author Danny Nguyen
   * @version 1.1.16
   * @since 1.1.15
   */
  public static class Modify {
    /**
     * Utility methods only.
     */
    private Modify() {
    }

    /**
     * Modifies 2 key string value pairs of an entity's entity data.
     *
     * @param entity interacting entity
     * @param key    key
     * @param value  key value
     * @param key2   second key
     * @param value2 second key value
     */
    public static void setEntityData(@NotNull Entity entity, @NotNull NamespacedKey key, @NotNull String value, @NotNull NamespacedKey key2, @NotNull String value2) {
      Objects.requireNonNull(entity, "Null entity");
      Objects.requireNonNull(key, "Null key");
      Objects.requireNonNull(value, "Null value");
      Objects.requireNonNull(key, "Null key2");
      Objects.requireNonNull(value2, "Null value2");
      entity.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
      entity.getPersistentDataContainer().set(key2, PersistentDataType.STRING, value2);
    }
  }

  /**
   * Serializes and deserializes Entities.
   *
   * @author Danny Nguyen
   * @version 1.0.104
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
      Objects.requireNonNull(location, "Null location");
      return Bukkit.getEntityFactory().createEntitySnapshot(data).createEntity(location);
    }
  }
}
