package me.pljr.bank.menus;

import me.pljr.bank.config.CfgLang;
import me.pljr.bank.config.CfgWithdrawMenu;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class WithdrawMenu implements Listener {

    public static void open(Player player){
        Inventory inventory = Bukkit.createInventory(player, 9*3, CfgWithdrawMenu.title);
        for (int i = 0;i<27;i++){
            inventory.setItem(i, CfgWithdrawMenu.background);
        }
        inventory.setItem(9, CfgWithdrawMenu.remove10);
        inventory.setItem(10, CfgWithdrawMenu.remove100);
        inventory.setItem(11, CfgWithdrawMenu.remove1000);
        inventory.setItem(12, CfgWithdrawMenu.remove10000);
        inventory.setItem(13, CfgWithdrawMenu.remove100000);
        inventory.setItem(14, CfgWithdrawMenu.remove1000000);
        inventory.setItem(15, CfgWithdrawMenu.remove10000000);
        inventory.setItem(16, CfgWithdrawMenu.remove100000000);
        inventory.setItem(17, CfgWithdrawMenu.removeAll);
        inventory.setItem(22, CfgWithdrawMenu.removeCustom);
        player.openInventory(inventory);
    }

    @EventHandler
    public static void onClick(InventoryClickEvent event){
        if (event.getView().getTitle().equals(CfgWithdrawMenu.title)){
            event.setCancelled(true);
            if (event.getWhoClicked() instanceof Player){
                Player player = (Player) event.getWhoClicked();
                int slot = event.getSlot();
                switch (slot){
                    case 9:
                        Bukkit.dispatchCommand(player, "bank remove 10");
                        break;
                    case 10:
                        Bukkit.dispatchCommand(player, "bank remove 100");
                        break;
                    case 11:
                        Bukkit.dispatchCommand(player, "bank remove 1000");
                        break;
                    case 12:
                        Bukkit.dispatchCommand(player, "bank remove 10000");
                        break;
                    case 13:
                        Bukkit.dispatchCommand(player, "bank remove 100000");
                        break;
                    case 14:
                        Bukkit.dispatchCommand(player, "bank remove 1000000");
                        break;
                    case 15:
                        Bukkit.dispatchCommand(player, "bank remove 10000000");
                        break;
                    case 16:
                        Bukkit.dispatchCommand(player, "bank remove 100000000");
                        break;
                    case 17:
                        Bukkit.dispatchCommand(player, "bank removeall");
                        break;
                    case 22:
                        for (String message : CfgLang.withdrawMessage){
                            TextComponent textComponent = new TextComponent(message);
                            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/bank remove "));
                            player.spigot().sendMessage(textComponent);
                        }
                        player.closeInventory();
                        break;
                }
            }
        }
    }
}
