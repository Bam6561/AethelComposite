package me.bam6561.aethelcomposite.modules.core.utils;

import me.bam6561.aethelcomposite.modules.core.references.Namespaced;
import me.bam6561.aethelcomposite.modules.core.references.PlayerHead;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

/**
 * Utilities for ItemStacks.
 *
 * @author Danny Nguyen
 * @version 1.1.4
 * @since 1.0.16
 */
public class ItemUtils {
  /**
   * Utility methods only.
   */
  private ItemUtils() {
  }

  /**
   * Creates ItemStacks with metadata.
   *
   * @author Danny Nguyen
   * @version 1.0.101
   * @since 1.0.16
   */
  public static class Create {
    /**
     * Utility methods only.
     */
    private Create() {
    }

    /**
     * Creates a named item.
     *
     * @param material item material
     * @param name     item name
     * @return named item
     */
    @NotNull
    public static ItemStack createItem(@NotNull Material material, @NotNull String name) {
      Objects.requireNonNull(material, "Null material");
      Objects.requireNonNull(name, "Null name");
      ItemStack item = new ItemStack(material);
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      item.setItemMeta(meta);
      return item;
    }

    /**
     * Creates a named item with lore.
     *
     * @param material item material
     * @param name     item name
     * @param lore     item lore
     * @return named item with lore
     */
    @NotNull
    public static ItemStack createItem(@NotNull Material material, @NotNull String name, @NotNull List<String> lore) {
      Objects.requireNonNull(material, "Null material");
      Objects.requireNonNull(name, "Null name");
      Objects.requireNonNull(lore, "Null lore");
      ItemStack item = new ItemStack(material);
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(lore);
      item.setItemMeta(meta);
      return item;
    }

    /**
     * Creates a named item with an item flag.
     *
     * @param material item material
     * @param name     item name
     * @param itemFlag item flag
     * @return named item with an item flag disabled
     */
    @NotNull
    public static ItemStack createItem(@NotNull Material material, @NotNull String name, @NotNull ItemFlag itemFlag) {
      Objects.requireNonNull(material, "Null material");
      Objects.requireNonNull(name, "Null name");
      Objects.requireNonNull(itemFlag, "Null item flag");
      ItemStack item = new ItemStack(material);
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      meta.addItemFlags(itemFlag);
      item.setItemMeta(meta);
      return item;
    }

    /**
     * Creates a named item with lore and an item flag.
     *
     * @param material item material
     * @param name     item name
     * @param lore     item lore
     * @param itemFlag item flag
     * @return named item with an item flag
     */
    @NotNull
    public static ItemStack createItem(@NotNull Material material, @NotNull String name, @NotNull List<String> lore, @NotNull ItemFlag itemFlag) {
      Objects.requireNonNull(material, "Null material");
      Objects.requireNonNull(name, "Null name");
      Objects.requireNonNull(lore, "Null lore");
      Objects.requireNonNull(itemFlag, "Null item flag");
      ItemStack item = new ItemStack(material);
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(lore);
      meta.addItemFlags(itemFlag);
      item.setItemMeta(meta);
      return item;
    }

    /**
     * Creates a named item with lore and two key string value pairs.
     *
     * @param material item material
     * @param name     item name
     * @param lore     item lore
     * @param key      key
     * @param value    key value
     * @param key2     second key
     * @param value2   second key value
     * @return named item with lore and 2 key string value pairs
     */
    @NotNull
    public static ItemStack createItem(@NotNull Material material, @NotNull String name, @NotNull List<String> lore, @NotNull NamespacedKey key, @NotNull String value, @NotNull NamespacedKey key2, @NotNull String value2) {
      Objects.requireNonNull(material, "Null material");
      Objects.requireNonNull(name, "Null name");
      Objects.requireNonNull(lore, "Null lore");
      Objects.requireNonNull(key, "Null key");
      Objects.requireNonNull(value, "Null value");
      Objects.requireNonNull(key, "Null key2");
      Objects.requireNonNull(value, "Null value2");
      ItemStack item = new ItemStack(material);
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(lore);
      meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
      meta.getPersistentDataContainer().set(key2, PersistentDataType.STRING, value2);
      item.setItemMeta(meta);
      return item;
    }

    /**
     * Creates a named player head.
     *
     * @param player interacting player
     * @return named player head
     */
    @NotNull
    public static ItemStack createPlayerHead(@NotNull Player player) {
      Objects.requireNonNull(player, "Null player");
      ItemStack item = new ItemStack(Material.PLAYER_HEAD);
      SkullMeta meta = (SkullMeta) item.getItemMeta();
      meta.setOwningPlayer(player);
      meta.setDisplayName(ChatColor.DARK_PURPLE + player.getName());
      item.setItemMeta(meta);
      return item;
    }

    /**
     * Creates a named player head with lore.
     *
     * @param player interacting player
     * @param lore   item lore
     * @return named player head with lore
     */
    @NotNull
    public static ItemStack createPlayerHead(@NotNull Player player, @NotNull List<String> lore) {
      Objects.requireNonNull(player, "Null player");
      Objects.requireNonNull(lore, "Null lore");
      ItemStack item = new ItemStack(Material.PLAYER_HEAD);
      SkullMeta meta = (SkullMeta) item.getItemMeta();
      meta.setOwningPlayer(player);
      meta.setDisplayName(ChatColor.DARK_PURPLE + player.getName());
      meta.setLore(lore);
      item.setItemMeta(meta);
      return item;
    }

    /**
     * Creates a named player head from {@link PlayerHead} textures.
     *
     * @param head {@link PlayerHead}
     * @param name item name
     * @return named player head texture
     */
    @NotNull
    public static ItemStack createPluginPlayerHead(@NotNull PlayerHead head, @NotNull String name) {
      Objects.requireNonNull(head, "Null head");
      Objects.requireNonNull(name, "Null name");
      ItemStack item = head.asItem();
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      item.setItemMeta(meta);
      return item;
    }

    /**
     * Creates a named player head from {@link PlayerHead} textures with lore.
     *
     * @param head {@link PlayerHead}
     * @param name item name
     * @param lore item lore
     * @return named player head texture with lore
     */
    @NotNull
    public static ItemStack createPluginPlayerHead(@NotNull PlayerHead head, @NotNull String name, @NotNull List<String> lore) {
      Objects.requireNonNull(head, "Null head");
      Objects.requireNonNull(name, "Null name");
      Objects.requireNonNull(lore, "Null lore");
      ItemStack item = head.asItem();
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(lore);
      item.setItemMeta(meta);
      return item;
    }
  }

  /**
   * Reads ItemStacks with metadata.
   *
   * @author Danny Nguyen
   * @version 1.0.112
   * @since 1.0.16
   */
  public static class Read {
    /**
     * Utility methods only.
     */
    private Read() {
    }

    /**
     * If the item is null or air.
     *
     * @param item interacting item
     * @return if the item is null or air
     */
    public static boolean isNullOrAir(@Nullable ItemStack item) {
      return item == null || item.getType() == Material.AIR;
    }

    /**
     * If the item is not null and not air.
     *
     * @param item interacting item
     * @return if the item is not null and not air
     */
    public static boolean isNotNullOrAir(@Nullable ItemStack item) {
      return item != null && item.getType() != Material.AIR;
    }

    /**
     * Gets the item's display name if it exists and its material name otherwise.
     *
     * @param item interacting item
     * @return effective item name
     */
    @NotNull
    public static String getEffectiveName(@NotNull ItemStack item) {
      Objects.requireNonNull(item, "Null item");
      if (!item.hasItemMeta()) {
        return TextUtils.Format.asTitle(item.getType().name());
      }
      ItemMeta meta = item.getItemMeta();
      if (!meta.hasDisplayName()) {
        return TextUtils.Format.asTitle(item.getType().name());
      }
      return meta.getDisplayName();
    }

    /**
     * Gets the item's {@link Namespaced.Key.Item#ID}, if it exists.
     *
     * @param item interacting item
     * @return item's {@link Namespaced.Key.Item#ID}
     */
    @Nullable
    public static String getItemID(ItemStack item) {
      if (item == null) {
        return null;
      }
      if (!item.hasItemMeta()) {
        return null;
      }
      PersistentDataContainer itemData = item.getItemMeta().getPersistentDataContainer();
      if (!itemData.has(Namespaced.Key.Item.ID.asKey())) {
        return null;
      }
      return itemData.get(Namespaced.Key.Item.ID.asKey(), PersistentDataType.STRING);
    }
  }

  /**
   * Modifies ItemStacks.
   *
   * @author Danny Nguyen
   * @version 1.1.4
   * @since 1.1.4
   */
  public static class Modify {
    /**
     * Utility methods only.
     */
    private Modify() {
    }

    /**
     * Modifies the amount of a given item and returns the item.
     *
     * @param amount item amount
     * @return item with modified amount
     */
    public static ItemStack setAmount(@NotNull ItemStack item, int amount) {
      Objects.requireNonNull(item, "Null item");
      item.setAmount(amount);
      return item;
    }
  }

  /**
   * Serializes and deserializes ItemStacks.
   *
   * @author Danny Nguyen
   * @version 1.0.60
   * @since 1.0.60
   */
  public static class Data {
    /**
     * Utility methods only.
     */
    private Data() {
    }

    /**
     * Serializes an item into a String of bytes.
     *
     * @param item item to encode
     * @return serialized item string
     */
    @Nullable
    public static String encodeItemString(@NotNull ItemStack item) {
      Objects.requireNonNull(item, "Null item");
      try {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        BukkitObjectOutputStream bukkitObjectOutput = new BukkitObjectOutputStream(byteOutput);
        bukkitObjectOutput.writeObject(item);
        bukkitObjectOutput.flush();
        return Base64.getEncoder().encodeToString(byteOutput.toByteArray());
      } catch (IOException ex) {
        return null;
      }
    }

    /**
     * Deserializes an item byte String.
     *
     * @param data serialized item string
     * @return decoded item
     */
    @Nullable
    public static ItemStack decodeItemString(@NotNull String data) {
      Objects.requireNonNull(data, "Null data");
      try {
        ByteArrayInputStream byteInput = new ByteArrayInputStream(Base64.getDecoder().decode(data));
        BukkitObjectInputStream bukkitObjectInput = new BukkitObjectInputStream(byteInput);
        return (ItemStack) bukkitObjectInput.readObject();
      } catch (IOException | ClassNotFoundException ex) {
        return null;
      }
    }
  }
}