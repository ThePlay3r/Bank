package me.pljr.bank.utils;

import me.pljr.bank.Bank;
import me.pljr.bank.config.BankType;
import me.pljr.bank.config.Lang;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.VaultUtil;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BankUtil {

    public static void buy(Player player, BankType bankType){
        UUID uuid = player.getUniqueId();
        double cost = bankType.getBank().getCost();
        double balance = VaultUtil.getBalance(player);
        if (balance < cost){
            ChatUtil.sendMessage(player, Lang.BUY_FAILURE.get());
            return;
        }
        CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(uuid);
        corePlayer.setBankType(bankType);
        VaultUtil.withdraw(player, cost);
        Bank.getPlayerManager().setCorePlayer(uuid, corePlayer);
        ChatUtil.sendMessage(player, Lang.BUY_SUCCESS.get());
    }
}
