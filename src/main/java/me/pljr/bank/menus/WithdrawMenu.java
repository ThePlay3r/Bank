package me.pljr.bank.menus;

import me.pljr.bank.config.CfgWithdrawMenu;
import me.pljr.bank.config.CfgLang;
import me.pljr.pljrapi.builders.GUIBuilder;
import me.pljr.pljrapi.managers.GUIManager;
import me.pljr.pljrapi.objects.GUI;
import me.pljr.pljrapi.objects.GUIItem;
import me.pljr.pljrapi.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WithdrawMenu implements Listener {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(CfgWithdrawMenu.title, 3).openOnClose(MainMenu.get(player));

        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, CfgWithdrawMenu.background);
        }
        guiBuilder.setItem(9, new GUIItem(CfgWithdrawMenu.remove10, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank remove 10");
            }
        }));
        guiBuilder.setItem(10, new GUIItem(CfgWithdrawMenu.remove100, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank remove 100");
            }
        }));
        guiBuilder.setItem(11, new GUIItem(CfgWithdrawMenu.remove1000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank remove 1000");
            }
        }));
        guiBuilder.setItem(12, new GUIItem(CfgWithdrawMenu.remove10000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank remove 10000");
            }
        }));
        guiBuilder.setItem(13, new GUIItem(CfgWithdrawMenu.remove100000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank remove 100000");
            }
        }));
        guiBuilder.setItem(14, new GUIItem(CfgWithdrawMenu.remove1000000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank remove 1000000");
            }
        }));
        guiBuilder.setItem(15, new GUIItem(CfgWithdrawMenu.remove10000000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank remove 10000000");
            }
        }));
        guiBuilder.setItem(16, new GUIItem(CfgWithdrawMenu.remove100000000, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank remove 100000000");
            }
        }));
        guiBuilder.setItem(17, new GUIItem(CfgWithdrawMenu.removeAll, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                Bukkit.dispatchCommand(player, "bank removeall");
            }
        }));
        guiBuilder.setItem(22, new GUIItem(CfgWithdrawMenu.removeCustom, new GUIManager.ClickRunnable() {
            @Override
            public void run(InventoryClickEvent inventoryClickEvent) {
                for (String message : CfgLang.withdrawMessage){
                    ChatUtil.sendMessage(player, message);
                }
                player.closeInventory();
            }
        }));

        return guiBuilder.create();
    }
}