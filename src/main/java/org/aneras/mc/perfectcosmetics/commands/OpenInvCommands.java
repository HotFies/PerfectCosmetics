package org.aneras.mc.perfectcosmetics.commands;

import org.aneras.mc.perfectcosmetics.PerfectCosmetics;
import org.aneras.mc.perfectcosmetics.config.PerfectCosmeticsConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenInvCommands implements CommandExecutor {
    private final PerfectCosmetics plugin;
    private static Map<Player, Inventory> HeadInvHash = new HashMap<>();
    private static Map<Player, Inventory> BodyInvHash = new HashMap<>();
    private static Map<Player, Inventory> OffHandInvHash = new HashMap<>();
    private static Map<Player, Inventory> TailInvHash = new HashMap<>();
    private static Map<Player, Inventory> PetsInvHash = new HashMap<>();
    public OpenInvCommands(PerfectCosmetics PerfectCosmetics, Map<Player, Inventory > Inv1, Map<Player, Inventory > Inv2, Map<Player, Inventory > Inv3, Map<Player, Inventory > Inv4, Map<Player, Inventory > Inv5){
        plugin = PerfectCosmetics;
        HeadInvHash = Inv1;
        BodyInvHash = Inv2;
        OffHandInvHash = Inv3;
        TailInvHash = Inv4;
        PetsInvHash = Inv5;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("perfectCosmetics")){
            if(args.length == 0){
                return false;
            }
            switch (args[0]){
                case "add":{
                    if(args.length == 1){
                        p.sendMessage(ChatColor.RED + "/perfectCosmetics add body %name% %description% %cmd%");
                        p.sendMessage(ChatColor.RED + "/perfectCosmetics add head %name% %description% %cmd%");
                        p.sendMessage(ChatColor.RED + "/perfectCosmetics add offhand %name% %description% %cmd%");
                        return true;
                    }
                    switch (args[1]){
                        case "head":{
                            if(args.length == 2){
                                p.sendMessage(ChatColor.RED + "/perfectCosmetics add head %name% %description% %cmd%");
                                return true;
                            }
                            ArrayList<String> Lore = new ArrayList<>();
                            List<ItemStack> Items = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Head");
                            ItemStack paper = new ItemStack(Material.POTION);
                            ItemMeta paperMeta = paper.getItemMeta();
                            Lore.add(args[3]);
                            paperMeta.setLore(Lore);
                            paperMeta.setDisplayName(args[2]);
                            paperMeta.setCustomModelData(Integer.parseInt(args[4]));
                            paper.setItemMeta(paperMeta);
                            for(ItemStack itemStack : Items){
                                if(itemStack.getItemMeta().hasCustomModelData()){
                                    if(itemStack.getItemMeta().getCustomModelData() == paperMeta.getCustomModelData()){
                                        p.sendMessage(ChatColor.RED + "This item already exists");
                                        return true;
                                    }
                                }
                            }
                            Items.add(paper);
                            Lore.clear();
                            PerfectCosmeticsConfig.get().set("Head", Items);
                            PerfectCosmeticsConfig.save();
                            PerfectCosmeticsConfig.reload();
                            p.sendMessage(ChatColor.GREEN + "Item was successful added");
                            break;
                        }
                        case "body":{
                            if(args.length == 2){
                                p.sendMessage(ChatColor.RED + "/perfectCosmetics add body %name% %description% %cmd%");
                                return true;
                            }
                            ArrayList<String> Lore = new ArrayList<>();
                            List<ItemStack> Items = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Body");
                            ItemStack paper = new ItemStack(Material.POTION);
                            ItemMeta paperMeta = paper.getItemMeta();
                            Lore.add(args[3]);
                            paperMeta.setLore(Lore);
                            paperMeta.setDisplayName(args[2]);
                            paperMeta.setCustomModelData(Integer.parseInt(args[4]));
                            paper.setItemMeta(paperMeta);
                            for(ItemStack itemStack : Items){
                                if(itemStack.getItemMeta().hasCustomModelData()){
                                    if(itemStack.getItemMeta().getCustomModelData() == paperMeta.getCustomModelData()){
                                        p.sendMessage(ChatColor.RED + "This item already exists");
                                        return true;
                                    }
                                }
                            }
                            Items.add(paper);
                            Lore.clear();
                            PerfectCosmeticsConfig.get().set("Body", Items);
                            PerfectCosmeticsConfig.save();
                            PerfectCosmeticsConfig.reload();
                            p.sendMessage(ChatColor.GREEN + "Item was successful added");
                            break;
                        }
                        case "offhand":{
                            if(args.length == 2){
                                p.sendMessage(ChatColor.RED + "/perfectCosmetics add offhand %name% %description% %cmd%");
                                return true;
                            }
                            ArrayList<String> Lore = new ArrayList<>();
                            List<ItemStack> Items = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("OffHand");
                            ItemStack paper = new ItemStack(Material.POTION);
                            ItemMeta paperMeta = paper.getItemMeta();
                            Lore.add(args[3]);
                            paperMeta.setLore(Lore);
                            paperMeta.setDisplayName(args[2]);
                            paperMeta.setCustomModelData(Integer.parseInt(args[4]));
                            paper.setItemMeta(paperMeta);
                            for(ItemStack itemStack : Items){
                                if(itemStack.getItemMeta().hasCustomModelData()){
                                    if(itemStack.getItemMeta().getCustomModelData() == paperMeta.getCustomModelData()){
                                        p.sendMessage(ChatColor.RED + "This item already exists");
                                        return true;
                                    }
                                }
                            }
                            Items.add(paper);
                            Lore.clear();
                            PerfectCosmeticsConfig.get().set("OffHand", Items);
                            PerfectCosmeticsConfig.save();
                            PerfectCosmeticsConfig.reload();
                            p.sendMessage(ChatColor.GREEN + "Item was successful added");
                            break;
                        }
                        case "tail":{
                            if(args.length == 2){
                                p.sendMessage(ChatColor.RED + "/perfectCosmetics add tail %name% %description% %cmd%");
                                return true;
                            }
                            ArrayList<String> Lore = new ArrayList<>();
                            List<ItemStack> Items = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Tail");
                            ItemStack paper = new ItemStack(Material.POTION);
                            ItemMeta paperMeta = paper.getItemMeta();
                            Lore.add(args[3]);
                            paperMeta.setLore(Lore);
                            paperMeta.setDisplayName(args[2]);
                            paperMeta.setCustomModelData(Integer.parseInt(args[4]));
                            paper.setItemMeta(paperMeta);
                            for(ItemStack itemStack : Items){
                                if(itemStack.getItemMeta().hasCustomModelData()){
                                    if(itemStack.getItemMeta().getCustomModelData() == paperMeta.getCustomModelData()){
                                        p.sendMessage(ChatColor.RED + "This item already exists");
                                        return true;
                                    }
                                }
                            }
                            Items.add(paper);
                            Lore.clear();
                            PerfectCosmeticsConfig.get().set("Tail", Items);
                            PerfectCosmeticsConfig.save();
                            PerfectCosmeticsConfig.reload();
                            p.sendMessage(ChatColor.GREEN + "Item was successful added");
                            break;
                        }
                        case "pets":{
                            if(args.length == 2){
                                p.sendMessage(ChatColor.RED + "/perfectCosmetics add pets %name% %description% %cmd%");
                                return true;
                            }
                            ArrayList<String> Lore = new ArrayList<>();
                            List<ItemStack> Items = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Pets");
                            ItemStack paper = new ItemStack(Material.POTION);
                            ItemMeta paperMeta = paper.getItemMeta();
                            Lore.add(args[3]);
                            paperMeta.setLore(Lore);
                            paperMeta.setDisplayName(args[2]);
                            paperMeta.setCustomModelData(Integer.parseInt(args[4]));
                            paper.setItemMeta(paperMeta);
                            for(ItemStack itemStack : Items){
                                if(itemStack.getItemMeta().hasCustomModelData()){
                                    if(itemStack.getItemMeta().getCustomModelData() == paperMeta.getCustomModelData()){
                                        p.sendMessage(ChatColor.RED + "This item already exists");
                                        return true;
                                    }
                                }
                            }
                            Items.add(paper);
                            Lore.clear();
                            PerfectCosmeticsConfig.get().set("Pets", Items);
                            PerfectCosmeticsConfig.save();
                            PerfectCosmeticsConfig.reload();
                            p.sendMessage(ChatColor.GREEN + "Item was successful added");
                            break;
                        }
                        default:{
                            p.sendMessage(ChatColor.RED + "/perfectCosmetics add body %name% %description% %cmd%");
                            p.sendMessage(ChatColor.RED + "/perfectCosmetics add head %name% %description% %cmd%");
                            p.sendMessage(ChatColor.RED + "/perfectCosmetics add offhand %name% %description% %cmd%");
                            p.sendMessage(ChatColor.RED + "/perfectCosmetics add tail %name% %description% %cmd%");
                            p.sendMessage(ChatColor.RED + "/perfectCosmetics add pets %name% %description% %cmd%");
                            return true;
                        }
                    }
                    break;
                }
                case "head":{
                    HeadInvHash.remove(p);
                    plugin.createHeadInv(p);
                    p.openInventory(HeadInvHash.get(p));
                    break;
                }
                case "body":{
                    BodyInvHash.remove(p);
                    plugin.createBodyInv(p);
                    p.openInventory(BodyInvHash.get(p));
                    break;
                }
                case "offhand":{
                    OffHandInvHash.remove(p);
                    plugin.createOffHandInv(p);
                    p.openInventory(OffHandInvHash.get(p));
                    break;
                }
                case "tail":{
                    TailInvHash.remove(p);
                    plugin.createTailInv(p);
                    p.openInventory(TailInvHash.get(p));
                    break;
                }
                case "pets":{
                    PetsInvHash.remove(p);
                    plugin.createPetsInv(p);
                    p.openInventory(PetsInvHash.get(p));
                    break;
                }
                default:{
                    p.sendMessage(ChatColor.RED + "/perfectCosmetics add body %name% %description% %cmd%");
                    p.sendMessage(ChatColor.RED + "/perfectCosmetics add head %name% %description% %cmd%");
                    p.sendMessage(ChatColor.RED + "/perfectCosmetics add offhand %name% %description% %cmd%");
                    return true;
                }
            }

        }
        return true;
    }
}
