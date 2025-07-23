package me.bam6561.aethelcomposite.modules.core.events;

import me.bam6561.aethelcomposite.modules.core.markers.ModuleRecipe;
import me.bam6561.aethelcomposite.modules.core.utils.RecipeCraftOperation;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Called before crafting a {@link ModuleRecipe}.
 * <p>
 * Cancellation prevents the {@link RecipeCraftOperation} from occurring.
 *
 * @author Danny Nguyen
 * @version 1.0.80
 * @since 1.0.50
 */
public class RecipeCraftEvent extends Event implements Cancellable {
  /**
   * Event handlers.
   */
  private static final HandlerList HANDLERS = new HandlerList();

  /**
   * Cancellation state.
   */
  private boolean isCancelled = false;

  /**
   * {@link ModuleRecipe}
   */
  private final ModuleRecipe recipe;

  /**
   * {@link InventorySource}
   */
  private InventorySource invSource = InventorySource.NULL;

  /**
   * Inventory being used to craft the {@link ModuleRecipe}.
   */
  private final Inventory inv;

  /**
   * Interacting entity.
   */
  private Entity entity = null;

  /**
   * Interacting block.
   */
  private Block block = null;

  /**
   * If a player caused the event.
   */
  private boolean isCausedByPlayer = false;

  /**
   * Associates the event with its {@link InventorySource} without any entity or block interaction.
   *
   * @param recipe {@link ModuleRecipe}
   * @param inv    inventory being used to craft the recipe
   */
  public RecipeCraftEvent(@NotNull ModuleRecipe recipe, @NotNull Inventory inv) {
    this.recipe = Objects.requireNonNull(recipe, "Null recipe");
    this.inv = Objects.requireNonNull(inv, "Null inventory");
  }

  /**
   * Associates the event with its {@link InventorySource}, inventory, and interacting entity.
   *
   * @param recipe {@link ModuleRecipe}
   * @param inv    inventory being used to craft the recipe
   * @param entity interacting entity
   */
  public RecipeCraftEvent(@NotNull ModuleRecipe recipe, @NotNull Inventory inv, @NotNull Entity entity) {
    this.recipe = Objects.requireNonNull(recipe, "Null recipe");
    this.invSource = InventorySource.ENTITY;
    this.inv = Objects.requireNonNull(inv, "Null inventory");
    this.entity = Objects.requireNonNull(entity, "Null entity");
    if (entity instanceof Player) {
      this.isCausedByPlayer = true;
    }
  }

  /**
   * Associates the event with its {@link InventorySource}, inventory, and interacting block.
   *
   * @param recipe {@link ModuleRecipe}
   * @param inv    inventory being used to craft the recipe
   * @param block  interacting block
   */
  public RecipeCraftEvent(@NotNull ModuleRecipe recipe, @NotNull Inventory inv, @NotNull Block block) {
    this.recipe = Objects.requireNonNull(recipe, "Null recipe");
    this.invSource = InventorySource.BLOCK;
    this.inv = Objects.requireNonNull(inv, "Null inventory");
    this.block = Objects.requireNonNull(block, "Null block");
  }

  /**
   * Associates the event with its {@link InventorySource}, inventory, interacting entity, and interacting block.
   * <p>
   * This constructor sets the {@link InventorySource} to {@link InventorySource#ENTITY}.
   *
   * @param recipe {@link ModuleRecipe}
   * @param inv    inventory being used to craft the recipe
   * @param entity interacting entity
   * @param block  interacting block
   */
  public RecipeCraftEvent(@NotNull ModuleRecipe recipe, @NotNull Inventory inv, @NotNull Entity entity, @NotNull Block block) {
    this.recipe = Objects.requireNonNull(recipe, "Null recipe");
    this.invSource = InventorySource.ENTITY;
    this.inv = Objects.requireNonNull(inv, "Null inventory");
    this.entity = Objects.requireNonNull(entity, "Null entity");
    this.block = Objects.requireNonNull(block, "Null block");
    if (entity instanceof Player) {
      this.isCausedByPlayer = true;
    }
  }

  /**
   * Gets the {@link ModuleRecipe}.
   *
   * @return {@link ModuleRecipe}
   */
  public ModuleRecipe getRecipe() {
    return this.recipe;
  }

  /**
   * Gets the {@link InventorySource}.
   *
   * @return {@link InventorySource}
   */
  @NotNull
  public InventorySource getInvSource() {
    return this.invSource;
  }

  /**
   * Gets the inventory being used to craft the {@link ModuleRecipe}.
   *
   * @return inventory being used to craft the {@link ModuleRecipe}
   */
  @NotNull
  public Inventory getInventory() {
    return this.inv;
  }

  /**
   * Gets the interacting entity.
   *
   * @return interacting entity
   */
  @Nullable
  public Entity getEntity() {
    return this.entity;
  }

  /**
   * Gets the interacting block.
   *
   * @return interacting block
   */
  @Nullable
  public Block getBlock() {
    return this.block;
  }

  /**
   * If a player caused the event.
   *
   * @return if a player caused the event
   */
  public boolean isCausedByPlayer() {
    return this.isCausedByPlayer;
  }

  /**
   * If the event is cancelled.
   *
   * @return if the event is cancelled
   */
  @Override
  public boolean isCancelled() {
    return this.isCancelled;
  }

  /**
   * Sets the event's cancellation state.
   *
   * @param cancelled cancellation state
   */
  @Override
  public void setCancelled(boolean cancelled) {
    this.isCancelled = cancelled;
  }

  /**
   * Gets the event's handlers.
   *
   * @return event handlers
   */
  @NotNull
  @Override
  public HandlerList getHandlers() {
    return HANDLERS;
  }

  /**
   * Gets the event's handlers.
   *
   * @return event handlers
   */
  @NotNull
  public static HandlerList getHandlerList() {
    return HANDLERS;
  }

  /**
   * {@link RecipeCraftOperation RecipeCraftOperation's} inventory source.
   *
   * @author Danny Nguyen
   * @version 1.0.76
   * @since 1.0.50
   */
  public enum InventorySource {
    /**
     * Block.
     */
    BLOCK,

    /**
     * Entity.
     */
    ENTITY,

    /**
     * Indeterminate.
     */
    NULL
  }
}