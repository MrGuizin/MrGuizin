package dungeons;

import bd.pablo.redwinsutil.*;
import inventorys.ItemBuilder;
import inventorys.Skull;

import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.*;
import net.citizensnpcs.api.event.NPCRightClickEvent;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.association.RegionAssociable;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;

import org.bukkit.entity.*;

public class Eventos3 implements Listener {
	@EventHandler
	public void textura(PlayerResourcePackStatusEvent e) {
		if (e.getStatus() == Status.ACCEPTED) {
			e.getPlayer().sendMessage("§aSua textura foi aplicada com sucesso!");
			return;
		}
		if (e.getStatus() == Status.FAILED_DOWNLOAD) {
			e.getPlayer().sendMessage(
					"§c\n O Download da textura falhou\n entre em contato com a staff para ser resolvido!\n");
			return;
		}
		if (e.getStatus() == Status.SUCCESSFULLY_LOADED) {
			e.getPlayer().sendMessage("§aSua textura foi aplicada com sucesso!");
			return;
		}
		if (e.getStatus() == Status.DECLINED) {
			e.getPlayer().sendMessage("§c\n Você aceitou no menu, mas recusou a textura");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(
					"§c Para resolver isso é fácil, vá até a lista de servidores \n §cadicione o servidor caso ainda não tenha \n §cclique no servidor, depois em §fEditar\n §ce mude o §fPacote de recursos §cpara ativo. \n");
			return;
		}
	}

	public HashMap<String, Long> cooldowns = new HashMap<String, Long>();

	// Atena
	@EventHandler
	public void atena(EntityDamageByEntityEvent e) {
		if (e.isCancelled())
			return;
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				if (Main.linhagem.getConfig().getString("Linhagens." + e.getDamager().getName()) != null
						&& Main.linhagem.getConfig().getString("Linhagens." + e.getDamager().getName() + ".Linhagem")
								.equalsIgnoreCase("Atena")) {
					Player damager = (Player) e.getDamager();
					Player entity = (Player) e.getEntity();
					RegionManager rm = this.getWorldGuard().getRegionManager(entity.getWorld());
					ApplicableRegionSet set = rm.getApplicableRegions(entity.getLocation());
					if (set.queryState((RegionAssociable) null,
							new StateFlag[] { DefaultFlag.PVP }) == StateFlag.State.ALLOW) {
						int cooldownTime = 140;
						if (cooldowns.containsKey(damager.getName())) {
							long secondsLeft = ((cooldowns.get(damager.getName()) / 1000) + cooldownTime)
									- (System.currentTimeMillis() / 1000);
							if (secondsLeft > 0) {
							}
						}
						double randDouble = Math.random();
						if (randDouble <= 0.01D) {
							for (int n = 0; n < 50; ++n) {
								damager.playEffect(damager.getLocation(), Effect.WITCH_MAGIC, 100);
							}
							cooldowns.put(damager.getName(), System.currentTimeMillis());
							damager.sendMessage("§aSeu poder foi ativado.");
							damager.playSound(damager.getEyeLocation(), Sound.ENDERMAN_TELEPORT, 2, 2);
							damager.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 70, 2));
							for (Player ps : Bukkit.getOnlinePlayers()) {
								ps.hidePlayer(damager);
							}
							new BukkitRunnable() {
								@Override
								public void run() {
									for (Player ps : Bukkit.getOnlinePlayers()) {
										ps.showPlayer(damager);
									}
								}
							}.runTaskLater(Main.plugin, 60);
						}
					}
				}
			}
		}
	}

	private WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}

	// Hermes
	@EventHandler
	public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
		Player p = event.getPlayer();
		int cooldownTime = 40;
		RegionManager rm = this.getWorldGuard().getRegionManager(p.getWorld());
		ApplicableRegionSet set = rm.getApplicableRegions(p.getLocation());
		if (set.queryState((RegionAssociable) null, new StateFlag[] { DefaultFlag.PVP }) == StateFlag.State.ALLOW) {
			if (Main.linhagem.getConfig().getString("Linhagens." + p.getName()) != null && Main.linhagem.getConfig()
					.getString("Linhagens." + p.getName() + ".Linhagem").equalsIgnoreCase("Hermes")) {
				if (cooldowns.containsKey(p.getName())) {
					long secondsLeft = ((cooldowns.get(p.getName()) / 1000) + cooldownTime)
							- (System.currentTimeMillis() / 1000);
					if (secondsLeft > 0) {
						p.sendMessage("§cAguarde §f" + secondsLeft + " §cpara usar o seu poder novamente.");
						event.setCancelled(true);
						p.setSneaking(false);
						return;
					}
				}
				if (p.isSneaking()) {
					cooldowns.put(p.getName(), System.currentTimeMillis());
					p.setSneaking(false);
					for (int n = 0; n < 50; ++n) {
						p.playEffect(p.getLocation(), Effect.FLAME, 100);
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
					p.playSound(p.getEyeLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);
				}
			}
		}
	}

	// Poseidon
	@EventHandler
	public void Damage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				if (Main.linhagem.getConfig().getString("Linhagens." + e.getDamager().getName()) != null
						&& Main.linhagem.getConfig().getString("Linhagens." + e.getDamager().getName() + ".Linhagem")
								.equalsIgnoreCase("Poseidon")) {
					Player damager = (Player) e.getDamager();
					Player entity = (Player) e.getEntity();
					RegionManager rm = this.getWorldGuard().getRegionManager(entity.getWorld());
					ApplicableRegionSet set = rm.getApplicableRegions(entity.getLocation());
					if (set.queryState((RegionAssociable) null,
							new StateFlag[] { DefaultFlag.PVP }) == StateFlag.State.ALLOW) {
						int cooldownTime = 180;
						if (cooldowns.containsKey(damager.getName())) {
							long secondsLeft = ((cooldowns.get(damager.getName()) / 1000) + cooldownTime)
									- (System.currentTimeMillis() / 1000);
							if (secondsLeft > 0) {
							}
						}
						double randDouble = Math.random();
						if (randDouble <= 0.01D) {
							if (!entity.hasPotionEffect(PotionEffectType.SLOW)) {
								for (int n = 0; n < 50; ++n) {
									damager.playEffect(damager.getLocation(), Effect.WATERDRIP, 100);
								}
								cooldowns.put(damager.getName(), System.currentTimeMillis());
								damager.sendMessage("§aSeu poder foi ativado.");
								damager.playSound(damager.getEyeLocation(), Sound.WATER, 1, 1);
								entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 100));
								entity.sendMessage("§cVocê foi paralisado :/");
							}
						}
					}
				}
			}
		}
	}

	// Zeus
	@EventHandler
	public void zeus(EntityDamageByEntityEvent e) {
		if (e.isCancelled())
			return;
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				if (Main.linhagem.getConfig().getString("Linhagens." + e.getDamager().getName()) != null
						&& Main.linhagem.getConfig().getString("Linhagens." + e.getDamager().getName() + ".Linhagem")
								.equalsIgnoreCase("Zeus")) {
					Player damager = (Player) e.getDamager();
					Player entity = (Player) e.getEntity();
					RegionManager rm = this.getWorldGuard().getRegionManager(entity.getWorld());
					ApplicableRegionSet set = rm.getApplicableRegions(entity.getLocation());
					if (set.queryState((RegionAssociable) null,
							new StateFlag[] { DefaultFlag.PVP }) == StateFlag.State.ALLOW) {
						int cooldownTime = 180;
						if (cooldowns.containsKey(damager.getName())) {
							long secondsLeft = ((cooldowns.get(damager.getName()) / 1000) + cooldownTime)
									- (System.currentTimeMillis() / 1000);
							if (secondsLeft > 0) {
							}
						}
						double randDouble = Math.random();
						if (entity.getHealth() >= 8 && entity.getInventory().getArmorContents() != null) {
							if (randDouble <= 0.01D) {
								for (int n = 0; n < 50; ++n) {
									entity.playEffect(entity.getLocation(), Effect.INSTANT_SPELL, 100);
								}
								cooldowns.put(damager.getName(), System.currentTimeMillis());
								damager.sendMessage("§aSeu poder foi ativado.");
								damager.playSound(damager.getEyeLocation(), Sound.WITHER_HURT, 1, 1);
								entity.getWorld().strikeLightning(entity.getLocation());
								entity.getWorld().playEffect(entity.getLocation(), Effect.EXPLOSION_HUGE, 5);
								e.setDamage(e.getDamage() + 10);
								entity.setVelocity(new Vector(0, 2, 0));
								new BukkitRunnable() {
									int tempo = 10;
									public void run() {
										if (damager.isDead()) {
											tempo = 10;
											this.cancel();
										}
										if (!damager.isOnline()) {
											tempo = 10;
											this.cancel();
										}
										switch (--this.tempo) {
										case 9: 
											damager.playSound(damager.getEyeLocation(), Sound.ANVIL_LAND, 5, 5);
											break;
										case 8: 
											damager.playSound(damager.getEyeLocation(), Sound.ANVIL_LAND, 5, 5);
											break;
										case 7: 
											damager.playSound(damager.getEyeLocation(), Sound.ANVIL_LAND, 5, 5);
											break;
										case 6: 
											damager.playSound(damager.getEyeLocation(), Sound.ANVIL_LAND, 5, 5);
											break;
										case 5: 
											damager.playSound(damager.getEyeLocation(), Sound.ANVIL_LAND, 5, 5);
											break;
										case 4: 
											damager.playSound(damager.getEyeLocation(), Sound.ANVIL_LAND, 5, 5);
											break;
										case 3: 
											damager.playSound(damager.getEyeLocation(), Sound.ANVIL_LAND, 5, 5);
											break;
										case 2: 
											damager.playSound(damager.getEyeLocation(), Sound.ANVIL_LAND, 5, 5);
											break;
										case 1: 
											damager.playSound(damager.getEyeLocation(), Sound.ANVIL_LAND, 5, 5);
											tempo = 10;
											this.cancel();
											break;

										}
									}
								}.runTaskTimer(Main.plugin, 0L, 20L);

							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void saiu(PlayerQuitEvent e) {
		Player p = (Player) e.getPlayer();
		if (cooldowns.containsKey(p.getName())) {
			cooldowns.remove(p.getName());
		}
	}

	@EventHandler
	public void clica(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase("§7Trocar de linhagem")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 10:
				if (JH_Shop.Main.getInstace().getPontos(p.getName()) >= 1) {
					if (!Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
							.equalsIgnoreCase("Atena")) {
						p.closeInventory();
						JH_Shop.Main.getAPI().removePontos(p.getName(), (double) 1);
						Main.linhagem.getConfig().set("Linhagens." + p.getName() + ".Linhagem", "Atena");
						Main.linhagem.saveConfig();
						Eventos2.tags.put(p.getName(), "§e[Atena]");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
						p.sendMessage(" \n §a§lGG! §aVocê agora faz parte da linhagem de §eAtena. \n ");
					} else {
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
						p.sendMessage("§cVocê já é ATENA!");
					}

				} else {
					p.sendMessage(
							" \n §cVocê precisa de 1 ponto para trocar de linhagem.. \n \n §f Compre em §ewww.redwins.com.br \n ");
				}
				break;
			case 12:
				if (JH_Shop.Main.getInstace().getPontos(p.getName()) >= 1) {
					if (!Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
							.equalsIgnoreCase("Hermes")) {
						p.closeInventory();
						JH_Shop.Main.getAPI().removePontos(p.getName(), (double) 1);
						Main.linhagem.getConfig().set("Linhagens." + p.getName() + ".Linhagem", "Hermes");
						Main.linhagem.saveConfig();
						Eventos2.tags.put(p.getName(), "§c[Hermes]");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
						p.sendMessage(" \n §a§lGG! §aVocê agora faz parte da linhagem de §cHermes. \n ");
					} else {
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
						p.sendMessage("§cVocê já é HERMES!");
					}

				} else {
					p.sendMessage(
							" \n §cVocê precisa de 1 ponto para trocar de linhagem.. \n \n §f Compre em §ewww.redwins.com.br \n ");
				}
				break;
			case 14:
				if (JH_Shop.Main.getInstace().getPontos(p.getName()) >= 1) {
					if (!Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
							.equalsIgnoreCase("Poseidon")) {
						p.closeInventory();
						JH_Shop.Main.getAPI().removePontos(p.getName(), (double) 1);
						Main.linhagem.getConfig().set("Linhagens." + p.getName() + ".Linhagem", "Poseidon");
						Main.linhagem.saveConfig();
						Eventos2.tags.put(p.getName(), "§b[Poseidon]");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
						p.sendMessage(" \n §a§lGG! §aVocê agora faz parte da linhagem de §bPoseidon. \n ");
					} else {
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
						p.sendMessage("§cVocê já é POSEIDON!");
					}

				} else {
					p.sendMessage(
							" \n §cVocê precisa de 1 ponto para trocar de linhagem.. \n \n §f Compre em §ewww.redwins.com.br \n ");
				}
				break;
			case 16:
				if (JH_Shop.Main.getInstace().getPontos(p.getName()) >= 1) {
					if (!Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
							.equalsIgnoreCase("Zeus")) {
						p.closeInventory();
						JH_Shop.Main.getAPI().removePontos(p.getName(), (double) 1);
						Main.linhagem.getConfig().set("Linhagens." + p.getName() + ".Linhagem", "Zeus");
						Main.linhagem.saveConfig();
						Eventos2.tags.put(p.getName(), "§f[Zeus]");
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
						p.sendMessage(" \n §a§lGG! §aVocê agora faz parte da linhagem de §fZeus. \n ");
					} else {
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
						p.sendMessage("§cVocê já é ZEUS!");
					}

				} else {
					p.sendMessage(
							" \n §cVocê precisa de 1 ponto para trocar de linhagem.. \n \n §f Compre em §ewww.redwins.com.br \n ");
				}
				break;
			}
		}
		if (event.getInventory().getName().equalsIgnoreCase("§7Menu de Linhagem")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 10:
				p.performCommand("warp linhagem");
				break;
			case 14:
				Inventory inv = Bukkit.createInventory((InventoryHolder) null, 27, "§7Trocar de linhagem");
				if (Main.linhagem.getConfig().contains("Linhagens." + p.getName() + ".Linhagem")) {
					inv.setItem(4, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/d01afe973c5482fdc71e6aa10698833c79c437f21308ea9a1a095746ec274a0f"))
							.name("§bInformações")
							.lore("",
									"§7Linhagem atual: §b" + Main.linhagem.getConfig()
											.getString("Linhagens." + p.getName() + ".Linhagem"),
									"§7Pontos: §6"
											+ Main.format((double) JH_Shop.Main.getInstace().getPontos(p.getName()))
													.replace("C", "").replace(" ", ""),
									"")
							.build());

					inv.setItem(10, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/6136724745c8c9abc75cdf2205f2cd1d33e6bfefa415f995be76922912169ec9"))
							.name("§fTrocar para §eAtena")
							.lore("", "§fPreço: §b1 PONTO", "", "§7Poderes: ", "§f Você terá 0.5% de chance de",
									"§f ficar invisivel no pvp por 3s!", "", "§bHabilidades da arma: ",
									"§7 Botão Direito:", "§f Transforma o inimigo em galinha por 5s", "",
									"§7Shift: §fCria um escudo em volta por 10s", "")
							.build());

					inv.setItem(12, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/ad9913a661c38f5f700b80798a8c485d332d78345b7671d0a248a84b2099bf4e"))
							.name("§fTrocar para §cHermes")
							.lore("", "§fPreço: §b1 PONTO", "", "§7Poderes: ", "§f Você terá 1% de chance de",
									"§f ganhar velocidade lv 3 por 10s!", "", "§bHabilidades da arma: ",
									"§7 Botão Direito:", "§f Joga várias teias venenosa no inimigo", "",
									"§bShift: §fVelocidade I por 3 minutos", "§f - Super pulo I por 3 minutos",
									"§f - Ganha repulsão 2 no Caduceu §8(Arma de Hermes)")
							.build());

					inv.setItem(14, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/d71b954e89aed156f90fafce6dc7788ac76b6466cecca3a4f4ec1e463eb914a1"))
							.name("§fTrocar para §bPoseidon")
							.lore("", "§fPreço: §b1 PONTO", "", "§7Poderes: ", "§f Você terá 1% de chance de",
									"§f paralizar seu inimigo por 5s!", "", "§bHabilidades da arma: ",
									"§7 Botão Direito:", "§f Cria um iglu em volta do inimigo",
									"§f e teleporta você até ele.", "", "§7Shift: §fAparece água em volta deixando",
									"§f seus inimigos mais lento e você mais rápido!", "")
							.build());

					inv.setItem(16, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/d37a159687be36877d4e3b05f61bb598d8331c79108acc3c1af80f2253892bb2"))
							.name("§fTrocar para §lZeus")
							.lore("", "§fPreço: §b1 PONTO", "", "§7Poderes: ", "§f Você terá 1% de chance de",
									"§f mandar um raio no seu inimigo!", "", "§bHabilidades da arma: ",
									"§7 Botão Direito:", "§f Joga vários raios no inimigo",
									"§f Joga o inimigo para o alto", "", "§7Shift: §fForça I por 3 minutos", "")
							.build());
				} else {
					inv.setItem(4, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/533fc9a45be13ca57a78b21762c6e1262dae411f13048b963d972a29e07096ab"))
							.name("§bInformações").lore("§r", "§7 Linhagem Atual: §cNenhum", "").build());
				}
				p.openInventory(inv);
				break;
			}
		}
	}

	public static void ConfirmarZeus(Player p) {
		final Inventory textura = Bukkit.createInventory((InventoryHolder) null, 27,
				"§aVocê tem certeza será §fZeus§a?");
		textura.setItem(12, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§cNão")
				.lore("§7Clique para fechar")
				.head("https://textures.minecraft.net/texture/5fde3bfce2d8cb724de8556e5ec21b7f15f584684ab785214add164be7624b")
				.build());
		textura.setItem(14, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§aSim, quero ser §fZeus")
				.lore("", "§7Ao escolher essa linhagem você passará",
						"§7a ser Zeus, o Deus Supremo dos Gregos e do Trovão!", "", "§aNovos poderes?",
						"§7Você terá 1% de chance de", "§7mandar um raio no seu inimigo!", "",
						"§aHabilidades da arma: ", "§8Botão Direito:", "§7Joga vários raios no inimigo",
						"§7Joga o inimigo para o alto", "", "§8Shift: §fForça II por 3 minutos", "")
				.head("https://textures.minecraft.net/texture/22d145c93e5eac48a661c6f27fdaff5922cf433dd627bf23eec378b9956197")
				.build());
		p.openInventory(textura);
	}

	public static void ConfirmarAtena(Player p) {
		final Inventory textura = Bukkit.createInventory((InventoryHolder) null, 27,
				"§aVocê tem certeza será §fAtena§a?");
		textura.setItem(12, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§cNão")
				.lore("§7Clique para fechar")
				.head("https://textures.minecraft.net/texture/5fde3bfce2d8cb724de8556e5ec21b7f15f584684ab785214add164be7624b")
				.build());
		textura.setItem(14, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§aSim, quero ser §fAtena")
				.lore("", "§7Ao escolher essa linhagem você passará", "§7a ser Atena, a Deusa da sabedoria!", "",
						"§aNovos poderes?", "§7Você terá 0.5% de chance de", "§7ficar invisivel no pvp por 3s!", "",
						"§7Sem contar que Atena é a Deusa da Sabedoria",
						"§7então você sempre estará um passo á frente do inimigo.", "", "§aHabilidades da arma: ",
						"§8Botão Direito:", "§7Transforma o inimigo em galinha por 5s", "",
						"§8Shift: §7Cria um escudo em volta por 10s", "")
				.head("https://textures.minecraft.net/texture/22d145c93e5eac48a661c6f27fdaff5922cf433dd627bf23eec378b9956197")
				.build());
		p.openInventory(textura);
	}

	public static void ConfirmarPoseidon(Player p) {
		final Inventory textura = Bukkit.createInventory((InventoryHolder) null, 27,
				"§aVocê tem certeza será §fPoseidon§a?");
		textura.setItem(12, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§cNão")
				.lore("§7Clique para fechar")
				.head("https://textures.minecraft.net/texture/5fde3bfce2d8cb724de8556e5ec21b7f15f584684ab785214add164be7624b")
				.build());
		textura.setItem(14, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§aSim, quero ser §fPoseidon")
				.lore("", "§7Ao escolher essa linhagem você passará", "§7a ser Poseidon, o Deus dos Mares e dos Rios.",
						"", "§aNovos poderes?", "§7Você terá 1% de chance de", "§7paralizar seu inimigo por 5s!", "",
						"§aHabilidades da arma: ", "§8Botão Direito:", "§7Cria um iglu em volta do inimigo",
						"§7e teleporta você até ele.", "", "§8Shift: §7Aparece água em volta deixando",
						"§7seus inimigos mais lento e você mais rápido!", "")
				.head("https://textures.minecraft.net/texture/22d145c93e5eac48a661c6f27fdaff5922cf433dd627bf23eec378b9956197")
				.build());
		p.openInventory(textura);
	}

	public static void ConfirmarHermes(Player p) {
		final Inventory textura = Bukkit.createInventory((InventoryHolder) null, 27,
				"§aVocê tem certeza será §fHermes§a?");
		textura.setItem(12, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§cNão")
				.lore("§7Clique para fechar")
				.head("https://textures.minecraft.net/texture/5fde3bfce2d8cb724de8556e5ec21b7f15f584684ab785214add164be7624b")
				.build());
		textura.setItem(14, new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§aSim, quero ser §fHermes")
				.lore("", "§7Ao escolher essa linhagem você passará",
						"§7a ser Hermes, o Deus da Velocidade e do Comércio.", "", "§aNovos poderes?",
						"§7Sem contar que Hermes", "§7é o Deus da velocidade e do comércio por algum motivo!",
						"§7Seja o mais rico e mais rápido do servidor!", "", "§aHabilidades da arma: ",
						"§8Botão Direito:", "§7Joga várias teias venenosa no inimigo", "",
						"§8Shift: §7Velocidade I por 3 minutos", "§7 - Super pulo I por 3 minutos",
						"§7 - Ganha repulsão 2 no Caduceu §8(Arma de Hermes)", "")
				.head("https://textures.minecraft.net/texture/22d145c93e5eac48a661c6f27fdaff5922cf433dd627bf23eec378b9956197")
				.build());
		p.openInventory(textura);
	}

	@EventHandler
	public void click(NPCRightClickEvent event) {
		Player p = event.getClicker();
		if (event.getNPC().getName().equalsIgnoreCase("§f§lZEUS")) {
			if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem") == null) {
				ConfirmarZeus(p);
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 2, 2);
			} else {
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2, 2);
				p.sendMessage(" \n §cVocê já tem uma linhagem.. \n §cCaso queira trocar, digite §e/linhagem \n ");
				event.setCancelled(true);
				return;
			}
		}
		if (event.getNPC().getName().equalsIgnoreCase("§c§lHERMES")) {
			if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem") == null) {
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 2, 2);
				ConfirmarHermes(p);
			} else {
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2, 2);
				p.sendMessage(" \n §cVocê já tem uma linhagem.. \n §cCaso queira trocar, digite §e/linhagem \n ");
				event.setCancelled(true);
				return;
			}
		}
		if (event.getNPC().getName().equalsIgnoreCase("§e§lATENA")) {
			if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem") == null) {
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 2, 2);
				ConfirmarAtena(p);
			} else {
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2, 2);
				p.sendMessage(" \n §cVocê já tem uma linhagem.. \n §cCaso queira trocar, digite §e/linhagem \n ");
				event.setCancelled(true);
				return;
			}
		}
		if (event.getNPC().getName().equalsIgnoreCase("§b§lPOSEIDON")) {
			if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem") == null) {
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 2, 2);
				ConfirmarPoseidon(p);
			} else {
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 2, 2);
				p.sendMessage(" \n §cVocê já tem uma linhagem.. \n §cCaso queira trocar, digite §e/linhagem \n ");
				event.setCancelled(true);
				return;
			}
		}
	}

}
