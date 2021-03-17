package me.pljr.bank.config;

import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.xseries.XMaterial;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public enum BankType {
    DEFAULT(new ItemBuilder(XMaterial.SOUL_SAND).withName("&7&lORDINARY &7Account").withLore("&7Max. Amount &8► &f{max}").create(), 100000, 0, ""),
    TWO(new ItemBuilder(XMaterial.DIRT).withName("&f&lCOMMON &fAccount").withLore("&7Max. Amount &8► &f{max}", "&7Cost &8► &f{cost}", "&7Perm &8► &f{perm}").create(), 200000, 50000, "bank.tier.2"),
    THREE(new ItemBuilder(XMaterial.GRASS).withName("&e&lUNCOMMON &eAccount").withLore("&7Max. Amount &8► &f{max}", "&7Cost &8► &f{cost}", "&7Perm &8► &f{perm}").create(), 300000, 100000, "bank.tier.3"),
    FOUR(new ItemBuilder(XMaterial.CHEST).withName("&b&lEPIC &bAccount").withLore("&7Max. Amount &8► &f{max}", "&7Cost &8► &f{cost}", "&7Perm &8► &f{perm}").create(),400000, 200000, "bank.tier.4"),
    FIVE(new ItemBuilder(XMaterial.ENDER_CHEST).withName("&d&lLEGENDARY &dAccount").withLore("&7Max. Amount &8► &f{max}", "&7Cost &8► &f{cost}", "&7Perm &8► &f{perm}").create(),500000, 250000, "bank.tier.5");

    private static HashMap<BankType, ItemStack> menuItems;
    private static HashMap<BankType, Integer> maxDeposits;
    private static HashMap<BankType, Integer> costs;
    private static HashMap<BankType, String> perms;
    private final ItemStack defaultMenuItem;
    private final int defaultMaxDeposit;
    private final int defaultCost;
    private final String defaultPerm;

    BankType(ItemStack defaultMenuItem, int defaultMaxDeposit, int defaultCost, String defaultPerm){
        this.defaultMenuItem = defaultMenuItem;
        this.defaultMaxDeposit = defaultMaxDeposit;
        this.defaultCost = defaultCost;
        this.defaultPerm = defaultPerm;
    }

    public static void load(ConfigManager config){
        menuItems = new HashMap<>();
        maxDeposits = new HashMap<>();
        costs = new HashMap<>();
        perms = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (BankType type : values()){
            if (!fileConfig.isSet(type.toString())){
                config.setSimpleItemStack(type.toString()+".menu-item", type.defaultMenuItem);
                System.out.println(type.defaultMenuItem);
                fileConfig.set(type.toString()+".max-deposit", type.defaultMaxDeposit);
                fileConfig.set(type.toString()+".cost", type.defaultCost);
                fileConfig.set(type.toString()+".perm", type.defaultPerm);
            }else{
                menuItems.put(type, config.getSimpleItemStack(type.toString()+".menu-item"));
                maxDeposits.put(type, config.getInt(type.toString()+".max-deposit"));
                costs.put(type, config.getInt(type.toString()+".cost"));
                perms.put(type, config.getString(type.toString()+".perm"));
            }
        }
        config.save();
    }

    public ItemStack getMenuItem(){
        return menuItems.getOrDefault(this, defaultMenuItem);
    }

    public int getMaxDeposit(){
        return maxDeposits.getOrDefault(this, defaultMaxDeposit);
    }

    public int getCost(){
        return costs.getOrDefault(this, defaultCost);
    }

    public String getPerm(){
        return perms.getOrDefault(this, defaultPerm);
    }
}
