package me.pljr.bank.commands;

import me.pljr.bank.config.Lang;
import me.pljr.bank.managers.PlayerManager;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ABankCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public ABankCommand(PlayerManager playerManager){
        super("abank", "abank.use");
        this.playerManager = playerManager;
    }

    private void executeSet(CommandSender sender, OfflinePlayer target, double amount){
        playerManager.getPlayer(target, bankTarget -> {
            bankTarget.removeMoneyAll();
            if (!bankTarget.addMoney(amount)) bankTarget.addMoney(bankTarget.getBankType().getMaxDeposit());
            sendMessage(sender, Lang.ADMIN_SET_SUCCESS.get().replace("{player}", target.getName()).replace("{money}", amount+""));
            if (target.isOnline()){
                sendMessage(target, Lang.ADMIN_SET_SUCCESS_NOTIFY.get().replace("{money}", amount+"").replace("{player}", sender.getName()));
            }
        });
    }

    private void executeAdd(CommandSender sender, OfflinePlayer target, double amount){
        playerManager.getPlayer(target, bankTarget -> {
            if (!bankTarget.addMoney(amount)) bankTarget.addMoney(bankTarget.getBankType().getMaxDeposit() - bankTarget.getAmount());
            sendMessage(sender, Lang.ADMIN_ADD_SUCCESS.get().replace("{player}", target.getName()).replace("{money}", amount+""));
            if (target.isOnline()){
                sendMessage(target, Lang.ADMIN_ADD_SUCCESS_NOTIFY.get().replace("{money}", amount+"").replace("{player}", sender.getName()));
            }
        });
    }

    private void executeRemove(CommandSender sender, OfflinePlayer target, double amount){
        playerManager.getPlayer(target, bankTarget -> {
            if (!bankTarget.removeMoney(amount)){
                sendMessage(sender,Lang.ADMIN_REMOVE_FAILURE_TOO_MUCH.get().replace("{player}", target.getName()));
                return;
            }
            sendMessage(sender, Lang.ADMIN_REMOVE_SUCCESS.get().replace("{player}", target.getName()).replace("{money}", amount+""));
            if (target.isOnline()){
                sendMessage((Player)target, Lang.ADMIN_REMOVE_SUCCESS_NOTIFY.get().replace("{money}", amount+"").replace("{player}", sender.getName()));
            }
        });
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
                executeSet(player, target, amount);
                return;
            }

            // /abank add <player> <amount>
            if (args[0].equalsIgnoreCase("add")){
                if (!checkPerm(player, "abank.add")) return;
                if (!checkInt(player, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                executeAdd(player, target, amount);
                return;
            }

            // /abank remove <player> <amount>
            if (args[0].equalsIgnoreCase("remove")){
                if (!checkPerm(player, "abank.remove")) return;
                if (!checkInt(player, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                executeRemove(player, target, amount);
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
                executeSet(sender, target, amount);
                return;
            }

            // /abank add <player> <amount>
            if (args[0].equalsIgnoreCase("add")){
                if (!checkInt(sender, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                executeAdd(sender, target, amount);
                return;
            }

            // /abank remove <player> <amount>
            if (args[0].equalsIgnoreCase("remove")){
                if (!checkInt(sender, args[2])) return;
                double amount = Integer.parseInt(args[2]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                executeRemove(sender, target, amount);
                return;
            }
        }

        sendMessage(sender, Lang.ADMIN_HELP.get());
    }
}
