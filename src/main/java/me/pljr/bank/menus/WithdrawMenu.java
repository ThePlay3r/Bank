package me.pljr.bank.menus;

import me.pljr.bank.config.Lang;
import me.pljr.bank.config.MenuItemType;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class WithdrawMenu implements Listener {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(Lang.MENU_TITLE.get(), 3).openOnClose(MainMenu.get(player));

        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, MenuItemType.WITHDRAW_BACKGROUND.get());
        }
        guiBuilder.setItem(9, new GUIItem(MenuItemType.WITHDRAW_REMOVE_10.get(), run -> Bukkit.dispatchCommand(player, "bank remove 10")));
        guiBuilder.setItem(10, new GUIItem(MenuItemType.WITHDRAW_REMOVE_100.get(), run -> Bukkit.dispatchCommand(player, "bank remove 100")));
        guiBuilder.setItem(11, new GUIItem(MenuItemType.WITHDRAW_REMOVE_1000.get(), run -> Bukkit.dispatchCommand(player, "bank remove 1000")));
        guiBuilder.setItem(12, new GUIItem(MenuItemType.WITHDRAW_REMOVE_10000.get(), run -> Bukkit.dispatchCommand(player, "bank remove 10000")));
        guiBuilder.setItem(13, new GUIItem(MenuItemType.WITHDRAW_REMOVE_100000.get(), run -> Bukkit.dispatchCommand(player, "bank remove 100000")));
        guiBuilder.setItem(14, new GUIItem(MenuItemType.WITHDRAW_REMOVE_1000000.get(), run -> Bukkit.dispatchCommand(player, "bank remove 1000000")));
        guiBuilder.setItem(15, new GUIItem(MenuItemType.WITHDRAW_REMOVE_10000000.get(), run -> Bukkit.dispatchCommand(player, "bank remove 10000000")));
        guiBuilder.setItem(16, new GUIItem(MenuItemType.WITHDRAW_REMOVE_100000000.get(), run -> Bukkit.dispatchCommand(player, "bank remove 100000000")));
        guiBuilder.setItem(17, new GUIItem(MenuItemType.WITHDRAW_REMOVE_ALL.get(), run -> Bukkit.dispatchCommand(player, "bank removeall")));
        guiBuilder.setItem(22, new GUIItem(MenuItemType.WITHDRAW_REMOVE_CUSTOM.get(),
                run -> {
                    ChatUtil.sendMessage(player, Lang.WITHDRAW_MESSAGE.get());
                    player.closeInventory();
                }));

        return guiBuilder.create();
    }
}