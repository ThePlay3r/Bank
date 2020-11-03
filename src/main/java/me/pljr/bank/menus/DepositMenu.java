package me.pljr.bank.menus;

import me.pljr.bank.config.CfgDepositMenu;
import me.pljr.bank.config.CfgLang;
import me.pljr.pljrapi.builders.GUIBuilder;
import me.pljr.pljrapi.managers.GUIManager;
import me.pljr.pljrapi.objects.GUI;
import me.pljr.pljrapi.objects.GUIItem;
import me.pljr.pljrapi.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DepositMenu {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(CfgDepositMenu.title, 3).openOnClose(MainMenu.get(player));

        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, CfgDepositMenu.background);
        }
        guiBuilder.setItem(9, new GUIItem(CfgDepositMenu.add10, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank add 10");
            }
        }));
        guiBuilder.setItem(10, new GUIItem(CfgDepositMenu.add100, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank add 100");
            }
        }));
        guiBuilder.setItem(11, new GUIItem(CfgDepositMenu.add1000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank add 1000");
            }
        }));
        guiBuilder.setItem(12, new GUIItem(CfgDepositMenu.add10000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank add 10000");
            }
        }));
        guiBuilder.setItem(13, new GUIItem(CfgDepositMenu.add100000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank add 100000");
            }
        }));
        guiBuilder.setItem(14, new GUIItem(CfgDepositMenu.add1000000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank add 1000000");
            }
        }));
        guiBuilder.setItem(15, new GUIItem(CfgDepositMenu.add10000000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank add 10000000");
            }
        }));
        guiBuilder.setItem(16, new GUIItem(CfgDepositMenu.add100000000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank add 100000000");
            }
        }));
        guiBuilder.setItem(17, new GUIItem(CfgDepositMenu.addAll, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank addall");
            }
        }));
        guiBuilder.setItem(22, new GUIItem(CfgDepositMenu.addCustom, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                for (String message : CfgLang.depositMessage){
                    ChatUtil.sendMessage(player, message);
                }
                player.closeInventory();
            }
        }));

        return guiBuilder.create();
    }
}
