package me.pljr.bank.menus;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgMainMenu;
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
        int maxAmount = corePlayer.getBankType().getBank().getMax();

        GUIBuilder guiBuilder = new GUIBuilder(CfgMainMenu.TITLE, 3);
        for (int i = 0; i<27;i++){
            guiBuilder.setItem(i, CfgMainMenu.BACKGROUND);
        }
        guiBuilder.setItem(13, new GUIItem(
                new ItemBuilder(CfgMainMenu.HEAD)
                        .replaceLore("{amount}", amount + "")
                        .replaceLore("{max}", maxAmount + "")
                        .create(),
                inventoryClickEvent -> BanksMenu.get(player).open(player)));
        guiBuilder.setItem(11, new GUIItem(CfgMainMenu.DEPOSIT, run -> DepositMenu.get(player).open(player)));
        guiBuilder.setItem(15, new GUIItem(CfgMainMenu.WITHDRAW, run -> WithdrawMenu.get(player).open(player)));

        return guiBuilder.create();
    }
}
