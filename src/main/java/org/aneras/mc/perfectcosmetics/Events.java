package org.aneras.mc.perfectcosmetics;

import org.aneras.mc.perfectcosmetics.config.PerfectCosmeticsConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.A;

import java.beans.Customizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Events implements Listener {
    private final PerfectCosmetics plugin;
    private static Map<Player, Inventory> HeadInvHash = new HashMap<>();
    private static Map<Player, Inventory> BodyInvHash = new HashMap<>();
    private static Map<Player, Inventory> OffHandInvHash = new HashMap<>();
    private static Map<Player, Inventory> TailInvHash = new HashMap<>();
    private static Map<Player, Inventory> PetsInvHash = new HashMap<>();

    private static Map<Player, ArmorStand> BodyArmorStand = new HashMap<>();
    private static Map<Player, ArmorStand> TailArmorStand = new HashMap<>();
    private static Map<Player, ArmorStand> PetsArmorStand = new HashMap<>();
    public Events(PerfectCosmetics PerfectCosmetics, Map<Player, Inventory > Inv1, Map<Player, Inventory > Inv2, Map<Player, Inventory > Inv3, Map<Player, Inventory > Inv4, Map<Player, Inventory > Inv5 ){
        plugin = PerfectCosmetics;
        HeadInvHash = Inv1;
        BodyInvHash = Inv2;
        OffHandInvHash = Inv3;
        TailInvHash = Inv4;
        PetsInvHash = Inv5;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        plugin.data.createPlayer(p);
        List<ItemStack> Head = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Head");
        List<ItemStack> Body = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Body");
        List<ItemStack> OffHand = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("OffHand");
        List<ItemStack> Tail = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Tail");
        List<ItemStack> Pets = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Pets");
        for(ItemStack item : Head){
            if(item.getItemMeta().hasCustomModelData()){
                if(item.getItemMeta().getCustomModelData() == plugin.data.getHead(p.getUniqueId())){
                    p.getEquipment().setHelmet(item);
                }
            }
        }
        for(ItemStack item : Body){
            if(item.getItemMeta().hasCustomModelData()){
                if(item.getItemMeta().getCustomModelData() == plugin.data.getBody(p.getUniqueId())){

                }
            }
        }
        for(ItemStack item : OffHand){
            if(item.getItemMeta().hasCustomModelData()){
                if(item.getItemMeta().getCustomModelData() == plugin.data.getOffHand(p.getUniqueId())){
                    p.getEquipment().setItemInOffHand(item);
                }
            }
        }
        for(ItemStack item : Tail){
            if(item.getItemMeta().hasCustomModelData()){
                if(item.getItemMeta().getCustomModelData() == plugin.data.getTail(p.getUniqueId())){

                }
            }
        }
        for(ItemStack item : Pets){
            if(item.getItemMeta().hasCustomModelData()){
                if(item.getItemMeta().getCustomModelData() == plugin.data.getPets(p.getUniqueId())){

                }
            }
        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getClickedInventory() == HeadInvHash.get(p)){
            e.setCancelled(true);
        }else if(e.getClickedInventory() == BodyInvHash.get(p)){
            e.setCancelled(true);
        }else if(e.getClickedInventory() == OffHandInvHash.get(p)){
            e.setCancelled(true);
        }else if(e.getClickedInventory() == TailInvHash.get(p)){
            e.setCancelled(true);
        }else if(e.getClickedInventory() == PetsInvHash.get(p)){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        BodyArmorStand.get(p).remove();
    }
    @EventHandler
    public void onClose(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        HeadInvHash.remove(p);
        BodyInvHash.remove(p);
        OffHandInvHash.remove(p);
        TailInvHash.remove(p);
        PetsInvHash.remove(p);
    }

    @EventHandler
    public void PlayerClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getClickedInventory() == HeadInvHash.get(p)){
            if(e.getCurrentItem() != null){
                ItemStack item = e.getCurrentItem();
                if(item.getItemMeta().hasCustomModelData()){
                    plugin.data.changeHead(p.getUniqueId(), item.getItemMeta().getCustomModelData());
                    p.getEquipment().setHelmet(item);
                }
            }
        }else if(e.getClickedInventory() == BodyInvHash.get(p)){
            if(e.getCurrentItem() != null){
                ItemStack item = e.getCurrentItem();
                if(item.getItemMeta().hasCustomModelData()){
                    if(BodyArmorStand.containsKey(p)){
                        BodyArmorStand.get(p).remove();
                    }
                    plugin.data.changeBody(p.getUniqueId(), item.getItemMeta().getCustomModelData());
                    createArmorStandBody(p);
                }
            }
        }else if(e.getClickedInventory() == OffHandInvHash.get(p)){
            if(e.getCurrentItem() != null){
                ItemStack item = e.getCurrentItem();
                if(item.getItemMeta().hasCustomModelData()){
                    plugin.data.changeSOffHand(p.getUniqueId(), item.getItemMeta().getCustomModelData());
                    p.getEquipment().setItemInOffHand(item);
                }
            }
        } else if(e.getClickedInventory() == TailInvHash.get(p)){
            if(e.getCurrentItem() != null){
                ItemStack item = e.getCurrentItem();
                if(item.getItemMeta().hasCustomModelData()){
                    if(TailArmorStand.containsKey(p)){
                        TailArmorStand.get(p).remove();
                    }
                    plugin.data.changeTail(p.getUniqueId(), item.getItemMeta().getCustomModelData());
                    createArmorStandTail(p);
                }
            }
        } else if(e.getClickedInventory() == PetsInvHash.get(p)){
            if(e.getCurrentItem() != null){
                ItemStack item = e.getCurrentItem();
                if(item.getItemMeta().hasCustomModelData()){
                    if(PetsArmorStand.containsKey(p)){
                        PetsArmorStand.get(p).remove();
                    }
                    plugin.data.changePets(p.getUniqueId(), item.getItemMeta().getCustomModelData());
                    createArmorStandPets(p);
                }
            }
        }
    }

    @EventHandler
    public void SpawnBody(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(BodyArmorStand.containsKey(p)){
            BodyArmorStand.get(p).setRotation(p.getLocation().getYaw(), p.getLocation().getPitch());
           p.addPassenger(BodyArmorStand.get(p));
        }else{
            createArmorStandBody(p);
        }
        if(TailArmorStand.containsKey(p)){
            TailArmorStand.get(p).setRotation(p.getLocation().getYaw(), p.getLocation().getPitch());
            p.addPassenger(TailArmorStand.get(p));
        }else{
            createArmorStandTail(p);
        }
        if(PetsArmorStand.containsKey(p)){
            PetsArmorStand.get(p).setRotation(p.getLocation().getYaw(), p.getLocation().getPitch());
            p.addPassenger(PetsArmorStand.get(p));
        }else{
            createArmorStandPets(p);
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e){
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInOffHand();
        if(item != null){
            if(item.getItemMeta().hasCustomModelData()){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void clickOffHand(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getClick().isRightClick() || e.getClick().isLeftClick() || e.getClick().isShiftClick()){
            if(e.getSlot() == 40){
                if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasCustomModelData()){
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void RightClickHead(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_HELMET)) {
                if(p.getInventory().getHelmet().getItemMeta().hasCustomModelData()){
                    ItemMeta itemMeta = p.getInventory().getHelmet().getItemMeta();
                    itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                    itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                    itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
                    itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER));
                    itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier("generic.armor.toughness",2, AttributeModifier.Operation.ADD_NUMBER));
                    itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    p.getInventory().getHelmet().setItemMeta(itemMeta);
                    p.getInventory().setItemInMainHand(null);
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void clickHelmet(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getClickedInventory() != null && e.getClickedInventory().getType() == InventoryType.PLAYER){
            if(e.getCurrentItem() != null){
                if(e.getClick().isShiftClick() || e.getClick().isLeftClick() || e.getClick().isRightClick()) {
                    if (e.getSlot() == 39) {
                        if (e.getCurrentItem().getItemMeta().hasCustomModelData()) {
                            if (e.getCursor().getType() == Material.DIAMOND_HELMET) {
                                ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
                                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER));
                                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier("generic.armor.toughness",2, AttributeModifier.Operation.ADD_NUMBER));
                                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                e.getCurrentItem().setItemMeta(itemMeta);
                                p.setItemOnCursor(null);
                                e.setCancelled(true);
                            }else if (e.getCursor().getType() == Material.GOLDEN_HELMET) {
                                ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
                                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 2, AttributeModifier.Operation.ADD_NUMBER));
                                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                e.getCurrentItem().setItemMeta(itemMeta);
                                p.setItemOnCursor(null);
                                e.setCancelled(true);
                            }else if (e.getCursor().getType() == Material.IRON_HELMET) {
                                ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
                                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 2, AttributeModifier.Operation.ADD_NUMBER));
                                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                e.getCurrentItem().setItemMeta(itemMeta);
                                p.setItemOnCursor(null);
                                e.setCancelled(true);
                            }if (e.getCursor().getType() == Material.CHAINMAIL_HELMET) {
                                ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
                                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 2, AttributeModifier.Operation.ADD_NUMBER));
                                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                e.getCurrentItem().setItemMeta(itemMeta);
                                p.setItemOnCursor(null);
                                e.setCancelled(true);
                            }else if (e.getCursor().getType() == Material.LEATHER_HELMET) {
                                ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
                                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 1, AttributeModifier.Operation.ADD_NUMBER));
                                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                e.getCurrentItem().setItemMeta(itemMeta);
                                p.setItemOnCursor(null);
                                e.setCancelled(true);
                            }else if (e.getCursor().getType() == Material.NETHERITE_HELMET) {
                                ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                                itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
                                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER));
                                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier("generic.armor.toughness",3, AttributeModifier.Operation.ADD_NUMBER));
                                itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier("generic.knockback.resistance",0.1, AttributeModifier.Operation.ADD_NUMBER));
                                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                e.getCurrentItem().setItemMeta(itemMeta);
                                p.setItemOnCursor(null);
                                e.setCancelled(true);
                            }
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
    public void createArmorStandBody(Player p){
        World w = p.getWorld();
        Location l = p.getLocation();
        ItemStack item = null;
        List<ItemStack> Body = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Body");
        for(ItemStack itemStack : Body){
            if(itemStack.getItemMeta().hasCustomModelData()) {
                if (plugin.data.getBody(p.getUniqueId()) == itemStack.getItemMeta().getCustomModelData()) {
                    item = itemStack;
                }
            }
        }
        ArmorStand armorStand = w.spawn(l.add(0, 1, 0), ArmorStand.class);
        armorStand.setMarker(true);
        armorStand.setInvulnerable(true);
        armorStand.setInvisible(true);
        armorStand.setCollidable(false);
        armorStand.setSmall(true);
        armorStand.setItem(EquipmentSlot.HEAD, item);
        armorStand.setGravity(false);
        armorStand.setArms(false);
        BodyArmorStand.put(p, armorStand);
    }
    public void createArmorStandTail(Player p){
        World w = p.getWorld();
        Location l = p.getLocation();
        ItemStack item = null;
        List<ItemStack> Body = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Tail");
        for(ItemStack itemStack : Body){
            if(itemStack.getItemMeta().hasCustomModelData()) {
                if (plugin.data.getTail(p.getUniqueId()) == itemStack.getItemMeta().getCustomModelData()) {
                    item = itemStack;
                }
            }
        }
        ArmorStand armorStand = w.spawn(l.add(0, 1, 0), ArmorStand.class);
        armorStand.setMarker(true);
        armorStand.setInvulnerable(true);
        armorStand.setInvisible(true);
        armorStand.setCollidable(false);
        armorStand.setSmall(true);
        armorStand.setItem(EquipmentSlot.HEAD, item);
        armorStand.setGravity(false);
        armorStand.setArms(false);
        TailArmorStand.put(p, armorStand);
    }
    public void createArmorStandPets(Player p){
        World w = p.getWorld();
        Location l = p.getLocation();
        ItemStack item = null;
        List<ItemStack> Body = (List<ItemStack>) PerfectCosmeticsConfig.get().getList("Pets");
        for(ItemStack itemStack : Body){
            if(itemStack.getItemMeta().hasCustomModelData()) {
                if (plugin.data.getPets(p.getUniqueId()) == itemStack.getItemMeta().getCustomModelData()) {
                    item = itemStack;
                }
            }
        }
        ArmorStand armorStand = w.spawn(l.add(0, 1, 0), ArmorStand.class);
        armorStand.setMarker(true);
        armorStand.setInvulnerable(true);
        armorStand.setInvisible(true);
        armorStand.setCollidable(false);
        armorStand.setSmall(true);
        armorStand.setItem(EquipmentSlot.HEAD, item);
        armorStand.setGravity(false);
        armorStand.setArms(false);
        PetsArmorStand.put(p, armorStand);
    }

}
