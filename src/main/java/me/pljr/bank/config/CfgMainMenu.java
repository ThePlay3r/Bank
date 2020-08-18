package me.pljr.bank.config;

import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgMainMenu {
    public static String title;
    public static ItemStack background;
    public static ItemStack head;
    public static ItemStack withdraw;
    public static ItemStack deposit;

    public static void load(ConfigManager config){
        CfgMainMenu.title = config.getString("main-menu.title");
        CfgMainMenu.background = config.getSimpleItemStack("main-menu.background");
        CfgMainMenu.head = config.getSimpleItemStack("main-menu.head");
        CfgMainMenu.withdraw = config.getSimpleItemStack("main-menu.withdraw");
        CfgMainMenu.deposit = config.getSimpleItemStack("main-menu.deposit");
    }
}
