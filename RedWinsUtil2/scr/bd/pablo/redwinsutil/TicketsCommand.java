package bd.pablo.redwinsutil;

import org.bukkit.command.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class TicketsCommand implements CommandExecutor, Listener {
	private HashMap<String, String> answing;

	public TicketsCommand() {
		this.answing = new HashMap<String, String>();
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String label,
			final String[] args) {
		if (!command.getName().equalsIgnoreCase("tickets")) {
			return false;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas jogadores in-game!");
			return true;
		}
		final Player p = (Player) sender;
		if (sender.hasPermission("redwins.ajuda")) {
			final ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			for (final Map.Entry<String, String> entry : HelpCommand.helped.entrySet()) {
				items.add(new ItemBuilder(Material.SKULL_ITEM).durability(3).owner(entry.getKey())
						.name("§7" + entry.getKey()).lore("", "§7D\u00favida: " + entry.getValue()).build());
			}
			final ScrollerInventory scroll = new ScrollerInventory(items, "D\u00favidas", p);
			ScrollerInventory.users.put(p.getUniqueId(), scroll);
			return true;
		}
		sender.sendMessage("§cVoc\u00ea precisa do grupo Ajudante ou superior para executar este comando.");
		return true;
	}

	@EventHandler
	public void onClick(final InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
			return;
		}
		if (e.getInventory().getName().equalsIgnoreCase("D\u00favidas")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				final Player p = (Player) e.getWhoClicked();
				final Player p2 = Bukkit
						.getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
				if (this.answing.containsValue(p2.getName())) {
					p.closeInventory();
					p.sendMessage("§cEste jogador j\u00e1 est\u00e1 sendo respondido.");
					return;
				}
				if (HelpCommand.helped.containsKey(p2.getName())) {
					this.answing.put(p.getName(), p2.getName());
					p.closeInventory();
					p.sendMessage("");
					p.sendMessage("§bSistema de tickets de d\u00favidas:");
					p.sendMessage("");
					p.sendMessage("§fEscreva uma resposta para esta d\u00favida:");
					p.sendMessage("§eD\u00favida: §f" + HelpCommand.helped.get(p2.getName()));
					p.sendMessage("");
					return;
				}
				p.closeInventory();
				p.sendMessage("§cEsta d\u00favida j\u00e1 foi respondida.");
			}
		}
	}

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

	@EventHandler
	public void onChat(final AsyncPlayerChatEvent e) {
		final Player p = e.getPlayer();
		if (!this.answing.containsKey(p.getName())) {
			return;
		}
		final String message = e.getMessage();
		e.setCancelled(true);
		final Player p2 = Bukkit.getPlayerExact((String) this.answing.get(p.getName()));
		if (p2 == null) {
			this.answing.remove(p.getName());
			HelpCommand.helped.remove(this.answing.get(p.getName()));
			p.sendMessage("§cEste usu\u00e1rio n\u00e3o est\u00e1 online.");
			return;
		}
		if (!hasAvaliableSlot(p2)) {
			ItemStack i = new ItemStack(
					new ItemBuilder(Material.PAPER).removeAttributes().name("§bSistema de tickets de duvidas:")
							.lore("", "§eResposta: §f" + message, "", "§bRespondido por: §f" + p.getName(), "").enchant(Enchantment.DURABILITY, 1).build());
			p2.getLocation().getWorld().dropItem(p2.getLocation(), i);
			p2.sendMessage("§cDuvida foi dropada pois seu inventário está cheio.");
			p2.playSound(p2.getLocation(), Sound.ANVIL_USE, 5, 5);
		}
		p2.getInventory()
				.addItem(new ItemStack(new ItemBuilder(Material.PAPER).removeAttributes()
						.name("§bSistema de tickets de duvidas:").lore("", "§eResposta: §f" + message, "", "§bRespondido por: §f" + p.getName(), "")
						.enchant(Enchantment.DURABILITY, 1).build()));
		p2.sendTitle("§b§lSUA DUVIDA", "§fFoi respondia!");
		p2.playSound(p2.getLocation(), Sound.AMBIENCE_THUNDER, 5, 5);
		p2.sendMessage("");
		p2.sendMessage("§bSistema de tickets de d\u00favidas:");
		p2.sendMessage("");
		p2.sendMessage("§eResposta: §f" + message);
		p2.sendMessage("");
		p2.sendMessage("§bRespondido por: §f" + p.getName());
		p2.sendMessage("");
		p.sendMessage("§aObrigado por responder esta duvidas, temos boas expectativas para voc\u00ea!");
		this.answing.remove(p.getName());
		HelpCommand.helped.remove(p2.getName());
	}

	@EventHandler
	public void onDisconnec(final PlayerQuitEvent e) {
		if (HelpCommand.helped.containsKey(e.getPlayer().getName())) {
			HelpCommand.helped.remove(e.getPlayer().getName());
		}
	}
}
