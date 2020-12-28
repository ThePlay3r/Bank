package me.pljr.bank.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgWithdrawMenu {
    public static String TITLE;
    public static ItemStack BACKGROUND;
    public static ItemStack REMOVE10;
    public static ItemStack REMOVE100;
    public static ItemStack REMOVE1000;
    public static ItemStack REMOVE10000;
    public static ItemStack REMOVE100000;
    public static ItemStack REMOVE1000000;
    public static ItemStack REMOVE10000000;
    public static ItemStack REMOVE100000000;
    public static ItemStack REMOVE_ALL;
    public static ItemStack REMOVE_CUSTOM;

    public static void load(ConfigManager config){
        CfgWithdrawMenu.TITLE = config.getString("withdraw-menu.title");
        CfgWithdrawMenu.BACKGROUND = config.getSimpleItemStack("withdraw-menu.background");
        CfgWithdrawMenu.REMOVE10 = config.getSimpleItemStack("withdraw-menu.remove-10");
        CfgWithdrawMenu.REMOVE100 = config.getSimpleItemStack("withdraw-menu.remove-100");
        CfgWithdrawMenu.REMOVE1000 = config.getSimpleItemStack("withdraw-menu.remove-1000");
        CfgWithdrawMenu.REMOVE10000 = config.getSimpleItemStack("withdraw-menu.remove-10000");
        CfgWithdrawMenu.REMOVE100000 = config.getSimpleItemStack("withdraw-menu.remove-100000");
        CfgWithdrawMenu.REMOVE1000000 = config.getSimpleItemStack("withdraw-menu.remove-1000000");
        CfgWithdrawMenu.REMOVE10000000 = config.getSimpleItemStack("withdraw-menu.remove-10000000");
        CfgWithdrawMenu.REMOVE100000000 = config.getSimpleItemStack("withdraw-menu.remove-100000000");
        CfgWithdrawMenu.REMOVE_ALL = config.getSimpleItemStack("withdraw-menu.remove-all");
        CfgWithdrawMenu.REMOVE_CUSTOM = config.getSimpleItemStack("withdraw-menu.remove-custom");
    }
}
