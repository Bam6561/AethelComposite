package me.bam6561.aethelcomposite.utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
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
   * Serializes and deserializes entities.
   *
   * @author Danny Nguyen
   * @version 1.0.59
   * @since 1.0.59
   */
  public static class Data {
    /**
     * Utility methods only.
     */
    private Data() {
    }

    /**
     * Serializes an entity into a byte string.
     *
     * @param entity entity to encode
     * @return serialized entity string
     */
    @Nullable
    public static String encodeEntityString(@NotNull Entity entity) {
      Objects.requireNonNull(entity, "Null entity");
      try {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        BukkitObjectOutputStream bukkitObjectOutput = new BukkitObjectOutputStream(byteOutput);
        bukkitObjectOutput.writeObject(entity);
        bukkitObjectOutput.flush();
        return Base64.getEncoder().encodeToString(byteOutput.toByteArray());
      } catch (IOException ex) {
        return null;
      }
    }

    /**
     * Deserializes an entity byte string.
     *
     * @param data serialized entity string
     * @return decoded entity
     */
    public static Entity decodeEntityString(@NotNull String data) {
      Objects.requireNonNull(data, "Null data");
      try {
        ByteArrayInputStream byteInput = new ByteArrayInputStream(Base64.getDecoder().decode(data));
        BukkitObjectInputStream bukkitObjectInput = new BukkitObjectInputStream(byteInput);
        return (Entity) bukkitObjectInput.readObject();
      } catch (IOException | ClassNotFoundException ex) {
        return null;
      }
    }
  }
}
