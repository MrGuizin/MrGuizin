package bd.pablo.redwinsutil;

import org.bukkit.craftbukkit.v1_8_R3.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.event.block.*;

import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.inventory.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

public class Eventos implements Listener {
	public static void applyParticles(Player p) {
		Color cor = Color.WHITE;
		Firework firework = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		FireworkMeta fireworkMeta = firework.getFireworkMeta();
		FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(cor).with(FireworkEffect.Type.BALL)
				.trail(true).build();
		FireworkEffect effect2 = FireworkEffect.builder().flicker(false).withColor(cor).with(FireworkEffect.Type.BALL)
				.trail(true).build();
		FireworkEffect effect3 = FireworkEffect.builder().flicker(false).withColor(cor).with(FireworkEffect.Type.BALL)
				.trail(true).build();
		FireworkEffect effect4 = FireworkEffect.builder().flicker(false).withColor(cor).with(FireworkEffect.Type.BALL)
				.trail(true).build();
		FireworkEffect effect5 = FireworkEffect.builder().flicker(false).withColor(cor).with(FireworkEffect.Type.BALL)
				.trail(true).build();
		FireworkEffect effect6 = FireworkEffect.builder().flicker(false).withColor(cor).with(FireworkEffect.Type.BALL)
				.trail(true).build();
		FireworkEffect effect7 = FireworkEffect.builder().flicker(false).withColor(cor).with(FireworkEffect.Type.BALL)
				.trail(true).build();
		FireworkEffect effect8 = FireworkEffect.builder().flicker(false).withColor(cor).with(FireworkEffect.Type.BALL)
				.trail(true).build();
		FireworkEffect effect9 = FireworkEffect.builder().flicker(false).withColor(cor).with(FireworkEffect.Type.BALL)
				.trail(true).build();
		fireworkMeta.addEffect(effect);
		fireworkMeta.addEffect(effect2);
		fireworkMeta.addEffect(effect3);
		fireworkMeta.addEffect(effect4);
		fireworkMeta.addEffect(effect5);
		fireworkMeta.addEffect(effect6);
		fireworkMeta.addEffect(effect7);
		fireworkMeta.addEffect(effect8);
		fireworkMeta.addEffect(effect9);
		fireworkMeta.setPower(1);
		firework.setFireworkMeta(fireworkMeta);
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), () -> firework.detonate(), 4L);
	}

	public static ItemStack setMaxStackSize(final ItemStack is, final int amount) {
		try {
			final net.minecraft.server.v1_8_R3.ItemStack nmsIS = CraftItemStack.asNMSCopy(is);
			nmsIS.getItem().c(amount);
			return CraftItemStack.asBukkitCopy(nmsIS);
		} catch (Throwable t) {
			return null;
		}
	}

	@EventHandler
	public void a(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR))
			if (p.getItemInHand().getType() == Material.PAPER && p.getItemInHand().getType() != null
					&& p.getItemInHand().hasItemMeta()
					&& p.getItemInHand().getItemMeta().getLore().contains("§7 para ativar o seu VIP")) {
				ItemStack item = p.getItemInHand();
				if (item.getAmount() == 1) {
					p.getInventory().removeItem(item);
				} else {
					item.setAmount(item.getAmount() - 1);
				}
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "darvip " + p.getName() + " vip 10");
				applyParticles(p);
			}
	}

	@EventHandler
	public void ender(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) && p.getItemInHand().getType().equals(Material.PAPER)
				&& p.getInventory().getItemInHand().hasItemMeta() && p.getInventory().getItemInHand().getItemMeta().hasLore() && p.getInventory().getItemInHand().getItemMeta().getLore()
						.contains("§7 Para usa-lo clique com o direito")) {
			final ItemStack item = p.getItemInHand();
			if (item.getAmount() == 1) {
				p.getInventory().removeItem(new ItemStack[] { item });
			} else {
				item.setAmount(item.getAmount() - 1);
			}
			applyParticles(p);
			Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "pontos add " + p.getName() + " 1");
			Bukkit.broadcastMessage("§2");
			Bukkit.broadcastMessage("§b " + p.getName() + "§f ativou um papel de ponto.");
			Bukkit.broadcastMessage("§f");
			for (Player p2 : Bukkit.getOnlinePlayers()) {
				p.sendTitle("§b" + p.getName(), "§fConseguiu 1 Ponto");
			}

		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerUse(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& p.getItemInHand() != null) {
			if (p.getItemInHand().getType().equals(Material.ENDER_PEARL)) {
				event.setCancelled(true);
				p.sendMessage("§cVoc\u00ea n\u00e3o pode usar isso no servidor.");
			}
			if (p.getItemInHand().getType().equals(Material.EGG)) {
				event.setCancelled(true);
				p.sendMessage("§cVoc\u00ea n\u00e3o pode usar isso no servidor.");
			}
			if (p.getItemInHand().getType().equals(Material.EYE_OF_ENDER)) {
				event.setCancelled(true);
				p.sendMessage("§cVoc\u00ea n\u00e3o pode usar isso no servidor.");
			}
		}
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) && p.getItemInHand().getType().equals(Material.PAPER)
				&& p.getInventory().getItemInHand().getItemMeta().hasLore() && p.getInventory().getItemInHand()
						.getItemMeta().getLore().contains(" §bVoc\u00ea tem §n23\u2030§b de chance.")) {
			final int chance = new Random().nextInt(2);
			if (chance <= 0.23) {
				final ItemStack item = p.getItemInHand();
				if (item.getAmount() == 1) {
					p.getInventory().removeItem(new ItemStack[] { item });
				} else {
					item.setAmount(item.getAmount() - 1);
				}
				Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "pontos add " + p.getName() + " 1");
				Bukkit.broadcastMessage("\n§f Jogador §6" + p.getName() + "§f \u00e9 um sortudo"
						+ "\n conseguiu um ponto em um papel" + "\n de 23% de chance, 2 palavras, Para b\u00e9ns!");
				Bukkit.broadcastMessage("§f");
				for (final Player p2 : Bukkit.getOnlinePlayers()) {
					p.sendTitle("§6" + p.getName(), "§fConseguiu um PONTO!");
				}
			} else {
				final ItemStack item = p.getItemInHand();
				if (item.getAmount() == 1) {
					p.getInventory().removeItem(new ItemStack[] { item });
				} else {
					item.setAmount(item.getAmount() - 1);
				}
				p.sendMessage("§cVoc\u00ea n\u00e3o teve sorte dessa vez!");
			}
		}
	}

	@EventHandler
	public void onClick(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§0Lixeira")) {
			if (e.getRawSlot() < 0) {
				return;
			}
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.CAULDRON_ITEM) {
				final Inventory lixeiragui = Bukkit.createInventory((InventoryHolder) null, 54, "§0Coloque o lixo");
				p.openInventory(lixeiragui);
			}
			if (e.getCurrentItem().getType() == Material.BARRIER) {
				final Inventory limpargui = Bukkit.createInventory((InventoryHolder) null, 27,
						"§0Confirma\u00e7\u00e3o");
				final ItemStack sim = new ItemStack(Material.WOOL, 1, (short) 5);
				final ItemMeta simmeta = sim.getItemMeta();
				simmeta.setDisplayName("§aAceitar (Leia abaixo)");
				final ArrayList<String> simlore = new ArrayList<String>();
				simlore.add("§7Todo o seu invent\u00e1rio ser\u00e1 limpo");
				simlore.add("§7e todos os itens ser\u00e3o perdidos.");
				simmeta.setLore(simlore);
				sim.setItemMeta(simmeta);
				final ItemStack nao = new ItemStack(Material.WOOL, 1, (short) 14);
				final ItemMeta naometa = nao.getItemMeta();
				naometa.setDisplayName("§cNegar");
				final ArrayList<String> naolore = new ArrayList<String>();
				naolore.add("§7Cancelar esta opera\u00e7\u00e3o.");
				naometa.setLore(naolore);
				nao.setItemMeta(naometa);
				limpargui.setItem(11, sim);
				limpargui.setItem(15, nao);
				p.openInventory(limpargui);
			}
		}
		if (e.getInventory().getName().equalsIgnoreCase("§0Confirma\u00e7\u00e3o")) {
			if (e.getRawSlot() < 0) {
				return;
			}
			e.setCancelled(true);
			if (e.getCurrentItem().getDurability() == 5) {
				p.getInventory().clear();
				p.closeInventory();
				p.sendMessage("§aSeu invent\u00e1rio foi limpo.");
			}
			if (e.getCurrentItem().getDurability() == 14) {
				p.closeInventory();
			}
		}
	}
}
