package me.pljr.bank.objects;

public class CoreBank {
    private final int max;
    private final int cost;
    private final String perm;

    public CoreBank(int max, int cost, String perm){
        this.max = max;
        this.cost = cost;
        this.perm = perm;
    }

    public int getMax() {
        return max;
    }

    public int getCost() {
        return cost;
    }

    public String getPerm() {
        return perm;
    }
}
