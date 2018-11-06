package de.thejotanic.myPlugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GameModeChangeListener implements Listener {
	
	@EventHandler
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent e) {
		Player p = e.getPlayer();
		
		GameMode gm = e.getNewGameMode();
		if(gm == GameMode.SPECTATOR) {
			Bukkit.broadcastMessage("§aDer Spieler §6" + p.getName() + " §ahat in den Zuschauermodus gewechselt!");
		}
	}
}
