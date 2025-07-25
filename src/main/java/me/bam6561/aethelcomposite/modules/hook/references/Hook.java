package me.bam6561.aethelcomposite.modules.hook.references;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.objects.recipe.ModuleRecipe;
import me.bam6561.aethelcomposite.modules.core.references.ModuleName;
import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.references.markers.ItemStackValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.ModuleRecipeValue;
import me.bam6561.aethelcomposite.modules.core.references.markers.NamespacedKeyValue;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Hook {@link ModuleName} references.
 *
 * @author Danny Nguyen
 * @version 1.1.2
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
   * Hook items.
   *
   * @author Danny Nguyen
   * @version 1.1.3
   * @since 1.1.3
   */
  public enum Item implements ItemStackValue {
    /**
     * Projectile that launches the user forward on impact when
     * loaded into a crossbow or {@link #HOOK_GEAR H.O.O.K gear}.
     */
    HOOK_SHOT(ItemUtils.Create.createItem(Material.ARROW, ChatColor.WHITE + "Hook Shot", List.of(
            Text.Label.ACTION.asColor() + "Load Hook " + Text.Label.TIP.asColor() + "[Off-Hand][Reload Crossbow]",
            Text.Label.DETAILS.asColor() + "Modifies the next crossbow shot to launch the user forward on impact.",
            Text.Label.DETAILS.asColor() + "ID: " + ChatColor.WHITE + "Hook Shot"),
        Namespaced.Key.Core.MODULE.asKey(), ModuleName.HOOK.asString(), Namespaced.Key.Item.ID.asKey(), "hook_shot")),

    /**
     * Leggings equipment that stores and fires {@link #HOOK_SHOT hook shots}.
     */
    HOOK_GEAR(ItemUtils.Create.createItem(Material.LEATHER_LEGGINGS, ChatColor.WHITE + "H.O.O.K Gear", List.of(
            Text.Label.ACTION.asColor() + "Propel " + Text.Label.TIP.asColor() + "[Empty Off-Hand][Sneak-Interact]",
            Text.Label.DETAILS.asColor() + "Fires a projectile that launches the user forward on impact.",
            Text.Label.DETAILS.asColor() + "ID " + ChatColor.WHITE + "H.O.O.K Gear"),
        Namespaced.Key.Core.MODULE.asKey(), ModuleName.HOOK.asString(), Namespaced.Key.Item.ID.asKey(), "h.o.o.k_gear"));

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
   * @version 1.1.4
   * @since 1.1.4
   */
  public enum Recipe implements ModuleRecipeValue {
    /**
     * {@link Item#HOOK_SHOT}
     */
    HOOK_SHOT(new ModuleRecipe(List.of(new ItemStack(Material.ARROW, 4), new ItemStack(Material.TRIPWIRE_HOOK)), List.of(ItemUtils.Modify.setAmount(Item.HOOK_SHOT.asItem(), 4)))),

    /**
     * {@link Item#HOOK_GEAR}
     */
    HOOK_GEAR(new ModuleRecipe(List.of(new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.CROSSBOW), new ItemStack(Material.CROSSBOW), new ItemStack(Material.IRON_INGOT, 8)), List.of(Item.HOOK_GEAR.asItem())));

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
