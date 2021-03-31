package me.pljr.bank;

import lombok.Getter;
import me.pljr.bank.commands.ABankCommand;
import me.pljr.bank.commands.BankCommand;
import me.pljr.bank.config.BankType;
import me.pljr.bank.config.Lang;
import me.pljr.bank.config.MenuItemType;
import me.pljr.bank.config.Settings;
import me.pljr.bank.listeners.AsyncPlayerPreLoginListener;
import me.pljr.bank.listeners.PlayerQuitListener;
import me.pljr.bank.managers.PlayerManager;
import me.pljr.bank.managers.QueryManager;
import me.pljr.pljrapispigot.PLJRApiSpigot;
import me.pljr.pljrapispigot.database.DataSource;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Bank extends JavaPlugin {

    private static Bank instance;
    public static Logger log;

    private PLJRApiSpigot pljrApiSpigot;

    private PlayerManager playerManager;
    private ConfigManager configManager;
    private QueryManager queryManager;

    @Getter private Settings settings;

    public static Bank get(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        log = getLogger();
        if (!setupPLJRApi()) return;
        setupConfig();
        setupDatabase();
        setupManagers();
        setupListeners();
        setupCommands();
        setupPapi();
    }

    public boolean setupPLJRApi(){
        if (PLJRApiSpigot.get() == null){
            getLogger().warning("PLJRApi-Spigot is not enabled!");
            return false;
        }
        pljrApiSpigot = PLJRApiSpigot.get();
        return true;
    }

    private void setupConfig(){
        saveDefaultConfig();
        configManager = new ConfigManager(this, "config.yml");
        settings = new Settings(configManager);
        Lang.load(new ConfigManager(this, "lang.yml"));
        BankType.load(new ConfigManager(this, "banktypes.yml"));
        MenuItemType.load(new ConfigManager(this, "menus.yml"));
    }

    private void setupDatabase(){
        DataSource dataSource = pljrApiSpigot.getDataSource(configManager);
        queryManager = new QueryManager(dataSource);
        queryManager.setupTables();
    }

    private void setupManagers(){
        playerManager = new PlayerManager(this, queryManager, settings.isCachePlayers());
        playerManager.initAutoSave();
    }

    private void setupListeners(){
        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(playerManager), this);
    }

    private void setupCommands(){
        new BankCommand(playerManager).registerCommand(this);
        new ABankCommand(playerManager).registerCommand(this);
    }

    private void setupPapi(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PapiExpansion(this, playerManager).register();
        }
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
