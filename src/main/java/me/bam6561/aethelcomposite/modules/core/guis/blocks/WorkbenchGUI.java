package me.bam6561.aethelcomposite.modules.core.guis.blocks;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.events.RecipeCraftEvent;
import me.bam6561.aethelcomposite.modules.core.guis.GUI;
import me.bam6561.aethelcomposite.modules.core.guis.blocks.markers.Workstation;
import me.bam6561.aethelcomposite.modules.core.guis.markers.CachedInventory;
import me.bam6561.aethelcomposite.modules.core.objects.ModuleRecipe;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.utils.RecipeCraftOperation;
import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Crafting table {@link GUI}, also known as a Workbench.
 *
 * @author Danny Nguyen
 * @version 1.0.92
 * @since 1.0.3
 */
public class WorkbenchGUI extends GUI implements Workstation, CachedInventory {
  /**
   * {@link CachedInventory}
   */
  private static final Inventory cachedInventory = initializeCachedInventory();

  /**
   * Inventory slot : {@link ModuleRecipe}
   */
  private static final Map<Integer, ModuleRecipe> cachedModuleRecipes = new HashMap<>();

  /**
   * No parameter constructor.
   */
  public WorkbenchGUI() {
  }

  /**
   * Initializes the {@link CachedInventory} and maps inventory slot : {@link ModuleRecipe}.
   *
   * @return {@link CachedInventory}
   */
  private static Inventory initializeCachedInventory() {
    Inventory inv = Bukkit.createInventory(null, 54, "Workbench");

    List<ModuleRecipe> moduleRecipes = new ArrayList<>();
    for (Lasso.Recipe lassoRecipe : Lasso.Recipe.values()) {
      moduleRecipes.add(lassoRecipe.asModuleRecipe());
    }

    for (int invSlot = 0; invSlot < moduleRecipes.size(); invSlot++) {
      ModuleRecipe recipe = moduleRecipes.get(invSlot);
      cachedModuleRecipes.put(invSlot, recipe);

      List<ItemStack> results = recipe.getResults();
      List<ItemStack> ingredients = recipe.getIngredients();

      ItemStack displayItem = results.getFirst();
      ItemMeta meta = displayItem.getItemMeta();
      List<String> lore = meta.getLore();

      List<String> recipeLore = new ArrayList<>(List.of("", ChatColor.WHITE + "Results"));
      for (ItemStack result : results) {
        recipeLore.add(Text.Label.DETAILS.asColor() + "- x" + result.getAmount() + " " + ItemUtils.Read.getEffectiveName(result));
      }
      recipeLore.add("");
      recipeLore.add(ChatColor.WHITE + "Ingredients");
      for (ItemStack ingredient : ingredients) {
        recipeLore.add(Text.Label.DETAILS.asColor() + "- x" + ingredient.getAmount() + " " + ItemUtils.Read.getEffectiveName(ingredient));
      }
      lore.addAll(recipeLore);
      meta.setLore(lore);

      displayItem.setItemMeta(meta);
      inv.setItem(invSlot, displayItem);
    }
    return inv;
  }

  /**
   * Creates the inventory.
   *
   * @return inventory
   */
  @NotNull
  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 54, "Workbench");
  }

  /**
   * Retrieves the {@link CachedInventory}.
   */
  @Override
  protected void addButtons() {
    getInventory().setContents(cachedInventory.getContents());
  }

  /**
   * Finishes {@link Plugin} interactions early if the user clicks
   * outside any inventories or uses their player inventory.
   *
   * @param event inventory click event
   * @return finished interaction
   */
  @Override
  protected boolean isNullOrPlayerInventoryClick(@NotNull InventoryClickEvent event) {
    Objects.requireNonNull(event, "Null event");
    Inventory cInv = event.getClickedInventory();
    if (cInv == null) {
      return true;
    }
    return cInv.getType() == InventoryType.PLAYER;
  }

  /**
   * Crafts a {@link ModuleRecipe} through a {@link RecipeCraftOperation}
   * if the interacting player has enough {@link ModuleRecipe} ingredients.
   *
   * @param event inventory click event
   */
  @Override
  public void onClick(@NotNull InventoryClickEvent event) {
    Objects.requireNonNull(event, "Null event");
    if (isNullOrPlayerInventoryClick(event)) {
      return;
    }
    event.setCancelled(true);

    ItemStack clicked = event.getCurrentItem();
    if (ItemUtils.Read.isNullOrAir(clicked)) {
      return;
    }

    ModuleRecipe recipe = cachedModuleRecipes.get(event.getSlot());
    Player player = (Player) event.getWhoClicked();
    Inventory inv = player.getInventory();

    RecipeCraftEvent recipeCraft = new RecipeCraftEvent(recipe, inv, player);
    Bukkit.getPluginManager().callEvent(recipeCraft);
    if (recipeCraft.isCancelled()) {
      return;
    }

    RecipeCraftOperation recipeCraftOperation = new RecipeCraftOperation(recipe, inv, 1);
    if (!recipeCraftOperation.craft()) {
      player.sendMessage(Text.Label.INVALID.asColor() + "[!] Insufficient ingredients.");
    }
  }

  /**
   * Cancels dragging items while the inventory is open.
   *
   * @param event inventory drag event
   */
  @Override
  public void onDrag(@NotNull InventoryDragEvent event) {
    Objects.requireNonNull(event, "Null event");
    event.setCancelled(true);
  }

  /**
   * Adds buttons to the {@link GUI}.
   *
   * @param event inventory open event
   */
  @Override
  public void onOpen(@NotNull InventoryOpenEvent event) {
    Objects.requireNonNull(event, "Null event");
    addButtons();
  }

  /**
   * Currently does nothing.
   *
   * @param event inventory close event
   */
  @Override
  public void onClose(@NotNull InventoryCloseEvent event) {
    Objects.requireNonNull(event, "Null event");
  }
}