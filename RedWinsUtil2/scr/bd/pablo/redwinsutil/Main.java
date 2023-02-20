package bd.pablo.redwinsutil;

import dungeons.Eventos3;
import evento.InventoryMinesClick;
import evento.zombiecoinsCommand;
import evento.zombiecoinsEvents;
import java.io.File;
import java.text.DecimalFormat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import apis.ConfigAPI;

public class Main extends JavaPlugin implements Listener {

	public static Plugin plugin;

	public static ConfigAPI linhagem;

	public static ConfigAPI dracma;
	public static ConfigAPI tag;

	public static Economy econ;

	public static ConfigAPI zombiecoins;


	public Main() {
		instance = this;
	}

	private static String[] suffix = new String[0];

	static DecimalFormat decimalFormat = new DecimalFormat("#.##");


	private static Main instance;

	void registerEvents(Main rc) {
		rc.getServer().getPluginManager().registerEvents((Listener) new zombiecoinsEvents(), (Plugin) this);
		rc.getServer().getPluginManager().registerEvents(new Eventos2(), (Plugin) this);
	}

	void registerCommands(Main rc) {
		rc.getCommand("zombiecoins").setExecutor((CommandExecutor) new zombiecoinsCommand());
		rc.getCommand("dracma").setExecutor(new CranioCommand());
		rc.getCommand("mercadodeus").setExecutor(new CranioCommand());
		rc.getCommand("linhagem").setExecutor(new Comandos());
		rc.getCommand("trocartag").setExecutor(new Comandos());
		rc.getCommand("npclinhagem").setExecutor(new ComandosHD());
		rc.getCommand("npctartaro").setExecutor(new ComandosHD());
		rc.getCommand("npcdeus").setExecutor(new ComandosHD());
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null)
			return false;
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null)
			return false;
		econ = rsp.getProvider();
		return (econ != null);
	}

	public void clearAll() {
		for (World w : getServer().getWorlds()) {
			byte b;
			int i;
			Chunk[] arrayOfChunk;
			for (i = (arrayOfChunk = w.getLoadedChunks()).length, b = 0; b < i;) {
				Chunk c = arrayOfChunk[b];
				c.load();
				byte b1;
				int j;
				Entity[] arrayOfEntity;
				for (j = (arrayOfEntity = c.getEntities()).length, b1 = 0; b1 < j;) {
					Entity e = arrayOfEntity[b1];
					if (e.hasMetadata("guizin"))
						e.remove();
					b1++;
				}
				c.unload(true);
				b++;
			}
		}
	}
	
	public void onEnable() {
		plugin = this;
		dracma = new ConfigAPI("dracma.yml", this);
		dracma.saveConfig();
		
		tag = new ConfigAPI("tag.yml", this);
		tag.saveConfig();
		
		linhagem = new ConfigAPI("linhagem.yml");
		if (!(new File(getDataFolder(), "linhagem.yml")).exists())
			linhagem.saveDefaultConfig();
		saveDefaultConfig();
		setupEconomy();
		(zombiecoins = new ConfigAPI("zombiecoins.yml", this)).saveConfig();
		registerEvents(this);
		registerCommands(this);
		suffix = (String[]) getConfig().getStringList("CasasDecimais").toArray((Object[]) new String[0]);
		Bukkit.getPluginManager().registerEvents(new TicketsCommand(), (Plugin) this);
		Bukkit.getPluginManager().registerEvents(new Listeners(), (Plugin) this);
		Bukkit.getPluginManager().registerEvents((Listener) new bd.pablo.redwinsutil.Eventos(), (Plugin) this);
		getCommand("luz").setExecutor(new LanternCommand());
		getCommand("reparar").setExecutor(new Reparar());
		getCommand("tickets").setExecutor(new TicketsCommand());
		getCommand("ajuda").setExecutor(new HelpCommand());
		getServer().getPluginManager().registerEvents(new Eventos3(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new Event2(), (Plugin) this);
		getServer().getPluginManager().registerEvents(new ComandosHD(), (Plugin) this);
		Bukkit.getPluginManager().registerEvents((Listener) new InventoryMinesClick(), (Plugin) this);
		RegistrarComandos();
		worlds();
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
		getServer().getMessenger().registerOutgoingPluginChannel((Plugin) this, "BungeeCord");
	}


	public void RegistrarComandos() {
		getCommand("Lixeira").setExecutor(new Comandos());
		getCommand("discord").setExecutor(new Comandos());
		getCommand("npcspawn").setExecutor(new ComandosHD());
		getCommand("npcdracma").setExecutor(new ComandosHD());
		getCommand("recompensa").setExecutor(new HelpCommand());
	}
	public static String format(double value) {
		int index;
		for (index = 0; value / 1000.0D >= 1.0D;) {
			value /= 1000.0D;
			index++;
		}
		return String.format("%s %s", new Object[] { decimalFormat.format(value), suffix[index] });
	}

	@EventHandler
	public void evento(WorldLoadEvent e) {
		world(e.getWorld());
	}

	@EventHandler
	public void chuva(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	public void worlds() {
		for (World world : Bukkit.getWorlds())
			world(world);
	}

	public void world(World world) {
		world.setGameRuleValue("doDaylightCycle", "false");
		world.setTime(0L);
		world.setStorm(false);
	}

	public static Main getInstance() {
		return instance;
	}

}
