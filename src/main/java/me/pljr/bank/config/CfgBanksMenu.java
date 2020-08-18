package me.pljr.bank.config;


import me.pljr.bank.enums.BankType;
import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CfgBanksMenu {
    public static String title;
    public static ItemStack background;
    public static HashMap<BankType, ItemStack> banks;

    public static void load(ConfigManager config){
        CfgBanksMenu.title = config.getString("banks-menu.title");
        CfgBanksMenu.background = config.getSimpleItemStack("banks-menu.background");
        CfgBanksMenu.banks = new HashMap<>();
        CfgBanksMenu.banks.put(BankType.ONE, config.getSimpleItemStack("banks-menu.type-default"));
        CfgBanksMenu.banks.put(BankType.TWO, config.getSimpleItemStack("banks-menu.type-2"));
        CfgBanksMenu.banks.put(BankType.THREE, config.getSimpleItemStack("banks-menu.type-3"));
        CfgBanksMenu.banks.put(BankType.FOUR, config.getSimpleItemStack("banks-menu.type-4"));
        CfgBanksMenu.banks.put(BankType.FIVE, config.getSimpleItemStack("banks-menu.type-5"));
    }
}
