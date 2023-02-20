package bd.pablo.redwinsutil;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

import inventorys.Skull;

public class HelpCommand implements CommandExecutor {
	public static HashMap<String, String> helped;

	static {
		HelpCommand.helped = new HashMap<String, String>();
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String label,
			final String[] args) {
		if (command.getName().equalsIgnoreCase("recompensa")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas jogadores in-game!");
				return true;
			}
			final Player p = (Player) sender;
			final Inventory inv = Bukkit.createInventory((InventoryHolder) null, 27, "§6Recompensa Diaria");
			inv.setItem(12, new ItemBuilder(Skull.getSkull(
					"http://textures.minecraft.net/texture/6144ccf110bc31ce2424c879567f43ae2f665968447f9fce858f62737f39c10"))
					.name("§eRecompensa §7[MEMBRO]:")
					.lore("§r", "§f Clique para receber sua recompensa", "§f Permiss\u00e3o: §7TODOS", "").build());
			inv.setItem(14, new ItemBuilder(Skull.getSkull(
					"http://textures.minecraft.net/texture/6144ccf110bc31ce2424c879567f43ae2f665968447f9fce858f62737f39c10"))
					.name("§eRecompensa §7[VIP]:")
					.lore("§r", "§f Clique para receber sua recompensa",
							"§f Permiss\u00e3o: §aVIP§f, §aVIP§e+§f, §bMVP§f", "§b MVP§e+§f,§a YTMirim§f, §cYoutuber",
							"")
					.build());
			p.openInventory(inv);
		}
		if (command.getName().equalsIgnoreCase("ajuda")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cApenas jogadores in-game!");
				return true;
			}
			final Player p = (Player) sender;
			if (args.length == 0) {
				sender.sendMessage("§cUtilize /ajuda <mensagem>.");
				return true;
			}
			if (args.length > 0) {
				String message = "";
				for (int i = 0; i < args.length; ++i) {
					message = String.valueOf(message) + " " + args[i];
				}
				if (HelpCommand.helped.containsKey(p.getName())) {
					p.sendMessage("§cVocê j\u00e1 possui uma d\u00favida pendente, aguarde ela ser respondida.");
					return true;
				}
				HelpCommand.helped.put(p.getName(), message);
				p.sendMessage("§aSua d\u00favida foi enviada, em breve um membro da equipe irá responde-lo.");
				for (Player online : Bukkit.getOnlinePlayers()) {
					if (online.hasPermission("redwins.ajuda")) {
						online.sendMessage(" \n §bVocê recebeu um ticket! \n §bUse §f/tickets §bpara responder. \n ");
						online.playSound(online.getLocation(), Sound.DOOR_OPEN, 5, 5);
					}
				}
				return true;
			}
		}
		return false;
	}
}
