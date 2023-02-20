package inventorys;

import org.bukkit.inventory.*;
import org.bukkit.*;

public class Kit
{
    public static Inventory getInventory() {
        final Inventory kits = Bukkit.createInventory((InventoryHolder)null, 27, "Kits");
        kits.setItem(10, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§eKits Normais").lore("§7Veja os kits em que cada jogador", "§7pode obter jogando no servidor.", "", "§aClique para abrir os Kits Normais.").head("http://textures.minecraft.net/texture/d357444ade64ec6cea645ec57e775864d67c5fa62299786e03799317ee4ad").build());
        kits.setItem(12, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aKits VIPs").lore("§7Cada VIP possui seu kit exclusivo", "§7os kits mais poderosos voc\u00ea", "§7encontra nesta categoria.", "", "§aClique para abrir os Kits VIPs.").head("http://textures.minecraft.net/texture/ded6cfbc4129108e9cf2df5f6179a4b26f3bc433901d942a49563d6bc7e22d").build());
        kits.setItem(14, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§dKits Especiais").lore("§7Veja os kits que apenas jogadores", "§7com cargos exclusivos possuem acesso.", "", "§aClique para abrir os Kits Especiais.").head("http://textures.minecraft.net/texture/17be35a133b8b8f43cc139463320c8d0824673c3b2be44019fa35975a890a03f").build());
        kits.setItem(16, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§6Shop").lore("§7Compre §fVIPs§7, §fKeys§7, §fItens §7por", "§7pontos em nossa loja.", "", "§aClique para abrir o Shop.").head("http://textures.minecraft.net/texture/4cbc412582e551ee4196d825652526952b9981cae2767d193fd8dc289b").build());
        return kits;
    }
}
