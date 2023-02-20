package inventorys;

import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;

public class Mines {
	public static Inventory getInventory(final Player player) {
		final Inventory warps = Bukkit.createInventory((InventoryHolder) null, 27, "Locais - Minas");
		warps.setItem(11, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§aMinas")
				.lore("§7Minere para conseguir dinheiro ", "§7e evoluir em nosso servidor.")
				.head("http://textures.minecraft.net/texture/ba9053d2163d0f561145d33a513145d4ac1f8a458baa796be383e7525a05f45")
				.build());
		warps.setItem(13,
				new ItemBuilder(Material.PRISMARINE).name("§aDracma")
						.lore("§7Consiga dracma´s minerando ", "§7e compre itens especiais.", "",
								"§aClique para ir at\u00e9 a mina Dracma. ")
						.build());
		warps.setItem(15, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§aMina PvP")
				.lore("§7Minere para conseguir dinheiro", "§7e evoluir em nosso servidor.", "", " §6§lMIN\u00c9RIOS:",
						"  §7\u25aa §fCarv\u00e3o: §7(100%)", "", "§aClique ir at\u00e9 a Mina MinaPvP.")
				.head("http://textures.minecraft.net/texture/54d943a6e9c755685b98b4f12a228767e0c6f980dfc1ea2650a8a06726c7d22f")
				.build());
		return warps;
	}
}
