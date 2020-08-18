package me.pljr.bank.config;

import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgDepositMenu {
    public static String title;
    public static ItemStack background;
    public static ItemStack add10;
    public static ItemStack add100;
    public static ItemStack add1000;
    public static ItemStack add10000;
    public static ItemStack add100000;
    public static ItemStack add1000000;
    public static ItemStack add10000000;
    public static ItemStack add100000000;
    public static ItemStack addAll;
    public static ItemStack addCustom;

    public static void load(ConfigManager config){
        CfgDepositMenu.title = config.getString("deposit-menu.title");
        CfgDepositMenu.background = config.getSimpleItemStack("deposit-menu.background");
        CfgDepositMenu.add10 = config.getSimpleItemStack("deposit-menu.add-10");
        CfgDepositMenu.add100 = config.getSimpleItemStack("deposit-menu.add-100");
        CfgDepositMenu.add1000 = config.getSimpleItemStack("deposit-menu.add-1000");
        CfgDepositMenu.add10000 = config.getSimpleItemStack("deposit-menu.add-10000");
        CfgDepositMenu.add100000 = config.getSimpleItemStack("deposit-menu.add-100000");
        CfgDepositMenu.add1000000 = config.getSimpleItemStack("deposit-menu.add-1000000");
        CfgDepositMenu.add10000000 = config.getSimpleItemStack("deposit-menu.add-10000000");
        CfgDepositMenu.add100000000 = config.getSimpleItemStack("deposit-menu.add-100000000");
        CfgDepositMenu.addAll = config.getSimpleItemStack("deposit-menu.add-all");
        CfgDepositMenu.addCustom = config.getSimpleItemStack("deposit-menu.add-custom");
    }
}
