package me.bam6561.aethelcomposite.modules.core.references.markers;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * References usable as spawnable Entities.
 *
 * @author Danny Nguyen
 * @version 1.1.16
 * @since 1.1.16
 */
public interface SpawnableEntityValue {
  /**
   * Spawns and returns the entity.
   *
   * @param loc spawn location
   * @return spawned entity
   */
  @NotNull
  Entity asEntity(Location loc);
}
