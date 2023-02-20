package bd.pablo.redwinsutil;

import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.command.*;

public class Reparar implements CommandExecutor
{
    public static void Reparar(Player p) {
        Inventory menu = Bukkit.createInventory((InventoryHolder)null, 27, "§7Reparar ITENS");
        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1.0f, 2.0f);
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add("§f Preço: §b1M");
        lore.add("");
        lore.add(" §7Botão direito para reparar tudo");
        final ItemStack a = new ItemStack(Material.DIAMOND_CHESTPLATE);
        final ItemMeta a2 = a.getItemMeta();
        a2.setDisplayName("§bSim, quero reparar!");
        a2.setLore(lore);
        a.setItemMeta(a2);
        menu.setItem(13, a);
        p.openInventory(menu);
    }
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        final Player player = (Player)commandSender;
        Reparar(player);
        return false;
    }
}
