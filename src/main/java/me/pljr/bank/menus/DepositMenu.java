package me.pljr.bank.menus;

import me.pljr.bank.config.CfgDepositMenu;
import me.pljr.bank.config.Lang;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.managers.GUIManager;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DepositMenu {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(CfgDepositMenu.TITLE, 3).openOnClose(MainMenu.get(player));

        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, CfgDepositMenu.BACKGROUND);
        }
        guiBuilder.setItem(9, new GUIItem(CfgDepositMenu.ADD10, run -> Bukkit.dispatchCommand(player, "bank add 10")));
        guiBuilder.setItem(10, new GUIItem(CfgDepositMenu.ADD100, run -> Bukkit.dispatchCommand(player, "bank add 100")));
        guiBuilder.setItem(11, new GUIItem(CfgDepositMenu.ADD1000, run -> Bukkit.dispatchCommand(player, "bank add 1000")));
        guiBuilder.setItem(12, new GUIItem(CfgDepositMenu.ADD10000, run -> Bukkit.dispatchCommand(player, "bank add 10000")));
        guiBuilder.setItem(13, new GUIItem(CfgDepositMenu.ADD100000, run -> Bukkit.dispatchCommand(player, "bank add 100000")));
        guiBuilder.setItem(14, new GUIItem(CfgDepositMenu.ADD1000000, run -> Bukkit.dispatchCommand(player, "bank add 1000000")));
        guiBuilder.setItem(15, new GUIItem(CfgDepositMenu.ADD10000000, run -> Bukkit.dispatchCommand(player, "bank add 10000000")));
        guiBuilder.setItem(16, new GUIItem(CfgDepositMenu.ADD100000000, run -> Bukkit.dispatchCommand(player, "bank add 100000000")));
        guiBuilder.setItem(17, new GUIItem(CfgDepositMenu.ADD_ALL, run -> Bukkit.dispatchCommand(player, "bank addall")));
        guiBuilder.setItem(22, new GUIItem(CfgDepositMenu.ADD_CUSTOM,
                run -> {
                    for (String message : Lang.DEPOSIT_MESSAGE){
                        ChatUtil.sendMessage(player, message);
                    }
                    player.closeInventory();
                }));

        return guiBuilder.create();
    }
}
