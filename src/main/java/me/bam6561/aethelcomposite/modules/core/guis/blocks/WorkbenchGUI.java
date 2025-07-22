package me.bam6561.aethelcomposite.modules.core.guis.blocks;

import me.bam6561.aethelcomposite.Plugin;
import me.bam6561.aethelcomposite.modules.core.events.RecipeCraftEvent;
import me.bam6561.aethelcomposite.modules.core.guis.GUI;
import me.bam6561.aethelcomposite.modules.core.guis.blocks.markers.Workstation;
import me.bam6561.aethelcomposite.modules.core.guis.markers.CachedInventory;
import me.bam6561.aethelcomposite.modules.core.references.Text;
import me.bam6561.aethelcomposite.modules.core.utils.RecipeCraftOperation;
import me.bam6561.aethelcomposite.modules.lasso.references.Lasso;
import me.bam6561.aethelcomposite.utils.ItemUtils;
import me.bam6561.aethelcomposite.utils.TextUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Crafting table {@link GUI}, also known as a Workbench.
 *
 * @author Danny Nguyen
 * @version 1.0.70
 * @since 1.0.3
 */
public class WorkbenchGUI extends GUI implements Workstation, CachedInventory {
  /**
   * {@link CachedInventory}
   */
  private static final Inventory cachedInventory = initializeCachedInventory();

  /**
   * No parameter constructor.
   */
  public WorkbenchGUI() {
  }

  /**
   * Initializes the {@link CachedInventory}.
   *
   * @return {@link CachedInventory}
   */
  private static Inventory initializeCachedInventory() {
    Inventory inv = Bukkit.createInventory(null, 54, "Workbench");

    Lasso.Item[] items = Lasso.Item.values();

    for (int invSlot = 0; invSlot < items.length; invSlot++) {
      ItemStack item = items[invSlot].asItem();
      ItemMeta meta = item.getItemMeta();
      List<String> lore = meta.getLore();

      List<ItemStack> recipe = items[invSlot].asRecipe();
      List<String> recipeLore = new ArrayList<>(List.of("", ChatColor.WHITE + "Recipe"));

      for (ItemStack ingredient : recipe) {
        recipeLore.add(Text.Label.DETAILS.asColor() + "- x" + ingredient.getAmount() + " " + ItemUtils.Read.getEffectiveName(ingredient));
      }
      lore.addAll(recipeLore);
      meta.setLore(lore);

      item.setItemMeta(meta);
      inv.setItem(invSlot, item);
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
    if (cInv.getType() == InventoryType.PLAYER) {
      return true;
    }
    return false;
  }

  /**
   * Crafts an item if the interacting player has enough recipe ingredients.
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

    Player player = (Player) event.getWhoClicked();
    RecipeCraftEvent recipeCraft = new RecipeCraftEvent(RecipeCraftEvent.InventorySource.PLAYER, player);
    Bukkit.getPluginManager().callEvent(recipeCraft);
    if (recipeCraft.isCancelled()) {
      return;
    }

    Lasso.Item itemEnum = Lasso.Item.valueOf(TextUtils.Format.asEnum(ItemUtils.Read.getItemID(clicked)));
    RecipeCraftOperation recipeCraftOperation = new RecipeCraftOperation(player.getInventory(), List.of(itemEnum.asItem()), itemEnum.asRecipe(), 1);

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