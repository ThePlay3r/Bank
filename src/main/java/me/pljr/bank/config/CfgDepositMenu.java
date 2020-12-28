package me.pljr.bank.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgDepositMenu {
    public static String TITLE;
    public static ItemStack BACKGROUND;
    public static ItemStack ADD10;
    public static ItemStack ADD100;
    public static ItemStack ADD1000;
    public static ItemStack ADD10000;
    public static ItemStack ADD100000;
    public static ItemStack ADD1000000;
    public static ItemStack ADD10000000;
    public static ItemStack ADD100000000;
    public static ItemStack ADD_ALL;
    public static ItemStack ADD_CUSTOM;

    public static void load(ConfigManager config){
        CfgDepositMenu.TITLE = config.getString("deposit-menu.title");
        CfgDepositMenu.BACKGROUND = config.getSimpleItemStack("deposit-menu.background");
        CfgDepositMenu.ADD10 = config.getSimpleItemStack("deposit-menu.add-10");
        CfgDepositMenu.ADD100 = config.getSimpleItemStack("deposit-menu.add-100");
        CfgDepositMenu.ADD1000 = config.getSimpleItemStack("deposit-menu.add-1000");
        CfgDepositMenu.ADD10000 = config.getSimpleItemStack("deposit-menu.add-10000");
        CfgDepositMenu.ADD100000 = config.getSimpleItemStack("deposit-menu.add-100000");
        CfgDepositMenu.ADD1000000 = config.getSimpleItemStack("deposit-menu.add-1000000");
        CfgDepositMenu.ADD10000000 = config.getSimpleItemStack("deposit-menu.add-10000000");
        CfgDepositMenu.ADD100000000 = config.getSimpleItemStack("deposit-menu.add-100000000");
        CfgDepositMenu.ADD_ALL = config.getSimpleItemStack("deposit-menu.add-all");
        CfgDepositMenu.ADD_CUSTOM = config.getSimpleItemStack("deposit-menu.add-custom");
    }
}
