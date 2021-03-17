package me.pljr.bank.menus;

import lombok.Getter;
import me.pljr.bank.Bank;
import me.pljr.bank.config.BankType;
import me.pljr.bank.config.Lang;
import me.pljr.bank.config.MenuItemType;
import me.pljr.bank.config.Settings;
import me.pljr.bank.managers.PlayerManager;
import me.pljr.bank.objects.BankPlayer;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.managers.GUIManager;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BanksMenu {
    private final static Settings SETTINGS = Bank.get().getSettings();

    @Getter
    private final GUI gui;

    public BanksMenu(BankPlayer bankPlayer, PlayerManager playerManager, MainMenu mainMenu){
        Player player = bankPlayer.getPlayer();
        GUIBuilder guiBuilder = new GUIBuilder(Lang.MENU_TITLE.get(), 3).openOnClose(mainMenu.getGui());

        for (int i = 0; i<27; i++){
            guiBuilder.setItem(i, MenuItemType.BANKS_BACKGROUND.get());
        }
        int i = 9;

        BankType bankType = bankPlayer.getBankType();
        for (BankType type : BankType.values()){
            GUIManager.ClickRunnable clickRunnable = run -> {};
            List<BankType> available = getAvailable(bankPlayer);
            switch (i){
                case 11:
                    if (available.contains(BankType.TWO) && player.hasPermission(BankType.TWO.getPerm())){
                        clickRunnable = click -> {
                            playerManager.buyBank(player, BankType.TWO);
                            player.closeInventory();
                        };
                    }
                    break;
                case 13:
                    if (available.contains(BankType.THREE) && player.hasPermission(BankType.THREE.getPerm())){
                        clickRunnable = click -> {
                            playerManager.buyBank(player, BankType.THREE);
                            player.closeInventory();
                        };
                    }
                    break;
                case 15:
                    if (available.contains(BankType.FOUR) && player.hasPermission(BankType.FOUR.getPerm())){
                        clickRunnable = click -> {
                            playerManager.buyBank(player, BankType.FOUR);
                            player.closeInventory();
                        };
                    }
                    break;
                case 17:
                    if (available.contains(BankType.FIVE) && player.hasPermission(BankType.FIVE.getPerm())){
                        clickRunnable = click -> {
                            playerManager.buyBank(player, BankType.FIVE);
                            player.closeInventory();
                        };
                    }
                    break;
            }
            if (SETTINGS.isBanksMenuChangeCurrent()){
                if (type.equals(bankType)){
                    String playerName = player.getName();
                    guiBuilder.setItem(i, new GUIItem(
                            new ItemBuilder(type.getMenuItem())
                                    .withOwner(playerName)
                                    .replaceLore("{max}", type.getMaxDeposit()+"")
                                    .replaceLore("{cost}", type.getCost()+"")
                                    .replaceLore("{perm}", Lang.PERM_YES.get())
                                    .create(),
                            clickRunnable
                    ));
                    i+=2;
                    continue;
                }
            }
            if (player.hasPermission(type.getPerm())){
                guiBuilder.setItem(i, new GUIItem(
                        new ItemBuilder(type.getMenuItem())
                                .replaceLore("{max}", type.getMaxDeposit()+"")
                                .replaceLore("{cost}", type.getCost()+"")
                                .replaceLore("{perm}", Lang.PERM_YES.get())
                                .create(),
                        clickRunnable
                ));
            }else{
                guiBuilder.setItem(i, new GUIItem(
                        new ItemBuilder(type.getMenuItem())
                                .replaceLore("{max}", type.getMaxDeposit()+"")
                                .replaceLore("{cost}", type.getCost()+"")
                                .replaceLore("{perm}", Lang.PERM_NO.get())
                                .create(),
                        clickRunnable
                ));
            }
            i+=2;
        }

        gui = guiBuilder.create();
    }

    private List<BankType> getAvailable(BankPlayer bankPlayer){
        List<BankType> available = new ArrayList<>();
        switch (bankPlayer.getBankType()){
            case DEFAULT:
                available.add(BankType.TWO);
                available.add(BankType.THREE);
                available.add(BankType.FOUR);
                available.add(BankType.FIVE);
                break;
            case TWO:
                available.add(BankType.THREE);
                available.add(BankType.FOUR);
                available.add(BankType.FIVE);
                break;
            case THREE:
                available.add(BankType.FOUR);
                available.add(BankType.FIVE);
                break;
            case FOUR:
                available.add(BankType.FIVE);
                break;
        }
        return available;
    }
}
