package org.aneras.mc.perfectcosmetics;

import org.aneras.mc.perfectcosmetics.MySQL.Data;
import org.aneras.mc.perfectcosmetics.MySQL.MySQL;
import org.aneras.mc.perfectcosmetics.commands.OpenInvCommands;
import org.aneras.mc.perfectcosmetics.config.MySQLConfig;
import org.aneras.mc.perfectcosmetics.config.PerfectCosmeticsConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.*;

public final class PerfectCosmetics extends JavaPlugin {
    public MySQL SQL;
    public Data data;
    private static final Map<Player, Inventory> HeadInvHash = new HashMap<>();
    private static final Map<Player, Inventory> BodyInvHash = new HashMap<>();
    private static final Map<Player, Inventory> OffHandInvHash = new HashMap<>();
    private static final Map<Player, Inventory> TailInvHash = new HashMap<>();
    private static final Map<Player, Inventory> PetsInvHash = new HashMap<>();
    @Override
    public void onEnable() {

        List<ItemStack> Head = new ArrayList<>();
        List<ItemStack> Body = new ArrayList<>();
        List<ItemStack> OffHand = new ArrayList<>();
        List<ItemStack> Tail = new ArrayList<>();
        List<ItemStack> Pets = new ArrayList<>();
        PerfectCosmeticsConfig.setup();
        PerfectCosmeticsConfig.get().addDefault("NameOfHeadInventory", "Head Cosmetics");
        PerfectCosmeticsConfig.get().addDefault("NameOfBodyInventory", "Body Cosmetics");
        PerfectCosmeticsConfig.get().addDefault("NameOfOffHandInventory", "OffHand Cosmetics");
        PerfectCosmeticsConfig.get().addDefault("NameOfTailInventory", "Tail Cosmetics");
        PerfectCosmeticsConfig.get().addDefault("NameOfPetsInventory", "Pets Cosmetics");
        PerfectCosmeticsConfig.get().addDefault("Head", Head);
        PerfectCosmeticsConfig.get().addDefault("Body", Body);
        PerfectCosmeticsConfig.get().addDefault("OffHand", OffHand);
        PerfectCosmeticsConfig.get().addDefault("Tail", Tail);
        PerfectCosmeticsConfig.get().addDefault("Pets", Pets);
        PerfectCosmeticsConfig.get().options().copyDefaults(true);
        PerfectCosmeticsConfig.save();

        MySQLConfig.setup();
        MySQLConfig.get().addDefault("Storage", "MySQL");
        MySQLConfig.get().addDefault("host", "localhost");
        MySQLConfig.get().addDefault("port", "3306");
        MySQLConfig.get().addDefault("database", "Default");
        MySQLConfig.get().addDefault("username", "Default");
        MySQLConfig.get().addDefault("password", "Default");
        MySQLConfig.get().addDefault("Lobby", true);
        MySQLConfig.get().options().copyDefaults(true);
        MySQLConfig.save();

        this.SQL = new MySQL();
        this.data = new Data(this);
        if(Objects.requireNonNull(MySQLConfig.get().get("Storage")).equals("MySQL")) {
            try {
                SQL.Connect();
            } catch (ClassNotFoundException | SQLException e) {
                //e.printStackTrace();
                Bukkit.getLogger().info(ChatColor.RED + "Database is not connected");
            }
            if (SQL.isConnected()) {
                Bukkit.getLogger().info(ChatColor.GREEN + "Database is connected");
                data.createTable();

            }
        }
        Bukkit.getPluginCommand("perfectCosmetics").setExecutor(new OpenInvCommands(this, HeadInvHash, BodyInvHash, OffHandInvHash, TailInvHash, PetsInvHash));
        Bukkit.getPluginManager().registerEvents(new Events(this, HeadInvHash, BodyInvHash, OffHandInvHash, TailInvHash, PetsInvHash), this);
        Bukkit.getConsoleSender().sendMessage("[PerfectCosmetics] Plugin was enabled");

    }

    @Override
    public void onDisable() {
        if (SQL.isConnected()) {
            Bukkit.getLogger().info(ChatColor.RED + "Database disconnected");
            SQL.Disconnect();
        }
        Bukkit.getConsoleSender().sendMessage("[PerfectCosmetics] Plugin was disabled");
    }

    public void createHeadInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', PerfectCosmeticsConfig.get().getString("NameOfHeadInventory")));
        List<ItemStack> add = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Head");
        for(ItemStack itemStack : add){
            inv.addItem(itemStack);
        }
        HeadInvHash.put(p, inv);
    }
    public void createBodyInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', PerfectCosmeticsConfig.get().getString("NameOfBodyInventory")));
        List<ItemStack> add = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Body");
        for(ItemStack itemStack : add){
            inv.addItem(itemStack);
        }
        BodyInvHash.put(p, inv);
    }
    public void createOffHandInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', PerfectCosmeticsConfig.get().getString("NameOfOffHandInventory")));
        List<ItemStack> add = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("OffHand");
        for(ItemStack itemStack : add){
            inv.addItem(itemStack);
        }
        OffHandInvHash.put(p, inv);
    }
    public void createTailInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', PerfectCosmeticsConfig.get().getString("NameOfTailInventory")));
        List<ItemStack> add = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Tail");
        for(ItemStack itemStack : add){
            inv.addItem(itemStack);
        }
        TailInvHash.put(p, inv);
    }
    public void createPetsInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', PerfectCosmeticsConfig.get().getString("NameOfPetsInventory")));
        List<ItemStack> add = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Pets");
        for(ItemStack itemStack : add){
            inv.addItem(itemStack);
        }
        PetsInvHash.put(p, inv);
    }
}
