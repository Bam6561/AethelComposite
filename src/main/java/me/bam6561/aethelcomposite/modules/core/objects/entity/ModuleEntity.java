package me.bam6561.aethelcomposite.modules.core.objects.entity;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.references.ModuleName;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.utils.TextUtils;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents an entity managed by the {@link Plugin}.
 * <p>
 * Unlike Entities, ModuleEntities belong to a {@link ModuleName} and have a {@link Namespaced.Key.Entity#ID}.
 *
 * @author Danny Nguyen
 * @version 1.1.14
 * @since 1.1.14
 */
public class ModuleEntity {
  /**
   * Entity.
   */
  private final Entity entity;

  /**
   * Entity data.
   */
  private final PersistentDataContainer entityData;

  /**
   * Associates the ModuleEntity with its entity.
   *
   * @param entity interacting entity
   */
  public ModuleEntity(@NotNull Entity entity) {
    this.entity = Objects.requireNonNull(entity, "Null entity");
    this.entityData = entity.getPersistentDataContainer();
    if (!entityData.has(Namespaced.Key.Core.MODULE.asKey(), PersistentDataType.STRING)) {
      throw new IllegalArgumentException("Not a ModuleEntity");
    }
    if (!entityData.has(Namespaced.Key.Entity.ID.asKey(), PersistentDataType.STRING)) {
      throw new IllegalArgumentException("Not a ModuleEntity");
    }
  }

  /**
   * Gets the entity.
   *
   * @return entity
   */
  public Entity getEntity() {
    return this.entity;
  }

  /**
   * Gets the entity data.
   *
   * @return entity data
   */
  public PersistentDataContainer getEntityData() {
    return this.entityData;
  }

  /**
   * Gets the {@link ModuleName}.
   *
   * @return {@link ModuleName}
   */
  public ModuleName getModuleName() {
    return ModuleName.valueOf(TextUtils.Format.asEnum(this.entityData.get(Namespaced.Key.Core.MODULE.asKey(), PersistentDataType.STRING)));
  }

  /**
   * Gets the {@link Namespaced.Key.Entity#ID}.
   *
   * @return {@link Namespaced.Key.Entity#ID}
   */
  public String getEntityID() {
    return this.entityData.get(Namespaced.Key.Entity.ID.asKey(), PersistentDataType.STRING);
  }
}
