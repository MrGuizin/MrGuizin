package evento;

import org.bukkit.entity.*;

import apis.ConfigAPI;

public class CraniosManager
{
    public static void setCranios(final Player p, final ConfigAPI config, final Long quantia) {
        config.set(p.getName(), (Object)quantia);
        config.saveConfig();
    }
    
    public static Long getCranios(final Player p, final ConfigAPI config) {
        return config.getLong(p.getName());
    }
    
    public static void addCranios(final Player p, final ConfigAPI config, final Long coins) {
        config.set(p.getName(), (Object)(getCranios(p, config) + coins));
        config.saveConfig();
    }
    
    public static void removeCranios(final Player p, final ConfigAPI config, final Long quantia) {
        config.set(p.getName(), (Object)(getCranios(p, config) - quantia));
        config.saveConfig();
    }
    
    public static boolean command(final String[] args, final String cmd) {
        return args[0].equalsIgnoreCase(cmd);
    }
    
    public static boolean countArgs(final String[] args, final int number) {
        return args.length == number;
    }
}
