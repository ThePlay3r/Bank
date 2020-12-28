package me.pljr.bank.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

import java.util.HashMap;
import java.util.List;

public enum Lang {
    NO_CONSOLE,
    ADD_SUCCESS,
    ADD_FAILURE_TOO_MUCH,
    ADD_FAILURE_NOT_ENOUGH,
    REMOVE_SUCCESS,
    REMOVE_FAILURE_TOO_MUCH,
    SHOW_PLAYER,
    PERM_YES,
    PERM_NO,
    BUY_SUCCESS,
    BUY_FAILURE,
    NO_POSITIVE,
    ADMIN_SET_SUCCESS,
    ADMIN_SET_SUCCESS_NOTIFY,
    ADMIN_ADD_SUCCESS,
    ADMIN_ADD_SUCCESS_NOTIFY,
    ADMIN_REMOVE_SUCCESS,
    ADMIN_REMOVE_FAILURE_TOO_MUCH,
    ADMIN_REMOVE_SUCCESS_NOTIFY;
    public static List<String> HELP;
    public static List<String> ADMIN_HELP;
    public static List<String> DEPOSIT_MESSAGE;
    public static List<String> WITHDRAW_MESSAGE;

    private static HashMap<Lang, String> lang;

    public static void load(ConfigManager config){
        HELP = config.getStringList("help");
        ADMIN_HELP = config.getStringList("admin-help");
        DEPOSIT_MESSAGE = config.getStringList("deposit-message");
        WITHDRAW_MESSAGE = config.getStringList("withdraw-message");
        lang = new HashMap<>();
        for (Lang lang : values()){
            Lang.lang.put(lang, config.getString("lang."+lang));
        }
    }

    public String get(){
        return lang.get(this);
    }
}
