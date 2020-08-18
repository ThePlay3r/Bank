package me.pljr.bank.menus;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgBanksMenu;
import me.pljr.bank.config.CfgLang;
import me.pljr.bank.config.CfgSettings;
import me.pljr.bank.enums.BankType;
import me.pljr.bank.enums.Lang;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.bank.utils.BankUtil;
import me.pljr.pljrapi.utils.ItemStackUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BanksMenu implements Listener {

    public static void open(Player player){
        Inventory inventory = Bukkit.createInventory(player, 9*3, CfgBanksMenu.title);
        for (int i = 0; i<27; i++){
            inventory.setItem(i, CfgBanksMenu.background);
        }
        int i = 9;
        BankType bankType = Bank.getPlayerManager().getCorePlayer(player.getUniqueId()).getBankType();
        for (BankType type : BankType.values()){
            if (CfgSettings.bankMenuChangeCurrent){
                if (type.equals(bankType)){
                    String playerName = player.getName();
                    ItemMeta itemMeta = CfgBanksMenu.banks.get(type).getItemMeta();
                    inventory.setItem(i, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(
                            ItemStackUtil.createHead(playerName, itemMeta.getDisplayName(), 1, itemMeta.getLore()),
                            "%max", BankUtil.getMax(type)+""),
                            "%cost", BankUtil.getCost(type)+""),
                            "%perm", CfgLang.lang.get(Lang.PERM_YES)));
                    i+=2;
                    continue;
                }
            }
            if (player.hasPermission(BankUtil.getPerm(type))){
                inventory.setItem(i, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(
                        CfgBanksMenu.banks.get(type),
                        "%max", BankUtil.getMax(type)+""),
                        "%cost", BankUtil.getCost(type)+""),
                        "%perm", CfgLang.lang.get(Lang.PERM_YES)));
            }else{
                inventory.setItem(i, ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(ItemStackUtil.replaceLore(
                        CfgBanksMenu.banks.get(type),
                        "%max", BankUtil.getMax(type)+""),
                        "%cost", BankUtil.getCost(type)+""),
                        "%perm", CfgLang.lang.get(Lang.PERM_NO)));
            }
            i+=2;
        }
        player.openInventory(inventory);
    }

    @EventHandler
    public static void load(InventoryClickEvent event){
        if (event.getView().getTitle().equals(CfgBanksMenu.title)){
            event.setCancelled(true);
            if (event.getWhoClicked() instanceof Player){
                Player player = (Player) event.getWhoClicked();
                CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(player.getUniqueId());
                List<BankType> avaliable = new ArrayList<>();
                switch (corePlayer.getBankType()){
                    case ONE:
                        avaliable.add(BankType.TWO);
                        avaliable.add(BankType.THREE);
                        avaliable.add(BankType.FOUR);
                        avaliable.add(BankType.FIVE);
                        break;
                    case TWO:
                        avaliable.add(BankType.THREE);
                        avaliable.add(BankType.FOUR);
                        avaliable.add(BankType.FIVE);
                        break;
                    case THREE:
                        avaliable.add(BankType.FOUR);
                        avaliable.add(BankType.FIVE);
                        break;
                    case FOUR:
                        avaliable.add(BankType.FIVE);
                        break;
                }
                int slot = event.getSlot();
                switch (slot){
                    case 11:
                        if (avaliable.contains(BankType.TWO) && player.hasPermission(BankUtil.getPerm(BankType.TWO))){
                            BankUtil.buy(player, BankType.TWO);
                            open(player);
                        }
                        break;
                    case 13:
                        if (avaliable.contains(BankType.THREE) && player.hasPermission(BankUtil.getPerm(BankType.THREE))){
                            BankUtil.buy(player, BankType.THREE);
                            open(player);
                        }
                        break;
                    case 15:
                        if (avaliable.contains(BankType.FOUR) && player.hasPermission(BankUtil.getPerm(BankType.FOUR))){
                            BankUtil.buy(player, BankType.FOUR);
                            open(player);
                        }
                        break;
                    case 17:
                        if (avaliable.contains(BankType.FIVE) && player.hasPermission(BankUtil.getPerm(BankType.FIVE))){
                            BankUtil.buy(player, BankType.FIVE);
                            open(player);
                        }
                        break;
                }
            }
        }
    }
}
