package org.aneras.mc.perfectcosmetics.config;

import org.aneras.mc.perfectcosmetics.PerfectCosmetics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;

public class PerfectCosmeticsConfig {
    private static File file;
    private static FileConfiguration SavePlayer;
    public static void setup(){

        file = new File(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("PerfectCosmetics")).getDataFolder(), "PerfectCosmeticsConfig.yml");

        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException ignored){

            }
        }
        SavePlayer = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration get(){
        return SavePlayer;
    }
    public static void save(){
        try {
            SavePlayer.save(file);
        }catch (IOException e){
            System.out.println("File gg");
        }
    }

    public static void reload() {
        SavePlayer = YamlConfiguration.loadConfiguration(file);
    }
}
