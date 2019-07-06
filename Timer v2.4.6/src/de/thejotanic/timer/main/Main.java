package de.thejotanic.timer.main;

import org.bukkit.plugin.java.JavaPlugin;

import de.thejotanic.timer.countdown.Scheduler;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	public void onEnable() {
		plugin = this;
		
		getCommand("countdown").setExecutor(new Scheduler());
	}
	
	public static Main getPlugin() {
		return plugin;
	}

}
