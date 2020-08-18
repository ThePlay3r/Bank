package me.pljr.bank.objects;

import me.pljr.bank.enums.BankType;

public class CorePlayer {
    private double amount;
    private BankType bankType;

    public CorePlayer(){
        this.amount = 0;
        this.bankType = BankType.ONE;
    }

    public CorePlayer(double amount, BankType bankType){
        this.amount = amount;
        this.bankType = bankType;
    }

    public void removeMoney(double amount){
        if (amount > this.amount){
            this.amount = 0;
            return;
        }
        this.amount-=amount;
    }

    public void addMoney(double amount){
        this.amount+=amount;
    }

    public void setBankType(BankType bankType){
        this.bankType = bankType;
    }

    public double getAmount() {
        return amount;
    }

    public BankType getBankType() {
        return bankType;
    }
}
