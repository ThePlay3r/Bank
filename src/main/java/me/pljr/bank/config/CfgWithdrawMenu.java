package me.pljr.bank.config;

import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgWithdrawMenu {
    public static String title;
    public static ItemStack background;
    public static ItemStack remove10;
    public static ItemStack remove100;
    public static ItemStack remove1000;
    public static ItemStack remove10000;
    public static ItemStack remove100000;
    public static ItemStack remove1000000;
    public static ItemStack remove10000000;
    public static ItemStack remove100000000;
    public static ItemStack removeAll;
    public static ItemStack removeCustom;

    public static void load(ConfigManager config){
        CfgWithdrawMenu.title = config.getString("withdraw-menu.title");
        CfgWithdrawMenu.background = config.getSimpleItemStack("withdraw-menu.background");
        CfgWithdrawMenu.remove10 = config.getSimpleItemStack("withdraw-menu.remove-10");
        CfgWithdrawMenu.remove100 = config.getSimpleItemStack("withdraw-menu.remove-100");
        CfgWithdrawMenu.remove1000 = config.getSimpleItemStack("withdraw-menu.remove-1000");
        CfgWithdrawMenu.remove10000 = config.getSimpleItemStack("withdraw-menu.remove-10000");
        CfgWithdrawMenu.remove100000 = config.getSimpleItemStack("withdraw-menu.remove-100000");
        CfgWithdrawMenu.remove1000000 = config.getSimpleItemStack("withdraw-menu.remove-1000000");
        CfgWithdrawMenu.remove10000000 = config.getSimpleItemStack("withdraw-menu.remove-10000000");
        CfgWithdrawMenu.remove100000000 = config.getSimpleItemStack("withdraw-menu.remove-100000000");
        CfgWithdrawMenu.removeAll = config.getSimpleItemStack("withdraw-menu.remove-all");
        CfgWithdrawMenu.removeCustom = config.getSimpleItemStack("withdraw-menu.remove-custom");
    }
}
