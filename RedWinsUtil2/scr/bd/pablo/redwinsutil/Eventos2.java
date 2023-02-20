package bd.pablo.redwinsutil;

import inventorys.*;

import org.bukkit.event.*;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;

import java.util.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.inventory.*;
import evento.*;
import org.bukkit.inventory.*;

import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;

public class Eventos2 implements Listener {
	public static Map<String, String> tags;

	static {
		Eventos2.tags = new HashMap<String, String>();
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerCommandExecute(final PlayerCommandPreprocessEvent e) {
		final String message = e.getMessage().split(" ")[0];
		if (!message.equalsIgnoreCase("/kit") && !message.equalsIgnoreCase("/kits")
				&& !message.equalsIgnoreCase("/system:kit") && !message.equalsIgnoreCase("/system:kits")) {
			return;
		}
		if (e.getMessage().split(" ").length > 1) {
			return;
		}
		if (e.isCancelled())
			return;
		e.setCancelled(true);
		final Player p = e.getPlayer();
		p.openInventory(Kit.getInventory());
		p.updateInventory();
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 5.0f);
	}

	@EventHandler
	public void onChat(ChatMessageEvent e) {
		if (e.getTags().contains("linhagem")) {
			final String tag = Eventos2.tags.get(e.getSender().getName());
			if (tag != null) {
				e.setTagValue("linhagem", tag);
			}
		}
	}

	@EventHandler
	public void escolha(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem") != null) {
			if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
					.equalsIgnoreCase("Poseidon")) {
				Eventos2.tags.put(p.getName(), "§b[Poseidon]");

			} else if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
					.equalsIgnoreCase("Zeus")) {
				Eventos2.tags.put(p.getName(), "§f[Zeus]");

			} else if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
					.equalsIgnoreCase("Atena")) {
				Eventos2.tags.put(p.getName(), "§e[Atena]");

			}
			if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
					.equalsIgnoreCase("Hermes")) {
				Eventos2.tags.put(p.getName(), "§c[Hermes]");

			}
		}
	}

	@EventHandler
	public void crystalDamage(final EntityDamageEvent event) {
		final Entity entity = event.getEntity();
		if (entity.getType() == EntityType.ENDER_CRYSTAL) {
			event.setCancelled(true);
		}
		if (entity.getType() == EntityType.ARMOR_STAND) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onClick2Invento2ry(final InventoryClickEvent event) {
		final Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase("§aVocê tem certeza será §fZeus§a?")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 12: {
				p.closeInventory();
				break;
			}
			case 14: {
				p.closeInventory();
				Main.linhagem.getConfig().set("Linhagens." + p.getName() + ".Linhagem", "Zeus");
				Main.linhagem.saveConfig();
				Eventos2.tags.put(p.getName(), "§f[Zeus]");
				p.playSound(p.getLocation(), Sound.DOOR_OPEN, 2.0f, 6.0f);
				p.sendTitle("§e§lLINHAGEM", "§7Agora Você é o(a) Deus dos Raios!");
				Bukkit.broadcastMessage(" \n §fO jogador §c" + p.getName()
						+ "§f se tornou \n §fo Deus dos Raios! \n \n §eConsiga um novo poder §f/linhagem \n ");
				break;
			}
			}
		}
		if (event.getInventory().getName().equalsIgnoreCase("§aVocê tem certeza será §fAtena§a?")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 12: {
				p.closeInventory();
				break;
			}
			case 14: {
				p.closeInventory();
				Main.linhagem.getConfig().set("Linhagens." + p.getName() + ".Linhagem", "Atena");
				Main.linhagem.saveConfig();
				Eventos2.tags.put(p.getName(), "§e[Atena]");
				p.playSound(p.getLocation(), Sound.DOOR_OPEN, 2.0f, 6.0f);
				p.getWorld().playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 100);
				p.sendTitle("§e§lLINHAGEM", "§7Agora Você é o(a) mais esperto(a)!");
				Bukkit.broadcastMessage(" \n §fO jogador §c" + p.getName()
						+ "§f se tornou \n §fo Deus da Sabedoria! \n \n §eConsiga um novo poder §f/linhagem \n ");
			}
			}
		}
		if (event.getInventory().getName().equalsIgnoreCase("§aVocê tem certeza será §fPoseidon§a?")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 12: {
				p.closeInventory();
				break;
			}
			case 14: {
				p.closeInventory();
				Main.linhagem.getConfig().set("Linhagens." + p.getName() + ".Linhagem", "Poseidon");
				Main.linhagem.saveConfig();
				Eventos2.tags.put(p.getName(), "§b[Poseidon]");
				p.getWorld().playEffect(p.getLocation(), Effect.WATERDRIP, 100);
				p.sendTitle("§b§lLINHAGEM", "§7Agora Você é o(a) Deus dos Mares!");
				Bukkit.broadcastMessage(" \n §fO jogador §b" + p.getName()
						+ "§f se tornou \n §fo Deus dos Mares! \n \n §eConsiga um novo poder §f/linhagem \n ");
			}
			}
		}
		if (event.getInventory().getName().equalsIgnoreCase("§aVocê tem certeza será §fHermes§a?")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 12: {
				p.closeInventory();
				break;
			}
			case 14: {
				p.closeInventory();
				Main.linhagem.getConfig().set("Linhagens." + p.getName() + ".Linhagem", "Hermes");
				Main.linhagem.saveConfig();
				Eventos2.tags.put(p.getName(), "§c[Hermes]");
				p.playSound(p.getLocation(), Sound.DOOR_OPEN, 2.0f, 6.0f);
				p.getWorld().playEffect(p.getLocation(), Effect.INSTANT_SPELL, 100);
				p.sendTitle("§c§lLINHAGEM", "§7Agora Você é o(a) Deus da Velocidade!");
				Bukkit.broadcastMessage(" \n §fO jogador §c" + p.getName()
						+ "§f se tornou \n §fo Deus da Velocidade! \n \n §eConsiga um novo poder §f/linhagem \n ");
			}
			}
		}
		if (event.getInventory().getName().equalsIgnoreCase("§7Armas dos Deuses")) {
			event.setCancelled(true);
			final ItemStack zumbi = new ItemStack(Material.DIAMOND_SWORD);
			switch (event.getRawSlot()) {
			case 9: {
				if (CraniosManager.getCranios(p, Main.dracma) >= 1000000L) {
					CraniosManager.removeCranios(p, Main.dracma, 1000000L);
					p.sendMessage("§aVoc\u00ea comprou um Booster de Spawner 1m!");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "giveboost1m " + p.getName());
					break;
				}
				p.sendMessage("§cVoc\u00ea precisa de 1 Milh\u00f5es de dracma §l\u262b");
				break;
			}
			case 10: {
				if (CraniosManager.getCranios(p, Main.dracma) >= 2000000L) {
					CraniosManager.removeCranios(p, Main.dracma, 2000000L);
					p.sendMessage("§aVoc\u00ea comprou um Booster de Spawner 10m!");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "giveboost10m " + p.getName());
					break;
				}
				p.sendMessage("§cVoc\u00ea precisa de 2 Milh\u00f5es de dracma §l\u262b");
				break;
			}
			case 11: {
				if (CraniosManager.getCranios(p, Main.dracma) >= 3000000L) {
					CraniosManager.removeCranios(p, Main.dracma, 3000000L);
					p.sendMessage("§aVoc\u00ea comprou um Booster de Spawner 30m!");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "giveboost30m " + p.getName());
					break;
				}
				p.sendMessage("§cVoc\u00ea precisa de 3 Milh\u00f5es de dracma §l\u262b");
				break;
			}
			case 21: {
				if (CraniosManager.getCranios(p, Main.dracma) >= 20000000L) {
					CraniosManager.removeCranios(p, Main.dracma, 20000000L);
					p.sendMessage("§aVoc\u00ea comprou uma L\u00e2mina do Olimpo!");
					p.getInventory().addItem(new ItemStack[] { new ItemBuilder(zumbi).name("§fL\u00e2mina do Olimpo")
							.enchant(Enchantment.DAMAGE_ALL, 20).enchant(Enchantment.DURABILITY, 20)
							.enchant(Enchantment.FIRE_ASPECT, 20).enchant(Enchantment.LOOT_BONUS_MOBS, 21).build() });
					break;
				}
				p.sendMessage("§cVoc\u00ea precisa de 20 Milh\u00f5es de dracma §l\u262b");
				break;
			}
			case 22: {
				final ItemStack zumbi2 = new ItemStack(Material.DIAMOND_SPADE);
				if (CraniosManager.getCranios(p, Main.dracma) >= 20000000L) {
					CraniosManager.removeCranios(p, Main.dracma, 20000000L);
					p.sendMessage("§aVoc\u00ea comprou uma P\u00e1 Divina!");
					p.getInventory().addItem(new ItemStack[] { new ItemBuilder(zumbi2).name("§fP\u00e1 Divina")
							.enchant(Enchantment.DIG_SPEED, 60).enchant(Enchantment.DURABILITY, 60).build() });
					break;
				}
				p.sendMessage("§cVoc\u00ea precisa de 20 Milh\u00f5es de dracma §l\u262b");
				break;
			}
			case 23: {
				final ItemStack zumbi3 = new ItemStack(Material.DIAMOND_PICKAXE);
				if (CraniosManager.getCranios(p, Main.dracma) >= 80000000L) {
					CraniosManager.removeCranios(p, Main.dracma, 80000000L);
					p.sendMessage("§aVoc\u00ea comprou uma Picareta Divina!");
					p.getInventory()
							.addItem(new ItemStack[] { new ItemBuilder(zumbi3).name("§fPicareta Divina")
									.enchant(Enchantment.DIG_SPEED, 60).enchant(Enchantment.LOOT_BONUS_BLOCKS, 60)
									.enchant(Enchantment.DURABILITY, 60).build() });
					break;
				}
				p.sendMessage("§cVoc\u00ea precisa de 80 Milh\u00f5es de dracma §l\u262b");
				break;
			}
			case 24: {
				final ItemStack zumbi4 = new ItemStack(Material.DIAMOND_AXE);
				if (CraniosManager.getCranios(p, Main.dracma) >= 50000000L) {
					CraniosManager.removeCranios(p, Main.dracma, 50000000L);
					p.sendMessage("§aVoc\u00ea comprou um Machado Divino!");
					p.getInventory()
							.addItem(new ItemStack[] { new ItemBuilder(zumbi4).name("§fMachado Divino")
									.enchant(Enchantment.DAMAGE_ALL, 50).enchant(Enchantment.DIG_SPEED, 20)
									.enchant(Enchantment.DURABILITY, 20).build() });
					break;
				}
				p.sendMessage("§cVoc\u00ea precisa de 50 Milh\u00f5es de dracma §l\u262b");
				break;
			}
			case 19: {
				if (CraniosManager.getCranios(p, Main.dracma) >= 150000L) {
					CraniosManager.removeCranios(p, Main.dracma, 150000L);
					Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
							"caixas givekey " + p.getName() + " boss 1");
					break;
				}
				p.closeInventory();
				p.sendMessage("§cVoc\u00ea precisa de 150K dracma §l\u262b");
				break;
			}
			}
		}
	}
}
