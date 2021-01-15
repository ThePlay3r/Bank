package me.pljr.bank.commands;

import me.pljr.bank.Bank;
import me.pljr.bank.config.Lang;
import me.pljr.bank.menus.MainMenu;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.VaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BankCommand extends CommandUtil {

    public BankCommand(){
        super("bank", "bank.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        // /bank
        if (args.length == 0){
            MainMenu.get(player).open(player);
            return;
        }

        else if (args.length == 1){
            // /bank help
            if (args[0].equalsIgnoreCase("help")){
                if (!checkPerm(player, "bank.help")) return;
                sendMessage(player, Lang.HELP.get());
                return;
            }

            // /bank addall
            if (args[0].equalsIgnoreCase("addall")){
                if (!checkPerm(player, "bank.add") || !checkPerm(player, "bank.addall")) return;
                double amount = VaultUtil.getBalance(player);
                if (!Bank.getPlayerManager().addMoneyAll(player)){
                    sendMessage(player, Lang.ADD_FAILURE_TOO_MUCH.get().replace("{money}", amount+""));
                    return;
                }
                sendMessage(player, Lang.ADD_SUCCESS.get().replace("{money}", amount+""));
                return;
            }

            // /bank removeall
            if (args[0].equalsIgnoreCase("removeall")){
                if (!checkPerm(player, "bank.remove") || !checkPerm(player, "bank.removeall")) return;
                double amount = Bank.getPlayerManager().getCorePlayer(player.getUniqueId()).getAmount();
                Bank.getPlayerManager().removeMoneyAll(player);
                sendMessage(player, Lang.REMOVE_SUCCESS.get().replace("{money}", amount+""));
                return;
            }
        }

        else if (args.length == 2){
            // /bank add <amount>
            if (args[0].equalsIgnoreCase("add")){
                if (!checkPerm(player, "bank.add")) return;
                if (!checkInt(player, args[1])) return;
                if (Integer.parseInt(args[1]) > VaultUtil.getBalance(player)){
                    sendMessage(player, Lang.ADD_FAILURE_NOT_ENOUGH.get());
                    return;
                }
                if (!Bank.getPlayerManager().addMoney(player, Integer.parseInt(args[1]))){
                    sendMessage(player, Lang.ADD_FAILURE_TOO_MUCH.get().replace("{money}", args[1]));
                    return;
                }
                sendMessage(player, Lang.ADD_SUCCESS.get().replace("{money}", args[1]));
                return;
            }

            // /bank remove <amount>
            if (args[0].equalsIgnoreCase("remove")){
                if (!checkPerm(player, "bank.remove")) return;
                if (!checkInt(player, args[1])) return;
                if (!Bank.getPlayerManager().removeMoney(player, Integer.parseInt(args[1]))){
                    sendMessage(player, Lang.REMOVE_FAILURE_TOO_MUCH.get().replace("{money}", args[1]));
                    return;
                }
                sendMessage(player, Lang.REMOVE_SUCCESS.get().replace("{money}", args[1]));
                return;
            }

            // /bank show <player>
            if (args[0].equalsIgnoreCase("show")){
                if (!checkPerm(player, "bank.show")) return;
                if (!checkPlayer(player, args[1])) return;
                Player request = Bukkit.getPlayer(args[1]);
                CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(request.getUniqueId());
                double amount = corePlayer.getAmount();
                sendMessage(player, Lang.SHOW_PLAYER.get().replace("{player}", args[1]).replace("{amount}", amount+""));
                return;
            }
        }

        if (checkPerm(player, "bank.help")){
            sendMessage(player, Lang.HELP.get());
        }
    }
}
