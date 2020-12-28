package me.pljr.bank.menus;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgSettings;
import me.pljr.bank.config.BankType;
import me.pljr.bank.config.Lang;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.bank.utils.BankUtil;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.managers.GUIManager;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class BanksMenu {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(BankType.MENU_TITLE, 3).openOnClose(MainMenu.get(player));

        for (int i = 0; i<27; i++){
            guiBuilder.setItem(i, BankType.MENU_BACKGROUND);
        }
        int i = 9;

        BankType bankType = Bank.getPlayerManager().getCorePlayer(player.getUniqueId()).getBankType();
        for (BankType type : BankType.values()){
            GUIManager.ClickRunnable clickRunnable = run -> {};
            List<BankType> avaliable = getAvailable(player);
            switch (i){
                case 11:
                    if (avaliable.contains(BankType.TWO) && player.hasPermission(BankType.TWO.getBank().getPerm())){
                        clickRunnable = run -> {
                            BankUtil.buy(player, BankType.TWO);
                            get(player).open(player);
                        };
                    }
                    break;
                case 13:
                    if (avaliable.contains(BankType.THREE) && player.hasPermission(BankType.THREE.getBank().getPerm())){
                        clickRunnable = run -> {
                            BankUtil.buy(player, BankType.THREE);
                            get(player).open(player);
                        };
                    }
                    break;
                case 15:
                    if (avaliable.contains(BankType.FOUR) && player.hasPermission(BankType.FOUR.getBank().getPerm())){
                        clickRunnable = run -> {
                            BankUtil.buy(player, BankType.FOUR);
                            get(player).open(player);
                        };
                    }
                    break;
                case 17:
                    if (avaliable.contains(BankType.FIVE) && player.hasPermission(BankType.FIVE.getBank().getPerm())){
                        clickRunnable = run -> {
                            BankUtil.buy(player, BankType.FIVE);
                            get(player).open(player);
                        };
                    }
                    break;
            }
            if (CfgSettings.BANKS_MENU_CHANGE_CURRENT){
                if (type.equals(bankType)){
                    String playerName = player.getName();
                    guiBuilder.setItem(i, new GUIItem(
                            new ItemBuilder(type.getItem())
                                    .withOwner(playerName)
                                    .replaceLore("{max}", type.getBank().getMax()+"")
                                    .replaceLore("{cost}", type.getBank().getCost()+"")
                                    .replaceLore("{perm}", Lang.PERM_YES.get())
                                    .create(),
                            clickRunnable
                    ));
                    i+=2;
                    continue;
                }
            }
            if (player.hasPermission(type.getBank().getPerm())){
                guiBuilder.setItem(i, new GUIItem(
                        new ItemBuilder(type.getItem())
                                .replaceLore("{max}", type.getBank().getMax()+"")
                                .replaceLore("{cost}", type.getBank().getCost()+"")
                                .replaceLore("{perm}", Lang.PERM_YES.get())
                                .create(),
                        clickRunnable
                ));
            }else{
                guiBuilder.setItem(i, new GUIItem(
                        new ItemBuilder(type.getItem())
                                .replaceLore("{max}", type.getBank().getMax()+"")
                                .replaceLore("{cost}", type.getBank().getCost()+"")
                                .replaceLore("{perm}", Lang.PERM_NO.get())
                                .create(),
                        clickRunnable
                ));
            }
            i+=2;
        }

        return guiBuilder.create();
    }

    private static List<BankType> getAvailable(Player player){
        CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(player.getUniqueId());
        List<BankType> available = new ArrayList<>();
        switch (corePlayer.getBankType()){
            case ONE:
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
