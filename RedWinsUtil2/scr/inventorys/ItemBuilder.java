package inventorys;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder
{
    private ItemStack is;
    
    public ItemBuilder(final Material m) {
        this(m, 1, (short)0);
    }
    
    public ItemBuilder(final ItemStack is) {
        this.is = is.clone();
    }
    
    public ItemBuilder(final Material m, final int amount, final short data) {
        this.is = new ItemStack(m, amount, data);
    }
    
    public ItemBuilder clone() {
        return new ItemBuilder(this.is);
    }
    
    public ItemBuilder durability(final int dur) {
        this.is.setDurability((short)dur);
        return this;
    }
    
    public ItemBuilder flag(final ItemFlag flag) {
        final ItemMeta im = this.is.getItemMeta();
        im.addItemFlags(new ItemFlag[] { flag });
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder glow() {
        final ItemMeta im = this.is.getItemMeta();
        im.addEnchant(Enchantment.LURE, 1, false);
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder name(final String name) {
        final ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder unsafeEnchantment(final Enchantment ench, final int level) {
        this.is.addUnsafeEnchantment(ench, level);
        return this;
    }
    
    public ItemBuilder enchant(final Enchantment ench, final int level) {
        final ItemMeta im = this.is.getItemMeta();
        im.addEnchant(ench, level, true);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder removeEnchantment(final Enchantment ench) {
        this.is.removeEnchantment(ench);
        return this;
    }
    
    public ItemBuilder owner(final String owner) {
        try {
            final SkullMeta im = (SkullMeta)this.is.getItemMeta();
            im.setOwner(owner);
            this.is.setItemMeta((ItemMeta)im);
        }
        catch (ClassCastException ex) {}
        return this;
    }
    
    public ItemBuilder infinityDurabilty() {
        this.is.setDurability((short)32767);
        return this;
    }
    
    public ItemBuilder lore(final String... lore) {
        final ItemMeta im = this.is.getItemMeta();
        final List<String> out = (im.getLore() == null) ? new ArrayList<String>() : im.getLore();
        final String[] arrayOfString = lore;
        final int i = lore.length;
        for (byte b = 0; b < i; ++b) {
            final String string = arrayOfString[b];
            out.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        im.setLore(out);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder listLore(final List<String> lore) {
        final ItemMeta im = this.is.getItemMeta();
        final List<String> out = (im.getLore() == null) ? new ArrayList<String>() : im.getLore();
        for (final String string : lore) {
            out.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        im.setLore(out);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemBuilder dyeColor(final DyeColor color) {
        this.is.setDurability((short)color.getDyeData());
        return this;
    }
    
    public ItemBuilder woolColor(final DyeColor color) {
        if (!this.is.getType().equals((Object)Material.WOOL)) {
            return this;
        }
        this.is.setDurability((short)color.getDyeData());
        return this;
    }
    
    public ItemBuilder amount(int amount) {
        if (amount > 64) {
            amount = 64;
        }
        this.is.setAmount(amount);
        return this;
    }
    
    public ItemBuilder removeAttributes() {
        final ItemMeta meta = this.is.getItemMeta();
        meta.addItemFlags(ItemFlag.values());
        this.is.setItemMeta(meta);
        return this;
    }
    
    public ItemStack build() {
        return this.is;
    }
    
    public ItemBuilder color(final Color color) {
        if (!this.is.getType().name().contains("LEATHER_")) {
            return this;
        }
        final LeatherArmorMeta meta = (LeatherArmorMeta)this.is.getItemMeta();
        meta.setColor(color);
        this.is.setItemMeta((ItemMeta)meta);
        return this;
    }
    
    public ItemBuilder head(String texture) {
        if (texture == null || texture.isEmpty())
          return this; 
        SkullMeta skullMeta = (SkullMeta)this.is.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { texture }).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
          profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException|SecurityException ex3) {
          Exception ex = null;
          Exception e = ex;
          e.printStackTrace();
        } 
        profileField.setAccessible(true);
        try {
          profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException|IllegalAccessException ex4) {
          Exception ex2 = null;
          Exception e = ex2;
          e.printStackTrace();
        } 
        this.is.setItemMeta((ItemMeta)skullMeta);
        return this;
      }
    
    public static boolean RefSet(final Class<?> sourceClass, final Object instance, final String fieldName, final Object value) {
        try {
            final Field field = sourceClass.getDeclaredField(fieldName);
            final Field modifiersField = Field.class.getDeclaredField("modifiers");
            final int modifiers = modifiersField.getModifiers();
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            if ((modifiers & 0x10) == 0x10) {
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, modifiers & 0xFFFFFFEF);
            }
            try {
                field.set(instance, value);
            }
            finally {
                if ((modifiers & 0x10) == 0x10) {
                    modifiersField.setInt(field, modifiers | 0x10);
                }
                if (!field.isAccessible()) {
                    field.setAccessible(false);
                }
            }
            if ((modifiers & 0x10) == 0x10) {
                modifiersField.setInt(field, modifiers | 0x10);
            }
            if (!field.isAccessible()) {
                field.setAccessible(false);
            }
            return true;
        }
        catch (Exception var11) {
            Bukkit.getLogger().log(Level.WARNING, "Unable to inject Gameprofile", var11);
            return false;
        }
    }
}
