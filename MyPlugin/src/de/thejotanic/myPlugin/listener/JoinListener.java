package de.thejotanic.myPlugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		e.setJoinMessage("§8[§a+§8] " + getTitle(p) + p.getName());

		if(p.hasPermission("tutorial.owner")) {
			p.sendMessage("§6Willkommen auf deinem Server §a" + p.getName() + " §6:)");
			p.sendMessage("§eDeine Plugins stehen einsatzbereit zur Verfügung!");
			p.sendMessage("§6§lViel Spaß :D");
		} else {
			p.sendMessage("§6Willkommen auf dem Server §a" + p.getName() + " §6:)");
		}
		
	}

	private String getTitle(Player p) {
		for(Map.Entry<String, String> perm_title : permission_to_title.entrySet()) {
			if(p.hasPermission("tutorial." + perm_title.getKey())) {
				return perm_title.getValue();
			}
		}
		return "";
	}

	private static Map<String, String> permission_to_title = new HashMap<>();

	static {
		permission_to_title.put("owner", "§2[Owner] ");
		permission_to_title.put("developer", "§4[Dev] ");
		permission_to_title.put("administrator", "§6[Admin] ");
		permission_to_title.put("moderator", "§a[Mod] ");
		permission_to_title.put("builder", "§b[Builder] ");
		permission_to_title.put("buddy", "§1[Buddy] ");
		permission_to_title.put("guard", "§e[Guard] ");
	}
	
}
