package bd.pablo.redwinsutil;

import org.bukkit.entity.*;
import evento.*;

import java.text.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.*;
import org.bukkit.command.*;

public class CranioCommand implements CommandExecutor
{    
    
    public String money(final Player p) {
        final double money = CraniosManager.getCranios(p, Main.dracma);
        final long monao = CraniosManager.getCranios(p, Main.dracma);
        if (money >= 1.0E9) {
            return new DecimalFormat("0.00 Bilh\u00f5es").format(money * 1.0 / 1.0E8);
        }
        if (money >= 1000000.0) {
            return new DecimalFormat("0.00 Milh\u00f5es").format(money * 1.0 / 1000000.0);
        }
        if (money >= 1000.0) {
            return new DecimalFormat("0.00 MIL").format(money * 1.0 / 1000.0);
        }
        if (money >= 1.0) {
            return String.valueOf(String.valueOf(monao)) + ".0";
        }
        return "0";
    }
    
    public void mercado(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 36, "§7Armas dos Deuses");
        inv.setItem(19, new ItemBuilder(new ItemStack(Material.TRIPWIRE_HOOK)).name("§2Chave BOSS").lore("§r", "§7Usado para abrir caixa na /key", "§7Pre\u00e7o: §b150K Dracma §l\u262b", "§r").build());
         ItemStack zumbi = new ItemStack(Material.DIAMOND_SWORD);
         ItemStack zumbi2 = new ItemStack(Material.DIAMOND_SPADE);
         ItemStack zumbi3 = new ItemStack(Material.DIAMOND_PICKAXE);
         ItemStack zumbi4 = new ItemStack(Material.DIAMOND_AXE);
         ItemStack boost = new ItemStack(Material.EXP_BOTTLE);
         ItemStack dracma = new ItemStack(Material.ARROW);
        inv.setItem(4, new ItemBuilder(dracma).name("§bDracma's").lore("§r", " §7Seus dracma's: §b" + Main.format(CraniosManager.getCranios(p, Main.dracma))).build());
        inv.setItem(21, new ItemBuilder(zumbi).name("§fL\u00e2mina do Olimpo").lore("§r", "§7Pre\u00e7o: §b20 Milh\u00f5es §7Dracma §l\u262b", "§r").enchant(Enchantment.DAMAGE_ALL, 20).enchant(Enchantment.DURABILITY, 20).enchant(Enchantment.FIRE_ASPECT, 20).enchant(Enchantment.LOOT_BONUS_MOBS, 21).build());
        inv.setItem(22, new ItemBuilder(zumbi2).name("§fP\u00e1 Divina").lore("§r", "§7Pre\u00e7o: §b20 Milh\u00f5es §7Dracma §l\u262b", "§r").enchant(Enchantment.DIG_SPEED, 60).enchant(Enchantment.DURABILITY, 60).build());
        inv.setItem(23, new ItemBuilder(zumbi3).name("§fPicareta Divina").lore("§r", "§7Pre\u00e7o: §b80 Milh\u00f5es §7Dracma §l\u262b", "§r").enchant(Enchantment.DIG_SPEED, 60).enchant(Enchantment.LOOT_BONUS_BLOCKS, 60).enchant(Enchantment.DURABILITY, 60).build());
        inv.setItem(24, new ItemBuilder(zumbi4).name("§fMachado Divino").lore("§r", "§7Pre\u00e7o: §b50 Milh\u00f5es §7Dracma §l\u262b", "§r").enchant(Enchantment.DAMAGE_ALL, 50).enchant(Enchantment.DIG_SPEED, 20).enchant(Enchantment.DURABILITY, 20).enchant(Enchantment.FIRE_ASPECT, 20).build());
        inv.setItem(9, new ItemBuilder(boost).name("§eBooster de Spawners").lore("§r", "§f Multiplicador: §72x", "§f Duração: §71m", "","§fPreço: §b1 Milhão", "§r").build());
        inv.setItem(10, new ItemBuilder(boost).name("§eBooster de Spawners").lore("§r", "§f Multiplicador: §72x", "§f Duração: §710m", "","§fPreço: §b2 Milhão", "§r").build());
        inv.setItem(11, new ItemBuilder(boost).name("§eBooster de Spawners").lore("§r", "§f Multiplicador: §72x", "§f Duração: §730m", "","§fPreço: §b3 Milhão", "§r").build());
        p.openInventory(inv);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String lb, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("mercadodeus")) {
            final Player p = (Player)sender;
            this.mercado(p);
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("dracma")) {
            if (!sender.hasPermission("redwins.cranios")) {
                if (args.length == 0) {
                    final Player p = (Player)sender;
                    p.sendMessage(" §eVoc\u00ea tem §f" + this.money(p) + "§e dracma §l\u262b");
                    return true;
                }
                if (args.length == 1) {
                	Player p = (Player)sender;
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						p.sendMessage(" §eO jogador §f" + target.getName() + " §etem §f" + this.money(target) + "§e dracma §l\u262b");
					} else {
						sender.sendMessage(ChatColor.BLUE + " Jogador não está online");
					}
				} 
                if (args[0].equalsIgnoreCase("enviar")) {
                    if (args.length < 3) {
                        sender.sendMessage(ChatColor.RED + "Sintaxe icorreta, tente por: /dracma enviar <nick> <quantidade>");
                        return true;
                    }
                    final Player target = Bukkit.getPlayerExact(args[1]);
                    if (target != null) {
                        final Player p2 = (Player)sender;
                        if (target.getName().equals(p2.getName())) {
                            sender.sendMessage(ChatColor.RED + "N\u00e3o tem como enviar dracma a si mesmo, procure um psic\u00f3logo.");
                            return true;
                        }
                        final int quantidade = Integer.valueOf(args[2]);
                        if (args[2].equals("NaN") || args[2].equals("null") || Double.parseDouble(args[2]) <= 0) {
                            return true;
                        }
                        try {
                        	Double.valueOf(args[2]);
                        }
                        catch (NumberFormatException e) {
                            sender.sendMessage(ChatColor.RED + "Ops, me parece que voc\u00ea n\u00e3o inseriu um n\u00famero.");
                        }
                        if (CraniosManager.getCranios(p2, Main.dracma) >= quantidade) {
                            CraniosManager.removeCranios(p2, Main.dracma, (long)quantidade);
                            sender.sendMessage("§aSucesso! Voc\u00ea enviou §f" + quantidade + " §adracma ao jogador §f" + target.getName());
                            CraniosManager.addCranios(target, Main.dracma, (long)quantidade);
                            target.sendMessage("§aVoc\u00ea recebeu §f" + quantidade + " §adracma do jogador §f" + sender.getName());
                        }
                        else {
                            sender.sendMessage("§cVoc\u00ea n\u00e3o tem dracma o suficiente.");
                        }
                    }
                    else {
                        sender.sendMessage("§cPlayer offline ou n\u00e3o encontrado no banco de dados.");
                    }
                }
                
            }
            if (sender.hasPermission("redwins.cranios")) {
            	if (args.length == 1) {
                	Player p = (Player)sender;
					Player target = Bukkit.getPlayer(args[0]);
					if (target != null) {
						p.sendMessage(" §eO jogador §f" + target.getName() + " §etem §f" + this.money(target) + "§e dracma §l\u262b");
					} else {
						sender.sendMessage(ChatColor.BLUE + " Jogador não está online");
					}
				} 
                if (args.length == 0) {
                    final Player p = (Player)sender;
                    p.sendMessage(" §eVoc\u00ea tem §f" + this.money(p) + "§e dracma §l\u262b");
                    return true;
                }
                if (args[0].equalsIgnoreCase("add")) {
                    if (args.length < 3) {
                        sender.sendMessage("§4Uso correto: /cranios add <player> <quantia>");
                        return true;
                    }
                    final Player target = Bukkit.getPlayerExact(args[1]);
                    if (target == null) {
                        sender.sendMessage("§cPlayer offline ou n\u00e3o encontrado no banco de dados.");
                        return true;
                    }
                    try {
                        Integer.valueOf(args[2]);
                    }
                    catch (Exception e2) {
                        final Player p3 = (Player)sender;
                        p3.sendMessage("§cVoc\u00ea n\u00e3o inseriu um numero natural.");
                        return true;
                    }
                    final Long coins = (long)Double.parseDouble(args[2]);
                    sender.sendMessage("§aVoc\u00ea adicionou §f" + coins + " §ade dracma para §f" + target.getName() + "§a.");
                    CraniosManager.addCranios(target, Main.dracma, coins);
                }
                if (args[0].equalsIgnoreCase("set")) {
                    if (args.length < 2) {
                        sender.sendMessage("§4Uso correto: /dracma set <player> <quantia>");
                        return true;
                    }
                    final Player target = Bukkit.getPlayerExact(args[1]);
                    if (target == null) {
                        sender.sendMessage("§cO jogador n\u00e3o est\u00e1 online, ou n\u00e3o existe");
                        return true;
                    }
                    try {
                        Integer.valueOf(args[2]);
                    }
                    catch (Exception e2) {
                        final Player p3 = (Player)sender;
                        p3.sendMessage("§cVoc\u00ea n\u00e3o inseriu um numero natural.");
                        return true;
                    }
                    final Long coins = (long)Double.parseDouble(args[2]);
                    final Player p3 = (Player)sender;
                    p3.sendMessage("§aVoc\u00ea setou os dracma de §f" + target.getName() + " §apara §f" + coins + "§a.");
                    CraniosManager.setCranios(target, Main.dracma, coins);
                }
            }
        }
        return false;
    }
}
