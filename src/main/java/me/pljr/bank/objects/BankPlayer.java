package me.pljr.bank.objects;

import lombok.Getter;
import lombok.Setter;
import me.pljr.bank.config.BankType;
import me.pljr.pljrapispigot.utils.VaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
@Setter
public class BankPlayer {
    private final UUID uniqueId;
    private double amount;
    private BankType bankType;

    public BankPlayer(UUID uniqueId){
        this.uniqueId = uniqueId;
        this.amount = 0;
        this.bankType = BankType.DEFAULT;
    }

    public BankPlayer(UUID uniqueId, double amount, BankType bankType){
        this.uniqueId = uniqueId;
        this.amount = amount;
        this.bankType = bankType;
    }

    public boolean removeMoney(double amount){
        if (amount > getAmount()){
            return false;
        }
        this.amount -= amount;
        VaultUtil.deposit(getPlayer(), amount);
        return true;
    }

    public void removeMoneyAll(){
        VaultUtil.deposit(getPlayer(), this.amount);
        this.amount = 0;
    }

    public boolean addMoney(double amount){
        double bankBalance = getAmount();
        if (bankBalance+amount > getBankType().getMaxDeposit()){
            return false;
        }
        VaultUtil.withdraw(getPlayer(), amount);
        this.amount += amount;
        return true;
    }

    public boolean addMoneyAll(){
        return addMoney(VaultUtil.getBalance(getPlayer()));
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(uniqueId);
    }
}
