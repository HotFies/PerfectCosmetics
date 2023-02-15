package org.aneras.mc.perfectcosmetics.MySQL;

import org.aneras.mc.perfectcosmetics.PerfectCosmetics;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Data {

    private PerfectCosmetics plugin;
    public Data(PerfectCosmetics plugin){
        this.plugin = plugin;
    }

    public void createTable(){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playersCosmetics "
                    + "(NAME VARCHAR(100),UUID VARCHAR(100),HEAD INT(100),BODY INT(100),OFFHAND INT(100),TAIL INT(100),PETS INT(100), PRIMARY KEY (NAME))");
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void createPlayer(Player player){
        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO playersCosmetics" + " (NAME,UUID,HEAD,BODY,OFFHAND,TAIL,PETS) VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, player.getName());
                ps.setString(2, uuid.toString());
                ps.setInt(3, 0);
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.setInt(6, 0);
                ps.setInt(7, 0);
                ps.executeUpdate();
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean exists(UUID uuid){
        try {
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("SELECT * FROM playersCosmetics WHERE UUID=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet results = preparedStatement.executeQuery();
            if(results.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void changeHead(UUID uuid, int Head){
        try {
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("UPDATE playersCosmetics SET HEAD=? WHERE UUID=?");
            preparedStatement.setInt(1, Head);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void changeBody(UUID uuid, int Body){
        try {
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("UPDATE playersCosmetics SET BODY=? WHERE UUID=?");
            preparedStatement.setInt(1, Body);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void changeSOffHand(UUID uuid, int OffHand){
        try {
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("UPDATE playersCosmetics SET OFFHAND=? WHERE UUID=?");
            preparedStatement.setInt(1, OffHand);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void changeTail(UUID uuid, int Tail){
        try {
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("UPDATE playersCosmetics SET TAIL=? WHERE UUID=?");
            preparedStatement.setInt(1, Tail);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void changePets(UUID uuid, int Pets){
        try {
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("UPDATE playersCosmetics SET PETS=? WHERE UUID=?");
            preparedStatement.setInt(1, Pets);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getHead(UUID uuid){
        try{
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("SELECT HEAD FROM playersCosmetics WHERE UUID=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet results = preparedStatement.executeQuery();
            int Head;
            if(results.next()){
                Head = results.getInt("HEAD");
                return Head;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int getBody(UUID uuid){
        try{
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("SELECT BODY FROM playersCosmetics WHERE UUID=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet results = preparedStatement.executeQuery();
            int Body;
            if(results.next()){
                Body = results.getInt("BODY");
                return Body;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int getOffHand(UUID uuid){
        try{
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("SELECT OFFHAND FROM playersCosmetics WHERE UUID=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet results = preparedStatement.executeQuery();
            int OffHand;
            if(results.next()){
                OffHand = results.getInt("OFFHAND");
                return OffHand;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int getTail(UUID uuid){
        try{
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("SELECT TAIL FROM playersCosmetics WHERE UUID=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet results = preparedStatement.executeQuery();
            int Tail;
            if(results.next()){
                Tail = results.getInt("TAIL");
                return Tail;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int getPets(UUID uuid){
        try{
            PreparedStatement preparedStatement = plugin.SQL.getConnection().prepareStatement("SELECT PETS FROM playersCosmetics WHERE UUID=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet results = preparedStatement.executeQuery();
            int Pets;
            if(results.next()){
                Pets = results.getInt("PETS");
                return Pets;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
