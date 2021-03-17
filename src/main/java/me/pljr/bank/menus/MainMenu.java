package me.pljr.bank.menus;

import lombok.Getter;
import me.pljr.bank.config.Lang;
import me.pljr.bank.config.MenuItemType;
import me.pljr.bank.managers.PlayerManager;
import me.pljr.bank.objects.BankPlayer;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import org.bukkit.entity.Player;

public class MainMenu {

    @Getter
    private final GUI gui;

    public MainMenu(BankPlayer bankPlayer, PlayerManager playerManager){
        Player player = bankPlayer.getPlayer();
        double amount = bankPlayer.getAmount();
        int maxAmount = bankPlayer.getBankType().getMaxDeposit();

        GUIBuilder guiBuilder = new GUIBuilder(Lang.MENU_TITLE.get(), 3);
        for (int i = 0; i<27;i++){
            guiBuilder.setItem(i, MenuItemType.MAIN_BACKGROUND.get());
        }
        guiBuilder.setItem(13, new GUIItem(
                new ItemBuilder(MenuItemType.MAIN_HEAD.get())
                        .replaceLore("{amount}", amount + "")
                        .replaceLore("{max}", maxAmount + "")
                        .create(),
                inventoryClickEvent -> new BanksMenu(bankPlayer, playerManager, this).getGui().open(player)));
        guiBuilder.setItem(11, new GUIItem(MenuItemType.MAIN_DEPOSIT.get(), run -> new DepositMenu(player, this).getGui().open(player)));
        guiBuilder.setItem(15, new GUIItem(MenuItemType.MAIN_WITHDRAW.get(), run -> new WithdrawMenu(player, this).getGui().open(player)));

        gui = guiBuilder.create();
    }
}
