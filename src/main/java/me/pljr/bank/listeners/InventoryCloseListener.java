package me.pljr.bank.listeners;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgBanksMenu;
import me.pljr.bank.config.CfgDepositMenu;
import me.pljr.bank.config.CfgWithdrawMenu;
import me.pljr.bank.menus.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getPlayer() instanceof Player){
            String title = event.getView().getTitle();
            if (title == null) return;
            Bukkit.broadcastMessage(title);
            Player player = (Player) event.getPlayer();
            if (title.equals(CfgWithdrawMenu.title) || title.equals(CfgDepositMenu.title) || title.equals(CfgBanksMenu.title)){
                Bukkit.getScheduler().runTaskLater(Bank.getInstance(), ()->{
                    MainMenu.open(player);
                }, 2);
            }
        }
    }
}
