package me.pljr.bank.utils;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgBanks;
import me.pljr.bank.config.CfgLang;
import me.pljr.bank.enums.BankType;
import me.pljr.bank.enums.Lang;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.pljrapi.utils.VaultUtil;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BankUtil {

    public static int getMax(BankType bankType){
        return CfgBanks.banks.get(bankType).getMax();
    }

    public static int getCost(BankType bankType){
        return CfgBanks.banks.get(bankType).getCost();
    }

    public static String getPerm(BankType bankType){
        return CfgBanks.banks.get(bankType).getPerm();
    }

    public static void buy(Player player, BankType bankType){
        UUID uuid = player.getUniqueId();
        double cost = getCost(bankType);
        double balance = VaultUtil.getBalance(player);
        if (balance < cost){
            player.sendMessage(CfgLang.lang.get(Lang.BUY_FAILURE));
            return;
        }
        CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(uuid);
        corePlayer.setBankType(bankType);
        VaultUtil.withdraw(player, cost);
        Bank.getPlayerManager().setCorePlayer(uuid, corePlayer);
        player.sendMessage(CfgLang.lang.get(Lang.BUY_SUCCESS));
    }
}
