package me.bam6561.aethelcomposite.modules.core.utils;

import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.utils.ItemUtils;
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
 * Represents a recipe craft operation.
 * <p>
 * Only removes items from the inventory if there are enough ingredients to craft the recipe.
 *
 * @author Danny Nguyen
 * @version 1.0.49
 * @since 1.0.46
 */
public class RecipeCraftOperation {
  /**
   * {@link me.bam6561.aethelcomposite.modules.core.references.Namespaced.Key#ITEM_ID}
   */
  private final NamespacedKey itemID = Namespaced.Key.ITEM_ID.asKey();

  /**
   * Inventory used to craft the recipe.
   */
  private final Inventory inv;

  /**
   * Recipe's results.
   */
  private final List<ItemStack> results;

  /**
   * Recipe's ingredients.
   */
  private final List<ItemStack> ingredients;

  /**
   * Amounts of crafts done at once.
   */
  private final int craftAmount;

  /**
   * Map of the inventory by material.
   */
  private final Map<Material, List<SlotItemStack>> invMap;

  /**
   * Inventory slots to update if the recipe craft is successful.
   */
  private final List<SlotItemStack> postCraft = new ArrayList<>();

  /**
   * Associates a recipe craft with its necessary components.
   *
   * @param inv         inventory used to craft the recipe
   * @param results     recipe results
   * @param ingredients recipe ingredients
   * @param craftAmount amount of crafts done at once
   */
  public RecipeCraftOperation(@NotNull Inventory inv, @NotNull List<ItemStack> results, @NotNull List<ItemStack> ingredients, int craftAmount) {
    this.inv = Objects.requireNonNull(inv, "Null inventory");
    this.results = Objects.requireNonNull(results, "Null results");
    this.ingredients = Objects.requireNonNull(ingredients, "Null ingredients");
    this.craftAmount = craftAmount;
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
   * Crafts the recipe, adding its results directly to the inventory if there's space.
   * Otherwise, the results are dropped at the crafting inventory's location.
   * <p>
   * Note that this method does not handle overflow for null InventoryHolders: the extra items are discarded.
   *
   * @return if the recipe craft was successful
   */
  public boolean craft() {
    if (hasEnoughOfAllIngredients()) {
      for (SlotItemStack invSlot : postCraft) {
        ItemStack item = invSlot.getItem();
        item.setAmount(invSlot.getAmount());
        inv.setItem(invSlot.getSlot(), item);
      }

      for (int i = 0; i < craftAmount; i++) {
        for (ItemStack item : results) {
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
   * If the inventory has enough ingredients to craft the recipe.
   *
   * @return has enough ingredients
   */
  private boolean hasEnoughOfAllIngredients() {
    for (ItemStack item : ingredients) {
      Material requiredMaterial = item.getType();
      if (!invMap.containsKey(requiredMaterial)) {
        return false;
      }

      int requiredAmount = item.getAmount() * craftAmount;
      PersistentDataContainer itemTags = item.getItemMeta().getPersistentDataContainer();
      boolean hasItemID = itemTags.has(itemID, PersistentDataType.STRING);

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
        String requiredID = itemTags.get(itemID, PersistentDataType.STRING);
        if (!hasEnoughIngredientsWithIDs(requiredMaterial, requiredAmount, requiredID)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * If the inventory has enough of the required ingredient by matching material type.
   *
   * @param requiredMaterial required material
   * @param requiredAmount   required amount
   * @return has enough ingredients
   */
  private boolean hasEnoughIngredients(Material requiredMaterial, int requiredAmount) {
    for (SlotItemStack invSlot : invMap.get(requiredMaterial)) {
      PersistentDataContainer itemTags = invSlot.getItem().getItemMeta().getPersistentDataContainer();
      if (!itemTags.has(itemID, PersistentDataType.STRING)) { // Don't use unique items for crafting
        if (invSlot.getAmount() > 0) {
          requiredAmount -= invSlot.getAmount();
          if (hasRequiredAmount(invSlot, requiredAmount)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * If the inventory has enough of the required ingredient by matching material type
   * and {@link me.bam6561.aethelcomposite.modules.core.references.Namespaced.Key#ITEM_ID}.
   *
   * @param requiredMaterial required material
   * @param requiredAmount   required amount
   * @param requiredItemID   required item ID
   * @return has enough ingredients
   */
  private boolean hasEnoughIngredientsWithIDs(Material requiredMaterial, int requiredAmount, String requiredItemID) {
    for (SlotItemStack invSlot : invMap.get(requiredMaterial)) {
      PersistentDataContainer itemTags = invSlot.getItem().getItemMeta().getPersistentDataContainer();
      if (itemTags.has(itemID, PersistentDataType.STRING) && itemTags.get(itemID, PersistentDataType.STRING).equals(requiredItemID)) {
        if (invSlot.getAmount() > 0) {
          requiredAmount -= invSlot.getAmount();
          if (hasRequiredAmount(invSlot, requiredAmount)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * If the inventory has enough of the required enchanted books by matching enchantments.
   *
   * @param enchantmentMeta enchantment meta
   * @param requiredAmount  required amount
   * @return has enough enchanted book ingredients
   */
  private boolean hasEnoughEnchantedBookIngredients(EnchantmentStorageMeta enchantmentMeta, int requiredAmount) {
    for (SlotItemStack invSlot : invMap.get(Material.ENCHANTED_BOOK)) {
      ItemMeta meta = invSlot.getItem().getItemMeta();
      PersistentDataContainer itemTags = meta.getPersistentDataContainer();
      if (!itemTags.has(itemID, PersistentDataType.STRING)) { // Don't use unique items for crafting
        EnchantmentStorageMeta enchantmentMeta2 = (EnchantmentStorageMeta) meta;
        if (invSlot.getAmount() > 0 && enchantmentMeta.getStoredEnchants().equals(enchantmentMeta2.getStoredEnchants())) {
          requiredAmount -= invSlot.getAmount();
          if (hasRequiredAmount(invSlot, requiredAmount)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * If the inventory has enough of the required potions by matching potion effects.
   *
   * @param material       material
   * @param potionMeta     potion meta
   * @param requiredAmount required amount
   * @return has enough potion ingredients
   */
  private boolean hasEnoughPotionIngredients(Material material, PotionMeta potionMeta, int requiredAmount) {
    for (SlotItemStack invSlot : invMap.get(material)) {
      ItemMeta meta = invSlot.getItem().getItemMeta();
      PersistentDataContainer itemTags = meta.getPersistentDataContainer();
      if (!itemTags.has(itemID, PersistentDataType.STRING)) { // Don't use unique items for crafting
        if (invSlot.getAmount() > 0) {
          List<PotionEffect> basePotionEffects = potionMeta.getBasePotionType().getPotionEffects();
          List<PotionEffect> customPotionEffects = potionMeta.getCustomEffects();

          PotionMeta potionMeta2 = (PotionMeta) meta;
          List<PotionEffect> basePotionEffects2 = potionMeta2.getBasePotionType().getPotionEffects();
          List<PotionEffect> customPotionEffects2 = potionMeta2.getCustomEffects();

          if (basePotionEffects.equals(basePotionEffects2) && customPotionEffects.equals(customPotionEffects2)) {
            requiredAmount -= invSlot.getAmount();
            if (hasRequiredAmount(invSlot, requiredAmount)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * If the required amount of ingredients was satisfied.
   *
   * @param invSlot        tracked post-craft inventory slot
   * @param requiredAmount required amount
   * @return has enough ingredients
   */
  private boolean hasRequiredAmount(SlotItemStack invSlot, int requiredAmount) {
    if (requiredAmount > 0 || requiredAmount == 0) {
      invSlot.setAmount(0);
      postCraft.add(new SlotItemStack(invSlot.getSlot(), invSlot.getItem(), 0));
      return requiredAmount == 0;
    } else {
      int difference = Math.abs(requiredAmount);
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