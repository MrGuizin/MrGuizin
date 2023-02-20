package evento;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import bd.pablo.redwinsutil.*;

import org.bukkit.command.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.meta.*;

import apis.ActionBar;
import apis.ConfigAPI;

import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;

public class zombiecoinsEvents implements Listener {
	public static boolean hasAvaliableSlot(final Player player) {
		final Inventory inv = (Inventory) player.getInventory();
		ItemStack[] contents;
		for (int length = (contents = inv.getContents()).length, i = 0; i < length; ++i) {
			final ItemStack item = contents[i];
			if (item == null) {
				return true;
			}
		}
		return false;
	}

	public static void addtag(final Player p, final ConfigAPI config, String msg) {
		config.set(p.getName(), msg);
		config.saveConfig();
	}

	public static HashMap<String, String> tag = new HashMap<String, String>();

	@EventHandler
	public void entrada(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Main.tag.getConfig().getString(p.getName()) != null) {
			JH_NickLines.Main.getInstance().setLine(p, "§7" + Main.tag.getConfig().getString(p.getName()));
		}
	}

	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage().replace("&", "§");
		if (tag.containsKey(p.getName())) {
			if (msg.equalsIgnoreCase("cancelar")) {
				tag.remove(p.getName());
				e.setCancelled(true);
				p.sendMessage("§cCancelado com sucesso!");
				if (Main.tag.getConfig().getString(p.getName()) == null) {
					final ItemStack skull = new ItemStack(Material.BOOK, 1);
					final ItemMeta skullMeta = skull.getItemMeta();
					skullMeta.setDisplayName("§b§lTAG");
					skullMeta.addEnchant(Enchantment.DURABILITY, 1, true);
					skullMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
					final ArrayList<String> zumbilore = new ArrayList<String>();
					zumbilore.add("");
					zumbilore.add(" §7Use este livro para escolher");
					zumbilore.add("§7 uma tag personalizada");
					zumbilore.add("§7 para colocar em cima do seu nick");
					zumbilore.add("§7");
					zumbilore.add("§7Para usar basta clicar com");
					zumbilore.add("§7o botão direito, após isso");
					zumbilore.add("§7escreva no chat a tag que deseja.");
					zumbilore.add("§fExemplo:");
					zumbilore.add("§5&5 Eu sou o Milior");
					zumbilore.add("§f");
					zumbilore.add("§c§lCUIDADO§c! após usar não escreva");
					zumbilore.add("§cnada no chat alem da TAG!");
					skullMeta.setLore(zumbilore);
					skull.setItemMeta(skullMeta);
					p.getInventory().addItem(new ItemStack[] { skull });
				}
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2.5f, 5.0f);
			} else if (msg.length() <= 16) {
				e.setCancelled(true);
				tag.remove(p.getName());
				addtag(p, Main.tag, msg);
				JH_NickLines.Main.getInstance().setLine(p, "§7" + msg);
				p.sendMessage(" \n §aSucesso, agora você tem a tag " + msg.replace("&", "§")
						+ " \n §apara trocar use §f/trocartag \n ");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.5f, 5.0f);
			} else {
				e.setCancelled(true);
				p.sendMessage("§cO tamanho máximo permitido é de 16 letras..");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2.5f, 5.0f);
			}
		}
	}

	@EventHandler
	public void a(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		String p2 = tag.getOrDefault(p.getName(), null);
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			if (p.getItemInHand().getType() == Material.BOOK && p.getItemInHand().hasItemMeta()
					&& p.getItemInHand().getItemMeta().hasLore()
					&& p.getItemInHand().getItemMeta().getLore().contains("§5&5 Eu sou o Milior")) {
				if (Main.tag.getConfig().getString(p.getName()) == null) {
					ItemStack item = p.getItemInHand();
					if (item.getAmount() == 1) {
						p.getInventory().removeItem(item);
					} else {
						item.setAmount(item.getAmount() - 1);
					}
					JH_NickLines.Main.getInstance().setLine(p, "§7Escolhendo uma tag...");
					p.sendTitle("§b§LTAG", "§fleia o chat..");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 2.5f, 5.0f);
					p.sendMessage(
							" \n §eDigite a Tag que você deseja, para usar cores use §f& \n §ePara cancelar, escreva §c§lCANCELAR \n ");
					tag.put(p.getName(), p2);
				} else {
					event.setCancelled(true);
					p.sendMessage("§cVocê já tem uma TAG, para trocar use §f/trocartag");
					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2.5f, 5.0f);
				}
			}
	}

	@EventHandler
	public void place(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.getMaterial(168) && e.getBlock().getData() == 1) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cEssse bloco não pode ser colocado no chão, use ele no /warp dracma");
		}
		if (e.getBlock().getType() == Material.getMaterial(168) && e.getBlock().getData() == 0) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cEssse bloco não pode ser colocado no chão, use ele no /warp dracma");
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onClickInvento2ry(final InventoryClickEvent event) {
		final Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getTitle().equalsIgnoreCase("§6§lMAQUINAS: §8Opções de compra")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 11:
				p.closeInventory();
				p.sendMessage(" \n §cAs maquinas ainda não foram liberadas! \n ");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2.5f, 5.0f);
				break;
			}
		}
		if (event.getInventory().getTitle().equalsIgnoreCase("§8Loja de Matadoras")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 11:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
				}
				if (CraniosManager.getCranios(p, Main.dracma) >= 10000000) {
					CraniosManager.removeCranios(p, Main.dracma, (long) 10000000);
					Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
							"bosses givematadora " + p.getName() + " mt1 1");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
					return;
				}
				p.closeInventory();
				p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");
				break;
			case 12:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
				}
				if (CraniosManager.getCranios(p, Main.dracma) >= 25000000) {
					CraniosManager.removeCranios(p, Main.dracma, (long) 25000000);
					Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
							"bosses givematadora " + p.getName() + " mt2 1");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
					return;
				}
				p.closeInventory();
				p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");
				break;
			case 13:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
				}
				if (CraniosManager.getCranios(p, Main.dracma) >= 40000000) {
					CraniosManager.removeCranios(p, Main.dracma, (long) 40000000);
					Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
							"bosses givematadora " + p.getName() + " mt3 1");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
					return;
				}
				p.closeInventory();
				p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");
				break;
			case 14:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
				}
				if (CraniosManager.getCranios(p, Main.dracma) >= 80000000) {
					CraniosManager.removeCranios(p, Main.dracma, (long) 80000000);
					Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
							"bosses givematadora " + p.getName() + " mt4 1");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
					return;
				}
				p.closeInventory();
				p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");
				break;
			case 15:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
				}
				if (CraniosManager.getCranios(p, Main.dracma) >= 120000000) {
					CraniosManager.removeCranios(p, Main.dracma, (long) 120000000);
					Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
							"bosses givematadora " + p.getName() + " mt5 1");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
					return;
				}
				p.closeInventory();
				p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");
				break;
			}
			
		}
		if (event.getInventory().getTitle().equalsIgnoreCase("§8Loja de Bosses")) {
			event.setCancelled(true);
			if (event.getClick() == ClickType.LEFT) {
				switch (event.getRawSlot()) {
				case 11:
					if (!hasAvaliableSlot(p)) {
						p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					}
					if (CraniosManager.getCranios(p, Main.dracma) >= 25000000) {
						CraniosManager.removeCranios(p, Main.dracma, (long) 25000000);
						Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
								"bosses giveboss " + p.getName() + " bosspegaso 1");
						p.sendMessage("§aVoc\u00ea comprou §f§lBOSS PÉGASO");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.closeInventory();
					p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");

					break;
				case 12:
					if (!hasAvaliableSlot(p)) {
						p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					}
					if (CraniosManager.getCranios(p, Main.dracma) >= 50000000) {
						CraniosManager.removeCranios(p, Main.dracma, (long) 50000000);
						Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
								"bosses giveboss " + p.getName() + " bossciclope 1");
						p.sendMessage("§aVoc\u00ea comprou §B§lBOSS CICLOPE");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.closeInventory();
					p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");
					break;
				case 13:
					if (!hasAvaliableSlot(p)) {
						p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					}
					if (CraniosManager.getCranios(p, Main.dracma) >= 75000000) {
						CraniosManager.removeCranios(p, Main.dracma, (long) 75000000);
						Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
								"bosses giveboss " + p.getName() + " bosscerbero 1");
						p.sendMessage("§aVoc\u00ea comprou §c§lBOSS CÉRBERO");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.closeInventory();
					p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");
					break;
				case 14:
					if (!hasAvaliableSlot(p)) {
						p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					}
					if (CraniosManager.getCranios(p, Main.dracma) >= 100000000) {
						CraniosManager.removeCranios(p, Main.dracma, (long) 100000000);
						Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
								"bosses giveboss " + p.getName() + " bosstifao 1");
						p.sendMessage("§aVoc\u00ea comprou §4§lBOSS TIFAO");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.closeInventory();
					p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");
					break;
				case 15:
					if (!hasAvaliableSlot(p)) {
						p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					}
					if (CraniosManager.getCranios(p, Main.dracma) >= 125000000) {
						CraniosManager.removeCranios(p, Main.dracma, (long) 125000000);
						Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
								"bosses giveboss " + p.getName() + " bossminotauro 1");
						p.sendMessage("§aVoc\u00ea comprou §e§lBOSS MINOTAURO");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.closeInventory();
					p.sendMessage("§cVoc\u00ea n\u00e3o tem dracma para isso!");
					break;
				}	
			}
		}
		if (event.getInventory().getName().equalsIgnoreCase("§2Loja")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 44:
				zombiecoinsCommand.menu(p);
				break;
			case 30:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					break;
				}
				if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 200000L) {
					zombiecoinsManager.removePecados(p, Main.zombiecoins, 200000L);
					final ItemStack skull = new ItemStack(Material.BOOK, 1);
					final ItemMeta skullMeta = skull.getItemMeta();
					skullMeta.setDisplayName("§b§lTAG");
					skullMeta.addEnchant(Enchantment.DURABILITY, 1, true);
					skullMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
					final ArrayList<String> zumbilore = new ArrayList<String>();
					zumbilore.add("");
					zumbilore.add(" §7Use este livro para escolher");
					zumbilore.add("§7 uma tag personalizada");
					zumbilore.add("§7 para colocar em cima do seu nick");
					zumbilore.add("§7");
					zumbilore.add("§7Para usar basta clicar com");
					zumbilore.add("§7o botão direito, após isso");
					zumbilore.add("§7escreva no chat a tag que deseja.");
					zumbilore.add("§fExemplo:");
					zumbilore.add("§5&5 Eu sou o Milior");
					zumbilore.add("§f");
					zumbilore.add("§c§lCUIDADO§c! após usar não escreva");
					zumbilore.add("§cnada no chat alem da TAG!");
					skullMeta.setLore(zumbilore);
					skull.setItemMeta(skullMeta);
					p.getInventory().addItem(new ItemStack[] { skull });
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
					p.sendMessage("§aVocê comprou um §b§lPAPEL DE TAG §ano mercado de Zombie Coins!");
					return;
				}
				p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
				break;
			case 28:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					break;
				}
				if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 30000L) {
					zombiecoinsManager.removePecados(p, Main.zombiecoins, 30000L);
					final ItemStack skull = new ItemStack(Material.IRON_SWORD, 1);
					final ItemMeta skullMeta = skull.getItemMeta();
					skullMeta.setDisplayName("§bLooting §l21");
					skullMeta.addEnchant(Enchantment.DURABILITY, 15, true);
					skullMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 21, true);
					skullMeta.addEnchant(Enchantment.DAMAGE_ALL, 15, true);
					skull.setItemMeta(skullMeta);
					p.getInventory().addItem(new ItemStack[] { skull });
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
					p.sendMessage("§aVocê comprou uma §bLooting §l21 §ano mercado de Zombie Coins!");
					return;
				}
				p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
				break;
			case 29:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					break;
				}
				if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 35000L) {
					zombiecoinsManager.removePecados(p, Main.zombiecoins, 35000L);
					final ItemStack skull = new ItemStack(Material.DIAMOND_SWORD, 1);
					final ItemMeta skullMeta = skull.getItemMeta();
					skullMeta.setDisplayName("§cs50");
					skullMeta.addEnchant(Enchantment.DURABILITY, 30, true);
					skullMeta.addEnchant(Enchantment.DAMAGE_ALL, 50, true);
					skullMeta.addEnchant(Enchantment.FIRE_ASPECT, 10, true);
					skull.setItemMeta(skullMeta);
					p.getInventory().addItem(new ItemStack[] { skull });
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
					p.sendMessage("§aVocê comprou uma §cs50 §ano mercado de Zombie Coins!");
					return;
				}
				p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
				break;
			case 15:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					break;
				}
				if (event.getClick() == ClickType.RIGHT) {
					p.performCommand("caixamisteriosa ver basica");
				}
				if (event.getClick() == ClickType.LEFT) {
					if (event.getClick().isShiftClick()) {
						if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 15000L * 64) {
							zombiecoinsManager.removePecados(p, Main.zombiecoins, 15000L * 64);
							Bukkit.getScheduler().runTask(Main.plugin,
									() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
											"caixamisteriosa give " + p.getName() + " basica 64"));
							p.sendMessage(
									"§aVoc\u00ea comprou 64 §fCAIXAS BASICA §apor " + 15000L * 64 + " §fZombieCoins");
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
							return;
						}
						p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
						break;
					} else {
						if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 15000L) {
							zombiecoinsManager.removePecados(p, Main.zombiecoins, 15000L);
							Bukkit.getScheduler().runTask(Main.plugin,
									() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
											"caixamisteriosa give " + p.getName() + " basica 1"));
							p.sendMessage("§aVoc\u00ea comprou §fCAIXA BASICA §apor §f15.000 ZombieCoins");
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
							return;
						}
						p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
					}
				}
				break;
			case 16:
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					break;
				}
				if (event.getClick() == ClickType.RIGHT || event.getClick() == ClickType.DROP) {
					p.performCommand("caixamisteriosa ver maquina");
				}
				if (event.getClick() == ClickType.LEFT) {
					if (event.getClick().isShiftClick()) {
						if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 35000L * 64) {
							zombiecoinsManager.removePecados(p, Main.zombiecoins, 35000L * 64);
							Bukkit.getScheduler().runTask(Main.plugin,
									() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
											"caixamisteriosa give " + p.getName() + " maquina 64"));
							p.sendMessage(
									"§aVoc\u00ea comprou 64 §fCAIXAS MAQUINA §apor " + 20000L * 64 + " §fZombieCoins");
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
							return;
						}
						p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
						break;
					} else {
						if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 35000L) {
							zombiecoinsManager.removePecados(p, Main.zombiecoins, 35000L);
							Bukkit.getScheduler().runTask(Main.plugin,
									() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
											"caixamisteriosa give " + p.getName() + " maquina 1"));
							p.sendMessage("§aVoc\u00ea comprou §fCAIXA MAQUINA §apor §f35.000 ZombieCoins");
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
							return;
						}
						p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
					}
				}
				break;
			case 11: {
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					break;
				}
				if (event.getClick().isShiftClick()) {
					if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 192000L) {
						zombiecoinsManager.removePecados(p, Main.zombiecoins, 192000L);
						Bukkit.getScheduler().runTask(Main.plugin,
								() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
										"caixas givekey " + p.getName() + " misteriosa 64"));
						p.sendMessage("§aVoc\u00ea comprou 64 §cChaves Misteriosa §apor 192.000§f ZombieCoins");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
					break;
				} else {
					if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 3000L) {
						zombiecoinsManager.removePecados(p, Main.zombiecoins, 3000L);
						Bukkit.getScheduler().runTask(Main.plugin,
								() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
										"caixas givekey " + p.getName() + " misteriosa 1"));
						p.sendMessage("§aVoc\u00ea comprou §cChave Misteriosa §apor §f3.000 ZombieCoins");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
				}
				break;
			}
			case 12: {
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					break;
				}
				if (event.getClick().isShiftClick()) {
					if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 224000L) {
						zombiecoinsManager.removePecados(p, Main.zombiecoins, 224000L);
						Bukkit.getScheduler().runTask(Main.plugin,
								() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
										"caixas givekey " + p.getName() + " all 64"));
						p.sendMessage("§aVoc\u00ea comprou 64 §6Chaves ALL §apor 224.000 §fZombieCoins");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
					break;
				} else {
					if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 3500L) {
						zombiecoinsManager.removePecados(p, Main.zombiecoins, 3500L);
						Bukkit.getScheduler().runTask(Main.plugin,
								() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
										"caixas givekey " + p.getName() + " all 1"));
						p.sendMessage("§aVoc\u00ea comprou §6Chave ALL §apor §f3.500 ZombieCoins");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins virutal para isso!");
				}
				break;
			}
			case 13: {
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					break;
				}
				if (event.getClick().isShiftClick()) {
					if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 80000L * 64) {
						zombiecoinsManager.removePecados(p, Main.zombiecoins, 80000L * 64);
						Bukkit.getScheduler().runTask(Main.plugin,
								() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
										"caixas givekey " + p.getName() + " VIP 64"));
						p.sendMessage("§aVoc\u00ea comprou 64 §bChaves VIP §apor " + 80000L * 64 + " §fZombieCoins");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.sendMessage("§cVoc\u00ea n\u00e3o tem ZombieCoins virutalmente suficientes!");
					break;
				} else {
					if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 80000L) {
						zombiecoinsManager.removePecados(p, Main.zombiecoins, 80000L);
						Bukkit.getScheduler().runTask(Main.plugin,
								() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
										"caixas givekey " + p.getName() + " vip 1"));
						p.sendMessage("§aVoc\u00ea comprou §bChave VIP §apor §f80.000 ZombieCoins");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.sendMessage("§cVoc\u00ea n\u00e3o tem ZombieCoins virutalmente suficientes!");
				}
				break;
			}
			case 10: {
				if (!hasAvaliableSlot(p)) {
					p.sendMessage("§cVoc\u00ea est\u00e1 com o invent\u00e1rio cheio");
					break;
				}
				if (event.getClick().isShiftClick()) {
					if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 83200L) {
						zombiecoinsManager.removePecados(p, Main.zombiecoins, 83200L);
						Bukkit.getScheduler().runTask(Main.plugin,
								() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
										"caixas givekey " + p.getName() + " boss 64"));
						p.sendMessage("§aVoc\u00ea comprou 64 §fChaves BOSS §apor 83.200 §fZombieCoins");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.sendMessage("§cVoc\u00ea n\u00e3o tem ZombieCoins virutalmente suficientes!");
					break;
				} else {
					if (zombiecoinsManager.getPecados(p, Main.zombiecoins) >= 1300L) {
						zombiecoinsManager.removePecados(p, Main.zombiecoins, 1300L);
						Bukkit.getScheduler().runTask(Main.plugin,
								() -> Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
										"caixas givekey " + p.getName() + " boss 1"));
						p.sendMessage("§aVoc\u00ea comprou §fChave BOSS §apor §f150.000 ZombieCoins");
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
						return;
					}
					p.sendMessage("§cVoc\u00ea n\u00e3o tem ZombieCoins virutalmente suficientes!");
				}
				break;
			}
			case 26: {
				zombiecoinsCommand.menu(p);
				break;
			}
			}
		}
		if (event.getInventory().getName().equalsIgnoreCase("§2Zombie Coins")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 22: {
				long amount = 0L;
				for (int i = 0; i < p.getInventory().getSize(); ++i) {
					final ItemStack item = p.getInventory().getItem(i);
					if (item != null && item.getAmount() > 0 && item.hasItemMeta()
							&& item.getItemMeta().hasDisplayName()
							&& item.getItemMeta().getDisplayName().equals("§b§lZombieCoins")) {
						amount += item.getAmount();
						p.getInventory().setItem(i, (ItemStack) null);
					}
				}
				if (amount > 0L) {
					zombiecoinsManager.addPecados(p, Main.zombiecoins, amount);
					ActionBar.sendActionbar(p,
							"§fVoc\u00ea adicionou §a" + amount + " §bZombieCoins §fpara usar virtualmente§f!");
					break;
				}
				p.sendMessage("§cVoc\u00ea n\u00e3o tem cabe\u00e7a de ZombieCoins em seu invent\u00e1rio!");
				p.closeInventory();
				break;
			}
			case 34: {
				zombiecoinsCommand.loja(p);
				break;
			}
			}
		}
	}
}
