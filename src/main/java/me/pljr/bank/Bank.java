package me.pljr.bank;

import me.pljr.bank.commands.ABankCommand;
import me.pljr.bank.commands.BankCommand;
import me.pljr.bank.config.*;
import me.pljr.bank.listeners.AsyncPlayerPreLoginListener;
import me.pljr.bank.listeners.PlayerQuitListener;
import me.pljr.bank.managers.PlayerManager;
import me.pljr.bank.managers.QueryManager;
import me.pljr.pljrapispigot.database.DataSource;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bank extends JavaPlugin {
    private static Bank instance;
    private static PlayerManager playerManager;
    private static ConfigManager configManager;
    private static QueryManager queryManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupConfig();
        setupManagers();
        setupDatabase();
        setupListeners();
        setupCommands();
        setupPapi();
    }

    private void setupConfig(){
        saveDefaultConfig();
        configManager = new ConfigManager(this, "config.yml");
        Lang.load(configManager);
        BankType.load(configManager);
        CfgDepositMenu.load(configManager);
        CfgMainMenu.load(configManager);
        CfgSettings.load(configManager);
        CfgWithdrawMenu.load(configManager);
    }

    private void setupManagers(){
        playerManager = new PlayerManager();
    }

    private void setupDatabase(){
        DataSource dataSource = DataSource.getFromConfig(configManager);
        queryManager = new QueryManager(dataSource);
        queryManager.setupTables();
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    private void setupCommands(){
        new BankCommand().registerCommand(this);
        new ABankCommand().registerCommand(this);
    }

    private void setupPapi(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PapiExpansion(this).register();
        }
    }

    public static Bank getInstance() {
        return instance;
    }
    public static QueryManager getQueryManager() {
        return queryManager;
    }
    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
