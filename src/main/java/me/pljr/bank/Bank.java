package me.pljr.bank;

import me.pljr.bank.commands.ABankCommand;
import me.pljr.bank.commands.BankCommand;
import me.pljr.bank.config.*;
import me.pljr.bank.listeners.AsyncPlayerPreLoginListener;
import me.pljr.bank.listeners.InventoryCloseListener;
import me.pljr.bank.listeners.PlayerQuitListener;
import me.pljr.bank.managers.PlayerManager;
import me.pljr.bank.managers.QueryManager;
import me.pljr.bank.menus.BanksMenu;
import me.pljr.bank.menus.DepositMenu;
import me.pljr.bank.menus.MainMenu;
import me.pljr.bank.menus.WithdrawMenu;
import me.pljr.pljrapi.PLJRApi;
import me.pljr.pljrapi.database.DataSource;
import me.pljr.pljrapi.managers.ConfigManager;
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
        if (!setupPLJRApi()) return;
        instance = this;
        setupConfig();
        setupManagers();
        setupDatabase();
        setupListeners();
        setupComamnds();
    }

    private boolean setupPLJRApi(){
        PLJRApi api = (PLJRApi) Bukkit.getServer().getPluginManager().getPlugin("PLJRApi");
        if (api == null){
            Bukkit.getConsoleSender().sendMessage("§cReactions: PLJRApi not found, disabling plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }else{
            Bukkit.getConsoleSender().sendMessage("§aReactions: Hooked into PLJRApi!");
            return true;
        }
    }

    private void setupConfig(){
        saveDefaultConfig();
        configManager = new ConfigManager(getConfig(), "§cBank:", "config.yml");
        CfgBanks.load(configManager);
        CfgBanksMenu.load(configManager);
        CfgDepositMenu.load(configManager);
        CfgLang.load(configManager);
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
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);

        getServer().getPluginManager().registerEvents(new MainMenu(), this);
        getServer().getPluginManager().registerEvents(new DepositMenu(), this);
        getServer().getPluginManager().registerEvents(new WithdrawMenu(), this);
        getServer().getPluginManager().registerEvents(new BanksMenu(), this);
    }

    private void setupComamnds(){
        getCommand("bank").setExecutor(new BankCommand());
        getCommand("abank").setExecutor(new ABankCommand());
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
