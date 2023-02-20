package inventorys;

import org.bukkit.inventory.*;
import org.bukkit.*;

public class KitsEspeciais
{
    public static Inventory getInventory() {
        final Inventory especiais = Bukkit.createInventory((InventoryHolder)null, 45, "Kits Especiais");
        especiais.setItem(12, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§dYT-MIRIM Di\u00e1rio").lore("§fTempo: §71 Dia", "§fGrupo: §dYT-MIRIM", "§fRaridade: §d\u00c9pico", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/4dea74de59b82cc44178a12f5715290e30304ade63b1904643b04b22dc1e88a2").build());
        especiais.setItem(21, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§dYT-MIRIM Semanal").lore("§fTempo: §77 Dias", "§fGrupo: §dYT-MIRIM", "§fRaridade: §d\u00c9pico", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/86f476871ed23f79e7b9d495489887e244c619c5e19e41cf95b271a2ebe75").build());
        especiais.setItem(14, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§cYoutuber Di\u00e1rio").lore("§fTempo: §71 Dia", "§fGrupo: §cYoutuber", "§fRaridade: §6Lend\u00e1rio", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/54d943a6e9c755685b98b4f12a228767e0c6f980dfc1ea2650a8a06726c7d22f").build());
        especiais.setItem(23, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§cYoutuber Semanal").lore("§fTempo: §77 Dias", "§fGrupo: §cYoutuber", "§fRaridade: §6Lend\u00e1rio", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/c168291abac4a5f86fe8b360338986aee7abcb7f4b8169eb55dfec928561258").build());
        especiais.setItem(40, new ItemBuilder(Material.HOPPER).name("§aVoltar").build());
        return especiais;
    }
}
