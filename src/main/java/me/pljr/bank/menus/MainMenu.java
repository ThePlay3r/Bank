package me.pljr.bank.menus;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgMainMenu;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.bank.utils.BankUtil;
import me.pljr.pljrapi.builders.GUIBuilder;
import me.pljr.pljrapi.builders.ItemBuilder;
import me.pljr.pljrapi.managers.GUIManager;
import me.pljr.pljrapi.objects.GUI;
import me.pljr.pljrapi.objects.GUIItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MainMenu implements Listener {
    public static GUI get(Player player){
        CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(player.getUniqueId());
        double amount = corePlayer.getAmount();
        int maxAmount = BankUtil.getMax(corePlayer.getBankType());

        GUIBuilder guiBuilder = new GUIBuilder(CfgMainMenu.title, 3);
        for (int i = 0; i<27;i++){
            guiBuilder.setItem(i, CfgMainMenu.background);
        }
        guiBuilder.setItem(13, new GUIItem(
                new ItemBuilder(CfgMainMenu.head)
                        .replaceLore("%amount", amount + "")
                        .replaceLore("%max", maxAmount + "")
                        .create(),
                new GUIManager.ClickRunnable() {
                    @Override
                    public void run(InventoryClickEvent inventoryClickEvent) {
                        BanksMenu.get(player).open(player);
                    }
                }
        ));
        guiBuilder.setItem(11, new GUIItem(
                CfgMainMenu.deposit,
                new GUIManager.ClickRunnable() {
                    @Override
                    public void run(InventoryClickEvent inventoryClickEvent) {
                        DepositMenu.get(player).open(player);
                    }
                }
        ));
        guiBuilder.setItem(15, new GUIItem(
                CfgMainMenu.withdraw,
                new GUIManager.ClickRunnable() {
                    @Override
                    public void run(InventoryClickEvent inventoryClickEvent) {
                        WithdrawMenu.get(player).open(player);
                    }
                }
        ));

        return guiBuilder.create();
    }
}
