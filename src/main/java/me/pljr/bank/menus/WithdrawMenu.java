package me.pljr.bank.menus;

import me.pljr.bank.config.CfgWithdrawMenu;
import me.pljr.bank.config.Lang;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.managers.GUIManager;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WithdrawMenu implements Listener {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(CfgWithdrawMenu.TITLE, 3).openOnClose(MainMenu.get(player));

        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, CfgWithdrawMenu.BACKGROUND);
        }
        guiBuilder.setItem(9, new GUIItem(CfgWithdrawMenu.REMOVE10, run -> Bukkit.dispatchCommand(player, "bank remove 10")));
        guiBuilder.setItem(10, new GUIItem(CfgWithdrawMenu.REMOVE100, run -> Bukkit.dispatchCommand(player, "bank remove 100")));
        guiBuilder.setItem(11, new GUIItem(CfgWithdrawMenu.REMOVE1000, run -> Bukkit.dispatchCommand(player, "bank remove 1000")));
        guiBuilder.setItem(12, new GUIItem(CfgWithdrawMenu.REMOVE10000, run -> Bukkit.dispatchCommand(player, "bank remove 10000")));
        guiBuilder.setItem(13, new GUIItem(CfgWithdrawMenu.REMOVE100000, run -> Bukkit.dispatchCommand(player, "bank remove 100000")));
        guiBuilder.setItem(14, new GUIItem(CfgWithdrawMenu.REMOVE1000000, run -> Bukkit.dispatchCommand(player, "bank remove 1000000")));
        guiBuilder.setItem(15, new GUIItem(CfgWithdrawMenu.REMOVE10000000, run -> Bukkit.dispatchCommand(player, "bank remove 10000000")));
        guiBuilder.setItem(16, new GUIItem(CfgWithdrawMenu.REMOVE100000000, run -> Bukkit.dispatchCommand(player, "bank remove 100000000")));
        guiBuilder.setItem(17, new GUIItem(CfgWithdrawMenu.REMOVE_ALL, run -> Bukkit.dispatchCommand(player, "bank removeall")));
        guiBuilder.setItem(22, new GUIItem(CfgWithdrawMenu.REMOVE_CUSTOM,
                run -> {
                    for (String message : Lang.WITHDRAW_MESSAGE){
                        ChatUtil.sendMessage(player, message);
                    }
                    player.closeInventory();
                }));

        return guiBuilder.create();
    }
}