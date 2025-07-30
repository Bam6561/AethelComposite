package me.bam6561.aethelcomposite.modules.hook.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.objects.recipe.ModuleRecipe;
import me.bam6561.aethelcomposite.modules.core.references.ModuleName;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.references.markers.*;
import me.bam6561.aethelcomposite.modules.core.utils.EntityUtils;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Hook {@link ModuleName} references.
 *
 * @author Danny Nguyen
 * @version 1.1.17
 * @since 1.1.2
 */
public class Hook {
  /**
   * Enum usage only.
   */
  private Hook() {
  }

  /**
   * Reserved namespaced keys.
   *
   * @author Danny Nguyen
   * @version 1.1.5
   * @since 1.1.5
   */
  public enum Key implements NamespacedKeyValue {
    /**
     * Loaded projectile.
     */
    PROJECTILE(new NamespacedKey(Plugin.getInstance(), Namespaced.Header.ITEM.asString() + "hook.projectile"));

    /**
     * Namespaced key.
     */
    private final NamespacedKey key;

    /**
     * Associates the entry with its namespaced key.
     *
     * @param key namespaced key
     */
    Key(NamespacedKey key) {
      this.key = key;
    }

    /**
     * Gets the namespaced key.
     *
     * @return namespaced key
     */
    @Override
    @NotNull
    public NamespacedKey asKey() {
      return this.key;
    }
  }

  /**
   * Spawnable Hook entities.
   *
   * @author Danny Nguyen
   * @version 1.1.17
   * @since 1.1.17
   */
  public enum SpawnableEntity implements StringValue, SpawnableEntityValue {
    /**
     * {@link Hook.Item#HOOK_SHOT} projectile.
     */
    HOOK_SHOT(EntityType.ARROW, "hook_shot");

    /**
     * Entity type.
     */
    private final EntityType entityType;

    /**
     * {@link Namespaced.Key.Entity#ID}
     */
    private final String entityID;

    /**
     * Associates the entry with its entity type and {@link Namespaced.Key.Entity#ID}.
     *
     * @param entityType entity type
     * @param entityID   {@link Namespaced.Key.Entity#ID}
     */
    SpawnableEntity(EntityType entityType, String entityID) {
      this.entityType = entityType;
      this.entityID = entityID;
    }

    /**
     * Gets the {@link Namespaced.Key.Entity#ID}.
     *
     * @return {@link Namespaced.Key.Entity#ID}
     */
    @Override
    @NotNull
    public String asString() {
      return this.entityID;
    }

    /**
     * Spawns the entity using its base template.
     *
     * @return spawned entity
     */
    @Override
    @NotNull
    public Entity asEntity(Location loc) {
      return EntityUtils.Spawn.spawnEntity(loc, this.entityType, Namespaced.Key.Core.MODULE.asKey(), ModuleName.HOOK.asString(), Namespaced.Key.Entity.ID.asKey(), this.entityID);
    }
  }

  /**
   * Hook items.
   *
   * @author Danny Nguyen
   * @version 1.1.32
   * @since 1.1.3
   */
  public enum Item implements ItemStackValue {
    /**
     * Projectile that continuously pulls the user towards the point
     * of impact when loaded into a crossbow or {@link #HOOK_HARNESS}.
     */
    HOOK_SHOT(ItemUtils.Create.createItem(Material.ARROW, ChatColor.WHITE + "Hook Shot", List.of(Text.Label.ACTION.asColor() + "Hook Shot " + Text.Label.TIP.asColor() + "[Main Hand + Crossbow]", Text.Label.DETAILS.asColor() + "Continuously pulls the user to ", Text.Label.DETAILS.asColor() + "the projectile's point of impact.", Text.Label.DETAILS.asColor() + "{Range: 16 + Piercing * 8}", Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Hook Shot"), Namespaced.Key.Core.MODULE.asKey(), ModuleName.HOOK.asString(), Namespaced.Key.Item.ID.asKey(), "hook_shot")),

    /**
     * Leggings equipment that stores and fires {@link #HOOK_SHOT hook shots}.
     */
    HOOK_HARNESS(ItemUtils.Modify.addAttributeModifier(ItemUtils.Create.createItem(Material.LEATHER_LEGGINGS, ChatColor.WHITE + "H.O.O.K Harness", List.of(Text.Label.ACTION.asColor() + "Fire Hook Shot", Text.Label.TIP.asColor() + "[Empty Off-Hand + Sneak-Interact]", Text.Label.DETAILS.asColor() + "Fires a projectile that continuously ", Text.Label.DETAILS.asColor() + "pulls the user to its point of impact.", Text.Label.DETAILS.asColor() + "{Range: 32}", Text.Label.ACTION.asColor() + "Reload", Text.Label.TIP.asColor() + "[Hook Shot(s) in Off-Hand + Sneak-Interact]", Text.Label.DETAILS.asColor() + "Reloads Hook Shot ammunition.", Text.Label.DETAILS.asColor() + "{1:15 + Unbreaking * 15}", Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "H.O.O.K Harness"), Namespaced.Key.Core.MODULE.asKey(), ModuleName.HOOK.asString(), Namespaced.Key.Item.ID.asKey(), "hook_harness"), Attribute.ARMOR, "H.O.O.K Harness", 0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));

    /**
     * Item.
     */
    private final ItemStack item;

    /**
     * Associates an entry with its item.
     *
     * @param item item
     */
    Item(ItemStack item) {
      this.item = item;
    }

    /**
     * Gets a copy of the item.
     *
     * @return item
     */
    @Override
    @NotNull
    public ItemStack asItem() {
      return this.item.clone();
    }
  }

  /**
   * Hook {@link ModuleRecipe ModuleRecipes}.
   *
   * @author Danny Nguyen
   * @version 1.1.30
   * @since 1.1.4
   */
  public enum Recipe implements ModuleRecipeValue {
    /**
     * {@link Item#HOOK_SHOT}
     */
    HOOK_SHOT(new ModuleRecipe(List.of(new ItemStack(Material.ARROW), new ItemStack(Material.CHAIN), new ItemStack(Material.TRIPWIRE_HOOK)), List.of(ItemUtils.Modify.setAmount(Item.HOOK_SHOT.asItem(), 1)))),

    /**
     * {@link Item#HOOK_HARNESS}
     */
    HOOK_HARNESS(new ModuleRecipe(List.of(new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.IRON_INGOT, 16), new ItemStack(Material.REPEATER, 2), new ItemStack(Material.CROSSBOW, 2)), List.of(Item.HOOK_HARNESS.asItem())));

    /**
     * {@link ModuleRecipe}
     */
    private final ModuleRecipe moduleRecipe;

    /**
     * Associates an entry with its {@link ModuleRecipe}.
     *
     * @param moduleRecipe {@link ModuleRecipe}
     */
    Recipe(ModuleRecipe moduleRecipe) {
      this.moduleRecipe = moduleRecipe;
    }

    /**
     * Gets a copy of the {@link ModuleRecipe}.
     *
     * @return copy of the {@link ModuleRecipe}
     */
    @NotNull
    public ModuleRecipe asModuleRecipe() {
      return new ModuleRecipe(this.moduleRecipe);
    }
  }
}
