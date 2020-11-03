package me.pljr.bank.menus;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgBanksMenu;
import me.pljr.bank.config.CfgLang;
import me.pljr.bank.config.CfgSettings;
import me.pljr.bank.enums.BankType;
import me.pljr.bank.enums.Lang;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.bank.utils.BankUtil;
import me.pljr.pljrapi.XMaterial;
import me.pljr.pljrapi.builders.GUIBuilder;
import me.pljr.pljrapi.builders.ItemBuilder;
import me.pljr.pljrapi.managers.GUIManager;
import me.pljr.pljrapi.objects.GUI;
import me.pljr.pljrapi.objects.GUIItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BanksMenu {
    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(CfgBanksMenu.title, 3).openOnClose(MainMenu.get(player));

        for (int i = 0; i<27; i++){
            guiBuilder.setItem(i, CfgBanksMenu.background);
        }
        int i = 9;

        BankType bankType = Bank.getPlayerManager().getCorePlayer(player.getUniqueId()).getBankType();
        for (BankType type : BankType.values()){
            GUIManager.ClickRunnable clickRunnable = new GUIManager.ClickRunnable() {public void run(InventoryClickEvent inventoryClickEvent) {}};
            List<BankType> avaliable = getAvailable(player);
            switch (i){
                case 11:
                    if (avaliable.contains(BankType.TWO) && player.hasPermission(BankUtil.getPerm(BankType.TWO))){
                        clickRunnable = new GUIManager.ClickRunnable() {
                            @Override
                            public void run(InventoryClickEvent inventoryClickEvent) {
                                BankUtil.buy(player, BankType.TWO);
                                get(player).open(player);
                            }
                        };
                    }
                    break;
                case 13:
                    if (avaliable.contains(BankType.THREE) && player.hasPermission(BankUtil.getPerm(BankType.THREE))){
                        clickRunnable = new GUIManager.ClickRunnable() {
                            @Override
                            public void run(InventoryClickEvent inventoryClickEvent) {
                                BankUtil.buy(player, BankType.THREE);
                                get(player).open(player);
                            }
                        };
                    }
                    break;
                case 15:
                    if (avaliable.contains(BankType.FOUR) && player.hasPermission(BankUtil.getPerm(BankType.FOUR))){
                        clickRunnable = new GUIManager.ClickRunnable() {
                            @Override
                            public void run(InventoryClickEvent inventoryClickEvent) {
                                BankUtil.buy(player, BankType.FOUR);
                                get(player).open(player);
                            }
                        };
                    }
                    break;
                case 17:
                    if (avaliable.contains(BankType.FIVE) && player.hasPermission(BankUtil.getPerm(BankType.FIVE))){
                        clickRunnable = new GUIManager.ClickRunnable() {
                            @Override
                            public void run(InventoryClickEvent inventoryClickEvent) {
                                BankUtil.buy(player, BankType.FIVE);
                                get(player).open(player);
                            }
                        };
                    }
                    break;
            }
            if (CfgSettings.bankMenuChangeCurrent){
                if (type.equals(bankType)){
                    String playerName = player.getName();
                    ItemMeta itemMeta = CfgBanksMenu.banks.get(type).getItemMeta();
                    guiBuilder.setItem(i, new GUIItem(
                            new ItemBuilder(XMaterial.PLAYER_HEAD)
                                    .withOwner(playerName)
                                    .withName(itemMeta.getDisplayName())
                                    .withLore(itemMeta.getLore())
                                    .replaceLore("%max", BankUtil.getMax(type)+"")
                                    .replaceLore("%cost", BankUtil.getCost(type)+"")
                                    .replaceLore("%perm", CfgLang.lang.get(Lang.PERM_YES))
                                    .create(),
                            clickRunnable
                    ));
                    i+=2;
                    continue;
                }
            }
            if (player.hasPermission(BankUtil.getPerm(type))){
                guiBuilder.setItem(i, new GUIItem(
                        new ItemBuilder(CfgBanksMenu.banks.get(type))
                                .replaceLore("%max", BankUtil.getMax(type)+"")
                                .replaceLore("%cost", BankUtil.getCost(type)+"")
                                .replaceLore("%perm", CfgLang.lang.get(Lang.PERM_YES))
                                .create(),
                        clickRunnable
                ));
            }else{
                guiBuilder.setItem(i, new GUIItem(
                        new ItemBuilder(CfgBanksMenu.banks.get(type))
                                .replaceLore("%max", BankUtil.getMax(type)+"")
                                .replaceLore("%cost", BankUtil.getCost(type)+"")
                                .replaceLore("%perm", CfgLang.lang.get(Lang.PERM_NO))
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
