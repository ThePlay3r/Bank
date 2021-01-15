package me.pljr.bank.menus;

import me.pljr.bank.config.Lang;
import me.pljr.bank.config.MenuItemType;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import me.pljr.pljrapispigot.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DepositMenu {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(Lang.MENU_TITLE.get(), 3).openOnClose(MainMenu.get(player));

        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, MenuItemType.DEPOSIT_BACKGROUND.get());
        }
        guiBuilder.setItem(9, new GUIItem(MenuItemType.DEPOSIT_ADD_10.get(), run -> Bukkit.dispatchCommand(player, "bank add 10")));
        guiBuilder.setItem(10, new GUIItem(MenuItemType.DEPOSIT_ADD_100.get(), run -> Bukkit.dispatchCommand(player, "bank add 100")));
        guiBuilder.setItem(11, new GUIItem(MenuItemType.DEPOSIT_ADD_1000.get(), run -> Bukkit.dispatchCommand(player, "bank add 1000")));
        guiBuilder.setItem(12, new GUIItem(MenuItemType.DEPOSIT_ADD_10000.get(), run -> Bukkit.dispatchCommand(player, "bank add 10000")));
        guiBuilder.setItem(13, new GUIItem(MenuItemType.DEPOSIT_ADD_100000.get(), run -> Bukkit.dispatchCommand(player, "bank add 100000")));
        guiBuilder.setItem(14, new GUIItem(MenuItemType.DEPOSIT_ADD_1000000.get(), run -> Bukkit.dispatchCommand(player, "bank add 1000000")));
        guiBuilder.setItem(15, new GUIItem(MenuItemType.DEPOSIT_ADD_10000000.get(), run -> Bukkit.dispatchCommand(player, "bank add 10000000")));
        guiBuilder.setItem(16, new GUIItem(MenuItemType.DEPOSIT_ADD_100000000.get(), run -> Bukkit.dispatchCommand(player, "bank add 100000000")));
        guiBuilder.setItem(17, new GUIItem(MenuItemType.DEPOSIT_ADD_ALL.get(), run -> Bukkit.dispatchCommand(player, "bank addall")));
        guiBuilder.setItem(22, new GUIItem(MenuItemType.DEPOSIT_ADD_CUSTOM.get(),
                run -> {
                    ChatUtil.sendMessage(player, Lang.DEPOSIT_MESSAGE.get());
                    player.closeInventory();
                }));

        return guiBuilder.create();
    }
}
