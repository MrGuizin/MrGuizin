package bd.pablo.redwinsutil;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import org.bukkit.inventory.meta.*;

import evento.zombiecoinsEvents;
import inventorys.Skull;

public class Comandos implements CommandExecutor {
	static HashMap<String, Boolean> esconder;

	public boolean onCommand(final CommandSender sender, final Command cmd, final String str, final String[] arg) {
		if (cmd.getName().equalsIgnoreCase("trocartag")) {
			final Player p = (Player) sender;
			if (!zombiecoinsEvents.tag.containsKey(p.getName())) {
				if (Main.tag.getConfig().getString(p.getName()) != null) {
					String p2 = zombiecoinsEvents.tag.getOrDefault(p.getName(), null);
					zombiecoinsEvents.tag.put(p.getName(), p2);
					p.sendMessage(
							" \n §eDigite a Tag que você deseja, para usar cores use §f& \n §ePara cancelar, escreva §c§lCANCELAR \n ");
				} else {
					p.sendMessage("§cVocê precisa ter uma tag para trocar, compre uma §f/zombiecoins");
				}
			} else {
				p.sendMessage("§cVocê já está escolhendo uma tag! \n §cDigite a tag que deseja no chat..");
			}
		}
		if (cmd.getName().equalsIgnoreCase("linhagem")) {
			final Player p = (Player) sender;
			Inventory inv = Bukkit.createInventory((InventoryHolder) null, 27, "§7Menu de Linhagem");
			inv.setItem(10, new ItemBuilder(Skull.getSkull(
					"https://textures.minecraft.net/texture/11b4b268e8ea16a2401f2e8b98428877de5be29cd329172325f5077130c4c9c9"))
					.name("§f§lIR PARA LINHAGEM").lore("§r", "§7Teleporte para o local das linhagens", "").build());
			if (Main.linhagem.getConfig().contains("Linhagens." + p.getName() + ".Linhagem")) {
				inv.setItem(14, new ItemBuilder(Skull.getSkull(
						"https://textures.minecraft.net/texture/de150ab7c01846f480897fbd35f0525caeb52aa624f9670095f396105893dacb"))
						.name("§fTrocar linhagem").lore("§r", "§7Clique para trocar de linhagem", "").build());
				if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
						.equalsIgnoreCase("Atena")) {
					inv.setItem(13, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/d01afe973c5482fdc71e6aa10698833c79c437f21308ea9a1a095746ec274a0f"))
							.name("§bInformações de sua Linhagem")
							.lore("§r",
									"§7Linhagem: §b" + Main.linhagem.getConfig()
											.getString("Linhagens." + p.getName() + ".Linhagem"),
									"§7Poderes: ", "§f Você terá 0.5% de chance de",
									"§f ficar invisivel no pvp por 3s!", "", "§bHabilidades da arma: ",
									"§7 Botão Direito:", "§f Transforma o inimigo em galinha por 5s", "",
									"§7Shift: §fCria um escudo em volta por 10s", "")
							.build());
				}
				if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
						.equalsIgnoreCase("Hermes")) {
					inv.setItem(13, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/d01afe973c5482fdc71e6aa10698833c79c437f21308ea9a1a095746ec274a0f"))
							.name("§bInformações de sua Linhagem")
							.lore("§r",
									"§7Linhagem: §b" + Main.linhagem.getConfig()
											.getString("Linhagens." + p.getName() + ".Linhagem"),
									"§7Poderes: ", "§f Você terá 1% de chance de", "§f ganhar velocidade lv 3 por 10s!",
									"", "§bHabilidades da arma: ", "§7 Botão Direito:",
									"§f Joga várias teias venenosa no inimigo", "",
									"§bShift: §fVelocidade I por 3 minutos", "§f - Super pulo I por 3 minutos",
									"§f - Ganha repulsão 2 no Caduceu §8(Arma de Hermes)")
							.build());
				}
				if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
						.equalsIgnoreCase("Poseidon")) {
					inv.setItem(13, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/d01afe973c5482fdc71e6aa10698833c79c437f21308ea9a1a095746ec274a0f"))
							.name("§bInformações de sua Linhagem")
							.lore("§r",
									"§7Linhagem: §b" + Main.linhagem.getConfig()
											.getString("Linhagens." + p.getName() + ".Linhagem"),
									"§7Poderes: ", "§f Você terá 1% de chance de", "§f paralizar seu inimigo por 5s!",
									"", "§bHabilidades da arma: ", "§7 Botão Direito:",
									"§f Cria um iglu em volta do inimigo", "§f e teleporta você até ele.", "",
									"§7Shift: §fAparece água em volta deixando",
									"§f seus inimigos mais lento e você mais rápido!", "")
							.build());
				}
				if (Main.linhagem.getConfig().getString("Linhagens." + p.getName() + ".Linhagem")
						.equalsIgnoreCase("Zeus")) {
					inv.setItem(13, new ItemBuilder(Skull.getSkull(
							"https://textures.minecraft.net/texture/d01afe973c5482fdc71e6aa10698833c79c437f21308ea9a1a095746ec274a0f"))
							.name("§bInformações de sua Linhagem")
							.lore("§r",
									"§7Linhagem: §b" + Main.linhagem.getConfig()
											.getString("Linhagens." + p.getName() + ".Linhagem"),
									"§7Poderes: ", "§f Você terá 1% de chance de", "§f mandar um raio no seu inimigo!",
									"", "§bHabilidades da arma: ", "§7 Botão Direito:",
									"§f Joga vários raios no inimigo", "§f Joga o inimigo para o alto", "",
									"§7Shift: §fForça II por 3 minutos", "")
							.build());
				}
			} else {
				inv.setItem(13, new ItemBuilder(Skull.getSkull(
						"https://textures.minecraft.net/texture/d01afe973c5482fdc71e6aa10698833c79c437f21308ea9a1a095746ec274a0f"))
						.name("§bInformações de sua Linhagem")
						.lore("§r", "§7 Linhagem: §cNenhum", "§7 Poderes: §cNenhum", "").build());
			}
			p.openInventory(inv);
		}
		if (cmd.getName().equalsIgnoreCase("discord")) {
			final Player p = (Player) sender;
			p.sendMessage("");
			p.sendMessage("§f discord.gg/redwins");
			p.sendMessage("");
		}
		if (cmd.getName().equalsIgnoreCase("Lixeira")) {
			final Player p = (Player) sender;
			final Inventory lixeiragui = Bukkit.createInventory((InventoryHolder) null, 27, "§0Lixeira");
			final ItemStack lixeira = new ItemStack(Material.CAULDRON_ITEM, 1);
			final ItemMeta lixeirameta = lixeira.getItemMeta();
			lixeirameta.setDisplayName("§bAbrir lixeira");
			final ArrayList<String> lixeiralore = new ArrayList<String>();
			lixeiralore.add("§7Tudo o que voc\u00ea colocar na lixeira");
			lixeiralore.add("§7ser\u00e1 perdido ao fech\u00e1-la.");
			lixeirameta.setLore(lixeiralore);
			lixeira.setItemMeta(lixeirameta);
			final ItemStack limpar = new ItemStack(Material.BARRIER, 1);
			final ItemMeta limparmeta = limpar.getItemMeta();
			limparmeta.setDisplayName("§cLimpar Invent\u00e1rio");
			final ArrayList<String> limparlore = new ArrayList<String>();
			limparlore.add("§7Todo o seu invent\u00e1rio ser\u00e1 limpo");
			limparlore.add("§7e todos os itens ser\u00e3o perdidos.");
			limparmeta.setLore(limparlore);
			limpar.setItemMeta(limparmeta);
			lixeiragui.setItem(12, lixeira);
			lixeiragui.setItem(14, limpar);
			p.openInventory(lixeiragui);
		}
		return false;
	}

}
