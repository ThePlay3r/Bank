package me.pljr.bank.config;

import me.pljr.bank.objects.CoreBank;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public enum BankType {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;
    public static String MENU_TITLE;
    public static ItemStack MENU_BACKGROUND;

    private static HashMap<BankType, CoreBank> banks;
    private static HashMap<BankType, ItemStack> items;

    public static void load(ConfigManager config){
        MENU_TITLE = config.getString("banks-menu.title");
        MENU_BACKGROUND = config.getSimpleItemStack("banks-menu.background");
        banks = new HashMap<>();
        items = new HashMap<>();
        for (BankType type : values()){
            banks.put(type, new CoreBank(
                    config.getInt("banks."+type+".max"),
                    config.getInt("banks."+type+".cost"),
                    config.getString("banks."+type+".perm")
            ));
            items.put(type, config.getSimpleItemStack("banks-menu."+type));
        }
    }

    public CoreBank getBank(){
        return banks.get(this);
    }

    public ItemStack getItem(){
        return items.get(this);
    }
}
