package evento;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.*;
import inventorys.*;

public class InventoryMinesClick implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerCommandExecute(PlayerCommandPreprocessEvent e) {
		String message = e.getMessage().split(" ")[0];
		if (!message.equalsIgnoreCase("/warp") && !message.equalsIgnoreCase("/warps")
				&& !message.equalsIgnoreCase("/system:warp") && !message.equalsIgnoreCase("/system:warps"))
			return;
		if ((e.getMessage().split(" ")).length > 1)
			return;
		if (e.isCancelled())
			return;
		e.setCancelled(true);
		Player p = e.getPlayer();
		p.openInventory(Others.getInventory());
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 5.0F);
	}

	@EventHandler
	public void onInventoryMinesClick(final InventoryClickEvent e) {
		if ((!e.getInventory().getName().equals("Local Principal")
				&& !e.getInventory().getName().equals("Locais - Minas")
				&& !e.getInventory().getName().equals("Locais - Arenas")
				&& !e.getInventory().getName().equals("Locais do Servidor")) || e.getInventory().getHolder() != null) {
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
		final Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equals("Locais - Minas")) {
			switch (e.getSlot()) {
			case 11: {
				p.performCommand("mina");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 15: {
				p.closeInventory();
				p.performCommand("warp minapvp");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 13: {
				p.performCommand("warp dracma");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			}
		} else if (e.getInventory().getName().equals("Locais - Arenas")) {
			switch (e.getSlot()) {
			case 11: {
				p.closeInventory();
				p.teleport(new Location(Bukkit.getWorld("Eventos"), 8003.5, 105.0, 9013.5, -90.0f, 0.0f));
				p.sendTitle("§6§lTELEPORTE", "§fVoc\u00ea chegou na §eArena Grega");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 13: {
				p.performCommand("warp fps");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
				break;
			}
			case 15: {
				p.sendMessage("§cEsta arena ainda n\u00e3o esta dispon\u00edvel.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
				break;
			}
			}
		} else if (e.getInventory().getName().equals("Locais do Servidor")) {
			switch (e.getSlot()) {
			case 11: {
				p.closeInventory();
				p.performCommand("warp loja");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 23: {
				p.closeInventory();
				p.performCommand("warp crates");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 24: {
				p.closeInventory();
				p.performCommand("mercadodeus");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 29: {
				p.closeInventory();
				p.performCommand("warp altar");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 33: {
				p.closeInventory();
				p.performCommand("cidade");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 21: {
				p.closeInventory();
				p.performCommand("warp trocadores");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 20: {
				p.closeInventory();
				p.performCommand("warp terrenos");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 22: {
				p.closeInventory();
				p.openInventory(Arenas.getInventory());
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 15: {
				p.performCommand("linhagem");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			case 13: {
				p.openInventory(Mines.getInventory(p));
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
				break;
			}
			}
		}
	}
}
