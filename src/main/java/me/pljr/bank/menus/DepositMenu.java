package me.pljr.bank.menus;

import me.pljr.bank.config.CfgDepositMenu;
import me.pljr.bank.config.CfgLang;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class DepositMenu implements Listener {

    public static void open(Player player){
        Inventory inventory = Bukkit.createInventory(player, 9*3, CfgDepositMenu.title);
        for (int i = 0;i<27;i++){
            inventory.setItem(i, CfgDepositMenu.background);
        }
        inventory.setItem(9, CfgDepositMenu.add10);
        inventory.setItem(10, CfgDepositMenu.add100);
        inventory.setItem(11, CfgDepositMenu.add1000);
        inventory.setItem(12, CfgDepositMenu.add10000);
        inventory.setItem(13, CfgDepositMenu.add100000);
        inventory.setItem(14, CfgDepositMenu.add1000000);
        inventory.setItem(15, CfgDepositMenu.add10000000);
        inventory.setItem(16, CfgDepositMenu.add100000000);
        inventory.setItem(17, CfgDepositMenu.addAll);
        inventory.setItem(22, CfgDepositMenu.addCustom);
        player.openInventory(inventory);
    }

    @EventHandler
    public static void onClick(InventoryClickEvent event){
        if (event.getView().getTitle().equals(CfgDepositMenu.title)){
            event.setCancelled(true);
            if (event.getWhoClicked() instanceof Player){
                Player player = (Player) event.getWhoClicked();
                int slot = event.getSlot();
                switch (slot){
                    case 9:
                        Bukkit.dispatchCommand(player, "bank add 10");
                        break;
                    case 10:
                        Bukkit.dispatchCommand(player, "bank add 100");
                        break;
                    case 11:
                        Bukkit.dispatchCommand(player, "bank add 1000");
                        break;
                    case 12:
                        Bukkit.dispatchCommand(player, "bank add 10000");
                        break;
                    case 13:
                        Bukkit.dispatchCommand(player, "bank add 100000");
                        break;
                    case 14:
                        Bukkit.dispatchCommand(player, "bank add 1000000");
                        break;
                    case 15:
                        Bukkit.dispatchCommand(player, "bank add 10000000");
                        break;
                    case 16:
                        Bukkit.dispatchCommand(player, "bank add 100000000");
                        break;
                    case 17:
                        Bukkit.dispatchCommand(player, "bank addall");
                        break;
                    case 22:
                        for (String message : CfgLang.depositMessage){
                            TextComponent textComponent = new TextComponent(message);
                            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/bank add "));
                            player.spigot().sendMessage(textComponent);
                        }
                        player.closeInventory();
                        break;
                }
            }
        }
    }
}
