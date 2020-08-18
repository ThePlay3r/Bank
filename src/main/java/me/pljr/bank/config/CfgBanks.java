package me.pljr.bank.config;

import me.pljr.bank.enums.BankType;
import me.pljr.bank.objects.CoreBank;
import me.pljr.pljrapi.managers.ConfigManager;

import java.util.HashMap;

public class CfgBanks {
    public static HashMap<BankType, CoreBank> banks;

    public static void load(ConfigManager config){
        CfgBanks.banks = new HashMap<>();
        CfgBanks.banks.put(BankType.ONE, new CoreBank(
                config.getInt("banks.default.max"),
                config.getInt("banks.default.cost"),
                config.getString("banks.default.perm")
        ));
        CfgBanks.banks.put(BankType.TWO, new CoreBank(
                config.getInt("banks.tier-2.max"),
                config.getInt("banks.tier-2.cost"),
                config.getString("banks.tier-2.perm")
        ));
        CfgBanks.banks.put(BankType.THREE, new CoreBank(
                config.getInt("banks.tier-3.max"),
                config.getInt("banks.tier-3.cost"),
                config.getString("banks.tier-3.perm")
        ));
        CfgBanks.banks.put(BankType.FOUR, new CoreBank(
                config.getInt("banks.tier-4.max"),
                config.getInt("banks.tier-4.cost"),
                config.getString("banks.tier-4.perm")
        ));
        CfgBanks.banks.put(BankType.FIVE, new CoreBank(
                config.getInt("banks.tier-5.max"),
                config.getInt("banks.tier-5.cost"),
                config.getString("banks.tier-5.perm")
        ));
    }
}
