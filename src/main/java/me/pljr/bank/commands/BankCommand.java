package me.pljr.bank.commands;

import me.pljr.bank.config.Lang;
import me.pljr.bank.managers.PlayerManager;
import me.pljr.bank.menus.MainMenu;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.utils.VaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BankCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public BankCommand(PlayerManager playerManager){
        super("bank", "bank.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        // /bank
        if (args.length == 0){
            playerManager.getPlayer(player, bankPlayer -> new MainMenu(bankPlayer, playerManager).getGui().open(player));
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
                playerManager.getPlayer(player, bankPlayer -> {
                    if (!bankPlayer.addMoneyAll()){
                        sendMessage(player, Lang.ADD_FAILURE_TOO_MUCH.get().replace("{money}", amount+""));
                        return;
                    }
                    sendMessage(player, Lang.ADD_SUCCESS.get().replace("{money}", amount+""));
                    playerManager.setPlayer(player, bankPlayer);
                });
                return;
            }

            // /bank removeall
            if (args[0].equalsIgnoreCase("removeall")){
                if (!checkPerm(player, "bank.remove") || !checkPerm(player, "bank.removeall")) return;
                playerManager.getPlayer(player, bankPlayer -> {
                    double amount = bankPlayer.getAmount();
                    bankPlayer.removeMoneyAll();
                    sendMessage(player, Lang.REMOVE_SUCCESS.get().replace("{money}", amount+""));
                    playerManager.setPlayer(player, bankPlayer);
                });
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
                // bankBalance+amount > bankPlayer.getBankType().getMaxDeposit()
                playerManager.getPlayer(player, bankPlayer -> {
                    if (!bankPlayer.addMoney(Integer.parseInt(args[1]))){
                        sendMessage(player, Lang.ADD_FAILURE_TOO_MUCH.get().replace("{money}", args[1]));
                        return;
                    }
                    sendMessage(player, Lang.ADD_SUCCESS.get().replace("{money}", args[1]));
                    playerManager.setPlayer(player, bankPlayer);
                });
                return;
            }

            // /bank remove <amount>
            if (args[0].equalsIgnoreCase("remove")){
                if (!checkPerm(player, "bank.remove")) return;
                if (!checkInt(player, args[1])) return;
                playerManager.getPlayer(player, bankPlayer -> {
                    if (!bankPlayer.removeMoney(Integer.parseInt(args[1]))){
                        sendMessage(player, Lang.REMOVE_FAILURE_TOO_MUCH.get().replace("{money}", args[1]));
                        return;
                    }
                    sendMessage(player, Lang.REMOVE_SUCCESS.get().replace("{money}", args[1]));
                });
                return;
            }

            // /bank show <player>
            if (args[0].equalsIgnoreCase("show")){
                if (!checkPerm(player, "bank.show")) return;
                if (!checkPlayer(player, args[1])) return;
                Player request = Bukkit.getPlayer(args[1]);
                playerManager.getPlayer(request, bankPlayer -> {
                    double amount = bankPlayer.getAmount();
                    sendMessage(player, Lang.SHOW_PLAYER.get().replace("{player}", args[1]).replace("{amount}", amount+""));
                });
                return;
            }
        }

        if (checkPerm(player, "bank.help")){
            sendMessage(player, Lang.HELP.get());
        }
    }
}
