package de.thejotanic.myPlugin.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.thejotanic.myPlugin.locations.Numbers;
import de.thejotanic.myPlugin.main.Main;

public class Scheduler extends Numbers implements CommandExecutor {
	
	private int taskID0;
	private int taskID1;
	private int taskID2;
	private int taskID3;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("myPlugins.countdown")) {
				if(args.length == 3) {
					if(Double.valueOf(args[0]) != null && Double.valueOf(args[1]) != null && Double.valueOf(args[2]) != null) {
						
						Location loc0 = new Location(p.getWorld(), Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2]));
						
						Location loc1 = new Location(p.getWorld(), loc0.getX(), loc0.getY(), loc0.getZ() - 04);
						Location loc2 = new Location(p.getWorld(), loc0.getX(), loc0.getY(), loc0.getZ() - 07);
						Location loc3 = new Location(p.getWorld(), loc0.getX(), loc0.getY(), loc0.getZ() - 10);
						Location loc4 = new Location(p.getWorld(), loc0.getX(), loc0.getY(), loc0.getZ() - 14);
						
						taskID0 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							
							int minutes = 6;
							int i = 0;
							
							@Override
							public void run() {
								
								minutes--;
								
								if(minutes < 5 && minutes >= 0) {
									onZero(loc0);
									
									switch (minutes) {
									case 4:
										onFour(loc1);
										break;
									case 3:
										onThree(loc1);
										break;
									case 2:
										onTwo(loc1);
										break;
									case 1:
										onOne(loc1);
										break;
									case 0:
										onZero(loc1);
										break;

									default:
										break;
									}
									
									taskID1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
										
										int tenseconds = 6;
												
										@Override
										public void run() {
											
											tenseconds--;
											
											switch (tenseconds) {
											case 5:
												onFive(loc3);
												break;
											case 4:
												onFour(loc3);
												break;
											case 3:
												onThree(loc3);
												break;
											case 2:
												onTwo(loc3);
												break;
											case 1:
												onOne(loc3);
												break;
											case 0:
												onZero(loc3);
												break;

											default:
												break;
											}
											
											taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
													
												int seconds = 10;
													
												@Override
												public void run() {
													
													seconds--;
													
													switch (seconds) {
													case 9:
														onNine(loc4);
														break;
													case 8:
														onEight(loc4);
														break;
													case 7:
														onSeven(loc4);
														break;
													case 6:
														onSix(loc4);
														break;
													case 5:
														onFive(loc4);
														break;
													case 4:
														onFour(loc4);
														break;
													case 3:
														onThree(loc4);
														break;
													case 2:
														onTwo(loc4);
														break;
													case 1:
														onOne(loc4);
														break;
													case 0:
														onZero(loc4);
														break;

													default:
														break;
													}
													
													taskID3 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
														
														int halfseconds = 2;
														
														@Override
														public void run() {
															
															halfseconds--;
															
															switch(halfseconds) {
															case 1:
																onDp(loc2);
																break;
															case 0:
																onDelete(loc2);
																break;
															default:
																break;
															}
															
															if(halfseconds <= 0) {
																Bukkit.getScheduler().cancelTask(taskID3);
																if(seconds <= 0) {
																	Bukkit.getScheduler().cancelTask(taskID2);
																	if(tenseconds <= 0) {
																		Bukkit.getScheduler().cancelTask(taskID1);
																		if(minutes <= 0) {
																			minutes--;
																			onDp(loc2);
																		}
																		
																	}
																	
																}
																
															}
															
														}
														
													}, 0, 10);
													
												}
												
											}, 0, 20);
											
										}
											
									}, 0, 200);	
									
								} else if(minutes == 5) {
									Bukkit.broadcastMessage("§aTimer auf §6" + minutes + "§a Minuten");
									onZero(loc0);
									onFive(loc1);
									onDp(loc2);
									onZero(loc3);
									onZero(loc4);
								}
								
								else if(minutes < 0 && i == 1) {
									Bukkit.getScheduler().cancelTask(taskID0);
									onDelete(loc0);
									onDelete(loc1);
									onDelete(loc2);
									onDelete(loc3);
									onDelete(loc4);
								}
								else
									i++;
							}
							
						}, 0, 1200);
						
					} else
						p.sendMessage("§cBitte benutze Zahlen für /countdown <x> <y> <z>");	
				} else
					p.sendMessage("§cBitte benutze /countdown <x> <y> <z>");
			} else
				p.sendMessage("§cDazu hast du keine Rechte!");
		}
		return false;
	}
	
}
