package me.bam6561.aethelcomposite.modules.core.guis.blocks;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.guis.GUI;
import me.bam6561.aethelcomposite.modules.core.guis.blocks.markers.Workstation;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Crafting table {@link GUI}.
 *
 * @author Danny Nguyen
 * @version 1.0.41
 * @since 1.0.3
 */
public class CraftingTableGUI extends GUI implements Workstation {
  /**
   * No parameter constructor.
   */
  public CraftingTableGUI() {
  }

  /**
   * Creates the inventory.
   *
   * @return inventory
   */
  @NotNull
  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 54, "Crafting Table");
  }

  /**
   * Currently does nothing.
   */
  @Override
  protected void addButtons() {
    Inventory inv = getInventory();

    Lasso.Item[] items = Lasso.Item.values();
    Lasso.Recipe[] recipes = Lasso.Recipe.values();

    for (int i = 0; i < items.length; i++) {
      ItemStack item = items[i].asItem();
      ItemMeta meta = item.getItemMeta();
      List<String> lore = meta.getLore();

      List<ItemStack> recipe = recipes[i].asList();
      List<String> recipeList = new ArrayList<>(List.of("", ChatColor.WHITE + "Recipe"));

      for (int j = 0; j < recipe.size(); j++) {
        ItemStack ingredient = recipe.get(j);
        recipeList.add(Text.Label.DETAILS.asColor() + "- x" + ingredient.getAmount() + " " + ItemUtils.Read.getEffectiveName(ingredient));
      }
      lore.addAll(recipeList);

      meta.setLore(lore);
      item.setItemMeta(meta);
      inv.setItem(i, item);
    }
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
    if (cInv.getType() == InventoryType.PLAYER) {
      return true;
    }
    return false;
  }

  /**
   * Currently does nothing.
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