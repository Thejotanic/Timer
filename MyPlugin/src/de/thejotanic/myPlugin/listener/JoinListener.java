package de.thejotanic.myPlugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
Player p = e.getPlayer();
		
		if(!p.hasPermission("tutorial.owner")) {
			if(!p.hasPermission("tutorial.developer")) {
				if(!p.hasPermission("tutorial.administrator")) {
					if(!p.hasPermission("tutorial.moderator")) {
						if(!p.hasPermission("tutorial.builder")) {
							if(!p.hasPermission("tutorial.buddy")) {
								if(!p.hasPermission("tutorial.guard")) {
									
									e.setJoinMessage("§8[§a+§8] " + p.getName());
									p.sendMessage("§6Willkommen auf dem Server §a" + p.getName() + " §6:)");
									
								} else {
									e.setJoinMessage("§8[§a+§8] §e[Guard] " + p.getName());
									p.sendMessage("§6Willkommen auf dem Server §a" + p.getName() + " §6:)");
								}
								
							} else {
								e.setJoinMessage("§8[§a+§8] §1[Buddy] " + p.getName());
								p.sendMessage("§6Willkommen auf dem Server §a" + p.getName() + " §6:)");
							}
							
						} else {
							e.setJoinMessage("§8[§a+§8] §b[Builder] " + p.getName());
							p.sendMessage("§6Willkommen auf dem Server §a" + p.getName() + " §6:)");
						}
						
					} else {
						e.setJoinMessage("§8[§a+§8] §a[Mod] " + p.getName());
						p.sendMessage("§6Willkommen auf dem Server §a" + p.getName() + " §6:)");
					}
						
				} else {
					e.setJoinMessage("§8[§a+§8] §6[Admin] " + p.getName());
					p.sendMessage("§6Willkommen auf dem Server §a" + p.getName() + " §6:)");
				}
				
			} else {
				e.setJoinMessage("§8[§a+§8] §4[Dev] " + p.getName());
				p.sendMessage("§6Willkommen auf dem Server §a" + p.getName() + " §6:)");
			}
			
		} else {
			e.setJoinMessage("§8[§a+§8] §2[Owner] " + p.getName());
			p.sendMessage("§6Willkommen auf deinem Server §a" + p.getName() + " §6:)");
			p.sendMessage("§eDeine Plugins stehen einsatzbereit zur Verfügung!");
			p.sendMessage("§6§lViel Spaß :D");
		}
		
	}
	
}
