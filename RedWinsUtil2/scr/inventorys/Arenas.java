package inventorys;

import org.bukkit.inventory.*;
import org.bukkit.*;

public class Arenas
{
    public static Inventory getInventory() {
        final Inventory arenas = Bukkit.createInventory((InventoryHolder)null, 27, "Locais - Arenas");
        arenas.setItem(11, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aArena Grega").lore("§7Trave batalhas incessantes ", "§7em nossa arena principal.", "", " §6§lINFORMA\u00c7\u00d5ES:", "   §7\u25aa §fTipo: §7Normal", "   §7\u25aa §fNota: §6\u272a\u272a\u272a\u272a§7\u272a", "", "§aClique para ir at\u00e9 a Arena Grega. ").head("http://textures.minecraft.net/texture/1e8778f001dcbefffadd43e4668a1011efed81255dc7356b4683e9250d031fee").build());
        arenas.setItem(13, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aArena FPS").lore("§7Jogue com um melhor desempenho ", "§7em uma arena otimizada para isso. ", "", " §6§lINFORMA\u00c7\u00d5ES:", "   §7\u25aa §fTipo: §7FPS", "   §7\u25aa §fNota: §6\u272a\u272a\u272a§7\u272a\u272a", "", "§aClique para ir at\u00e9 a Arena FPS. ").head("http://textures.minecraft.net/texture/82a823f1a054b83bbe08011b66936efc1a7a85d89d9d402e6652108e238a354").build());
        arenas.setItem(15, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aArena END").lore("§7Em meio a tantas arenas de ", "§7longe esta \u00e9 a mais bonita.", "", " §6§lINFORMA\u00c7\u00d5ES:", "   §7\u25aa §fTipo: §7Normal", "   §7\u25aa §fNota: §6\u272a\u272a\u272a\u272a\u272a", "", "§aClique para ir at\u00e9 a Arena END. ").head("http://textures.minecraft.net/texture/7d255fee5f7cf9081f2c0d5a0442ac3f3fd1fc24cb956e3f1d331863d11de176").build());
        return arenas;
    }
}
