package de.thejotanic.myPlugin.countdown;

import org.bukkit.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.thejotanic.myPlugin.main.Main;

public class SchedulerChat implements CommandExecutor {
	
	private int taskID;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("myPlugin.countdown")) {
				if(args.length == 0) {
					
					taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
						
						int tenminutes = 0;
						int minutes = 4;
						int tenseconds = 5;
						int seconds = 9;
									
						@Override
						public void run() {
							
							Bukkit.broadcastMessage("§e" + tenminutes + minutes + ":" + tenseconds + seconds);
							
							seconds--;
							if(seconds < 0) {
								tenseconds--;
								if(tenseconds < 0) {
									minutes--;
									if(minutes < 0) {
										tenminutes--;
										if(tenminutes <= 0 && minutes <= 0 && tenseconds <= 0 && seconds <= 0) {
											Bukkit.getScheduler().cancelTask(taskID);
										}
										minutes = 9;
									}
									tenseconds = 5;
								}
								seconds = 9;
							}
							
						}
						
					}, 0, 20);
				
				} else
					p.sendMessage("§cBitte benutze /chatcountdown!");
			} else
				p.sendMessage("§cDazu hast du keine Rechte!");
		}
		return false;
	}
	
}
