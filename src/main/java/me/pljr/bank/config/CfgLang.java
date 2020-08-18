package me.pljr.bank.config;

import me.pljr.bank.enums.Lang;
import me.pljr.pljrapi.managers.ConfigManager;

import java.util.HashMap;
import java.util.List;

public class CfgLang {
    public static List<String> help;
    public static List<String> adminHelp;
    public static List<String> depositMessage;
    public static List<String> withdrawMessage;
    public static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        CfgLang.help = config.getStringList("help");
        CfgLang.adminHelp = config.getStringList("admin-help");
        CfgLang.depositMessage = config.getStringList("deposit-message");
        CfgLang.withdrawMessage = config.getStringList("withdraw-message");
        CfgLang.lang = new HashMap<>();
        for (Lang lang : Lang.values()){
            CfgLang.lang.put(lang, config.getString("lang." + lang.toString()));
        }
    }
}
