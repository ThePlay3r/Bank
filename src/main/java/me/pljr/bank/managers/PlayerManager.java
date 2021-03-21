package me.pljr.bank.managers;

import lombok.AllArgsConstructor;
import me.pljr.bank.Bank;
import me.pljr.bank.config.BankType;
import me.pljr.bank.config.Lang;
import me.pljr.bank.objects.BankPlayer;
import me.pljr.pljrapispigot.utils.ChatUtil;
import me.pljr.pljrapispigot.utils.VaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
public class PlayerManager {
    private final static int AUTOSAVE = 12000;

    private final HashMap<UUID, BankPlayer> players = new HashMap<>();
    private final JavaPlugin plugin;
    private final QueryManager queryManager;
    private final boolean cachePlayers;

    public void getPlayer(OfflinePlayer player, Consumer<BankPlayer> consumer){
        getPlayer(player.getUniqueId(), consumer);
    }

    public void getPlayer(UUID uuid, Consumer<BankPlayer> consumer){
        if (!players.containsKey(uuid)){
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                BankPlayer player = queryManager.loadPlayer(uuid);
                setPlayer(uuid, player);
                consumer.accept(player);
            });
        }else{
            consumer.accept(players.get(uuid));
        }
    }

    public BankPlayer getPlayerSync(UUID uuid){
        if (!players.containsKey(uuid)){
            BankPlayer player = queryManager.loadPlayer(uuid);
            setPlayer(uuid, player);
            return player;
        }else{
            return players.get(uuid);
        }
    }

    public void setPlayer(OfflinePlayer player, BankPlayer bankPlayer){
        setPlayer(player.getUniqueId(), bankPlayer);
    }

    public void setPlayer(UUID uuid, BankPlayer player){
        players.put(uuid, player);
    }

    public void savePlayer(UUID uuid){
        queryManager.savePlayer(players.get(uuid));
        if (!cachePlayers) players.remove(uuid);
    }

    public void initAutoSave(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            Bank.log.info("Saving players..");
            for (Map.Entry<UUID, BankPlayer> entry : players.entrySet()){
                savePlayer(entry.getKey());
            }
            Bank.log.info("All players were saved.");
        }, AUTOSAVE, AUTOSAVE);
    }

    public void buyBank(Player player, BankType bankType){
        double cost = bankType.getCost();
        double balance = VaultUtil.getBalance(player);
        if (balance < cost){
            ChatUtil.sendMessage(player, Lang.BUY_FAILURE.get());
            return;
        }
        getPlayer(player, bankPlayer -> {
            bankPlayer.setBankType(bankType);
            VaultUtil.withdraw(player, cost);
            setPlayer(player, bankPlayer);
            ChatUtil.sendMessage(player, Lang.BUY_SUCCESS.get());
        });
    }
}
