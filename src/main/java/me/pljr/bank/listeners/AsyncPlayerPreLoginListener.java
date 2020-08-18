package me.pljr.bank.listeners;

import me.pljr.bank.Bank;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class AsyncPlayerPreLoginListener implements Listener {

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        UUID playerId = event.getUniqueId();
        Bank.getQueryManager().loadPlayerSync(playerId);
    }
}
