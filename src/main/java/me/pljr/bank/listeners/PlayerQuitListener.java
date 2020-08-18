package me.pljr.bank.listeners;

import me.pljr.bank.Bank;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        UUID playerId = event.getPlayer().getUniqueId();
        Bank.getPlayerManager().savePlayer(playerId);
    }
}
