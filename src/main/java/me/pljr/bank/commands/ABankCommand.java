package me.pljr.bank.commands;

import me.pljr.bank.Bank;
import me.pljr.bank.config.Lang;
import me.pljr.pljrapispigot.utils.CommandUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ABankCommand extends CommandUtil {

    public ABankCommand(){
        super("abank", "abank.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /abank help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(player, "abank.help")) return;
                sendMessage(player, Lang.ADMIN_HELP.get());
                return;
            }
        }

        else if (args.length == 3){
            // /abank set <player> <amount>
            if (args[0].equalsIgnoreCase("set")){
                if (!checkPerm(player, "abank.set")) return;
                if (!checkInt(player, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                Bank.getPlayerManager().removeMoneyAll(target);
                Bank.getPlayerManager().addMoney(target, amount);
                sendMessage(player, Lang.ADMIN_SET_SUCCESS.get().replace("{player}", args[1]).replace("{money}", amount+""));
                if (target.isOnline()){
                    sendMessage((Player)target, Lang.ADMIN_SET_SUCCESS_NOTIFY.get().replace("{money}", args[2]).replace("{player}", player.getName()));
                }
                return;
            }

            // /abank add <player> <amount>
            if (args[0].equalsIgnoreCase("add")){
                if (!checkPerm(player, "abank.add")) return;
                if (!checkInt(player, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                Bank.getPlayerManager().addMoney(target, amount);
                sendMessage(player, Lang.ADMIN_ADD_SUCCESS.get().replace("{player}", args[1]).replace("{money}", amount+""));
                if (target.isOnline()){
                    sendMessage((Player)target, Lang.ADMIN_ADD_SUCCESS_NOTIFY.get().replace("{money}", args[2]).replace("{player}", player.getName()));
                }
                return;
            }

            // /abank remove <player> <amount>
            if (args[0].equalsIgnoreCase("remove")){
                if (!checkPerm(player, "abank.remove")) return;
                if (!checkInt(player, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                double playerAccount = Bank.getPlayerManager().getCorePlayer(target.getUniqueId()).getAmount();
                if (amount>playerAccount){
                    sendMessage(player, Lang.ADMIN_REMOVE_FAILURE_TOO_MUCH.get().replace("{player}", args[1]));
                    return;
                }
                Bank.getPlayerManager().removeMoneyAll(target);
                Bank.getPlayerManager().addMoney(target, amount);
                sendMessage(player, Lang.ADMIN_REMOVE_SUCCESS.get().replace("{player}", args[1]).replace("{money}", amount+""));
                if (target.isOnline()){
                    sendMessage((Player)target, Lang.ADMIN_REMOVE_SUCCESS_NOTIFY.get().replace("{money}", args[2]).replace("{player}", player.getName()));
                }
                return;
            }
        }

        if (checkPerm(player, "abank.help")){
            sendMessage(player, Lang.ADMIN_HELP.get());
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /abank help
            if (args[0].equalsIgnoreCase("help")){
                sendMessage(sender, Lang.ADMIN_HELP.get());
                return;
            }
        }

        else if (args.length == 3){
            // /abank set <player> <amount>
            if (args[0].equalsIgnoreCase("set")){
                if (!checkInt(sender, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                Bank.getPlayerManager().removeMoneyAll(target);
                Bank.getPlayerManager().addMoney(target, amount);
                sendMessage(sender, Lang.ADMIN_SET_SUCCESS.get().replace("{player}", args[1]).replace("{money}", amount+""));
                if (target.isOnline()){
                    sendMessage((Player)target, Lang.ADMIN_SET_SUCCESS_NOTIFY.get().replace("{money}", args[2]).replace("{player}", sender.getName()));
                }
                return;
            }

            // /abank add <player> <amount>
            if (args[0].equalsIgnoreCase("add")){
                if (!checkInt(sender, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                Bank.getPlayerManager().addMoney(target, amount);
                sendMessage(sender, Lang.ADMIN_ADD_SUCCESS.get().replace("{player}", args[1]).replace("{money}", amount+""));
                if (target.isOnline()){
                    sendMessage((Player)target, Lang.ADMIN_ADD_SUCCESS_NOTIFY.get().replace("{money}", args[2]).replace("{player}", sender.getName()));
                }
                return;
            }

            // /abank remove <player> <amount>
            if (args[0].equalsIgnoreCase("remove")){
                if (!checkInt(sender, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                double playerAccount = Bank.getPlayerManager().getCorePlayer(target.getUniqueId()).getAmount();
                if (amount>playerAccount){
                    sendMessage(sender,Lang.ADMIN_REMOVE_FAILURE_TOO_MUCH.get().replace("{player}", args[1]));
                    return;
                }
                Bank.getPlayerManager().removeMoneyAll(target);
                Bank.getPlayerManager().addMoney(target, amount);
                sendMessage(sender, Lang.ADMIN_REMOVE_SUCCESS.get().replace("{player}", args[1]).replace("{money}", amount+""));
                if (target.isOnline()){
                    sendMessage((Player)target, Lang.ADMIN_REMOVE_SUCCESS_NOTIFY.get().replace("{money}", args[2]).replace("{player}", sender.getName()));
                }
                return;
            }
        }

        sendMessage(sender, Lang.ADMIN_HELP.get());
    }
}
