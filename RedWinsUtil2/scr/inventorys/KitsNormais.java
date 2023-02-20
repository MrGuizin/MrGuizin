package inventorys;

import org.bukkit.inventory.*;
import org.bukkit.*;

public class KitsNormais
{
    public static Inventory getInventory() {
        final Inventory normais = Bukkit.createInventory((InventoryHolder)null, 54, "Kits Normais");
        normais.setItem(12, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§eMinerador").lore("§fTempo: §71 Dia", "§fRaridade: §7Comum", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/d357444ade64ec6cea645ec57e775864d67c5fa62299786e03799317ee4ad").build());
        normais.setItem(13, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§dFestivo").lore("§fTipo: §cFérias", "§fRaridade: §4Lendária", "", "§7Kit férias disponivel.").head("http://textures.minecraft.net/texture/75419fce506a495343a1d368a71d22413f08c6d67cb951d656cd03f80b4d3d3").build());
        normais.setItem(14, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§cPvP").lore("§fTempo: §73 Minutos", "§fRaridade: §7Comum", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/f7c17865c27934f8c8ec6c627e1fe2d99f783ec8ae414ca2d4fd3640a7f3c").build());
        normais.setItem(19, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aAfrodite").lore("§fTempo: §73 Dias", "§fGrupo: §aAfrodite", "§fRaridade: §7Comum", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/bd97c05184637d4c41f66302260ab8d2d58d8bf0ed2d0c7c9a767c34c6da95f4").build());
        normais.setItem(20, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aApolo").lore("§fTempo: §73 Dias", "§fGrupo: §aApolo", "§fRaridade: §7Comum", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/c4ac5c50923737753ea8b630a42f89f5d98c1724482bc3ffc14f9ed1fbfeea").build());
        normais.setItem(21, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aAres").lore("§fTempo: §73 Dias", "§fGrupo: §aAres", "§fRaridade: §7Comum", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/81fdce4aefe86a2c7da74abafcdc346146340e8fa169af1581e2f8ae2dfeed5").build());
        normais.setItem(22, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aArtemis").lore("§fTempo: §73 Dias", "§fGrupo: §aArtemis", "§fRaridade: §8Incomum", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/e93ee5bb0c7facca0f3dfe09430c5b84a90e6588d0a1099da85b6eaeb8958f9a").build());
        normais.setItem(23, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aDemeter").lore("§fTempo: §73 Dias", "§fGrupo: §aDemeter", "§fRaridade: §8Incomum", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/a2dbd9e3b89135cbdadac67052c6580b15c9e074a4310129fedc92daa88a03df").build());
        normais.setItem(24, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aDionisio").lore("§fTempo: §73 Dias", "§fGrupo: §aDionisio", "§fRaridade: §8Incomum", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/225f51d47a2b6c8b5a0224532a55c93804c5b5746999a85950c582a82f1105e6").build());
        normais.setItem(25, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aHades").lore("§fTempo: §73 Dias", "§fGrupo: §aHades", "§fRaridade: §bRaro", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/982b62734a99bda666917b67f9ead5ec7666deddc3938e9ebb0925a808ef791").build());
        normais.setItem(28, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aHefesto").lore("§fTempo: §73 Dias", "§fGrupo: §aHefesto", "§fRaridade: §bRaro", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/48a5c334d7948ad9a6e85e3d47a103e877ef0978d8cd93ec0edaf49cc581b74").build());
        normais.setItem(29, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aHera").lore("§fTempo: §73 Dias", "§fGrupo: §aHera", "§fRaridade: §bRaro", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/a3805785fec1d3747ea26fa85be93049e7c4df498ab2e5394ae6a38ccb1c40dd").build());
        normais.setItem(30, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aHestia").lore("§fTempo: §73 Dias", "§fGrupo: §aHestia", "§fRaridade: §d\u00c9pico", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/7aa2748a22539bc351dee7b7f8ba495c7568bc9cfb6ffd2f9f3ae6e9aa56d29").build());
        normais.setItem(31, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aPoseidon").lore("§fTempo: §73 Dias", "§fGrupo: §aPoseidon", "§fRaridade: §d\u00c9pico", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/60354b079cea00f11a28e7c21ba9c13a17cccb06056019cb4c64bc243575529a").build());
        normais.setItem(32, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aSelene").lore("§fTempo: §73 Dias", "§fGrupo: §aSelene", "§fRaridade: §d\u00c9pico", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/6827aa9b23482e35cc7e6157caa8b662ec93545176e9e25bbc4a42805dbd").build());
        normais.setItem(33, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aTemis").lore("§fTempo: §73 Dias", "§fGrupo: §aTemis", "§fRaridade: §6Lend\u00e1rio", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/30b4b5417363736a64ecbad82197e37de831b1aaffdb4aaf15c1c6ddd6a5e").build());
        normais.setItem(34, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).name("§aZeus").lore("§fTempo: §73 Dias", "§fGrupo: §aZeus", "§fRaridade: §6Lend\u00e1rio", "", "§7Bot\u00e3o Direito: §fVizualizar", "§7Bot\u00e3o Esquerdo: §fColetar").head("http://textures.minecraft.net/texture/dcd9ddf4fb9e25e62d2e98595d5168de2b3367ba78f3697be1c479f35102ad76").build());
        normais.setItem(49, new ItemBuilder(Material.HOPPER).name("§aVoltar").build());
        return normais;
    }
}
