package me.pljr.bank.config;

import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.pljrapispigot.xseries.XMaterial;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public enum MenuItemType {
    MAIN_BACKGROUND(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).withName("&0").create()),
    MAIN_HEAD(new ItemBuilder(XMaterial.PLAYER_HEAD).withName("&a&lBANK").withLore("&8► &7{amount}&8/&7{max}", "", "&eClick &fto change bank.").withOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDY3ZGNlNDY0NTM0OWU0MWE3ZjM1Nzk3ZTJiOTI3OWUzNWE2NWY1ZTgxYTM0NDk2ODg1ZDI3MjY4ZjM2OTEzOSJ9fX0=").create()),
    MAIN_WITHDRAW(new ItemBuilder(XMaterial.BUCKET).withName("&e&lWITHDRAW").withLore("&8► &fClick to withdraw money from bank.").create()),
    MAIN_DEPOSIT(new ItemBuilder(XMaterial.WATER_BUCKET).withName("&e&lDEPOSIT").withLore("&8► &fClick to deposit money to bank.").create()),

    DEPOSIT_BACKGROUND(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).withName("&0").create()),
    DEPOSIT_ADD_10(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("&a&lADD &a10$").withLore("&8► &fClick to deposit &e10$").create()),
    DEPOSIT_ADD_100(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("&a&lADD &a100$").withLore("&8► &fClick to deposit &e100$").create()),
    DEPOSIT_ADD_1000(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("&a&lADD &a1,000$").withLore("&8► &fClick to deposit &e1,000$").create()),
    DEPOSIT_ADD_10000(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("&a&lADD &a10,000$").withLore("&8► &fClick to deposit &e10,000$").create()),
    DEPOSIT_ADD_100000(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("&a&lADD &a100,000$").withLore("&8► &fClick to deposit &e100,000$").create()),
    DEPOSIT_ADD_1000000(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("&a&lADD &a1,000,000$").withLore("&8► &fClick to deposit &e1,000,000$").create()),
    DEPOSIT_ADD_10000000(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("&a&lADD &a10,000,000$").withLore("&8► &fClick to deposit &e10,000,000$").create()),
    DEPOSIT_ADD_100000000(new ItemBuilder(XMaterial.LIME_STAINED_GLASS_PANE).withName("&a&lADD &a100,000,000$").withLore("&8► &fClick to deposit &e100,000,000$").create()),
    DEPOSIT_ADD_ALL(new ItemBuilder(XMaterial.LIME_STAINED_GLASS).withName("&a&lADD &aALL").withLore("&8► &fClick to deposit &eALL &fof your money.").create()),
    DEPOSIT_ADD_CUSTOM(new ItemBuilder(XMaterial.COMMAND_BLOCK).withName("&a&lADD &aCustom").withLore("&8► &fClick to deposit &eCustom &famount of money.").create()),

    WITHDRAW_BACKGROUND(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).withName("&0").create()),
    WITHDRAW_REMOVE_10(new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE).withName("&c&lREMOVE &c10$").withLore("&8► &fClick to withdraw &e10$").create()),
    WITHDRAW_REMOVE_100(new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE).withName("&c&lREMOVE &c100$").withLore("&8► &fClick to withdraw &e100$").create()),
    WITHDRAW_REMOVE_1000(new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE).withName("&c&lREMOVE &c1,000$").withLore("&8► &fClick to withdraw &e1,000$").create()),
    WITHDRAW_REMOVE_10000(new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE).withName("&c&lREMOVE &c10,000$").withLore("&8► &fClick to withdraw &e10,000$").create()),
    WITHDRAW_REMOVE_100000(new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE).withName("&c&lREMOVE &c100,000$").withLore("&8► &fClick to withdraw &e100,000$").create()),
    WITHDRAW_REMOVE_1000000(new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE).withName("&c&lREMOVE &c1,000,000$").withLore("&8► &fClick to withdraw &e1,000,000$").create()),
    WITHDRAW_REMOVE_10000000(new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE).withName("&c&lREMOVE &c10,000,000$").withLore("&8► &fClick to withdraw &e10,000,000$").create()),
    WITHDRAW_REMOVE_100000000(new ItemBuilder(XMaterial.RED_STAINED_GLASS_PANE).withName("&c&lREMOVE &c100,000,000$").withLore("&8► &fClick to withdraw &e100,000,000$").create()),
    WITHDRAW_REMOVE_ALL(new ItemBuilder(XMaterial.RED_STAINED_GLASS).withName("&c&lREMOVE &cALL").withLore("&8► &fClick to withdraw &eALL &fof your money.").create()),
    WITHDRAW_REMOVE_CUSTOM(new ItemBuilder(XMaterial.COMMAND_BLOCK).withName("&c&lREMOVE &cCustom").withLore("&8► &fClick to withdraw &eCustom &famount of money.").create()),

    BANKS_BACKGROUND(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).withName("&0").create());

    private static HashMap<MenuItemType, ItemStack> menuItems;
    private final ItemStack defaultValue;

    MenuItemType(ItemStack defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        menuItems = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (MenuItemType menuItem : values()){
            if (!fileConfig.isSet(menuItem.toString())){
                config.setSimpleItemStack(menuItem.toString(), menuItem.defaultValue);
            }
            menuItems.put(menuItem, config.getSimpleItemStack(menuItem.toString()));
        }
        config.save();
    }

    public ItemStack get(){
        return menuItems.getOrDefault(this, defaultValue);
    }
}
