package bd.pablo.redwinsutil;

import java.util.stream.*;
import org.bukkit.configuration.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.text.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.event.inventory.*;

public class Listeners implements Listener {
	private double price;
	private String semMoney;
	private String semItens;
	private String reparado;
	private List<Material> reparaveis;

	public Listeners() {
		final ConfigurationSection section = Main.getInstance().getConfig().getConfigurationSection("Reparar");
		this.price = section.getDouble("Price", 1000.0);
		this.semMoney = ChatColor.translateAlternateColorCodes('&', section.getString("Sem money", ""));
		this.semItens = ChatColor.translateAlternateColorCodes('&', section.getString("Sem itens", ""));
		this.reparado = ChatColor.translateAlternateColorCodes('&', section.getString("Reparado", ""));
		this.reparaveis = (List<Material>) section.getStringList("Reparaveis").stream().map(Material::getMaterial)
				.collect(Collectors.toList());
	}

	@EventHandler
	private void onCombust(final EntityCombustEvent e) {
		if (e.getEntityType() == EntityType.ZOMBIE) {
			e.setCancelled(true);
		}
	}


	public boolean hasAvaliableSlot(final Player player) {
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

	public static String format(final double coins) {
		final NumberFormat nf = new DecimalFormat("#.##", new DecimalFormatSymbols(new Locale("pt", "BR")));
		return nf.format(coins);
	}

	@EventHandler
	public void onClick22Invento2ry(final InventoryClickEvent event) {
		final Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase("Lista de Ranks")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 40: {
				p.closeInventory();
				p.performCommand("rankup");
				break;
			}
			}
		}
		if (event.getInventory().getName().equalsIgnoreCase("§6Recompensa Diaria")) {
			event.setCancelled(true);
			switch (event.getRawSlot()) {
			case 12: {
				p.chat("/kit keydiaria");
				p.closeInventory();
				break;
			}
			case 14: {
				p.chat("/kit keydiariav");
				p.closeInventory();
				break;
			}
			}
		}
	}

	@EventHandler
	public void click2(final InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase("§7Reparar ITENS")) {
			event.setCancelled(true);
			if (event.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
				final boolean b = player.hasPermission("redwins.reparar");
				if (!b && Main.econ.getBalance((OfflinePlayer) player) < this.price) {
					player.sendMessage(this.semMoney);
					event.setCancelled(true);
					player.closeInventory();
					return;
				}
				int amount = 0;
				ItemStack[] contents;
				for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
					final ItemStack itemStack = contents[i];
					if (itemStack != null) {
						if (this.reparaveis.contains(itemStack.getType())) {
							if (itemStack.getDurability() <= itemStack.getType().getMaxDurability()) {
								itemStack.setDurability((short) 0);
								amount += itemStack.getAmount();
							}
						}
					}
				}
				if (amount > 0) {
					if (!b) {
						Main.econ.withdrawPlayer((OfflinePlayer) player, (long) this.price);
					}
					player.sendMessage(this.reparado.replace("{amount}", String.valueOf(amount)).replace("{price}",
							String.valueOf(b ? 0.0 : this.price)));
					event.setCancelled(true);
					player.closeInventory();
				} else {
					player.sendMessage(this.semItens);
				}
				event.setCancelled(true);	
				player.closeInventory();
			}
		}
	}

	@EventHandler
	public void a(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		final Action action = e.getAction();
		if (action.equals((Object) Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType() == Material.ANVIL) {
			e.setCancelled(true);
			Reparar.Reparar(p);
		}
	}


	@EventHandler
	public void onCraft(final CraftItemEvent craft) {
		final Player player = Bukkit.getPlayer(craft.getWhoClicked().getName());
		player.sendMessage(String.valueOf(craft.getRecipe().getResult()));
		if (craft.getRecipe().getResult().getType() == Material.STONE_BUTTON
				|| craft.getRecipe().getResult().getType() == Material.FIREWORK) {
			craft.setCancelled(true);
		}
	}
}
