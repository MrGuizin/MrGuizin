package bd.pablo.redwinsutil;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.command.*;
import org.bukkit.potion.*;

public class LanternCommand implements CommandExecutor
{
    public static List<Player> lanterna;
    
    static {
        LanternCommand.lanterna = new ArrayList<Player>();
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (command.getName().equalsIgnoreCase("luz")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cApenas jogadores in-game!");
                return true;
            }
            final Player p = (Player)sender;
            if (args.length == 0) {
                if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    LanternCommand.lanterna.remove(p);
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    p.sendMessage("§cLanterna desativada!");
                    return true;
                }
                LanternCommand.lanterna.add(p);
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
                p.sendMessage("");
                p.sendMessage("§eLanterna ativada!");
                p.sendMessage("");
                p.sendMessage("§eSeu caminho agora ser\u00e1 iluminado quando ficar escuro.");
                p.sendMessage("");
                return true;
            }
            else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    if (!p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
                        p.sendMessage("");
                        p.sendMessage("§eLanterna ativada!");
                        p.sendMessage("");
                        p.sendMessage("§eSeu caminho agora ser\u00e1 iluminado quando ficar escuro.");
                        p.sendMessage("");
                        return true;
                    }
                    p.sendMessage("§cSua lanterna j\u00e1 est\u00e1 ativada!");
                    return true;
                }
                else if (args[0].equalsIgnoreCase("off")) {
                    if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        p.sendMessage("§cLanterna desativada!");
                        return true;
                    }
                    p.sendMessage("§cSua lanterna j\u00e1 est\u00e1 desativada.");
                    return true;
                }
            }
        }
        return false;
    }
}
