package me.pljr.bank.config;

import me.pljr.pljrapi.managers.ConfigManager;

public class CfgSettings {
    public static boolean bankMenuChangeCurrent;

    public static void load(ConfigManager config){
        CfgSettings.bankMenuChangeCurrent = config.getBoolean("settings.banks-menu-change-current");
    }
}
