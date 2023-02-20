package bd.pablo.redwinsutil;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class ScrollerInventory
{
    public ArrayList<Inventory> pages;
    public UUID id;
    public int currpage;
    public int atual;
    public static HashMap<UUID, ScrollerInventory> users;
    
    static {
        ScrollerInventory.users = new HashMap<UUID, ScrollerInventory>();
    }
    
    public ScrollerInventory(final ArrayList<ItemStack> items, final String name, final Player p) {
        this.pages = new ArrayList<Inventory>();
        this.currpage = 0;
        this.atual = 1;
        this.id = UUID.randomUUID();
        Inventory page = this.getBlankPage(name);
        ++this.atual;
        int slot = 0;
        for (int a = -1, b = 0; b < 1000 && a + 1 != items.size(); ++b) {
            if (slot == 35) {
                this.pages.add(page);
                page = this.getBlankPage(name);
                ++this.atual;
                final int i;
                slot = (i = 0);
                if (i >= 9 && i != 17 && i != 26 && i != 35 && i != 44 && i != 53) {
                    if (i % 9 != 0) {
                        ++a;
                        page.setItem(slot, (ItemStack)items.get(a));
                    }
                }
            }
            else {
                final int i = ++slot;
                if (i >= 9 && i != 17 && i != 26 && i != 35 && i != 44 && i != 53) {
                    if (i % 9 != 0) {
                        ++a;
                        page.setItem(slot, (ItemStack)items.get(a));
                    }
                }
            }
        }
        this.pages.add(page);
        p.openInventory((Inventory)this.pages.get(this.currpage));
        ScrollerInventory.users.put(p.getUniqueId(), this);
    }
    
    private Inventory getBlankPage(final String name) {
        final Inventory page = Bukkit.createInventory((InventoryHolder)null, 54, name);
        final ItemStack nextpage = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = nextpage.getItemMeta();
        meta.setDisplayName("§ePagina " + (this.atual + 1));
        nextpage.setItemMeta(meta);
        final ItemStack prevpage = new ItemStack(Material.ARROW, 1);
        meta = prevpage.getItemMeta();
        meta.setDisplayName("§ePagina " + (this.atual - 1));
        prevpage.setItemMeta(meta);
        final ItemStack back = new ItemStack(Material.ARROW);
        final ItemMeta meta2 = back.getItemMeta();
        meta2.setDisplayName("§eVoltar");
        back.setItemMeta(meta2);
        page.setItem(49, back);
        page.setItem(26, nextpage);
        if (this.atual != 1) {
            page.setItem(18, prevpage);
        }
        return page;
    }
}
