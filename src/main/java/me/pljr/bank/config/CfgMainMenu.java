package me.pljr.bank.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgMainMenu {
    public static String TITLE;
    public static ItemStack BACKGROUND;
    public static ItemStack HEAD;
    public static ItemStack WITHDRAW;
    public static ItemStack DEPOSIT;

    public static void load(ConfigManager config){
        CfgMainMenu.TITLE = config.getString("main-menu.title");
        CfgMainMenu.BACKGROUND = config.getSimpleItemStack("main-menu.background");
        CfgMainMenu.HEAD = config.getSimpleItemStack("main-menu.head");
        CfgMainMenu.WITHDRAW = config.getSimpleItemStack("main-menu.withdraw");
        CfgMainMenu.DEPOSIT = config.getSimpleItemStack("main-menu.deposit");
    }
}
