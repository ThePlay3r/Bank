package me.pljr.bank.commands;

import me.pljr.bank.Bank;
import me.pljr.bank.config.CfgLang;
import me.pljr.bank.enums.Lang;
import me.pljr.bank.menus.MainMenu;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.VaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BankCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "bank.use")) return false;

        // /bank
        if (args.length == 0){
            MainMenu.open(player);
            return true;
        }

        else if (args.length == 1){
            // /bank help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(player, "bank.help")) return false;
                sendHelp(player, CfgLang.help);
                return true;
            }

            // /bank addall
            if (args[0].equalsIgnoreCase("addall")){
                if (!checkPerm(player, "bank.add") || !checkPerm(player, "bank.addall")) return false;
                double amount = VaultUtil.getBalance(player);
                if (!Bank.getPlayerManager().addMoneyAll(player)){
                    player.sendMessage(CfgLang.lang.get(Lang.ADD_FAILURE_TOO_MUCH).replace("%money", amount+""));
                    return false;
                }
                player.sendMessage(CfgLang.lang.get(Lang.ADD_SUCCESS).replace("%money", amount+""));
                return true;
            }

            // /bank removeall
            if (args[0].equalsIgnoreCase("removeall")){
                if (!checkPerm(player, "bank.remove") || !checkPerm(player, "bank.removeall")) return false;
                double amount = Bank.getPlayerManager().getCorePlayer(player.getUniqueId()).getAmount();
                Bank.getPlayerManager().removeMoneyAll(player);
                player.sendMessage(CfgLang.lang.get(Lang.REMOVE_SUCCESS).replace("%money", amount+""));
                return true;
            }
        }

        else if (args.length == 2){
            // /bank add <amount>
            if (args[0].equalsIgnoreCase("add")){
                if (!checkPerm(player, "bank.add")) return false;
                if (!checkInt(player, args[1])) return false;
                if (Integer.parseInt(args[1]) > VaultUtil.getBalance(player)){
                    player.sendMessage(CfgLang.lang.get(Lang.ADD_FAILURE_NOT_ENOUGH));
                    return false;
                }
                if (!Bank.getPlayerManager().addMoney(player, Integer.parseInt(args[1]))){
                    player.sendMessage(CfgLang.lang.get(Lang.ADD_FAILURE_TOO_MUCH).replace("%money", args[1]));
                    return false;
                }
                player.sendMessage(CfgLang.lang.get(Lang.ADD_SUCCESS).replace("%money", args[1]));
                return true;
            }

            // /bank remove <amount>
            if (args[0].equalsIgnoreCase("remove")){
                if (!checkPerm(player, "bank.remove")) return false;
                if (!checkInt(player, args[1])) return false;
                if (!Bank.getPlayerManager().removeMoney(player, Integer.parseInt(args[1]))){
                    player.sendMessage(CfgLang.lang.get(Lang.REMOVE_FAILURE_TOO_MUCH).replace("%money", args[1]));
                    return false;
                }
                player.sendMessage(CfgLang.lang.get(Lang.REMOVE_SUCCESS).replace("%money", args[1]));
                return true;
            }

            // /bank show <player>
            if (args[0].equalsIgnoreCase("show")){
                if (!checkPerm(player, "bank.show")) return false;
                if (!checkPlayer(player, args[1])) return false;
                Player request = Bukkit.getPlayer(args[1]);
                CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(request.getUniqueId());
                double amount = corePlayer.getAmount();
                player.sendMessage(CfgLang.lang.get(Lang.SHOW_PLAYER).replace("%player", args[1]).replace("%amount", amount+""));
                return true;
            }
        }

        if (checkPerm(player, "bank.help")){
            sendHelp(player, CfgLang.help);
        }
        return false;
    }
}
