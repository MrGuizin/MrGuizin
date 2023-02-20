package apis;

import org.bukkit.plugin.java.*;
import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.inventory.*;
import org.bukkit.configuration.*;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import java.util.*;

public class ConfigAPI
{
    private JavaPlugin plugin;
    private String name;
    private File file;
    private FileConfiguration config;
    
    public String getName() {
        return this.name;
    }
    
    public ConfigAPI setPlugin(final JavaPlugin plugin) {
        this.plugin = plugin;
        return this;
    }
    
    public FileConfiguration getConfig() {
        return this.config;
    }
    
    public ConfigAPI(final String name, final JavaPlugin plugin) {
        this.plugin = plugin;
        if (plugin == null) {
            this.plugin = JavaPlugin.getProvidingPlugin(getClass());
        }
        this.name = name;
        this.reloadConfig();
    }
    
    public ConfigAPI(final String name) {
        this(name, null);
    }
    
    public ConfigAPI reloadConfig() {
        this.file = new File(this.plugin.getDataFolder(), this.name);
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
        final InputStream defaults = this.plugin.getResource(this.file.getName());
        if (defaults != null) {
            final YamlConfiguration loadConfig = YamlConfiguration.loadConfiguration(defaults);
            this.config.setDefaults((Configuration)loadConfig);
        }
        return this;
    }
    
    public ConfigAPI saveConfig() {
        try {
            this.config.save(this.file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return this;
    }
    
    public String message(final String path) {
        return ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(path));
    }
    
    public ConfigAPI saveDefaultConfig() {
        this.plugin.saveResource(this.name, false);
        return this;
    }
    
    public ConfigAPI saveDefault() {
        this.config.options().copyDefaults(true);
        this.saveConfig();
        return this;
    }
    
    public void setItem(final String path, final ItemStack item) {
        setItem(this.create(path), item);
    }
    
    public ItemStack getItem(final String path) {
        return getItem(this.getSection(path));
    }
    
    public void setLocation(final String path, final Location location) {
        setLocation(this.create(path), location);
    }
    
    public Location getLocation(final String path) {
        return getLocation(this.getSection(path));
    }
    
    public static void setItem(final ConfigurationSection section, final ItemStack item) {
        section.set("id", (Object)item.getTypeId());
        section.set("data", (Object)item.getDurability());
        if (item.hasItemMeta()) {
            final ItemMeta meta = item.getItemMeta();
            if (meta.hasDisplayName()) {
                section.set("name", (Object)meta.getDisplayName());
            }
            if (meta.hasLore()) {
                final List<String> lines = new ArrayList<String>();
                for (final String line : meta.getLore()) {
                    lines.add(line);
                }
                section.set("lore", (Object)lines);
            }
        }
        final StringBuilder text = new StringBuilder();
        for (final Map.Entry<Enchantment, Integer> enchant : item.getEnchantments().entrySet()) {
            text.append(String.valueOf(String.valueOf(String.valueOf(enchant.getKey().getId()))) + "-" + enchant.getValue() + ",");
        }
        section.set("enchant", (Object)text.toString());
    }
    
    public static void setLocation(final ConfigurationSection section, final Location location) {
        section.set("world", (Object)location.getWorld().getName());
        section.set("x", (Object)location.getX());
        section.set("y", (Object)location.getY());
        section.set("z", (Object)location.getZ());
        section.set("yaw", (Object)location.getYaw());
        section.set("pitch", (Object)location.getPitch());
    }
    
    public static Location getLocation(final ConfigurationSection section) {
        final World world = Bukkit.getWorld(section.getString("world"));
        final double x = section.getDouble("x");
        final double y = section.getDouble("y");
        final double z = section.getDouble("z");
        final float yaw = (float)section.getDouble("yaw");
        final float pitch = (float)section.getDouble("pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }
    
    public static Location toLocation(final String text) {
        final String[] split = text.split(",");
        final World world = Bukkit.getWorld(split[0]);
        final double x = Double.parseDouble(split[1]);
        final double y = Double.parseDouble(split[2]);
        final double z = Double.parseDouble(split[3]);
        final float yaw = Float.parseFloat(split[4]);
        final float pitch = Float.parseFloat(split[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }
    
    public static String toChatMessage(final String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    
    public static String saveLocation(final Location location) {
        final StringBuilder text = new StringBuilder();
        text.append(String.valueOf(String.valueOf(String.valueOf(location.getWorld().getName()))) + ",");
        text.append(String.valueOf(String.valueOf(String.valueOf(location.getX()))) + ",");
        text.append(String.valueOf(String.valueOf(String.valueOf(location.getY()))) + ",");
        text.append(String.valueOf(String.valueOf(String.valueOf(location.getZ()))) + ",");
        text.append(String.valueOf(String.valueOf(String.valueOf(location.getYaw()))) + ",");
        text.append(location.getPitch());
        return text.toString();
    }
    
    public static String toConfigMessage(final String text) {
        return text.replace("\ufffd", "&");
    }
    
    public static ItemStack getItem(final ConfigurationSection section) {
        final ItemStack item = new ItemStack(section.getInt("id"), section.getInt("data"));
        final ItemMeta meta = item.getItemMeta();
        if (section.contains("name")) {
            meta.setDisplayName(toChatMessage(section.getString("name")));
        }
        if (section.contains("lore")) {
            final List<String> lines = new ArrayList<String>();
            for (final String line : meta.getLore()) {
                lines.add(toChatMessage(line));
            }
        }
        if (section.contains("enchant")) {
            String[] split2;
            for (int length = (split2 = section.getString("enchant").split(",")).length, i = 0; i < length; ++i) {
                final String value = split2[i];
                if (value != null && !value.isEmpty() && value.contains("-")) {
                    final String[] split3 = value.split("-");
                    item.addUnsafeEnchantment(Enchantment.getById((int)Integer.valueOf(split3[0])), (int)Integer.valueOf(split3[1]));
                }
            }
        }
        return item;
    }
    
    public boolean delete() {
        return this.file.delete();
    }
    
    public boolean exists() {
        return this.file.exists();
    }
    
    public void add(final String path, final Object value) {
        this.config.addDefault(path, value);
    }
    
    public boolean contains(final String path) {
        return this.config.contains(path);
    }
    
    public ConfigurationSection create(final String path) {
        return this.config.createSection(path);
    }
    
    public Object get(final String path) {
        return this.config.get(path);
    }
    
    public boolean getBoolean(final String path) {
        return this.config.getBoolean(path);
    }
    
    public ConfigurationSection getSection(final String path) {
        return this.config.getConfigurationSection(path);
    }
    
    public double getDouble(final String path) {
        return this.config.getDouble(path);
    }
    
    public int getInt(final String path) {
        return this.config.getInt(path);
    }
    
    public List<Integer> getIntegerList(final String path) {
        return (List<Integer>)this.config.getIntegerList(path);
    }
    
    public ItemStack getItemStack(final String path) {
        return this.config.getItemStack(path);
    }
    
    public Set<String> getKeys(final boolean deep) {
        return (Set<String>)this.config.getKeys(deep);
    }
    
    public List<?> getList(final String path) {
        return (List<?>)this.config.getList(path);
    }
    
    public long getLong(final String path) {
        return this.config.getLong(path);
    }
    
    public List<Long> getLongList(final String path) {
        return (List<Long>)this.config.getLongList(path);
    }
    
    public List<Map<?, ?>> getMapList(final String path) {
        return (List<Map<?, ?>>)this.config.getMapList(path);
    }
    
    public String getString(final String path) {
        return this.config.getString(path);
    }
    
    public List<String> getStringList(final String path) {
        return (List<String>)this.config.getStringList(path);
    }
    
    public Map<String, Object> getValues(final boolean deep) {
        return (Map<String, Object>)this.config.getValues(deep);
    }
    
    public void set(final String path, final Object value) {
        this.config.set(path, value);
    }
}
