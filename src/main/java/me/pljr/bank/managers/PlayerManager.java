package me.pljr.bank.managers;

import me.pljr.bank.Bank;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.bank.utils.BankUtil;
import me.pljr.pljrapi.utils.VaultUtil;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {
    private final HashMap<UUID, CorePlayer> players = new HashMap<>();

    public CorePlayer getCorePlayer(UUID uuid){
        if (players.containsKey(uuid)){
            return players.get(uuid);
        }
        Bank.getQueryManager().loadPlayerSync(uuid);
        return getCorePlayer(uuid);
    }

    public void setCorePlayer(UUID uuid, CorePlayer corePlayer){
        players.put(uuid, corePlayer);
        savePlayer(uuid);
    }

    public void savePlayer(UUID uuid){
        if (!players.containsKey(uuid)) return;
        Bank.getQueryManager().savePlayer(uuid);
    }

    public boolean addMoney(OfflinePlayer player, double amount){
        UUID uuid = player.getUniqueId();
        CorePlayer corePlayer = getCorePlayer(uuid);
        double bankBalance = corePlayer.getAmount();
        if (bankBalance+amount > BankUtil.getMax(corePlayer.getBankType())){
            return false;
        }
        VaultUtil.withdraw(player, amount);
        corePlayer.addMoney(amount);
        setCorePlayer(uuid, corePlayer);
        return true;
    }

    public boolean addMoneyAll(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        CorePlayer corePlayer = getCorePlayer(uuid);
        double balance = VaultUtil.getBalance(player);
        double bankBalance = corePlayer.getAmount();
        if (bankBalance+balance > BankUtil.getMax(corePlayer.getBankType())){
            return false;
        }
        VaultUtil.withdraw(player, balance);
        corePlayer.addMoney(balance);
        setCorePlayer(uuid, corePlayer);
        return true;
    }

    public boolean removeMoney(OfflinePlayer player, double amount){
        UUID uuid = player.getUniqueId();
        CorePlayer corePlayer = getCorePlayer(uuid);
        if (amount > corePlayer.getAmount()){
            return false;
        }
        corePlayer.removeMoney(amount);
        VaultUtil.deposit(player, amount);
        setCorePlayer(uuid, corePlayer);
        return true;
    }

    public void removeMoneyAll(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        CorePlayer corePlayer = getCorePlayer(uuid);
        double balance = corePlayer.getAmount();
        corePlayer.removeMoney(balance);
        VaultUtil.deposit(player, balance);
    }
}
