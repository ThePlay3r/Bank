package me.pljr.bank.menus;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgMainMenu;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.bank.utils.BankUtil;
import me.pljr.pljrapi.utils.ItemStackUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MainMenu implements Listener {

    public static void open(Player player){
        CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(player.getUniqueId());
        double amount = corePlayer.getAmount();
        int maxAmount = BankUtil.getMax(corePlayer.getBankType());
        Inventory inventory = Bukkit.createInventory(player, 9*3, CfgMainMenu.title);
        for (int i = 0; i<27;i++){
            inventory.setItem(i, CfgMainMenu.background);
        }
        inventory.setItem(13, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(
                CfgMainMenu.head,
                "%amount", amount+""),
                "%max", maxAmount+""));
        inventory.setItem(11, CfgMainMenu.deposit);
        inventory.setItem(15, CfgMainMenu.withdraw);
        player.openInventory(inventory);
    }

    @EventHandler
    public static void onClick(InventoryClickEvent event){
        if (event.getView().getTitle().equals(CfgMainMenu.title)){
            event.setCancelled(true);
            if (event.getWhoClicked() instanceof Player){
                Player player = (Player) event.getWhoClicked();
                int slot = event.getSlot();
                switch (slot){
                    case 11:
                        DepositMenu.open(player);
                        break;
                    case 15:
                        WithdrawMenu.open(player);
                        break;
                    case 13:
                        BanksMenu.open(player);
                        break;
                }
            }
        }
    }
}
