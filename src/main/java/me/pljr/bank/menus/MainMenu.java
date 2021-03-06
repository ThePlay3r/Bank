package me.pljr.bank.menus;

import me.pljr.bank.Bank;
import me.pljr.bank.config.Lang;
import me.pljr.bank.config.MenuItemType;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class MainMenu implements Listener {
    public static GUI get(Player player){
        CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(player.getUniqueId());
        double amount = corePlayer.getAmount();
        int maxAmount = corePlayer.getBankType().getMaxDeposit();

        GUIBuilder guiBuilder = new GUIBuilder(Lang.MENU_TITLE.get(), 3);
        for (int i = 0; i<27;i++){
            guiBuilder.setItem(i, MenuItemType.MAIN_BACKGROUND.get());
        }
        guiBuilder.setItem(13, new GUIItem(
                new ItemBuilder(MenuItemType.MAIN_HEAD.get())
                        .replaceLore("{amount}", amount + "")
                        .replaceLore("{max}", maxAmount + "")
                        .create(),
                inventoryClickEvent -> BanksMenu.get(player).open(player)));
        guiBuilder.setItem(11, new GUIItem(MenuItemType.MAIN_DEPOSIT.get(), run -> DepositMenu.get(player).open(player)));
        guiBuilder.setItem(15, new GUIItem(MenuItemType.MAIN_WITHDRAW.get(), run -> WithdrawMenu.get(player).open(player)));

        return guiBuilder.create();
    }
}
