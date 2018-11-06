package de.thejotanic.myPlugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(!p.hasPermission("tutorial.owner")) {
			if(!p.hasPermission("tutorial.developer")) {
				if(!p.hasPermission("tutorial.administrator")) {
					if(!p.hasPermission("tutorial.moderator")) {
						if(!p.hasPermission("tutorial.builder")) {
							if(!p.hasPermission("tutorial.buddy")) {
								if(!p.hasPermission("tutorial.guard")) {
									
									e.setQuitMessage("§8[§c-§8] " + p.getName());
									
								} else {
									e.setQuitMessage("§8[§c-§8] §e[Guard] " + p.getName());
								}
									
							} else {
								e.setQuitMessage("§8[§c-§8] §1[Buddy] " + p.getName());
							}
									
						} else {
							e.setQuitMessage("§8[§c-§8] §b[Builder] " + p.getName());
						}
									
					} else {
						e.setQuitMessage("§8[§c-§8] §a[Mod] " + p.getName());
					}
									
				} else {
					e.setQuitMessage("§8[§c-§8] §6[Admin] " + p.getName());
				}
				
			} else {
				e.setQuitMessage("§8[§c-§8] §4[Dev] " + p.getName());
			}
			
		} else {
			e.setQuitMessage("§8[§c-§8] §2[Owner] " + p.getName());
		}
	}
}
