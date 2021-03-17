package me.pljr.bank.config;

import lombok.Getter;
import me.pljr.pljrapispigot.managers.ConfigManager;

@Getter
public class Settings {
    private final static String PATH = "settings";

    private final boolean banksMenuChangeCurrent;
    private final boolean cachePlayers;
    
    public Settings(ConfigManager config){
        banksMenuChangeCurrent = config.getBoolean(PATH+".banks-menu-change-current");
        cachePlayers = config.getBoolean(PATH+".cache-players");
    }
}
