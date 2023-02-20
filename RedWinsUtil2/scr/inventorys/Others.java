package inventorys;

import org.bukkit.inventory.*;
import org.bukkit.scheduler.BukkitRunnable;

import bd.pablo.redwinsutil.Main;

import org.bukkit.*;

public class Others {
	public static Inventory getInventory() {
		final Inventory outros = Bukkit.createInventory((InventoryHolder) null, 45, "Locais do Servidor");
		outros.setItem(11,
				new ItemBuilder(Material.ITEM_FRAME).name("§aLoja")
						.lore("§7Tudo que voc\u00ea pode encontrar ", "§7para construir, batalhar e",
								"§7evoluir em nosso servidor.", "", "§aClique para ir at\u00e9 a Loja. ")
						.build());
		outros.setItem(13,
				new ItemBuilder(Material.DIAMOND_PICKAXE).name("§aMinas").lore("§7Minere para conseguir dinheiro ",
						"§7e evoluir em nosso servidor.", "", "§aClique para abrir a lista de Minas. ").build());
		outros.setItem(15,
				new ItemBuilder(Material.IRON_HELMET).name("§aLinhagem").lore("§7Escolha uma linhagem ",
						"§7para seguir e consiga", "§7novos poderes!", "", "§aClique para abrir o menu de Linhagem. ")
						.build());
		outros.setItem(20,
				new ItemBuilder(Material.PRISMARINE, 1, (short) 2).name("§aTerrenos")
						.lore("§7Fa\u00e7a constru\u00e7\u00f5es em um local separado ",
								"§7para isso, onde todos os jogadores", "§7podem construir em um terreno.", "",
								"§aClique para ir at\u00e9 os Terrenos. ")
						.build());
		outros.setItem(21, new ItemBuilder(Material.getMaterial(383), 1, (short) 120)
				.name("§aTrocadores").lore("§7Converse com nossos in\u00fameros trocadores ",
						"§7e consiga itens incr\u00edvelmente raros.", "", "§aClique para ir at\u00e9 os Trocadores. ")
				.build());
		outros.setItem(22,
				new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("§aArenas").lore("§7Participe de uma batalha ",
						"§7contra seus inimigos.", "", "§aClique abrir a lista de Arenas. ").build());
		outros.setItem(23,
				new ItemBuilder(Material.TRIPWIRE_HOOK)
						.name("§aCrates").lore("§7Veja todas as caixas dispon\u00edveis em",
								"§7nosso servidor para serem abertas. ", "", "§aClique para ir at\u00e9 as Crates. ")
						.build());
		outros.setItem(24, new ItemBuilder(Material.NETHER_STAR)
				.name("§aMercado dos Deuses").lore("§7Compre artefatos m\u00edsticos incr\u00edveis",
						"§7de cada linhagem do servidor.", "", "§aClique para ir at\u00e9 o Mercado dos Deuses. ")
				.build());
		outros.setItem(29,
				new ItemBuilder(Material.PAPER).name("§aAltar").lore("§7Crie batalhas na Arena do Altar para ",
						"§7pegar os pontos que o altar jorra.", "", "§aClique para ir at\u00e9 o Altar Divino. ")
						.build());
		outros.setItem(33, new ItemBuilder(Material.GOLD_BLOCK)
				.name("§e§lCIDADE GREGA").lore("§7Cumpra tarefas, derrote monstros ",
						"§7e ganhe diversas recompensas. ")
				.build());
		return outros;
	}
}
