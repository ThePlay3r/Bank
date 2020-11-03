package me.pljr.bank.commands;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgLang;
import me.pljr.bank.enums.Lang;
import me.pljr.pljrapi.utils.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ABankCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "abank.use")) return false;

        if (args.length == 1){
            // /abank help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(sender, "abank.help")) return false;
                sendHelp(sender, CfgLang.adminHelp);
                return true;
            }
        }

        else if (args.length == 3){
            // /abank set <player> <amount>
            if (args[0].equalsIgnoreCase("set")){
                if (!checkPerm(sender, "abank.set")) return false;
                if (!checkInt(sender, args[2])) return false;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                Bank.getPlayerManager().removeMoneyAll(player);
                Bank.getPlayerManager().addMoney(player, amount);
                sendMessage(sender, CfgLang.lang.get(Lang.ADMIN_SET_SUCCESS).replace("%player", args[1]).replace("%money", amount+""));
                if (player.isOnline()){
                    sendMessage((Player)player, CfgLang.lang.get(Lang.ADMIN_SET_SUCCESS_NOTIFY).replace("%money", args[2]).replace("%player", sender.getName()));
                }
                return true;
            }

            // /abank add <player> <amount>
            if (args[0].equalsIgnoreCase("add")){
                if (!checkPerm(sender, "abank.add")) return false;
                if (!checkInt(sender, args[2])) return false;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                Bank.getPlayerManager().addMoney(player, amount);
                sendMessage(sender, CfgLang.lang.get(Lang.ADMIN_ADD_SUCCESS).replace("%player", args[1]).replace("%money", amount+""));
                if (player.isOnline()){
                    sendMessage((Player)player, CfgLang.lang.get(Lang.ADMIN_ADD_SUCCESS_NOTIFY).replace("%money", args[2]).replace("%player", sender.getName()));
                }
                return true;
            }

            // /abank remove <player> <amount>
            if (args[0].equalsIgnoreCase("remove")){
                if (!checkPerm(sender, "abank.remove")) return false;
                if (!checkInt(sender, args[2])) return false;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                double playerAccount = Bank.getPlayerManager().getCorePlayer(player.getUniqueId()).getAmount();
                if (amount>playerAccount){
                    sendMessage(sender, CfgLang.lang.get(Lang.ADMIN_REMOVE_FAILURE_TOO_MUCH).replace("%player", args[1]));
                    return false;
                }
                Bank.getPlayerManager().removeMoneyAll(player);
                Bank.getPlayerManager().addMoney(player, amount);
                sendMessage(sender, CfgLang.lang.get(Lang.ADMIN_REMOVE_SUCCESS).replace("%player", args[1]).replace("%money", amount+""));
                if (player.isOnline()){
                    sendMessage((Player)player, CfgLang.lang.get(Lang.ADMIN_REMOVE_SUCCESS_NOTIFY).replace("%money", args[2]).replace("%player", sender.getName()));
                }
                return true;
            }
        }

        if (checkPerm(sender, "abank.help")){
            sendHelp(sender, CfgLang.adminHelp);
        }
        return false;
    }
}
