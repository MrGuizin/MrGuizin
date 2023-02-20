package evento;

import org.bukkit.entity.*;
import bd.pablo.redwinsutil.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

public class zombiecoinsCommand implements CommandExecutor
{
	
	public static void loja (Player p ) {
		Inventory inv = Bukkit.createInventory((InventoryHolder)null, 45, "§2Loja");
		inv.setItem(10, new ItemBuilder(new ItemStack(Material.TRIPWIRE_HOOK)).lore("§fClique SHIFT + ESQUERDO", "§fpara comprar 64x", "", " §fPreço: §21.300","").name("§fKey §LBOSS").build());
		inv.setItem(11, new ItemBuilder(new ItemStack(Material.TRIPWIRE_HOOK)).lore("§fClique SHIFT + ESQUERDO", "§fpara comprar 64x", "", " §fPreço: §23.000","").name("§cKey §lMISTERIOSA").build());
		inv.setItem(12, new ItemBuilder(new ItemStack(Material.TRIPWIRE_HOOK)).lore("§fClique SHIFT + ESQUERDO", "§fpara comprar 64x", "", " §fPreço: §23.500","").name("§6Key §lALL").build());
		inv.setItem(13, new ItemBuilder(new ItemStack(Material.TRIPWIRE_HOOK)).lore("§fClique SHIFT + ESQUERDO", "§fpara comprar 64x", "", " §fPreço: §280.000","").name("§bKey §lVIP").build());
		inv.setItem(15, new ItemBuilder(new ItemStack(Material.CHEST)).lore("§fClique SHIFT + ESQUERDO", "§fpara comprar 64x", "", " §fPreço: §215.000","","§7Clique com o DIREITO", "§7para ver as recompensas").name("§5Caixa §lBÁSICA").build());
		inv.setItem(16, new ItemBuilder(new ItemStack(Material.ENDER_CHEST)).lore("§fClique SHIFT + ESQUERDO", "§fpara comprar 64x", "", " §fPreço: §235.000","","§7Clique com o DIREITO", "§7para ver as recompensas").name("§5Caixa §lMÁQUINA BÁSICA").build());
		inv.setItem(28, new ItemBuilder(new ItemStack(Material.IRON_SWORD)).lore("", " §fPreço: §230.000","").name("§bLooting §l21").build());
		inv.setItem(29, new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD)).lore("", " §fPreço: §235.000","").name("§cs50").build());
		inv.setItem(30, new ItemBuilder(new ItemStack(Material.BOOK)).lore("", "§7Use este livro para escolher", "§7uma tag personalizada", "§7para colocar em cima do seu nick.", "", " §fPreço: §2200.000","").name("§b§LTAG §cem cima do §lNICK").build());
		inv.setItem(44, new ItemBuilder(new ItemStack(Material.ARROW)).lore("§7Voltar para página inicial","").name("§aVoltar").build());
		p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
		p.openInventory(inv);
	}
	public static void menu (Player p ) {
		Inventory inv = Bukkit.createInventory((InventoryHolder)null, 45, "§2Zombie Coins");
		double dinheiro = zombiecoinsManager.getPecados(p, Main.zombiecoins);
        inv.setItem(12, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5)).name("").build());
        inv.setItem(13, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5)).name("").build());
        inv.setItem(14, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5)).name("").build());
        inv.setItem(21, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5)).name("").build());
        inv.setItem(22, new ItemBuilder(new ItemStack(Material.getMaterial(397), 1, (short) 2)).lore("§fTroque seus §b§lZombieCoins", "§fpor zombiecoins virtual", "§fe compre itens especiais!", "", "§2Zombie Coins: §f§n" + Main.format(dinheiro).replace("C", ""),"", "§aClique para trocar").name("§2Zombie Coins Virtual").build());
        inv.setItem(23, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5)).name("").build());
        inv.setItem(30, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5)).name("").build());
        inv.setItem(31, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5)).name("").build());
        inv.setItem(32, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5)).name("").build());
        inv.setItem(28, new ItemBuilder(new ItemStack(Material.BARRIER)).lore("§fPara conseguir §bZombieCoins", "§fvocê precisa matar ZOMBIE", "§fusando uma espada com LOOTING", "", "§fO §bZombieCoins §fserá dropado", "§fno chão, você pode vender", "§fpara outros jogadores ou", "§ftransferir para §2Zombie Coins Virtual", "§f(clicando na cabeça de zombie)", "§fe gastar na loja de zombiecoins!", "").name("§aInformações").build());
        inv.setItem(34, new ItemBuilder(new ItemStack(Material.ITEM_FRAME)).lore("§fCompre itens especiais usando", "§fseus §2Zombie Coins Virtual", "").name("§aLoja").build());
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 2.5f, 5.0f);
        p.openInventory(inv);
	}
    void updatezombiecoins(final Player target, final String caractere, final long quantia) {
        if (caractere.equalsIgnoreCase("-")) {
            if (target != null) {
                zombiecoinsManager.removePecados(target, Main.zombiecoins, quantia);
            }
        }
        else {
            if (!caractere.equalsIgnoreCase("+")) {
                return;
            }
            if (target != null) {
                zombiecoinsManager.addPecados(target, Main.zombiecoins, quantia);
            }
        }
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String lb, final String[] args) {
        if (args.length < 3) {
            final Player p = (Player)sender;
			menu(p);
            return true;
        }
        if (args[0].equals("enviar")) {
            final Player target = Bukkit.getPlayerExact(args[1]);
            if (args.length < 3) {
                sender.sendMessage(ChatColor.RED + "Sintaxe icorreta, tente por: /zombiecoins enviar <nick> <quantidade>");
                return true;
            }
            if (target != null) {
                final Player p2 = (Player)sender;
                if (target.getName().equals(p2.getName())) {
                    sender.sendMessage(ChatColor.RED + "N\u00e3o tem como enviar zombiecoins a si mesmo, procure um psic\u00f3logo.");
                    return true;
                }
                final int quantidade = Integer.valueOf(args[2]);
                if (args[2].equals("NaN") || args[2].equals("null") || Integer.parseInt(args[2]) <= 0) {
                    return true;
                }
                try {
                    Integer.valueOf(args[2]);
                }
                catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Ops, me parece que voc\u00ea n\u00e3o inseriu um n\u00famero.");
                }
                if (zombiecoinsManager.getPecados(p2, Main.zombiecoins) >= quantidade) {
                    this.updatezombiecoins(p2, "-", quantidade);
                    this.updatezombiecoins(target, "+", quantidade);
                    sender.sendMessage("§aSucesso! Voc\u00ea enviou §f" + quantidade + " §azombiecoins ao jogador §f" + target.getName());
                    target.sendMessage("§aVoc\u00ea recebeu §f" + quantidade + " §azombiecoins do jogador §f" + sender.getName());
                }
                else {
                    sender.sendMessage("§cVoc\u00ea n\u00e3o tem zombiecoins o suficiente.");
                }
            }
            else {
                sender.sendMessage("§cPlayer offline ou n\u00e3o encontrado no banco de dados.");
            }
        }
        return false;
    }
}
