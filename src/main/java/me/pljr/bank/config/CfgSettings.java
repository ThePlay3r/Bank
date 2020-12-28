package me.pljr.bank.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

public class CfgSettings {
    public static boolean BANKS_MENU_CHANGE_CURRENT;

    public static void load(ConfigManager config){
        CfgSettings.BANKS_MENU_CHANGE_CURRENT = config.getBoolean("settings.banks-menu-change-current");
    }
}
