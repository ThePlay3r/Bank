package me.pljr.bank.listeners;

import lombok.AllArgsConstructor;
import me.pljr.bank.Bank;
import me.pljr.bank.managers.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

@AllArgsConstructor
public class AsyncPlayerPreLoginListener implements Listener {
    private final PlayerManager playerManager;

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        playerManager.getPlayer(event.getUniqueId(), ignored -> Bank.log.info("Loaded " + event.getName()));
    }
}

