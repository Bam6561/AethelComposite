package me.bam6561.aethelcomposite.modules.core.objects.recipe;

import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Represents a {@link ModuleRecipe} craft operation.
 * <p>
 * Only removes items from the inventory if there are enough
 * ingredients to craft the results of the {@link ModuleRecipe}.
 *
 * @author Danny Nguyen
 * @version 1.0.93
 * @since 1.0.46
 */
public class RecipeCraftOperation {
  /**
   * {@link Namespaced.Key.Item#ID}
   */
  private final NamespacedKey itemID = Namespaced.Key.Item.ID.asKey();

  /**
   * {@link ModuleRecipe}
   */
  private final ModuleRecipe recipe;

  /**
   * Inventory used to craft the {@link ModuleRecipe}.
   */
  private final Inventory inv;

  /**
   * Number of crafts done at once.
   */
  private final int crafts;

  /**
   * Map of the inventory by material.
   */
  private final Map<Material, List<SlotItemStack>> invMap;

  /**
   * Inventory slots to update if the {@link ModuleRecipe} craft is successful.
   */
  private final List<SlotItemStack> postCraft = new ArrayList<>();

  /**
   * Associates a {@link ModuleRecipe} craft with its necessary components.
   *
   * @param recipe {@link ModuleRecipe}
   * @param inv    inventory used to craft the {@link ModuleRecipe}
   * @param crafts number of crafts done at once
   */
  public RecipeCraftOperation(@NotNull ModuleRecipe recipe, @NotNull Inventory inv, int crafts) {
    this.recipe = Objects.requireNonNull(recipe, "Null recipe");
    this.inv = Objects.requireNonNull(inv, "Null inventory");
    this.crafts = crafts;
    this.invMap = mapInventoryMaterials();
  }

  /**
   * Maps the inventory by material.
   *
   * @return map of material : inventory slots
   */
  private Map<Material, List<SlotItemStack>> mapInventoryMaterials() {
    Map<Material, List<SlotItemStack>> materialSlots = new HashMap<>();
    for (int i = 0; i < inv.getSize(); i++) {
      ItemStack item = inv.getItem(i);
      if (ItemUtils.Read.isNullOrAir(item)) {
        continue;
      }

      Material material = item.getType();
      int amount = item.getAmount();
      if (materialSlots.containsKey(material)) {
        materialSlots.get(material).add(new SlotItemStack(i, item, amount));
      } else {
        materialSlots.put(material, new ArrayList<>(List.of(new SlotItemStack(i, item, amount))));
      }
    }
    return materialSlots;
  }

  /**
   * Crafts the {@link ModuleRecipe}, adding its results directly to the inventory if there's space.
   * Otherwise, the results are dropped at the crafting inventory's location.
   * <p>
   * Note that this method does not handle overflow for null InventoryHolders: the extra items are discarded.
   *
   * @return true if the {@link ModuleRecipe} craft succeeded
   */
  public boolean craft() {
    if (hasEnoughOfAllIngredients()) {
      for (SlotItemStack invSlot : postCraft) {
        ItemStack item = invSlot.getItem();
        item.setAmount(invSlot.getAmount());
        inv.setItem(invSlot.getSlot(), item);
      }

      for (int i = 0; i < crafts; i++) {
        for (ItemStack item : recipe.getResults()) {
          if (inv.firstEmpty() != -1) {
            inv.addItem(item);
          } else {
            InventoryHolder invHolder = inv.getHolder();
            if (invHolder instanceof Player player) {
              player.getWorld().dropItem(player.getLocation(), item);
            } else if (invHolder instanceof BlockInventoryHolder blockInventoryHolder) {
              Location loc = blockInventoryHolder.getBlock().getLocation();
              loc.getWorld().dropItem(loc, item);
            }
          }
        }
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks whether the inventory has enough ingredients to craft the {@link ModuleRecipe}.
   *
   * @return true if sufficient ingredients
   */
  private boolean hasEnoughOfAllIngredients() {
    for (ItemStack item : recipe.getIngredients()) {
      Material requiredMaterial = item.getType();
      if (!invMap.containsKey(requiredMaterial)) {
        return false;
      }

      int requiredAmount = item.getAmount() * crafts;
      PersistentDataContainer itemData = item.getItemMeta().getPersistentDataContainer();
      boolean hasItemID = itemData.has(itemID, PersistentDataType.STRING);

      if (!hasItemID) {
        switch (requiredMaterial) {
          case ENCHANTED_BOOK -> {
            if (!hasEnoughEnchantedBookIngredients((EnchantmentStorageMeta) item.getItemMeta(), requiredAmount)) {
              return false;
            }
          }
          case POTION -> {
            if (!hasEnoughPotionIngredients(Material.POTION, (PotionMeta) item.getItemMeta(), requiredAmount)) {
              return false;
            }
          }
          case SPLASH_POTION -> {
            if (!hasEnoughPotionIngredients(Material.SPLASH_POTION, (PotionMeta) item.getItemMeta(), requiredAmount)) {
              return false;
            }
          }
          case LINGERING_POTION -> {
            if (!hasEnoughPotionIngredients(Material.LINGERING_POTION, (PotionMeta) item.getItemMeta(), requiredAmount)) {
              return false;
            }
          }
          default -> {
            if (!hasEnoughIngredients(requiredMaterial, requiredAmount)) {
              return false;
            }
          }
        }
      } else {
        String requiredID = itemData.get(itemID, PersistentDataType.STRING);
        if (!hasEnoughIngredientsWithIDs(requiredMaterial, requiredAmount, requiredID)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Checks whether the inventory has enough of the required ingredient by matching material type.
   *
   * @param requiredMaterial required material
   * @param requiredNumber   required number
   * @return true if sufficient ingredients
   */
  private boolean hasEnoughIngredients(Material requiredMaterial, int requiredNumber) {
    for (SlotItemStack invSlot : invMap.get(requiredMaterial)) {
      PersistentDataContainer itemData = invSlot.getItem().getItemMeta().getPersistentDataContainer();
      if (!itemData.has(itemID, PersistentDataType.STRING)) { // Don't use unique items for crafting
        if (invSlot.getAmount() > 0) {
          requiredNumber -= invSlot.getAmount();
          if (hasRequiredNumber(invSlot, requiredNumber)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Checks whether the inventory has enough of the required ingredient
   * by matching material type and {@link Namespaced.Key.Item#ID}.
   *
   * @param requiredMaterial required material
   * @param requiredNumber   required number
   * @param requiredItemID   {@link Namespaced.Key.Item#ID}
   * @return true if sufficient ingredients
   */
  private boolean hasEnoughIngredientsWithIDs(Material requiredMaterial, int requiredNumber, String requiredItemID) {
    for (SlotItemStack invSlot : invMap.get(requiredMaterial)) {
      PersistentDataContainer itemData = invSlot.getItem().getItemMeta().getPersistentDataContainer();
      if (itemData.has(itemID, PersistentDataType.STRING) && itemData.get(itemID, PersistentDataType.STRING).equals(requiredItemID)) {
        if (invSlot.getAmount() > 0) {
          requiredNumber -= invSlot.getAmount();
          if (hasRequiredNumber(invSlot, requiredNumber)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Checks whether the inventory has enough of the required enchanted books by matching enchantments.
   *
   * @param enchantmentMeta enchantment meta
   * @param requiredNumber  required number
   * @return true if sufficient enchanted book ingredients
   */
  private boolean hasEnoughEnchantedBookIngredients(EnchantmentStorageMeta enchantmentMeta, int requiredNumber) {
    for (SlotItemStack invSlot : invMap.get(Material.ENCHANTED_BOOK)) {
      ItemMeta meta = invSlot.getItem().getItemMeta();
      PersistentDataContainer itemData = meta.getPersistentDataContainer();
      if (!itemData.has(itemID, PersistentDataType.STRING)) { // Don't use unique items for crafting
        EnchantmentStorageMeta enchantmentMeta2 = (EnchantmentStorageMeta) meta;
        if (invSlot.getAmount() > 0 && enchantmentMeta.getStoredEnchants().equals(enchantmentMeta2.getStoredEnchants())) {
          requiredNumber -= invSlot.getAmount();
          if (hasRequiredNumber(invSlot, requiredNumber)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Checks whether the inventory has enough of the required potions by matching potion effects.
   *
   * @param material       material
   * @param potionMeta     potion meta
   * @param requiredNumber required number
   * @return true if sufficient potion ingredients
   */
  private boolean hasEnoughPotionIngredients(Material material, PotionMeta potionMeta, int requiredNumber) {
    for (SlotItemStack invSlot : invMap.get(material)) {
      ItemMeta meta = invSlot.getItem().getItemMeta();
      PersistentDataContainer itemData = meta.getPersistentDataContainer();
      if (!itemData.has(itemID, PersistentDataType.STRING)) { // Don't use unique items for crafting
        if (invSlot.getAmount() > 0) {
          List<PotionEffect> basePotionEffects = potionMeta.getBasePotionType().getPotionEffects();
          List<PotionEffect> customPotionEffects = potionMeta.getCustomEffects();

          PotionMeta potionMeta2 = (PotionMeta) meta;
          List<PotionEffect> basePotionEffects2 = potionMeta2.getBasePotionType().getPotionEffects();
          List<PotionEffect> customPotionEffects2 = potionMeta2.getCustomEffects();

          if (basePotionEffects.equals(basePotionEffects2) && customPotionEffects.equals(customPotionEffects2)) {
            requiredNumber -= invSlot.getAmount();
            if (hasRequiredNumber(invSlot, requiredNumber)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * Checks whether the required number of ingredients was satisfied.
   *
   * @param invSlot        tracked post-craft inventory slot
   * @param requiredNumber required number
   * @return true if sufficient amount
   */
  private boolean hasRequiredNumber(SlotItemStack invSlot, int requiredNumber) {
    if (requiredNumber > 0 || requiredNumber == 0) {
      invSlot.setAmount(0);
      postCraft.add(new SlotItemStack(invSlot.getSlot(), invSlot.getItem(), 0));
      return requiredNumber == 0;
    } else {
      int difference = Math.abs(requiredNumber);
      invSlot.setAmount(difference);
      postCraft.add(new SlotItemStack(invSlot.getSlot(), invSlot.getItem(), difference));
      return true;
    }
  }

  /**
   * Represents an inventory slot containing an ItemStack.
   * <p>
   * The "amount" field is separate from the ItemStack's built-in amount since it is
   * only used to set a new value after crafting if the requirements are met.
   *
   * @author Danny Nguyen
   * @version 1.0.46
   * @since 1.0.46
   */
  private static class SlotItemStack {
    /**
     * Inventory slot number.
     */
    private final int slot;

    /**
     * ItemStack at the inventory slot.
     */
    private final ItemStack item;

    /**
     * Post-craft amount of the ItemStack.
     */
    private int amount;

    /**
     * Associates an inventory slot with its item and current amount.
     *
     * @param slot   inventory slot number
     * @param item   ItemStack
     * @param amount amount of ItemStack
     */
    SlotItemStack(int slot, @NotNull ItemStack item, int amount) {
      this.item = Objects.requireNonNull(item, "Null item");
      this.slot = slot;
      this.amount = amount;
    }

    /**
     * Gets the inventory slot.
     *
     * @return inventory slot
     */
    private int getSlot() {
      return this.slot;
    }

    /**
     * Gets the ItemStack.
     *
     * @return ItemStack
     */
    @NotNull
    private ItemStack getItem() {
      return this.item;
    }

    /**
     * Gets the post-craft amount of the ItemStack.
     *
     * @return post-craft amount of the ItemStack
     */
    private int getAmount() {
      return this.amount;
    }

    /**
     * Sets the post-craft amount of the ItemStack.
     *
     * @param amount post-craft amount of the ItemStack
     */
    private void setAmount(int amount) {
      this.amount = amount;
    }
  }
}