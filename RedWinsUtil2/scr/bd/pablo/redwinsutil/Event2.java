package bd.pablo.redwinsutil;

import org.bukkit.block.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import inventorys.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import apis.ActionBar;

import org.bukkit.event.block.*;
import org.bukkit.command.*;
import java.util.*;
import org.bukkit.enchantments.Enchantment;

import org.bukkit.*;

public class Event2 implements Listener {
	@EventHandler
	public void onClickInventoryEvent(InventoryClickEvent e) {
		if ((!e.getInventory().getTitle().equals("Kits") && !e.getInventory().getTitle().equals("Kits Normais")
				&& !e.getInventory().getTitle().equals("Kits VIPs")
				&& !e.getInventory().getTitle().equals("Kits Especiais")) || e.getInventory().getHolder() != null) {
			return;
		}
		e.setCancelled(true);
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR
				|| !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName()) {
			return;
		}
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getWhoClicked();

		if (e.getInventory().getTitle().equals("Kits")) {
			switch (e.getSlot()) {
			case 10: {
				p.closeInventory();
				p.openInventory(KitsNormais.getInventory());
				break;
			}
			case 12: {
				p.closeInventory();
				p.openInventory(KitsVIPs.getInventory());
				break;
			}
			case 14: {
				p.closeInventory();
				p.openInventory(KitsEspeciais.getInventory());
				break;
			}
			case 16: {
				p.closeInventory();
				p.performCommand("shop");
				break;
			}
			}
		} else if (e.getInventory().getTitle().equals("Kits Normais")) {
			switch (e.getSlot()) {
			case 12: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit minerador");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit minerador");
					break;
				}
				break;
			}
			case 13: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit ferias");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit ferias");
					break;
				}
				break;
			}
			case 14: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit pvp");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit pvp");
					break;
				}
				break;
			}
			case 19: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit afrodite");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit afrodite");
					break;
				}
				break;
			}
			case 20: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit apolo");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit apolo");
					break;
				}
				break;
			}
			case 21: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit ares");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit ares");
					break;
				}
				break;
			}
			case 22: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit artemis");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit artemis");
					break;
				}
				break;
			}
			case 23: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit demeter");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit demeter");
					break;
				}
				break;
			}
			case 24: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit dionisio");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit dionisio");
					break;
				}
				break;
			}
			case 25: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit hades");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit hades");
					break;
				}
				break;
			}
			case 28: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit hefesto");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit hefesto");
					break;
				}
				break;
			}
			case 29: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit hera");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit hera");
					break;
				}
				break;
			}
			case 30: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit hestia");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit hestia");
					break;
				}
				break;
			}
			case 31: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit poseidon");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit poseidon");
					break;
				}
				break;
			}
			case 32: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit selene");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit selene");
					break;
				}
				break;
			}
			case 33: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit temis");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit temis");
					break;
				}
				break;
			}
			case 34: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit zeus");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit zeus");
					break;
				}
				break;
			}
			case 49: {
				p.closeInventory();
				p.openInventory(Kit.getInventory());
				break;
			}
			}
		} else if (e.getInventory().getTitle().equals("Kits VIPs")) {
			switch (e.getSlot()) {
			case 10: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit vipdiario");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit vipdiario");
					break;
				}
				break;
			}
			case 19: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit vip");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit vip");
					break;
				}
				break;
			}
			case 12: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit vipmaisd");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit vipmaisd");
					break;
				}
				break;
			}
			case 21: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit vipmais");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit vipmais");
					break;
				}
				break;
			}
			case 14: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit mvpdiario");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit mvpdiario");
					break;
				}
				break;
			}
			case 23: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit mvp");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit mvp");
					break;
				}
				break;
			}
			case 16: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit mvpmaisd");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit mvpmaisd");
					break;
				}
				break;
			}
			case 25: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit mvpmais");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit mvpmais");
					break;
				}
				break;
			}
			case 40: {
				p.closeInventory();
				p.openInventory(Kit.getInventory());
				break;
			}
			}
		} else if (e.getInventory().getTitle().equals("Kits Especiais")) {
			switch (e.getSlot()) {
			case 12: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit ytmirimd");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit ytmirimd");
					break;
				}
				break;
			}
			case 21: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit ytmirim");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit ytmirim");
					break;
				}
				break;
			}
			case 14: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit ytdiario");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit ytdiario");
					break;
				}
				break;
			}
			case 23: {
				p.closeInventory();
				if (e.getClick().isRightClick()) {
					p.performCommand("verkit youtuber");
					break;
				}
				if (e.getClick().isLeftClick()) {
					p.performCommand("kit youtuber");
					break;
				}
				break;
			}
			case 40: {
				p.closeInventory();
				p.openInventory(Kit.getInventory());
				break;
			}
			}
		}
	}

	public static boolean hasAvaliableSlot(Player player) {
		Inventory inv = (Inventory) player.getInventory();
		ItemStack[] contents;
		for (int length = (contents = inv.getContents()).length, i = 0; i < length; ++i) {
			ItemStack item = contents[i];
			if (item == null) {
				return true;
			}
		}
		return false;
	}

	@EventHandler
	public void onBlock2Break(BlockBreakEvent e) {
		Player p = e.getPlayer();
		int chance = new Random().nextInt(100);
		Block b = e.getBlock();
		String block = String.valueOf(String.valueOf(String.valueOf(b.getTypeId()))) + ":" + b.getData() + ":"
				+ b.getWorld().getName() + ":" + b.getX() + ":" + b.getY() + ":" + b.getZ();
		if (!hasAvaliableSlot(p)) {
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
			p.sendTitle("§c§lINVENTÁRIO", "§fesvazie seu inventário");
			p.sendMessage("§cO seu invent\u00e1rio est\u00e1 cheio.");
		}
		if (!(e instanceof BlockBreakEvent)) {
			return;
		}
		if (e.getBlock().getType() == Material.COAL_ORE) {
			if (p.getWorld().getName().equalsIgnoreCase("Eventos")) {
				e.getBlock().setTypeId(0);
				int loot = p.getInventory().getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
				Main.econ.depositPlayer(p, 10 * loot);
				ActionBar.sendActionbar(p, "§a" + Main.format(10 * loot) + " de money");
				if (Math.random() < 0.0008) {
					p.sendTitle("§e§lSORTE", "§fVoc\u00ea achou um §eFRAGMENTO DE MAQUINA");
					final ItemStack item3 = new ItemStack(Material.INK_SACK, 1, (short) 11);
					item3.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
					final ItemMeta meta = item3.getItemMeta();
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					meta.setDisplayName("§6§lFRAGMENTO DE MAQUINA");
					final ArrayList<String> lores = new ArrayList<String>();
					lores.add("");
					lores.add("§fTipo: §6§lRaro");
					lores.add("");
					lores.add("§7Use esse fragmento na §f/warp upmaquinas");
					meta.setLore(lores);
					item3.setItemMeta(meta);
					p.getInventory().addItem(new ItemStack[] { item3 });
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
					for (int n = 0; n < 50; ++n) {
						p.playEffect(e.getBlock().getLocation(), Effect.LAVA_POP, 100);
					}
				}
				if (Math.random() < 0.0001) {
					p.sendTitle("§c§lSORTE", "§fVoc\u00ea achou um §cFRAGMENTO DE MAQUINA");
					final ItemStack item4 = new ItemStack(Material.INK_SACK, 1, (short) 1);
					item4.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 4);
					final ItemMeta meta = item4.getItemMeta();
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					meta.setDisplayName("§c§lFRAGMENTO DE MAQUINA");
					final ArrayList<String> lores = new ArrayList<String>();
					lores.add("");
					lores.add("§fTipo: §c§lLend\u00e1rio");
					lores.add("");
					lores.add("§7Use esse fragmento na §f/warp upmaquinas");
					meta.setLore(lores);
					item4.setItemMeta(meta);
					p.getInventory().addItem(new ItemStack[] { item4 });
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
					for (int n = 0; n < 50; ++n) {
						p.playEffect(e.getBlock().getLocation(), Effect.LAVA_POP, 100);
					}
				}
			}
		}
		if (e.getBlock().getType() == Material.QUARTZ_ORE) {
			if (p.getWorld().getName().equalsIgnoreCase("pescaria")) {
				if (Math.random() < 0.0006) {
					p.sendTitle("§e§lSORTE", "§fVoc\u00ea achou um §eFRAGMENTO DE MAQUINA");
					final ItemStack item3 = new ItemStack(Material.INK_SACK, 1, (short) 11);
					item3.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
					final ItemMeta meta = item3.getItemMeta();
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					meta.setDisplayName("§6§lFRAGMENTO DE MAQUINA");
					final ArrayList<String> lores = new ArrayList<String>();
					lores.add("");
					lores.add("§fTipo: §6§lRaro");
					lores.add("");
					lores.add("§7Use esse fragmento na §f/warp upmaquinas");
					meta.setLore(lores);
					item3.setItemMeta(meta);
					p.getInventory().addItem(new ItemStack[] { item3 });
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
					for (int n = 0; n < 50; ++n) {
						p.playEffect(e.getBlock().getLocation(), Effect.LAVA_POP, 100);
					}
				}
			}
		}
		if (e.getBlock().getType() == Material.QUARTZ_BLOCK) {
			if (p.getWorld().getName().equalsIgnoreCase("pescaria")) {
				if (Math.random() < 0.0005) {
					p.sendTitle("§e§lSORTE", "§fVoc\u00ea achou um §eFRAGMENTO DE MAQUINA");
					final ItemStack item3 = new ItemStack(Material.INK_SACK, 1, (short) 11);
					item3.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
					final ItemMeta meta = item3.getItemMeta();
					meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					meta.setDisplayName("§6§lFRAGMENTO DE MAQUINA");
					final ArrayList<String> lores = new ArrayList<String>();
					lores.add("");
					lores.add("§fTipo: §6§lRaro");
					lores.add("");
					lores.add("§7Use esse fragmento na §f/warp upmaquinas");
					meta.setLore(lores);
					item3.setItemMeta(meta);
					p.getInventory().addItem(new ItemStack[] { item3 });
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
					for (int n = 0; n < 50; ++n) {
						p.playEffect(e.getBlock().getLocation(), Effect.LAVA_POP, 100);
					}
				}
			}
		}

		if (e.getBlock().getWorld().getName().contains("Eventos") && e.getBlock().getType() == Material.PRISMARINE
				&& e.getBlock().getData() == 1) {
			if (e.isCancelled())
				return;
			if (p.getWorld().getName().equalsIgnoreCase("Eventos")) {
				e.getBlock().setType(Material.AIR);
				if (hasAvaliableSlot(p)) {
					if (Math.random() < 0.01) {
						p.sendTitle("§C§LSORTE", "§7Voc\u00ea encontrou uma §cKEY MISTERIOSA");
						p.playSound(p.getLocation(), Sound.BAT_LOOP, 10.0f, 10.0f);
						Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
								"caixas givekey " + p.getName() + " misteriosa 1");
					}
					if (Math.random() < 0.001) {
						p.sendTitle("§a§LSORTE", "§7Voc\u00ea encontrou uma §aKEY BOSS");
						p.getWorld().strikeLightning(p.getLocation());
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
						Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
								"caixas givekey " + p.getName() + " boss 1");
					}
					p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10.0f, 1.0f);
					p.getInventory()
							.addItem(new ItemStack[] {
									new ItemStack(new ItemBuilder(new ItemStack(Material.PRISMARINE, 1, (short) 1))
											.name("§bFragmento de Dracma")
											.lore("", " §fLevel: §a2", "", "§7Para usar esse fragmento",
													"§7clique com o bot\u00e3o direito no npc",
													"§7que est\u00e1 no §b/warp dracma",
													"§7para depositar seus fragmentos.", "")
											.build()) });
				}
			}
		}
		if (e.getBlock().getWorld().getName().contains("Eventos") && e.getBlock().getType() == Material.PRISMARINE
				&& e.getBlock().getData() == 0 && p.getWorld().getName().equalsIgnoreCase("Eventos")) {
			if (e.isCancelled())
				return;
			if (hasAvaliableSlot(p)) {
				e.getBlock().setType(Material.AIR);
				if (Math.random() < 0.01) {
					p.sendTitle("§C§LSORTE", "§7Voc\u00ea encontrou uma §cKEY MISTERIOSA");
					p.playSound(p.getLocation(), Sound.BAT_LOOP, 10.0f, 10.0f);
					Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
							"caixas givekey " + p.getName() + " misteriosa 1");
				}
				if (Math.random() < 0.001) {
					p.sendTitle("§a§LSORTE", "§7Voc\u00ea encontrou uma §aKEY BOSS");
					p.getWorld().strikeLightning(p.getLocation());
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0f, 10.0f);
					Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
							"caixas givekey " + p.getName() + " boss 1");
				}
				p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10.0f, 1.0f);
				p.getInventory()
						.addItem(new ItemStack[] { new ItemStack(new ItemBuilder(new ItemStack(Material.PRISMARINE))
								.name("§bFragmento de Dracma")
								.lore("", " §fLevel: §a1", "", "§7Para usar esse fragmento",
										"§7clique com o bot\u00e3o direito no npc", "§7que est\u00e1 no §b/warp dracma",
										"§7para depositar seus fragmentos.", "")
								.build()) });
			}
		}
	}
}
