package me.pljr.bank.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum Lang {
    HELP("" +
            "\n&a&lBank Help" +
            "\n" +
            "\n&e/bank &8» &fOpens the main GUI." +
            "\n&e/bank help &8» &fDisplays this message." +
            "\n&e/bank add <amount> &8» &fDeposits amount to your bank account." +
            "\n&e/bank addall &8» &fDeposits all of your current money to your bank account." +
            "\n&e/bank remove <amount> &8» &fWithdraws amount from your bank account." +
            "\n&e/bank removeall &8» &fWithdraws all of the money in your bank account." +
            "\n&e/bank show <player> &8» &fShows information about player."),

    ADMIN_HELP("" +
            "\n&a&lBank Admin-Help" +
            "\n" +
            "\n&e/abank set <player> <amount> &8» &fSets money of player's bank account. &7(Ignores his Tier)" +
            "\n&e/abank add <player> <amount> &8» &fAdds money to player's bank account. &7(Ignores his Tier)" +
            "\n&e/abank remove <player> <amount> &8» &fRemoves money from player's bank account."),

    DEPOSIT_MESSAGE("" +
            "\n&e&lBANK" +
            "\n<click:suggest_command:'/bank add '>&8» &fClick on this message to &eadd &fcustom amount of money to your account.</click>" +
            "\n"),

    WITHDRAW_MESSAGE("" +
            "\n&e&lBANK" +
            "\n<click:suggest_command:'/bank remove '>&8» &fClick on this message to &eremove &fcustom amount of money from your account.</click>" +
            "\n"),

    MENU_TITLE("&8&lBank"),
    ADD_SUCCESS("&aBank &8» &b{money} &fhas been deposited to your bank account."),
    ADD_FAILURE_TOO_MUCH("&aBank &8» &fYou can't add &b{money} &fto your account!"),
    ADD_FAILURE_NOT_ENOUGH("&aBank &8» &fYou don't have enough money to do this!"),
    REMOVE_SUCCESS("&aBank &8» &fSuccessfully withdrawn &b{money} &ffrom your bank account."),
    REMOVE_FAILURE_TOO_MUCH("&aBank &8» &fYou don't have enough money in your bank account!"),
    SHOW_PLAYER("&aBank &8» &b{player} &fhas $&b{amount} &fon his Bank Account."),
    PERM_YES("&aYes"),
    PERM_NO("&aNo"),
    BUY_SUCCESS("&aBank &8» &fYou successfully bought new &bbank account&f!"),
    BUY_FAILURE("&aBank &8» &fYou don't have enough money to buy this account!"),
    NO_POSITIVE("&aBank &8» &fThe number must be positive!"),
    ADMIN_SET_SUCCESS("&aBank &8» &fSuccessfully set bank balance of &b{player} &fto &b{money}&f."),
    ADMIN_SET_SUCCESS_NOTIFY("&aBank &8» &fYour bank balance has been set to &b{money} &fby &b{player}&f."),
    ADMIN_ADD_SUCCESS("&aBank &8» &fSuccessfully added &b{money} &fto bank balance of &b{player}&f."),
    ADMIN_ADD_SUCCESS_NOTIFY("&aBank &8» &b{money} &fhas been added to your bank balance by &b{player}&f."),
    ADMIN_REMOVE_SUCCESS("&aBank &8» &fSuccessfully removed &b{money} &ffrom bank balance of &b{player}&f."),
    ADMIN_REMOVE_FAILURE_TOO_MUCH("&aBank &8» &b{player} &fdoes not have enough money in bank!"),
    ADMIN_REMOVE_SUCCESS_NOTIFY("&aBank &8» &b{money} &fhas been removed from your bank balance by &b{player}&f.");

    private static HashMap<Lang, String> lang;
    private final String defaultValue;

    Lang(String defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        lang = new HashMap<>();
        FileConfiguration fileConfig = config.getConfig();
        for (Lang lang : values()){
            if (!fileConfig.isSet(lang.toString())){
                fileConfig.set(lang.toString(), lang.defaultValue);
            }else{
                Lang.lang.put(lang, config.getString(lang.toString()));
            }
        }
        config.save();
    }

    public String get(){
        return lang.getOrDefault(this, defaultValue);
    }
}
