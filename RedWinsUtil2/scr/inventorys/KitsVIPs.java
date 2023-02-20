package inventorys;

import org.bukkit.inventory.*;
import org.bukkit.*;

public class KitsVIPs
{
    public static Inventory getInventory() {
        final Inventory vips = Bukkit.createInventory((InventoryHolder)null, 45, "Kits VIPs");
        vips.setItem(10, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§bSemi Deus Di\u00e1rio").lore("§fTempo: §71 Dia", "§fCargo: §bSemi Deus", "§fRaridade: §bRaro", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/b2268cc2fb0af675fff17f15af4a6ac240bdbf40a8396e035bd1a5aeb4bcc27c").build());
        vips.setItem(19, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§bSemi Deus Semanal").lore("§fTempo: §77 Dias", "§fCargo: §bSemi Deus", "§fRaridade: §bRaro", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/126b772329cf32f8643c4928626b6a325233ff61aa9c7725873a4bd66db3d692").build());
        vips.setItem(12, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§cTitan Di\u00e1rio").lore("§fTempo: §71 Dia", "§fCargo: §cTitan", "§fRaridade: §d\u00c9pico", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/78e99ddf9511d35b579067f6fb79a0a2a5b77fc4c2898bbef5712f9853cfbd04").build());
        vips.setItem(21, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§cTitan Semanal").lore("§fTempo: §77 Dias", "§fCargo: §cTitan", "§fRaridade: §d\u00c9pico", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/54bf893fc6defad218f7836efefbe636f1c2cc1bb650c82fccd99f2c1ee6").build());
        vips.setItem(14, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§fZeus Di\u00e1rio").lore("§fTempo: §71 Dia", "§fCargo: §fZeus", "§fRaridade: §6Lend\u00e1rio", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/fb1c268efec8d7d88a1cb88c2bfa097fa57037942299f7d202159fc93cd3036d").build());
        vips.setItem(23, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§fZeus Semanal").lore("§fTempo: §77 Dias", "§fCargo: §fZeus", "§fRaridade: §6Lend\u00e1rio", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/1f74a9644ec3ccbe936ca6294297c0eece4716d25127bb1b12521f3f58df96da").build());
        vips.setItem(16, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§4Cronos Di\u00e1rio").lore("§fTempo: §71 Dia", "§fCargo: &4Cronos", "§fRaridade: §4Ultra-Lend\u00e1rio", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/4053851527c4c9ef30a61fb067ebce957c726e1687f8b530fb4a6beeba438bd").build());
        vips.setItem(25, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§4Cronos Semanal").lore("§fTempo: §77 Dias", "§fCargo: &4Cronos", "§fRaridade: §4Ultra-Lend\u00e1rio", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/af121f7c1ab1567ff21983ff7a9e55c40c0b865f050d37e5d35defbaa").build());
        vips.setItem(40, new ItemBuilder(Material.HOPPER).name("§aVoltar").build());
        return vips;
    }
}
