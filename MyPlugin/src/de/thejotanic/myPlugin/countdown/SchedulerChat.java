package de.thejotanic.myPlugin.countdown;

import org.bukkit.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.thejotanic.myPlugin.main.Main;

public class SchedulerChat implements CommandExecutor {
	
	private int taskID0;
	private int taskID1;
	private int taskID2;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("myPlugins.countdown")) {
				if(args.length == 0) {
					
					taskID0 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
									
						int minutes = 6;
									
						@Override
						public void run() {
										
							minutes--;
										
							if(minutes < 5) {
								
								taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
												
									int tenseconds = 6;
														
									@Override
									public void run() {
													
										tenseconds--;
													
										taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
															
											int seconds = 10;
													
											@Override
											public void run() {
															
												seconds--;
																
												Bukkit.broadcastMessage("§e0" + minutes + ":" + tenseconds + seconds);
												
												if(seconds <= 0) {
													Bukkit.getScheduler().cancelTask(taskID2);
													if(tenseconds <= 0) {
														Bukkit.getScheduler().cancelTask(taskID1);
														if(minutes <= 0) {
															
															Bukkit.getScheduler().cancelTask(taskID0);
														}
																	
													}
																
												}
															
											}
														
										}, 0, 20);
										
									}
													
								}, 0, 200);	
											
							} else 
								Bukkit.broadcastMessage("§6" + minutes);		
						}
						
					}, 0, 1200);
				
				} else
					p.sendMessage("§cBitte benutze /countdown <x> <y> <z>");
			} else
				p.sendMessage("§cDazu hast du keine Rechte!");
		}
		return false;
	}
	
}
