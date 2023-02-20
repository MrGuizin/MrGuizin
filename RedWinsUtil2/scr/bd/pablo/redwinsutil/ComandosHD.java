package bd.pablo.redwinsutil;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import apis.ActionBar;
import evento.CraniosManager;

import org.bukkit.event.player.*;
import inventorys.Skull;
import org.bukkit.*;
import org.bukkit.event.*;

public class ComandosHD implements CommandExecutor, Listener {
	public static String name;
	public static ArmorStand armorStand;

	public boolean onCommand(final CommandSender sender, final Command cmd, final String lb, final String[] args) {
		final Player p = (Player) sender;
		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("npcdeus")) {
			deuses(p);
		}
		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("npclinhagem")) {
			linhagem(p);
		}
		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("npctartaro")) {
			tartaro(p);
		}
		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("npcdracma")) {
			this.HologramaDracma(p);
		}
		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("npcspawn")) {
			if (args.length < 4) {
				p.sendMessage(
						"/npcspawn <nome> <useHead (sim ou n§o)> <nome da cabe> <rotation (sim ou n§o)> <comando>");
				return true;
			}
			ComandosHD.name = args[0].replace("_", " ").replace("&", "§");
			final String useHead = args[1];
			final String nameOfHead = args[2];
			final String rotation = args[3];
			boolean head = false;
			boolean ortation = false;
			if (ComandosHD.name != null) {
				if (useHead.equalsIgnoreCase("sim")) {
					head = true;
				}
				if (head) {
					if (nameOfHead != null) {
						if (rotation.equalsIgnoreCase("sim")) {
							ortation = true;
						} else if (rotation.equalsIgnoreCase("não")) {
							ortation = false;
						}
						if (ortation) {
							this.createHologram(ComandosHD.name, p, true, nameOfHead, true);
						} else {
							this.createHologram(ComandosHD.name, p, true, nameOfHead, false);
						}
					}
				} else if (nameOfHead != null) {
					if (useHead.equalsIgnoreCase("sim")) {
						ortation = true;
					} else if (useHead.equalsIgnoreCase("não")) {
						ortation = false;
					}
					if (ortation) {
						this.createHologram(ComandosHD.name, p, true, nameOfHead, true);
					} else {
						this.createHologram(ComandosHD.name, p, false, nameOfHead, true);
					}
				}
			}
		}
		return false;
	}

	public void tartaro(final Player playerSender) {
		ComandosHD.armorStand = (ArmorStand) playerSender.getWorld().spawnEntity(playerSender.getLocation(),
				EntityType.ARMOR_STAND);
		final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skull.setItemMeta((ItemMeta) skullMeta);
		ComandosHD.armorStand.setCustomName(ChatColor.DARK_AQUA + "");
		ComandosHD.armorStand.setCustomNameVisible(true);
		ComandosHD.armorStand.setHelmet(Skull.getSkull(
				"https://textures.minecraft.net/texture/afb8beb9ca85d63785b10501900e8da7b8572870697f34639c448064e5508f58"));
		ComandosHD.armorStand.setBasePlate(false);
		ComandosHD.armorStand.setSmall(false);
		ItemStack peito = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack calca = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack bota = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta meta = (LeatherArmorMeta) peito.getItemMeta();
		LeatherArmorMeta meta2 = (LeatherArmorMeta) calca.getItemMeta();
		LeatherArmorMeta meta3 = (LeatherArmorMeta) bota.getItemMeta();
		meta.setColor(Color.BLACK);
		peito.setItemMeta(meta);
		meta2.setColor(Color.WHITE);
		calca.setItemMeta(meta2);
		meta3.setColor(Color.BLACK);
		bota.setItemMeta(meta3);
		ComandosHD.armorStand.setChestplate(peito);
		ComandosHD.armorStand.setLeggings(calca);
		ComandosHD.armorStand.setBoots(bota);
		ComandosHD.armorStand.setItemInHand(new ItemStack(Material.IRON_SWORD));
		ComandosHD.armorStand.setVisible(true);
	}

	public void deuses(final Player playerSender) {
		ComandosHD.armorStand = (ArmorStand) playerSender.getWorld().spawnEntity(playerSender.getLocation(),
				EntityType.ARMOR_STAND);
		final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skull.setItemMeta((ItemMeta) skullMeta);
		ComandosHD.armorStand.setCustomName(ChatColor.GOLD + "");
		ComandosHD.armorStand.setCustomNameVisible(true);
		ComandosHD.armorStand.setHelmet(Skull.getSkull(
				"https://textures.minecraft.net/texture/6e2911fd73d2ee2ada48f8e294c22b12435e25bd1c82b398e875980dcbd4f993"));
		ComandosHD.armorStand.setBasePlate(false);
		ComandosHD.armorStand.setSmall(false);
		ItemStack peito = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack calca = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack bota = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta meta = (LeatherArmorMeta) peito.getItemMeta();
		LeatherArmorMeta meta2 = (LeatherArmorMeta) calca.getItemMeta();
		LeatherArmorMeta meta3 = (LeatherArmorMeta) bota.getItemMeta();
		meta.setColor(Color.YELLOW);
		peito.setItemMeta(meta);
		meta2.setColor(Color.YELLOW);
		calca.setItemMeta(meta2);
		meta3.setColor(Color.YELLOW);
		bota.setItemMeta(meta3);
		ComandosHD.armorStand.setChestplate(peito);
		ComandosHD.armorStand.setLeggings(calca);
		ComandosHD.armorStand.setBoots(bota);
		ComandosHD.armorStand.setItemInHand(new ItemStack(Material.TRIPWIRE_HOOK));
		ComandosHD.armorStand.setVisible(true);
	}

	public void linhagem(final Player playerSender) {
		ComandosHD.armorStand = (ArmorStand) playerSender.getWorld().spawnEntity(playerSender.getLocation(),
				EntityType.ARMOR_STAND);
		final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skull.setItemMeta((ItemMeta) skullMeta);
		ComandosHD.armorStand.setCustomName(ChatColor.AQUA + "");
		ComandosHD.armorStand.setCustomNameVisible(true);
		ComandosHD.armorStand.setHelmet(Skull.getSkull(
				"https://textures.minecraft.net/texture/edf0d1bcc6cb4c1cfa5fddb89053ddaada8bf31a2b37e30a571696eba83d7c82"));
		ComandosHD.armorStand.setBasePlate(false);
		ComandosHD.armorStand.setSmall(false);
		ItemStack peito = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack calca = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack bota = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta meta = (LeatherArmorMeta) peito.getItemMeta();
		LeatherArmorMeta meta2 = (LeatherArmorMeta) calca.getItemMeta();
		LeatherArmorMeta meta3 = (LeatherArmorMeta) bota.getItemMeta();
		meta.setColor(Color.AQUA);
		peito.setItemMeta(meta);
		meta2.setColor(Color.AQUA);
		calca.setItemMeta(meta2);
		meta3.setColor(Color.AQUA);
		bota.setItemMeta(meta3);
		ComandosHD.armorStand.setChestplate(peito);
		ComandosHD.armorStand.setLeggings(calca);
		ComandosHD.armorStand.setBoots(bota);
		ComandosHD.armorStand.setItemInHand(new ItemStack(Material.WOOD_PICKAXE));
		ComandosHD.armorStand.setVisible(true);
	}

	public void createHologram(final String name, final Player playerSender, final boolean useHead,
			final String nameOfHead, final boolean rotation) {
		ComandosHD.armorStand = (ArmorStand) playerSender.getWorld().spawnEntity(playerSender.getLocation(),
				EntityType.ARMOR_STAND);
		final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		if (useHead) {
			skullMeta.setOwner(nameOfHead);
		}
		skull.setItemMeta((ItemMeta) skullMeta);
		ComandosHD.armorStand.setCustomName(name);
		ComandosHD.armorStand.setCustomNameVisible(true);
		ComandosHD.armorStand.setHelmet(skull);
		ComandosHD.armorStand.setBasePlate(false);
		ComandosHD.armorStand.setSmall(true);
		ComandosHD.armorStand.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
		ComandosHD.armorStand.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
		ComandosHD.armorStand.setBoots(new ItemStack(Material.LEATHER_BOOTS));
		ComandosHD.armorStand.setItemInHand(new ItemStack(Material.WOOD_PICKAXE));
		ComandosHD.armorStand.setVisible(true);
	}

	public void HologramaDracma(final Player playerSender) {
		ComandosHD.armorStand = (ArmorStand) playerSender.getWorld().spawnEntity(playerSender.getLocation(),
				EntityType.ARMOR_STAND);
		final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		final SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skull.setItemMeta((ItemMeta) skullMeta);
		ComandosHD.armorStand.setCustomName("§bMerc\u00fario §7(Deus da Venda)");
		ComandosHD.armorStand.setCustomNameVisible(true);
		ComandosHD.armorStand.setHelmet(Skull.getSkull(
				"https://textures.minecraft.net/texture/e5e79d71a7e785708441f8fa956157722eeeb4f890d779982053df1dd3c84353"));
		ComandosHD.armorStand.setBasePlate(false);
		ComandosHD.armorStand.setSmall(false);
		ComandosHD.armorStand.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		ComandosHD.armorStand.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
		ComandosHD.armorStand.setVisible(false);
		ComandosHD.armorStand.setGravity(false);

	}

	@EventHandler
	public void onRight(PlayerInteractAtEntityEvent e) {
		if (e.getRightClicked() instanceof ArmorStand) {

			Player player = e.getPlayer();
			if (player.hasPermission("redwins.admin") && player.isOp() && player.getItemInHand() != null
					&& player.getItemInHand().getType() == Material.STICK) {
				e.getRightClicked().remove();
				return;
			}

			ArmorStand armorStand = (ArmorStand) e.getRightClicked();

			if (armorStand.getCustomName() == null)
				return;
			switch (armorStand.getCustomName()) {
			case "§bZOMBIECOINS":
				player.performCommand("zombiecoins");
				break;
			case "§bMerc\u00fario §7(Deus da Venda)":
				long amount = 0L;
				for (int i = 0; i < player.getInventory().getSize(); i++) {
				        ItemStack item = player.getInventory().getItem(i);
				        if(item == null||item.getType()==Material.AIR||item.getAmount()<=0||!item.hasItemMeta()||!item.getItemMeta().hasDisplayName()||!item.getItemMeta().hasLore())continue;
				        if(item.getItemMeta().getDisplayName().equals("§bFragmento de Dracma")){
				            amount+=item.getAmount();
				            player.getInventory().setItem(i,null);
				        }
				        if(item.getItemMeta().getLore().contains(" §fLevel: §a2"))amount+=item.getAmount() * 800L;
				        if(item.getItemMeta().getLore().contains(" §fLevel: §a1"))amount+=item.getAmount() * 200L;
				}
				if(amount>0){
				    CraniosManager.addCranios(player, Main.dracma, amount);
				    ActionBar.sendActionbar(player,"§fVoc\u00ea vendeu §a" + amount + " §bFragmentos de Dracma §fpara o Deus da Venda");
				}
				break;
			case "§3":
				player.performCommand("bosses");
				break;
			case "§b":
				player.performCommand("linhagem");
				break;
			case "§6RECOMPENSAS":
				player.performCommand("recompensa");
				break;
			case "§6":
				player.performCommand("mercadodeus");
				break;
			default:
				return;
			}
			e.setCancelled(true);
			player.playSound(player.getEyeLocation(), Sound.NOTE_PIANO, 1F, 2F);
		}
	}

}
